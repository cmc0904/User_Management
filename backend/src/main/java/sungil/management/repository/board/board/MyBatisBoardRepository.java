package sungil.management.repository.board.board;

import org.apache.ibatis.annotations.*;
import sungil.management.domain.Board;

import java.util.List;

@Mapper
public interface MyBatisBoardRepository extends BoardRepository {

    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board")
    List<Board> getAllBoard();
    @Select("SELECT idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt FROM stn_board where idx = #{boardIdx}")
    Board getBoardByBoardIdx(Integer boardIdx);

    @Select("Select idx as boardIdx, title as boardTitle, detail as boardDetail, writerId, createAt from stn_board where writerId = #{writerId}")
    List<Board> getBoardByWriterId(String writerId);

    @Update("update stn_board set title = #{boardTitle}, detail = #{boardDetail} where idx = #{boardIdx}")
    void updateBoard(Board board);

    @Insert("insert into stn_board (title, detail, writerId, createAt) values (#{boardTitle}, #{boardDetail}, #{writerId}, NOW())")
    void insertBoard(Board board);

    @Delete("delete from stn_board where idx = #{boardIdx}")
    void deleteBoard(int boardIdx);
}
