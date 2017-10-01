/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.revisione.command.factory;

import ch.iceage.icedms.core.service.ServiceRequest;
import ch.iceage.icedms.core.service.command.SimpleCommand;
import ch.iceage.icedms.core.service.command.factory.AbstractCommandFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.reflections.Reflections;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sid
 */
@Component
public final class DynamicReflectionCommandFactory extends AbstractCommandFactory {

    @Autowired
    protected BeanFactory factory;
    protected Map<Class<? extends ServiceRequest>, Class<? extends SimpleCommand>> commands;

    public DynamicReflectionCommandFactory() {
        commands = new HashMap<>();
    }

    @Override
    @PostConstruct
    protected final void initCommands() {
        Reflections reflections = new Reflections("ch.iceage.icedms.service");
        Set<Class<? extends SimpleCommand>> commandTypes = reflections.getSubTypesOf(SimpleCommand.class);
        Set<Class<? extends ServiceRequest>> requestTypes = reflections.getSubTypesOf(ServiceRequest.class);
        for(final Class<? extends SimpleCommand> commandType : commandTypes) {
            for(Class<? extends ServiceRequest> requestType : requestTypes) {
                if(requestType.getSimpleName().replace("Request", "")
                        .equals(commandType.getSimpleName().replace("Command", ""))) {
                    commands.put(requestType, commandType);
                    break;
                }
            }
        }
    }

    @Override
    public <T extends ServiceRequest> SimpleCommand getCommand(Class<T> clazz) {
        SimpleCommand command = null;
        if (commands != null) {
            if (clazz != null && commands.containsKey(clazz)) {
                command = factory.getBean(commands.get(clazz));
            } else {
                command = new SimpleCommand() {
                    @Override
                    public void execute() {
                        System.out.println("Do nothing command");
                    }

                    @Override
                    public Integer getExecutionOrder() {
                        return 0;
                    }

                    @Override
                    public void setExecutionOrder(Integer order) {
                        
                    }

                    @Override
                    public void setRequest(ServiceRequest request) {
                        
                    }
                };
            }
        }
        return command;
    }

}
