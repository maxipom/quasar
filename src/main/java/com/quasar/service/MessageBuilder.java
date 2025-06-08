package com.quasar.service;

import com.quasar.exception.InvalidMessageLengthException;

public class MessageBuilder {

    public String GetMessage(String[][] messages) {
        int messageLength = messages[0].length;
        String[] completeMessage = new String[messageLength];
        for (String[] words : messages) {
            for (int i = 0; i < messageLength; i++) {
                if (words.length != messageLength) {
                    throw new InvalidMessageLengthException("Messages must have the same length");
                }
                String word = words[i];
                String lastWord = completeMessage[i];
                Boolean lastWordWasEmpty = "".equals(lastWord) || lastWord == null;
                if (lastWordWasEmpty) {
                    completeMessage[i] = word;
                }
            }
        }
        return String.join(" ", completeMessage);
    }
}
