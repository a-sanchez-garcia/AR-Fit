package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserIdOrderByCreatedAtDesc(Long userId);
    int countByUserId(Long userId);
    List<Workout> findByUserIdOrUserIdIsNullOrderByCreatedAtDesc(Long userId);
    Page<Workout> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    Page<Workout> findByUserIdOrUserIdIsNullOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
