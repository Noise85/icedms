/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service.command.factory;

/**
 *
 * @author Sid
 * @param <T>
 */
public abstract class AbstractCommandFactory implements CommandFactory {
    
    protected abstract void initCommands();
    
}
