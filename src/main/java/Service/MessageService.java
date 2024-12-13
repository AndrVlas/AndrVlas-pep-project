package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        return messageDAO.getMessageById(messageId);
    }

    public Message createMessage(Message message) {
        if(message.getMessage_text().isBlank()) {
            return null;
        }

        if(message.getMessage_text().length() > 255) {
            return null;
        }

        if(accountDAO.getAccountById(message.getPosted_by()) == null) {
            return null;
        }

        return messageDAO.createMessage(message);
    }

    public Message deleteMessageById(int messageId) {
        return messageDAO.deleteMessageById(messageId);
    }

    public Message updateMessageById(int messageId, Message message) {
        if(message.getMessage_text().isBlank()) {
            return null;
        }

        if(message.getMessage_text().length() > 255) {
            return null;
        }

        if(messageDAO.getMessageById(messageId) == null) {
            return null;
        }

        return messageDAO.updateMessageById(messageId, message);
    }

    public List<Message> getMessagesByUser(int userId) {
        return messageDAO.getMessagesByUser(userId);
    }
}
