/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service.command;

import ch.iceage.icedms.core.service.ServiceRequest;

/**
 *
 * @author Sid
 * @param <T>
 */
public interface SimpleCommand<T extends ServiceRequest> extends Command {
    public Integer getExecutionOrder();
    public void setExecutionOrder(Integer order);
    public void setRequest(T request);
}
