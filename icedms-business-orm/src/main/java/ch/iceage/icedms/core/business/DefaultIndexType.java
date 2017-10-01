/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sid
 */
@Entity
@Table(name="index_type")
public class DefaultIndexType implements IndexType, Serializable {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="code", nullable=false, unique = true)
    private String code;
    @Column(name="description", nullable=true)
    private String description;
    @Column(name="validation_rule", nullable=false)
    private String validationRule;
    @OneToMany(targetEntity = DefaultIndexTypeRule.class, mappedBy = "indexType", cascade = CascadeType.PERSIST)
    private List<IndexTypeRule> indexTypeRules;

    public DefaultIndexType(String code, String description, String validationRule) {
        this.id = null;
        this.code = code;
        this.description = description;
        this.validationRule = validationRule;
        this.indexTypeRules=new ArrayList<>();
    }

    public DefaultIndexType() {
        this(null, null, null);
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getValidationRule() {
        return validationRule;
    }

    @Override
    public void setValidationRule(String validationRule) {
        this.validationRule = validationRule;
    }

    @Override
    public List<IndexTypeRule> getIndexTypeRules() {
        return indexTypeRules;
    }

    protected void setIndexTypeRules(List<IndexTypeRule> indexTypeRules) {
        this.indexTypeRules = indexTypeRules;
    }
    
}
