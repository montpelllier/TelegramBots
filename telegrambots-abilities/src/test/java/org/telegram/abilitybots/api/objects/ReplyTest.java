package org.telegram.abilitybots.api.objects;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class ReplyTest {

    @Test
    public void convertConsumerTest0() {
        Consumer<Update> consumer = update -> {
        };
        Reply reply = Reply.of(consumer, Flag.TEXT, Flag.REPLY);

        assertEquals(Flag.TEXT, reply.conditions.get(0));
        assertEquals(Flag.REPLY, reply.conditions.get(1));
        assertNotNull(reply.action);
        assertFalse(reply.statsEnabled());
    }

    @Test
    public void convertConsumerTest1() {
        Consumer<Update> consumer = update -> {
        };
        List<Predicate<Update>> conditions = asList(Flag.PHOTO, Flag.DOCUMENT);
        Reply reply = Reply.of(consumer, conditions);

        assertEquals(Flag.PHOTO, reply.conditions.get(0));
        assertEquals(Flag.DOCUMENT, reply.conditions.get(1));
        assertNotNull(reply.action);
        assertFalse(reply.statsEnabled());
    }
}
