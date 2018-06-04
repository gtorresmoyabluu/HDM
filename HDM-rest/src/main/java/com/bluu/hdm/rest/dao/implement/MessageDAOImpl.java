/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.implement;

import com.bluu.hdm.rest.dao.implement.inventory.ModelDAOImpl;
import com.bluu.hdm.rest.dao.interfaces.IMessageDAO;
import com.bluu.hdm.rest.entity.MessageEntity;
import com.bluu.hdm.rest.repository.MessageRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo Torres
 */
@Service
@Transactional
public class MessageDAOImpl implements IMessageDAO {

    private static Logger logger = LogManager.getLogger(ModelDAOImpl.class.getName());

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessageEntity save(MessageEntity entity) {
	MessageEntity mesage = new MessageEntity();
	try {
	    mesage = messageRepository.save(entity);
	} catch (NonTransientDataAccessException ex) {
	    logger.error(String.format("Error: %s", ex.getMessage()));
	}
	return mesage;
    }

    @Override
    public Optional<MessageEntity> findById(Long id) {
	return messageRepository.findById(id);
    }

    @Override
    public List<MessageEntity> findAll() {
	return messageRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
	messageRepository.deleteById(id);
	return !messageRepository.existsById(id);
    }

    @Override
    public boolean isExist(Long id) {
	return messageRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
	messageRepository.deleteAll();
    }

    @Override
    public List<MessageEntity> getMessagesBylocale(String locale) {
	List<MessageEntity> list = new ArrayList<>();
	messageRepository.findAll().stream().filter(r -> r.getLocale().equals(locale)).collect(Collectors.toList()).forEach((message) -> {
	    list.add(message);
	});
	return list;
    }

}
