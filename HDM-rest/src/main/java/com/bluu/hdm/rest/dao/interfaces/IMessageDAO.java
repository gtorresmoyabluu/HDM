/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.interfaces;

import com.bluu.hdm.rest.dao.generic.IGenericDAO;
import com.bluu.hdm.rest.entity.MessageEntity;
import java.util.List;

/**
 *
 * @author Gonzalo Torres
 */
public interface IMessageDAO extends IGenericDAO<MessageEntity, Long> {

    List<MessageEntity> getMessagesBylocale(String locale);
}
