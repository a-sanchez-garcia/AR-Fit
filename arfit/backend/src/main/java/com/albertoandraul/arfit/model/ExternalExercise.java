package com.albertoandraul.arfit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "external_exercise")
public class ExternalExercise {

    @Id
    private String id; // ID de la API es un string

    private String name;
    private String bodyPart;
    private String target;
    private String equipment;
    private String gifUrl;

    public ExternalExercise() {}

    public ExternalExercise(String id, String name, String bodyPart, String target, String equipment, String gifUrl) {
        this.id = id;
        this.name = name;
        this.bodyPart = bodyPart;
        this.target = target;
        this.equipment = equipment;
        this.gifUrl = gifUrl;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBodyPart() { return bodyPart; }
    public void setBodyPart(String bodyPart) { this.bodyPart = bodyPart; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }

    public String getGifUrl() { return gifUrl; }
    public void setGifUrl(String gifUrl) { this.gifUrl = gifUrl; }
}
