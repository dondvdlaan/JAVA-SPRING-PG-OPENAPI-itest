package dev.manyroads.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatterResponseTestRepo extends JpaRepository<MatterResponseTestEntity,Integer> {

    Optional<MatterResponseTestEntity> findByCustomerNr(long customerNr);
}
