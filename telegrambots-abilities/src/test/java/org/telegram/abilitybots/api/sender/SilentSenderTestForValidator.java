package org.telegram.abilitybots.api.sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SilentSenderTestForValidator {

    private SilentSender silent;
    private MessageSender sender;

    @BeforeEach
    void setUp() {
        sender = mock(MessageSender.class);
        silent = new SilentSender(sender);
    }

    @Test
    void sendValidMdMessage(){
        String message = "message";
        assertTrue(silent.checkMdValidator(message));
    }
    @Test
    void sendValidMdMessage_bold(){
        String message = "*message*";
        assertTrue(silent.checkMdValidator(message));
    }
    @Test
    void sendValidMdMessage_italic(){
        String message = "_message_";
        assertTrue(silent.checkMdValidator(message));
    }
    @Test
    void sendValidMdMessage_inline(){
        String message = "`message`";
        assertTrue(silent.checkMdValidator(message));
    }
    @Test
    void sendValidMdMessage_inline_URL(){
        String message = "[message]";
        assertTrue(silent.checkMdValidator(message));
    }
    @Test
    void sendValidMdMessage_language(){
        String message = "```message```";
        assertTrue(silent.checkMdValidator(message));
    }

    @Test
    void sendInvalidMdMessage_bold(){
        String message = "*message\\**message";
        assertFalse(silent.checkMdValidator(message));
    }
    @Test
    void sendInvalidMdMessage_italic(){
        String message = "_message\\_message_";
        assertFalse(silent.checkMdValidator(message));
    }
    @Test
    void sendValidMdMessage_bold_italic(){
        String message = "*message_*_message_";
        assertTrue(silent.checkMdValidator(message));
    }
    @Test
    void sendInvalidMdMessage_language(){
        String message = "```message````";
        assertFalse(silent.checkMdValidator(message));
    }
    @Test
    void sendInvalidMdMessage_language2(){
        String message = "``message````";
        assertFalse(silent.checkMdValidator(message));
    }

    @Test
    void sendInvalidMdMessage_language3(){
        String message = "```````message```";
        assertTrue(silent.checkMdValidator(message));
    }



}
