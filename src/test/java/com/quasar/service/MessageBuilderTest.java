package com.quasar.service;

import com.quasar.exception.InvalidMessageLengthException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class MessageBuilderTest {

    @Test
    public void testNormalMessage() {

        String[][] messages = {
                {"este", "", "", "mensaje", ""},
                {"", "es", "", "", "secreto"},
                {"este", "", "un", "", ""},
        };

        MessageBuilder messageBuilder = new MessageBuilder();
        String completeMessage = messageBuilder.getMessage(messages);

        assertEquals("este es un mensaje secreto", completeMessage);
    }

    @Test
    public void testUncompleteMessage() {

        String[][] messages = {
                {"este", "", "", "", ""},
                {"", "es", "", "", ""},
                {"este", "", "un", "", ""},};

        MessageBuilder messageBuilder = new MessageBuilder();
        String completeMessage = messageBuilder.getMessage(messages);

        assertEquals("este es un  ", completeMessage);
    }

    @Test
    public void testDifferentLenghts() {

        String[][] messages = {
                {"este", "", "", "", ""},
                {"", "es"},
                {"este", "", "un", "mensaje", "distinto"},};

        MessageBuilder messageBuilder = new MessageBuilder();

        assertThrowsExactly(
                InvalidMessageLengthException.class,
                () -> messageBuilder.getMessage(messages)
        );
    }

}
