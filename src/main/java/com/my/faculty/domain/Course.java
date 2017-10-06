package com.my.faculty.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public class Course {
    private Long id;
    private String title;
    private LocalDate createDate;
    private Boolean active;
    private User user;
    private Set<Student> students;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != null ? !id.equals(course.id) : course.id != null) return false;
        if (title != null ? !title.equals(course.title) : course.title != null) return false;
        if (createDate != null ? !createDate.equals(course.createDate) : course.createDate != null) return false;
        if (active != null ? !active.equals(course.active) : course.active != null) return false;
        if (user != null ? !user.equals(course.user) : course.user != null) return false;
        return students != null ? students.equals(course.students) : course.students == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        return result;
    }
}
