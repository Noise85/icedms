/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.MimeType;
import java.util.List;

/**
 *
 * @author Enea
 */
public interface MimeTypeMapper extends GenericMapper<MimeType, Long> {
    public MimeType getByIsoCode(String isoCode);
    public MimeType getByCode(String code);
    /**
     * Returns all the mime type objects matching the parameter in a loosy way.
     * If it were to query a relational db the query would be [like '%code%']
     * @param code
     * @return a list of mimetypes for which the code loosely matches the param.
     */
    public List<MimeType> getAllLike(String code);
}
