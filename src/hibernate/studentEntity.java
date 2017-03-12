package hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student", schema = "hibernate", catalog = "")
public class studentEntity {
	// Creates variables
    private int student_id;
    private String firstname;
    private String lastname;

    // Gets the student_id
    @Id
    @Column(name = "student_id")
    public int getId() {
        return student_id;
    }

    // Sets the student_id
    public void setId(int student_id) {
        this.student_id = student_id;
    }

    // Teacher list
    @OneToMany(mappedBy = "studentEntity")
    private List<teacherEntity> teacherEntity;

    // Gets first name
    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    // Sets first name
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    // Gets last name
    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    // Sets last name
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        studentEntity that = (studentEntity) o;

        if (student_id != that.student_id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = student_id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}


