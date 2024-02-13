package sungil.management.service.board.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.domain.Comment;
import sungil.management.repository.board.comment.CommentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentSerivceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentSerivceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Map<String, String> addComment(Comment comment) {
        Map<String, String> map = new HashMap<>();
        try {
            commentRepository.insertComment(comment);
            map.put("result", "ADD_COMMENT_COMPLETE");
        } catch (Exception e) {
            map.put("result", "FAIL_ADD_COMMENT");
        }
        return map;
    }

    @Override
    public List<Comment> getCommentByBoardIdx(int boardIdx) {
        return commentRepository.getCommnetByBoardIdx(boardIdx);
    }
}
