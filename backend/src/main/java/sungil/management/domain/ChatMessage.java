package sungil.management.domain;

import lombok.Data;

@Data
public class ChatMessage {
    private Long id;
    private String content;
    private String sender;
}
