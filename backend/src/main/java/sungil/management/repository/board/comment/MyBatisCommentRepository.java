package sungil.management.repository.board.comment;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sungil.management.domain.Comment;

import java.util.Date;
import java.util.List;

@Mapper
public interface MyBatisCommentRepository extends CommentRepository{

    @Select("Select * from stn_board_comment where boardIdx = #{boardIdx}")
    List<Comment> getCommnetByBoardIdx(Integer boardIdx);
    @Insert("insert into stn_board_comment(boardIdx, comment, writerId, createAt) values(#{boardIdx}, #{comment}, #{writerId}, NOW())")
    void insertComment(Comment comment);
}
