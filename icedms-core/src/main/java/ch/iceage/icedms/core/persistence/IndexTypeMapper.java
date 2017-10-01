/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.persistence;

import ch.iceage.icedms.core.business.IndexType;

/**
 *
 * @author Enea
 */
public interface IndexTypeMapper extends GenericMapper<IndexType, Long> {
    public IndexType getByCode(String code);
}
