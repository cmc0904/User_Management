package sungil.management.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Board {
    private Integer boardIdx;
    private String boardTitle;
    private String boardDetail;
    private String writerId;
    private Date createAt;
}
