package org.ssv.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class Smell {

    @JsonProperty("name")
    private String code; // analysis kubehound
    private String description; // analysis kubehound
    private int id; // parser

    private String extendedName; // file json
    private String smellTypeDescription; // file json
    private Refactoring refactoring; // parser - from json
    private List<QualityAttribute> propertiesAffected; // file json
    private UrgencyCode urgencyCode; // triage
    private SmellStatus smellStatus; // frontend
    private  boolean isChecked; // frontend
    private Microservice microservice; // frontend
    private EffortTime effortTime; // frontend


}
