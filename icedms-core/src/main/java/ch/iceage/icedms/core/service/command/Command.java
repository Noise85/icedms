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
public interface Command {
    
    public void execute();
    
    public enum ExecutionOrder {
        HIGHEST_PRIORITY(Integer.MIN_VALUE), LOWEST_PRIORITY(Integer.MIN_VALUE);
        
        Integer value;

        private ExecutionOrder(Integer value) {
            this.value = value;
        }
        
        public Integer intVal()  {
            return value;
        }
        
    }
    
}
