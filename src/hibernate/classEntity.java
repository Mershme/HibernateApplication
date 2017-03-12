package hibernate;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "class", schema = "hibernate", catalog = "")
public class classEntity {

    private String name;
    private String code;
    private int year_offered;
    private String school;
    private String class_id;

    @Id
    @Column(name = "id")
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "classEntity")
    private List<teacherEntity> teacherEntity;

    public List<teacherEntity> getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(List<teacherEntity> teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "year_offered")
    public int getYearOffered() {
        return year_offered;
    }

    public void setYearOffered(int year_offered) {
        this.year_offered = year_offered;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "class_id")
    public String getClassId() {
        return class_id;
    }

    public void setClassId(String class_id) {
        this.class_id = class_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        classEntity classEntity = (classEntity) o;

        if (id != classEntity.id) return false;
        if (year_offered != classEntity.year_offered) return false;
        if (name != null ? !name.equals(classEntity.name) : classEntity.name != null) return false;
        if (code != null ? !code.equals(classEntity.code) : classEntity.code != null) return false;
        if (school != null ? !school.equals(classEntity.school) : classEntity.school != null) return false;
        if (class_id != null ? !class_id.equals(classEntity.class_id) : classEntity.class_id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + year_offered;
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (class_id != null ? class_id.hashCode() : 0);
        return result;
    }
}

