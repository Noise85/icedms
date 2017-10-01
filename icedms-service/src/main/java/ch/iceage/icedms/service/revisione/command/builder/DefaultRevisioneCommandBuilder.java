/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.revisione.command.builder;

import ch.iceage.icedms.core.service.ServiceRequest;
import ch.iceage.icedms.core.service.command.Command;
import ch.iceage.icedms.core.service.command.CompositeCommand;
import ch.iceage.icedms.core.service.command.SimpleCommand;
import ch.iceage.icedms.core.service.command.factory.CommandFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sid
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultRevisioneCommandBuilder implements RevisioneCommandBuilder {
    
    @Autowired
    private CommandFactory factory;
    @Autowired
    private ObjectFactory<CompositeCommand> compositeCommandFactory;
    //Command list to be executed by the facade service.
    private final List<SimpleCommand> commands;
    
    public DefaultRevisioneCommandBuilder() {
        this.commands = new ArrayList<>();
    }
    
    @Override
    public RevisioneCommandBuilder createSimpleCommand(ServiceRequest request, Integer executionOrder) {
        //Instantiate the command using the command type stored in the request.
        SimpleCommand command = factory.getCommand(request.getClass());
        command.setRequest(request);
        command.setExecutionOrder(executionOrder);
        this.commands.add(command);
        return this;
    }

    @Override
    public Command build() {
        CompositeCommand compositeCommand = compositeCommandFactory.getObject();
        for(SimpleCommand command : commands) {
            compositeCommand.addCommand(command);
        }
        return compositeCommand;
    }
}
