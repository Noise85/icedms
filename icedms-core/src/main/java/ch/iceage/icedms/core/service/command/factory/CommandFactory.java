/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service.command.factory;

import ch.iceage.icedms.core.service.ServiceRequest;
import ch.iceage.icedms.core.service.command.SimpleCommand;

/**
 *
 * @author Sid
 */
public interface CommandFactory {
    public <T extends ServiceRequest> SimpleCommand getCommand(Class<T> clazz);
}
