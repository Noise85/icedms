/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import ch.iceage.icedms.core.business.exceptions.InvalidIndexException;
import ch.iceage.icedms.core.business.exceptions.LockedResourceException;
import ch.iceage.icedms.core.business.exceptions.SingleValuedIndexException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author Sid
 */
@RunWith(JUnit4.class)
public class DomainModelTest {
    
    private User user;
    private DocumentGroup documentGroup;
    private DocumentType documentType;
    private Map<String,IndexType> indexTypes;
    private MimeType mimeType;
    private Map<String,Language> languages;
    
    @Before
    public void setUp() {
        user = new DefaultUser("Sid", "Enea", "Betté", "enea.bette@abraxas.ch", "password");
        documentGroup = new DefaultDocumentGroup("ADM", "Administration and invoicing");
        documentType = new DefaultDocumentType("INV", "Invoice");
        indexTypes = new HashMap<>();
        indexTypes.put("PERS", new DefaultIndexType("PERS", "Person", "\\D+"));
        indexTypes.put("INV", new DefaultIndexType("INV", "Invoice number", "[0-9]+"));
        indexTypes.put("USER", new DefaultIndexType("USER", "User name", "[a-zA-Z]+"));
        mimeType = new DefaultMimeType("PDF", "application/pdf", "Portale document format (PDF)");
        languages = new HashMap<>();
        languages.put("it", new DefaultLanguage("it", "it_CH", "Italiano (Svizzera)"));
        languages.put("fr", new DefaultLanguage("fr", "fr_CH", "Francese (Svizzera)"));
        languages.put("de", new DefaultLanguage("DE", "de_CH", "Tedesco (Svizzera)"));
    }

    @Test
    public void testAddDocumentTypeToDocumentGroup() {
        documentGroup.addDocumentType(documentType);
        assertEquals(1, documentGroup.getDocumentTypes().size());
        assertEquals(1, documentType.getDocumentGroups().size());
    }
    
    @Test
    public void testAddIndexRuleToDocumentType() {
        documentType.addIndexRule(indexTypes.get("INV"), Boolean.TRUE, Boolean.TRUE);
        documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
        assertEquals(2, documentType.getIndexTypeRules().size());
        assertEquals(1, indexTypes.get("INV").getIndexTypeRules().size());
        assertEquals(1, indexTypes.get("USER").getIndexTypeRules().size());
        assertEquals(0, indexTypes.get("PERS").getIndexTypeRules().size());
    }
    
    @Test
    public void testCreateDocument() {
        documentGroup.addDocumentType(documentType);
        documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
        DefaultDocument document = new DefaultDocument("Test document", new Date(), false, documentType, Status.NEW, languages.get("it"), "Sid", new Date());
        DefaultDocumentVersion documentVersion = new DefaultDocumentVersion();
        DefaultDocumentVersion documentVersion1 = new DefaultDocumentVersion();
        try {
            document.addDocumentVersion(documentVersion);
            documentVersion.setContent("hola que pasa por la calle?".getBytes(), Boolean.TRUE);
            assertEquals(document.getLastRevision(), "0.1");
            assertEquals(documentVersion.getRevisionNumber(), "0.1");
            assertNotNull(documentVersion.getContent());
            document.addDocumentVersion(documentVersion1);
            documentVersion1.setContent("uela".getBytes(), true);
            document.addDocumentVersion(documentVersion1);
            assertEquals("0.2", document.getLastRevision());
            assertEquals("0.2", documentVersion1.getRevisionNumber());
            assertNotNull(documentVersion1.getContent());
        } catch (LockedResourceException ex) {
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Fail on set document content");
        }
    }
    
