/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sid
 */
@Entity
@Table(name="document_language")
public class DefaultLanguage implements Language, Serializable  {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="code", nullable=false)
    private String code;
    @Column(name="iso_code", nullable=false)
    private String isoCode;
    @Column(name="description")
    private String description;

    public DefaultLanguage(String code, String isoCode, String description) {
        this.id = null;
        this.code = code;
        this.isoCode = isoCode;
        this.description = description;
    }

    public DefaultLanguage() {
        this(null ,null, null);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getIsoCode() {
        return isoCode;
    }

    @Override
    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
