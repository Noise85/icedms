/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.command;

import ch.iceage.icedms.core.service.ServiceRequest;
import ch.iceage.icedms.core.service.command.Command.ExecutionOrder;
import ch.iceage.icedms.core.service.command.SimpleCommand;

/**
 *
 * @author Sid
 * @param <T>
 */
public abstract class AbstractSimpleCommand<T extends ServiceRequest> implements SimpleCommand<T> {
    
    private Integer executionOrder;
    protected T request;

    public AbstractSimpleCommand(Integer executionOrder) {
        this.executionOrder = executionOrder;
    }

    public AbstractSimpleCommand() {
        this(ExecutionOrder.LOWEST_PRIORITY.intVal());
    }

    @Override
    public void setRequest(T request) {
        this.request=request;
    }

    @Override
    public Integer getExecutionOrder() {
        return this.executionOrder;
    }

    @Override
    public void setExecutionOrder(Integer order) {
        this.executionOrder=order;
    }
    
}
