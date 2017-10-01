/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

/**
 *
 * @author 
 */
public interface Comment extends Identity, Loggable {
    public String getCommentText();
    public void setCommentText(String comment);
}
