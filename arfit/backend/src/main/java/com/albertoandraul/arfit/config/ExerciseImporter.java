/*
* package com.albertoandraul.arfit.config;

import com.albertoandraul.arfit.model.Exercise;
import com.albertoandraul.arfit.repository.ExerciseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Component
public class ExerciseImporter implements CommandLineRunner {

    @Value("classpath:data/exercises.json")
    private Resource exercisesResource;

    private final ExerciseRepository exerciseRepository;

    public ExerciseImporter(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cambia a 'false' si no quieres que se ejecute automáticamente cada arranque
        boolean importEnabled = true;
        if (!importEnabled) return;

        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = exercisesResource.getInputStream()) {
            JsonNode root = mapper.readTree(is);
            if (!root.isArray()) return;

            for (JsonNode node : root) {
                String name = node.path("name").asText(null);
                if (name == null || name.isBlank()) continue;

                // Evita duplicados por nombre
                if (exerciseRepository.findByName(name).isPresent()) continue;

                // description <- concatenación de "instructions"
                String description = "";
                JsonNode instr = node.path("instructions");
                if (instr.isArray()) {
                    StringBuilder sb = new StringBuilder();
                    Iterator<JsonNode> it = instr.elements();
                    int i = 1;
                    while (it.hasNext()) {
                        sb.append(i++).append(". ").append(it.next().asText()).append("\n");
                    }
                    description = sb.toString().trim();
                }

                // muscle_group <- join de primaryMuscles (coma-separado)
                String muscleGroup = "";
                JsonNode primary = node.path("primaryMuscles");
                if (primary.isArray() && primary.size() > 0) {
                    List<String> muscles = mapper.convertValue(
                            primary,
                            new TypeReference<List<String>>() {}
                    );
                    muscleGroup = String.join(",", muscles);
                } else {
                    muscleGroup = node.path("category").asText("");
                }

                String equipment = node.path("equipment").asText("");

                Exercise e = new Exercise();
                e.setName(name);
                e.setDescription(description);
                e.setMuscleGroup(muscleGroup);
                e.setEquipment(equipment);

                exerciseRepository.save(e);
            }
            System.out.println("Import de exercises.json finalizado.");
        }
    }
}
*/