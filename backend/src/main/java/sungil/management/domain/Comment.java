package sungil.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Comment {
    private Integer commentIdx;
    private Integer boardIdx;
    private String comment;
    private String writerId;
    private Date createAt;
}
