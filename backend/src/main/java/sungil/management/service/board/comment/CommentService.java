package sungil.management.service.board.comment;

import sungil.management.domain.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Map<String, String> addComment(Comment comment);

    List<Comment> getCommentByBoardIdx(int boardIdx);

}
