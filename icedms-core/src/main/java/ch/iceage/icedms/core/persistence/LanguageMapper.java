/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.Language;

/**
 *
 * @author Enea
 */
public interface LanguageMapper extends GenericMapper<Language, Long> {
    public Language getByIsoCode(String isoCode);
    public Language getByCode(String code);
}
