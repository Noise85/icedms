/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.core.business;

import java.util.List;
/**
 *
 * @author 
 */
public interface Commentable {
    public List<Comment> getComments();
    public void addComment(Comment comment);
}
