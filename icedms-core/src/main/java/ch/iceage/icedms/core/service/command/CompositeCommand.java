/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service.command;

/**
 *
 * @author Sid
 */
public interface CompositeCommand extends Command {
    public void addCommand(SimpleCommand command);
    public void removeCommand(SimpleCommand command);
}
