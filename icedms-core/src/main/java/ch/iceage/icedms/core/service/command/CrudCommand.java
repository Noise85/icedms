/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.service.command;

import ch.iceage.icedms.core.service.ServiceResponse;

/**
 *
 * @author Sid
 * @param <O>
 */
public interface CrudCommand<O extends ServiceResponse> extends Command {
    O executeCreate();
    O executeUpdate();
    O executeDelete();
    O executeSearch();
}
