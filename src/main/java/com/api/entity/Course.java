package com.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Document(indexName = "onlinecourseregistration")
@TypeAlias("courses")
@UserDefinedType(value = "course")
public class Course implements Serializable {
    @Id
    @Field(type = FieldType.Keyword)
    private UUID courseId;
    @NotNull
    @NotEmpty
    @Field(type = FieldType.Text,name = "courseName")
    private String courseName;
    @Field(type = FieldType.Text)
    private String description;
    @NotNull
    @NotEmpty
    @Field(type = FieldType.Text)
    private String instructor;
    @NotNull
    @NotEmpty
    @Field(type = FieldType.Integer)
    private int credits;
    @Field(type = FieldType.Integer)
    private int studentsEnrolled;
    @Field(type = FieldType.Text)
    private String classTime;
    @Field(type = FieldType.Text)
    private String day;
    @Field(type = FieldType.Text)
    private String handsOnVsLecture;

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHandsOnVsLecture() {
        return handsOnVsLecture;
    }

    public void setHandsOnVsLecture(String handsOnVsLecture) {
        this.handsOnVsLecture = handsOnVsLecture;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(int studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
