package sungil.management.service.faq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.domain.FaQ;
import sungil.management.repository.faq.FaQRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FaQSerivceImpl implements FaQService{
    private final FaQRepository faQRepository;

    @Autowired
    public FaQSerivceImpl(FaQRepository faQRepository) {
        this.faQRepository = faQRepository;
    }

    @Override
    public Map<String, String> addFaQ(FaQ faQ) {
        Map<String, String> map = new HashMap<>();

        try {
            faQRepository.insertFaQ(faQ);
            map.put("result", "ADD_FAQ_COMPLETE");
        } catch (Exception e) {
            map.put("result", "FAILED_ADD_FAQ");
        }

        return map;
    }

    @Override
    public Map<String, String> updateFaQ(FaQ faQ) {
        Map<String, String> map = new HashMap<>();

        try {
            faQRepository.updateFaQ(faQ);
            map.put("result", "UPDATE_FAQ_COMPLETE");

        } catch (Exception e) {

            map.put("result", "FAILED_UPDATE_FAQ");
        }
        return map;
    }

    @Override
    public Map<String, String> deleteFaQByIdx(int idx) {
        Map<String, String> map = new HashMap<>();

        try {
            faQRepository.deleteFaQ(idx);
            map.put("result", "DELETE_FAQ_COMPLETE");

        } catch (Exception e) {
            map.put("result", "FAILED_DELETE_FAQ");
        }
        return map;
    }

    @Override
    public List<FaQ> getAllFaQ() {
        return faQRepository.getAllFaQ();
    }

    @Override
    public FaQ getFaQByIdx(int idx) {
        return faQRepository.getFaQByIdx(idx);
    }
}
