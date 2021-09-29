package com.project.catalog.model;

import javax.persistence.*;

/*
    Entities in JPA are nothing but POJOs representing data that can be persisted to the database.
    An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
    We should define an entity so that JPA is aware of it. We must also ensure that the entity has a no-arg constructor and a primary key.
    The entity name defaults to the name of the class. We can change its name using the name element. @Entity(name="subject")
    Each JPA entity must have a primary key which uniquely identifies it. The @Id annotation defines the primary key.
    We can generate the identifiers in different ways which are specified by the @GeneratedValue annotation.
    We can choose from four id generation strategies with the strategy element. The value can be AUTO, TABLE, SEQUENCE, or IDENTITY.
    If we specify GenerationType.AUTO, the JPA provider will use any strategy it wants to generate the identifiers.
    In most cases, the name of the table in the database and the name of the entity will not be the same. In these cases, we can specify the table name using the @Table annotation
    @Column annotation mentions the details of a column in the table. @Column(name="name", length=50, nullable=false, unique=false)
    JPA provides the @Embeddable annotation to declare that a class will be embedded by other entities. The JPA annotation @Embedded is used to embed a type into another entity.
 */

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "credit_point")
    private Integer creditPoints;

    @Column(name = "is_optional")
    private Boolean isOptional;

    @Column(name = "course_percent")
    private Integer coursePercent;

    @Column(name = "seminary_percent")
    private Integer seminaryPercent;

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

    public Integer getCoursePercent() {
        return coursePercent;
    }

    public void setCoursePercent(Integer coursePercent) {
        this.coursePercent = coursePercent;
    }

    public Integer getSeminaryPercent() {
        return seminaryPercent;
    }

    public void setSeminaryPercent(Integer seminaryPercent) {
        this.seminaryPercent = seminaryPercent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Subject{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", creditPoints=").append(creditPoints);
        sb.append(", isOptional=").append(isOptional);
        sb.append(", coursePercent=").append(coursePercent);
        sb.append(", seminaryPercent=").append(seminaryPercent);
        sb.append('}');
        return sb.toString();
    }
}
