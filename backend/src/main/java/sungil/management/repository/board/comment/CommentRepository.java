package sungil.management.repository.board.comment;

import sungil.management.domain.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getCommnetByBoardIdx(Integer idx);

    void insertComment(Comment comment);
}

