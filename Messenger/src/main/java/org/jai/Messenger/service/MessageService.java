package org.jai.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jai.Messenger.database.DatabaseClass;
import org.jai.Messenger.exception.DataNotFoundException;
import org.jai.Messenger.model.Message;

public class MessageService {

	private static Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {

		messages.put(1L, new Message(1, "Hello, This is Messenger(RESTful-Api) Application.", "jai"));
		messages.put(2L, new Message(2, "Hey! This is Jersey framework which is used to build the web service.", "sameer"));
		messages.put(3L, new Message(3, "Hi. This is JAX-RS the component using which the application is implemented.", "rajesh"));
	}

	public List<Message> getAllMessages() {

		return new ArrayList<Message>(messages.values());

		/*
		 * Message m1 = new Message(1L,"Hello World","Jai"); Message m2 = new
		 * Message(1L,"Hello Rest-API","Jai"); Message m3 = new
		 * Message(1L,"Hello Jersey","Jai");
		 * 
		 * List<Message> list = new ArrayList<>(); list.add(m1); list.add(m2);
		 * list.add(m3); return list;
		 */
	}

	public List<Message> getAllMessagesforYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreationDate());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}

		return messagesForYear;
	}

	public List<Message> getAllMessagePaginated(int start, int size) {
		List<Message> list = new ArrayList<Message>(messages.values());
		if (start > list.size() || size > list.size()) {
			return new ArrayList<Message>();
		}
		return list.subList(start, size);
	}

	public Message getMessage(Long id) {

		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id :" + id + " not found.");
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0)
			return null;

		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(Long id) {

		return messages.remove(id);

	}

}
