package com.quasar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.Test;

import com.quasar.exception.InvalidMessageLengthException;

public class MessageBuilderTest {

    @Test
    public void testNormalMessage() {

        String[][] messages = {
            {"este", "", "", "mensaje", ""},
            {"", "es", "", "", "secreto"},
            {"este", "", "un", "", ""},};

        MessageBuilder messageBuilder = new MessageBuilder();
        String completeMessage = messageBuilder.GetMessage(messages);

        assertEquals("este es un mensaje secreto", completeMessage);
    }

    @Test
    public void testUncompleteMessage() {

        String[][] messages = {
            {"este", "", "", "", ""},
            {"", "es", "", "", ""},
            {"este", "", "un", "", ""},};

        MessageBuilder messageBuilder = new MessageBuilder();
        String completeMessage = messageBuilder.GetMessage(messages);

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
                () -> messageBuilder.GetMessage(messages)
        );
    }

}
