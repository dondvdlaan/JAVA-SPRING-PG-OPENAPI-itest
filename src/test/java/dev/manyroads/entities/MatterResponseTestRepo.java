package dev.manyroads.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatterResponseTestRepo extends JpaRepository<MatterResponseTestEntity,Integer> {

    Optional<MatterResponseTestEntity> findByCustomerNr(long customerNr);
}
