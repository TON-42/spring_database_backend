package com.springtel.repository;
import com.springtel.model.TextEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextEntityRepository extends JpaRepository<TextEntity, Long> {
}
