package sungil.management.service.faq;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sungil.management.domain.FaQ;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FaQSerivceImplTest {

    private FaQService faQService;

    @Autowired
    public FaQSerivceImplTest(FaQService faQService) {
        this.faQService = faQService;
    }

    @Test
    void addFaQ() {

        FaQ faQ  = new FaQ(0, "테스트 입니까 ?", "네 테스트 입니다.");

        faQService.addFaQ(faQ);

        Assertions.assertThat(faQ.getIdx()).isEqualTo(faQService.getFaQByIdx(faQ.getIdx()).getIdx());

        faQService.deleteFaQByIdx(faQ.getIdx());

    }

    @Test
    void updateFaQ() {

        FaQ faQ  = new FaQ(0, "테스트 입니까 ?", "네 테스트 입니다.");

        faQService.addFaQ(faQ);

        FaQ newFaQ = new FaQ(faQ.getIdx(), "테스트 입니까 ?", "아니요 ?");

        faQService.updateFaQ(newFaQ);

        Assertions.assertThat(faQ.getAnswer()).isNotEqualTo(faQService.getFaQByIdx(newFaQ.getIdx()).getAnswer());

        faQService.deleteFaQByIdx(faQ.getIdx());

    }

    @Test
    void getAllFaQ() {
        Assertions.assertThat(faQService.getAllFaQ()).isNotNull();
    }

}