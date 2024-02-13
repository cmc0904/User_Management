package sungil.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class FaQ {
    private Integer idx;
    private String question;
    private String answer;
}
