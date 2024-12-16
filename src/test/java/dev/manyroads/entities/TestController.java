package dev.manyroads.entities;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    MatterResponseTestRepo matterResponseTestRepo;

    public TestController(MatterResponseTestRepo matterResponseTestRepo) {
        this.matterResponseTestRepo = matterResponseTestRepo;
    }

    @GetMapping("/test")
    void testingDB() {
        MatterResponseTestEntity matterResponseTestEntity
                = MatterResponseTestEntity.builder()
                .customerNr(123456L)
                .chargeID(UUID.randomUUID())
                .build();
        var saved = matterResponseTestRepo.save(matterResponseTestEntity);
        System.out.println("saved: " + saved);
    }
}
