package sungil.management.repository.board.board;

import sungil.management.domain.Board;

import javax.swing.*;
import java.util.List;

public interface BoardRepository {

    List<Board> getAllBoard();

    Board getBoardByBoardIdx(Integer boardIdx);

    List<Board> getBoardByWriterId(String writerId);

    void updateBoard(Board board);

    void insertBoard(Board board);

    void deleteBoard(int boardIdx);

}
