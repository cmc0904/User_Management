package sungil.management.repository.faq;

import sungil.management.domain.FaQ;
import sungil.management.domain.Role;
import sungil.management.domain.User;

import java.util.List;
import java.util.Optional;

public interface FaQRepository {
    List<FaQ> getAllFaQ();
    void insertFaQ(FaQ faQ);
    void deleteFaQ(int idx);
    void updateFaQ(FaQ faQ);
    FaQ getFaQByIdx(int idx);
}
