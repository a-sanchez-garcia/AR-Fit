package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.TrainingSet;
import com.albertoandraul.arfit.model.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainingSetRepository extends JpaRepository<TrainingSet, Long> {

    // Buscar sets de una sesión usando el objeto
    List<TrainingSet> findBySessionOrderByWorkoutExerciseIdAscSetNumberAsc(TrainingSession session);

    // Borrar sets de una sesión usando objeto
    void deleteBySession(TrainingSession session);
}
