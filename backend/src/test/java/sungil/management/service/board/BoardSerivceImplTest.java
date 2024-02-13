package sungil.management.service.board.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sungil.management.domain.Board;
import sungil.management.domain.Comment;
import sungil.management.service.board.comment.CommentService;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;



@SpringBootTest
class BoardSerivceImplTest {
    private BoardSerivce boardSerivce;

    private CommentService commentService;

    @Autowired
    public BoardSerivceImplTest(BoardSerivce boardSerivce, CommentService commentService) {
        this.boardSerivce = boardSerivce;
        this.commentService = commentService;
    }

    @Test
    void getAllBoard() {
        Assertions.assertThat(boardSerivce.getAllBoard()).isNotNull();
    }

    @Test
    void getBoardByBoardIdx() {
        Board post = new Board(null,"제목","내용","kai06120",new Date(2023,2,13));
        boardSerivce.postBoard(post);
        Assertions.assertThat(boardSerivce.getBoardByBoardIdx(post.getBoardIdx())).isNotNull();
        boardSerivce.deleteBoard(post.getBoardIdx());
    }

    @Test
    void getBoardByWriterId() {
        Board post = new Board(null,"제목","내용","kai06120",new Date(2023,2,13));
        boardSerivce.postBoard(post);
        Assertions.assertThat(boardSerivce.getBoardByWriterId(post.getWriterId())).isNotNull();
        boardSerivce.deleteBoard(post.getBoardIdx());
    }

    @Test
    void postBoard() {
        Board post = new Board(null,"제목","내용","kai06120",new Date(2023,2,13));
        boardSerivce.postBoard(post);
        Board findBoard = boardSerivce.getBoardByBoardIdx(post.getBoardIdx());
        Assertions.assertThat(post.getBoardIdx()).isEqualTo(findBoard.getBoardIdx());
        boardSerivce.deleteBoard(post.getBoardIdx());

    }

    @Test
    void addComment() {
        Board post = new Board(null,"제목","내용","kai06120",new Date(2023,2,13));
        boardSerivce.postBoard(post);
        Comment addComment = new Comment(null, post.getBoardIdx(), "내용","kai06120",new Date(2023,2,13));
        commentService.addComment(addComment);
        List<Comment> comments = commentService.getCommentByBoardIdx(post.getBoardIdx());
        Assertions.assertThat(addComment.getCommentIdx()).isEqualTo(comments.get(0).getCommentIdx());
        boardSerivce.deleteBoard(post.getBoardIdx());


    }




}