    @Test
    public void testLockAndUnlockDocument() {
        try {
            documentGroup.addDocumentType(documentType);
            documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
            DefaultDocument document = new DefaultDocument("Test document", new Date(), false, documentType, Status.NEW, languages.get("it"), "Sid", new Date());
            DefaultDocumentVersion documentVersion = new DefaultDocumentVersion("0.0", "Sid", new Date());
            documentVersion.setContent("hola que pasa por la calle?".getBytes());
            documentVersion.lock(user);
            assertTrue(documentVersion.isLocked());
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "Locked document with id {0} by user {1}", new Object[]{document.getId(), user.getUserName()});
            documentVersion.unlock();
            assertFalse(documentVersion.isLocked());
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "Unlocked document with id {0} ", new Object[]{document.getId()});
        } catch (LockedResourceException ex) {
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("The ressource is locked");
        }
    }
    
    @Test
    public void testAddIndexOkCase() {
        try {
            documentGroup.addDocumentType(documentType);
            documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
            documentType.addIndexRule(indexTypes.get("INV"), Boolean.TRUE, Boolean.TRUE);
            DefaultDocument document = new DefaultDocument("Test document", new Date(), false, documentType, Status.NEW, languages.get("it"), "Sid", new Date());
            document.addIndex(indexTypes.get("INV"), "0112365478");
            document.addIndex(indexTypes.get("USER"), "Noise");
            assertEquals(document.getIndexes().size(), 2);
        } catch (InvalidIndexException | SingleValuedIndexException ex) {
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Indexes are invalid");
        }
    }
    
    @Test
    public void testAddIndexSingleValuedKoCase() {
        documentGroup.addDocumentType(documentType);
        documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
        documentType.addIndexRule(indexTypes.get("INV"), Boolean.TRUE, Boolean.TRUE);
        DefaultDocument document = new DefaultDocument("Test document", new Date(), false, documentType, Status.NEW, languages.get("it"), "Sid", new Date());
        
        try {    
            document.addIndex(indexTypes.get("INV"), "0112365478");
            document.addIndex(indexTypes.get("USER"), "Noise");
            document.addIndex(indexTypes.get("USER"), "Sid");
        } catch (InvalidIndexException | SingleValuedIndexException ex) {
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "Cannot add single valued index type USER two times", ex);
        } finally {
            assertEquals(2, document.getIndexes().size());
        }
    }
    
    @Test
    public void testAddInvalidIndexCaseKoCase() {
        documentGroup.addDocumentType(documentType);
        documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
        //documentType.addIndexRule(indexTypes.get("INV"), Boolean.TRUE, Boolean.TRUE);
        DefaultDocument document = new DefaultDocument("Test document", new Date(), false, documentType, Status.NEW, languages.get("it"), "Sid", new Date());
        
        try {    
            document.addIndex(indexTypes.get("USER"), "Noise");
            document.addIndex(indexTypes.get("INV"), "0112365478");
        } catch (InvalidIndexException | SingleValuedIndexException ex) {
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "Invalid index type INV", ex);
        } finally {
            assertEquals(1, document.getIndexes().size());
        }
    }
    
    @Test
    public void testRemoveMandatoryIndexCaseKoCase() {
        documentGroup.addDocumentType(documentType);
        documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
        //documentType.addIndexRule(indexTypes.get("INV"), Boolean.TRUE, Boolean.TRUE);
        DefaultDocument document = new DefaultDocument("Test document", new Date(), false, documentType, Status.NEW, languages.get("it"), "Sid", new Date());
        
        try {    
            document.addIndex(indexTypes.get("USER"), "Noise");
            document.removeAllIndexes();
        } catch (InvalidIndexException | SingleValuedIndexException ex) {
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "Cannot remove mandatory index type USER", ex);
        } finally {
            assertEquals(1, document.getIndexes().size());
        }
    }
    
    @Test
    public void testDoubleLockDocumentKoCase() {
        documentGroup.addDocumentType(documentType);
        documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
        DefaultDocument document = new DefaultDocument("Test document", new Date(), false, documentType, Status.NEW, languages.get("it"), "Sid", new Date());
        DefaultDocumentVersion documentVersion = new DefaultDocumentVersion("0.0", "Sid", new Date());
        try {
            documentVersion.lock(user);
            documentVersion.lock(user);
        } catch (LockedResourceException ex) {
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, null, ex);
        }
    }
    
    @Test
    public void testLockDocument() {
        documentGroup.addDocumentType(documentType);
        documentType.addIndexRule(indexTypes.get("USER"), Boolean.TRUE, Boolean.FALSE);
        DefaultDocument document = new DefaultDocument("Test document", new Date(), false, documentType, Status.NEW, languages.get("it"), "Sid", new Date());
        DefaultDocumentVersion documentVersion = new DefaultDocumentVersion();
        try {
            documentVersion.unlock();
            assertFalse(documentVersion.isLocked());
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "Unlocked document with id {0} ", new Object[]{document.getId()});
            documentVersion.setContent("hola que pasa por la calle?".getBytes());
            documentVersion.lock(user, 1000L);
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "Locked document with id {0} by user {1} with max time {3} ms", new Object[]{document.getId(), documentVersion.getLockUser().getUserName(), documentVersion.getMaxIntervalMilliseconds()});
            assertTrue(documentVersion.isLocked());
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "Document still locked");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException ex) {
                Logger.getLogger(DomainModelTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            Boolean locked = documentVersion.isLocked();
            assertFalse(locked);
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.INFO, "document {0} automatically unlocked!", document.getId());
        } catch (LockedResourceException ex) {
            Logger.getLogger(DomainModelTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("The resource is locked");
        }
    }
}
