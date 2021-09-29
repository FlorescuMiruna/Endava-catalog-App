package com.project.catalog.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TeacherBasicInfoDto {

    private Long id;

    @NotNull(message = "firstName.cannot.be.null")
    @NotEmpty(message = "firstName.cannot.be.empty")
    @NotBlank(message = "firstName.cannot.be.blank")
    private String firstName;

    @NotNull(message = "lastName.cannot.be.null")
    @NotEmpty(message = "lastName.cannot.be.empty")
    @NotBlank(message = "lastName.cannot.be.blank")
    private String lastName;

    @Valid
    private List<SubjectDto> subjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }
}
