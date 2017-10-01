/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service.command.builder;

import ch.iceage.icedms.core.service.command.Command;

/**
 *
 * @author Sid
 * @param <T>
 */
public interface CommandBuilder<T extends Command> {
    public T build();
}
