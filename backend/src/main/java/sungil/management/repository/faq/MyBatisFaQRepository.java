package sungil.management.repository.faq;

import org.apache.ibatis.annotations.*;
import sungil.management.domain.FaQ;
import sungil.management.domain.Role;
import sungil.management.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MyBatisFaQRepository extends FaQRepository {

    @Select("Select * from stn_faq")
    List<FaQ> getAllFaQ();

    @Select("Select * from stn_faq where idx = #{idx}")
    FaQ getFaQByIdx(int idx);

    @Insert("insert into stn_faq(idx, question, answer) values (#{idx}, #{question}, #{answer})")
    @Options(useGeneratedKeys = true, keyProperty = "idx", keyColumn = "idx")
    void insertFaQ(FaQ faQ);

    @Delete("delete from stn_faq where idx = #{idx}")
    void deleteFaQ(int idx);

    @Update("update stn_faq set question = #{question}, answer = #{answer} where idx = #{idx}")
    void updateFaQ(FaQ faQ);
}
