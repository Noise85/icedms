/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.service.command.impl;

import ch.iceage.icedms.core.service.command.CompositeCommand;
import ch.iceage.icedms.core.service.command.SimpleCommand;
import ch.iceage.icedms.service.command.Command;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Sid
 */
@Command
public class CompositeCommandImpl implements CompositeCommand {
    
    Set<SimpleCommand> commands;
    
    public CompositeCommandImpl() {
        commands = new HashSet<>();
    }
    
    @Override
    public void addCommand(SimpleCommand command) {
        this.commands.add(command);
    }

    @Override
    public void removeCommand(SimpleCommand command) {
        this.commands.remove(command);
    }

    @Override
    public void execute() {
        if(!commands.isEmpty()) {
            //Sort the commands by its executionOrder id and execute them all.
            Iterator<SimpleCommand> it = commands.stream().sorted().iterator();
            while(it.hasNext()) {
                it.next().execute();
            }
        }
    }
    
}
