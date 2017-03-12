package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "teacher", schema = "hibernate", catalog = "")
public class teacherEntity {

	// creates variables
    private String teacher_id;
    private String hireDate;
    private int is_current_teacher;

    @Id
    @Column(name = "id")
    private int id;

    // Gets teacher ID
    public int getId() {
        return id;
    }

    // Sets teacher ID
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private classEntity classEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private studentEntity studentEntity;

    // Gets Class
    public classEntity getClassEntity() {
        return classEntity;
    }

    // Sets Class
    public void setClassEntity(classEntity classEntity) {
        this.classEntity = classEntity;
    }

    // Gets Student
    public studentEntity getStudentEntity() {
        return studentEntity;
    }

    // Sets Student
    public void setStudentEntity(studentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    @Basic
    @Column(name = "teacher_id")
    // Gets teacher_id
    public String getTeacher_id() {
        return teacher_id;
    }

    // Sets teacher_id
    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Basic
    @Column(name = "hireDate")
    // Gets Hire Date
    public String getHireDate() {
        return hireDate;
    }

    // Sets Hire Date
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @Basic
    @Column(name = "is_current_teacher")
    // Gets the current teacher
    public int getIsCurrentTeacher() {
        return is_current_teacher;
    }

    // Sets the current teacher
    public void setIsCurrentTeacher(int IsCurrentTeacher) {
        this.is_current_teacher = IsCurrentTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        teacherEntity that = (teacherEntity) o;

        if (id != that.id) return false;
        if (is_current_teacher != that.is_current_teacher) return false;
        if (teacher_id != null ? !teacher_id.equals(that.teacher_id) : that.teacher_id != null) return false;
        if (hireDate != null ? !hireDate.equals(that.hireDate) : that.hireDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (teacher_id != null ? teacher_id.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + is_current_teacher;
        return result;
    }
}

