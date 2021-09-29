package com.project.catalog.dto;

import javax.validation.constraints.*;

public class SubjectDto {

    private Long id;

    @NotNull(message = "name.cannot.be.null")
    @NotEmpty(message = "name.cannot.be.empty")
    @NotBlank(message = "name.cannot.be.blank")
    @Size(min = 2, max = 50, message = "name.cannot.have.this.size")
    private String name;

    @NotNull(message = "credit.points.cannot.be.null")
    @Min(value = 1, message = "credit.points.cannot.be.less.than.one")
    @Max(value = 20, message = "credit.points.cannot.be.more.than.20")
    private Integer creditPoints;

/*    @NotNull(message = "optional.cannot.be.null")*/
    private Boolean isOptional;

    @NotNull(message = "subject.scoring.cannot.be.null")
    private SubjectScoringDto subjectScoring;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(Integer creditPoints) {
        this.creditPoints = creditPoints;
    }

    public Boolean getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(Boolean optional) {
        isOptional = optional;
    }

    public SubjectScoringDto getSubjectScoring() {
        return subjectScoring;
    }

    public void setSubjectScoring(SubjectScoringDto subjectScoring) {
        this.subjectScoring = subjectScoring;
    }
}
