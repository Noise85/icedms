/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc.query.clause;

/**
 *
 * 
 */
public class JoinClause {
    
    JoinClauseType type;
    String tableFrom;
    String tableAliasFrom;
    String fieldFrom;
    String tableTo;
    String tableAliasTo;
    String fieldTo;

    public JoinClause(JoinClauseType type, String tableFrom, String tableAliasFrom, String fieldFrom, String tableTo, String tableAliasTo, String fieldTo) {
        this.type = type;
        this.tableFrom = tableFrom;
        this.tableAliasFrom = tableAliasFrom;
        this.fieldFrom = fieldFrom;
        this.tableTo = tableTo;
        this.tableAliasTo = tableAliasTo;
        this.fieldTo = fieldTo;
    }

    public JoinClauseType getType() {
        return type;
    }

    public void setType(JoinClauseType type) {
        this.type = type;
    }

    public String getTableFrom() {
        return tableFrom;
    }

    public void setTableFrom(String tableFrom) {
        this.tableFrom = tableFrom;
    }

    public String getTableAliasFrom() {
        return tableAliasFrom;
    }

    public void setTableAliasFrom(String tableAliasFrom) {
        this.tableAliasFrom = tableAliasFrom;
    }

    public String getFieldFrom() {
        return fieldFrom;
    }

    public void setFieldFrom(String fieldFrom) {
        this.fieldFrom = fieldFrom;
    }

    public String getTableTo() {
        return tableTo;
    }

    public void setTableTo(String tableTo) {
        this.tableTo = tableTo;
    }

    public String getTableAliasTo() {
        return tableAliasTo;
    }

    public void setTableAliasTo(String tableAliasTo) {
        this.tableAliasTo = tableAliasTo;
    }

    public String getFieldTo() {
        return fieldTo;
    }

    public void setFieldTo(String fieldTo) {
        this.fieldTo = fieldTo;
    }

    

}
