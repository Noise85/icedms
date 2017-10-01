/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.revisione.command.builder;

import ch.iceage.icedms.core.service.ServiceRequest;
import ch.iceage.icedms.core.service.command.CompositeCommand;
import ch.iceage.icedms.core.service.command.builder.CommandBuilder;

/**
 *
 * @author Sid
 * @param <T>
 */
public interface RevisioneCommandBuilder<T extends CompositeCommand> extends CommandBuilder<T> {
    public RevisioneCommandBuilder 
        createSimpleCommand(ServiceRequest request, Integer executionOrder);
}
