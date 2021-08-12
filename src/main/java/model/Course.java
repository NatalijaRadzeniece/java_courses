package model;

import model.enums.CourseName;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private CourseName courseName;
    private int lessonsCount;
    private List<Student> courseStudents = new ArrayList<>();

    public Course(CourseName courseName, int lessonsCount) {
        this.courseName = courseName;
        this.lessonsCount = lessonsCount;
    }

    public CourseName getCourseName() {
        return courseName;
    }

    public int getLessonsCount() {
        return lessonsCount;
    }

    public List<Student> getCourseStudents() {
        return courseStudents;
    }

    public void addStudent(Student student) {
        this.courseStudents.add(student);
    }
}