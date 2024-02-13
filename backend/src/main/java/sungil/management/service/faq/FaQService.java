package sungil.management.service.faq;

import sungil.management.domain.FaQ;

import java.util.List;
import java.util.Map;

public interface FaQService {
    Map<String, String> addFaQ(FaQ faQ);
    Map<String, String> updateFaQ(FaQ faQ);
    Map<String, String> deleteFaQByIdx(int idx);
    List<FaQ> getAllFaQ();
    FaQ getFaQByIdx(int idx);

}
