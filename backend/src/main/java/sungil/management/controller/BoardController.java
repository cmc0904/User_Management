package sungil.management.controller;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.Board;
import sungil.management.domain.Comment;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.repository.board.comment.CommentRepository;
import sungil.management.service.board.board.BoardSerivce;
import sungil.management.service.board.comment.CommentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    private final BoardSerivce boardSerivce;
    private final CommentService commentService;
    private final JwtTokenValidator jwtTokenValidator;
    @Autowired
    public BoardController(BoardSerivce boardSerivce, CommentService commentService, JwtTokenValidator jwtTokenValidator) {
        this.boardSerivce = boardSerivce;
        this.commentService = commentService;
        this.jwtTokenValidator = jwtTokenValidator;
    }

    @PostMapping("/postBoard")
    public ResponseEntity<?> postBoard(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Board board) {
        board.setWriterId(jwtTokenValidator.getUserIdFromToken(jwtTokenValidator.extractJwtToken(authorizationHeader)));
        return ResponseEntity.ok(boardSerivce.postBoard(board));
    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<?> getAllBoard() {

        return ResponseEntity.ok(boardSerivce.getAllBoard());
    }

    @GetMapping("/getBoardByUserIdx")
    public ResponseEntity<?> getBoardByWriterId(@RequestHeader("Authorization") String authorizationHeader, String userId) {
        return ResponseEntity.ok(boardSerivce.getBoardByWriterId(userId == null ? jwtTokenValidator.getUserIdFromToken(jwtTokenValidator.extractJwtToken(authorizationHeader)) : userId));
    }

    @GetMapping("/getBoardByIdx")
    public ResponseEntity<Board> getBoardByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(boardSerivce.getBoardByBoardIdx(boardIdx));
    }

    @PutMapping("/updateBoard")
    public ResponseEntity<Map<String, String>> updateBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardSerivce.updateBoard(board));
    }

    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Map<String, String>> deleteBoard(Integer boardIdx) {
        return ResponseEntity.ok(boardSerivce.deleteBoard(boardIdx));
    }


    @GetMapping("/getComment")
    public ResponseEntity<List<Comment>> getCommentByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(commentService.getCommentByBoardIdx(boardIdx));
    }

    @PostMapping("/addComment")
    public ResponseEntity<Map<String, String>> addComment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Comment comment) {
        comment.setWriterId(jwtTokenValidator.getUserIdFromToken(jwtTokenValidator.extractJwtToken(authorizationHeader)));
        return ResponseEntity.ok(commentService.addComment(comment));
    }

}
