/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service;

import java.util.List;

/**
 *
 * @author Sid
 * @param <T> Response Object type. May be a domain model class type or a DTO.
 */
public interface ListResponse<T> extends ServiceResponse<T> {
    public List<T> getResponseObjects();
}
