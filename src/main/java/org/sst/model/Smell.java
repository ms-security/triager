package org.sst.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sst.service.SmellId;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a smell entity associated with an analysis.
 */
@Entity
@Table(name = "smell")
@Data
@SuperBuilder
@NoArgsConstructor
@IdClass(SmellId.class)
public class Smell {
    /**
     * The unique identifier for the smell.
     */
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    /**
     * The unique identifier for the analysis associated with the smell.
     */
    @Id
    @Column(name = "analysis_id", nullable = false, insertable = false, updatable = false)
    private String analysisId;

    /**
     * The analysis associated with the smell.
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "analysis", nullable = false)
    private Analysis analysis;

    /**
     * The code of the smell.
     */
    @JsonProperty("name")
    @Column(name = "code")
    private String code;

    /**
     * The description of the smell.
     */
    @Column(name = "description")
    private String description;

    /**
     * The extended name of the code of the smell.
     */
    @Column(name = "extended_name")
    private String extendedName;

    /**
     * The microservice associated with the smell.
     */
    @ManyToOne
    @JoinColumn(name = "microservice_id", nullable = true)
    private Microservice microservice;

    /**
     * The effort time associated with the smell.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "effort_time_id")
    private EffortTime effortTime;

    /**
     * The refactoring associated with the smell.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "refactoring_id")
    private Refactoring refactoring;

    /**
     * The urgency code associated with the smell.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "urgency_code")
    private UrgencyCode urgencyCode;

    /**
     * The status of the smell.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SmellStatus status;

    /**
     * The value of the checkbox of the smell.
     */
    @Column(name = "is_checked")
    private boolean isChecked;

    /**
     * The analysis that was performed to detect the smell.
     */
    @Column(name = "output_Analysis")
    private String outputAnalysis;

    /**
     * The list of quality attributes affected by the smell.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "smell_quality",
            joinColumns = {
                    @JoinColumn(name = "smell_id", referencedColumnName = "id"),
                    @JoinColumn(name = "smell_analysis", referencedColumnName = "analysis_id")
            },
            inverseJoinColumns = @JoinColumn(name = "quality_attribute_id")
    )
    private List<QualityAttribute> propertiesAffected;

}

