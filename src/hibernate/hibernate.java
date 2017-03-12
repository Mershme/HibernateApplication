package hibernate;

import javax.persistence.*;
import java.security.acl.Owner;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class hibernate {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerF = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        // Scanner for user input
        Scanner input = new Scanner(System.in);

        // Variable to store user input
        String userInput;


        String action = null;
        while(action != "0") {
            System.out.println("\nPlease indicate an action to take");
            System.out.println("1 - Check available classes\n" +
                                "2 - Add a class for availability\n" +
                                "3 - See each student and their classes\n" +
                                "4 - Add a student\n" +
                                "5 - Add a student to a class\n" +
                                "6 - Remove a class for availability\n" +
                                "7 - Remove a student\n" +
                                "8 - Remove a student from a class\n" +
                                "0 - Quit\n");

            //code to loop through all of the possible commands
            userInput = input.nextLine();
            switch(userInput){
                case "1":
                    selectClasses(entityManagerF);
                    break;

                case "2":
                    addClass(entityManagerF, input);
                    break;

                case "3":
                    studentClasses(entityManagerF);
                    break;

                case "4":
                    addStudent(entityManagerF, input);
                    break;

                case "5":
                    addStudentToClass(entityManagerF, input);
                    break;

                case "6":
                    removeClass(entityManagerF, input);
                    break;

                case "7":
                    removeStudent(entityManagerF, input);
                    break;

                case "8":
                    removeStudentFromClass(entityManagerF, input);
                    break;

                case "0":
                    return;

                default:
                    break;
            }
        }


    }

    // selects all of the classes
    public static void selectClasses(EntityManagerFactory entityManagerF){
    	// creates the entity manager
        EntityManager entityManager = entityManagerF.createEntityManager();
        // begins the transaction
        entityManager.getTransaction().begin();

        System.out.println("All vehicles currently in our database:");

        // creates a list that returns all of the classes
        List<classEntity> carE = entityManager.createQuery("from classEntity").getResultList();

        // Loop to go through the whole list
        for (classEntity temp : carE) {
            System.out.println(temp.getSchool() + " " + temp.getYearOffered() + " " + temp.getName() + " " + temp.getCode());
        }
        entityManager.close();

    }

    public static void addClass(EntityManagerFactory entityManagerF, Scanner input){
        EntityManager entityManager = entityManagerF.createEntityManager();
        entityManager.getTransaction().begin();

        // prompts the user for class information
        System.out.println("Enter the class information as follows:");
        System.out.println("<school> <year_offered> <name> <code>");

		// saves the user input
        String[]userInput = input.nextLine().split(" ");

        // creates a new class
        classEntity newClass = new classEntity();

        // uses the user input to enter class information
        newClass.setSchool(userInput[0]);
        newClass.setYearOffered(Integer.parseInt(userInput[1]));
        newClass.setName(userInput[2]);
        newClass.setCode(userInput[3]);


        entityManager.persist(newClass);
        entityManager.flush();
        entityManager.getTransaction().commit();

        System.out.println("Class added!");

        entityManager.close();


    }

    public static void studentClasses(EntityManagerFactory entityManagerF){
        EntityManager entityManager = entityManagerF.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Student \\ School \\ Year Offered \\ Name \\ Code");

        // Creates Teacher Entity which is used to access the class/student tables
        List<teacherEntity> teachers = entityManager.createQuery("from teacherEntity ").getResultList();

        // Shows the list
        for (teacherEntity teacher: teachers) {
            System.out.println(teacher.getStudentEntity().getFirstname() + " " + teacher.getStudentEntity().getLastname() + "\t" + teacher.getClassEntity().getSchool() +
                                " " + teacher.getClassEntity().getYearOffered() + " " + teacher.getClassEntity().getName() + " " + teacher.getClassEntity().getCode());
        }
        entityManager.close();
    }

    public static void addStudent(EntityManagerFactory entityManagerF, Scanner input){
        EntityManager entityManager = entityManagerF.createEntityManager();
        entityManager.getTransaction().begin();


        System.out.println("Enter the first and last name of the student:");

        // Stores input from the user in an array
        String[] name = input.nextLine().split(" ");

        // Creates new student
        studentEntity newStudent = new studentEntity();

        // sets first and last name of the student using user input
        newStudent.setFirstname(name[0]);
        newStudent.setLastname(name[1]);

        entityManager.persist(newStudent);
        entityManager.flush();
        entityManager.getTransaction().commit();

        System.out.println("Student added!");

        entityManager.close();
    }

    public static void addStudentToClass(EntityManagerFactory entityManagerF, Scanner input){
        EntityManager entityManager = entityManagerF.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Please select Owner  from the list");

        // Creates student list
        List<studentEntity> studentList = entityManager.createQuery("from studentEntity ").getResultList();

        int count = 0;

        // Loops through student list
        for (studentEntity temp : studentList) {
            System.out.println(count + ". " +temp.getFirstname() + " " + temp.getLastname());
            count++;
        }

        // prompts the user to select which student to add to the class
        int studentNumber = input.nextInt();

		// Creates new student
        studentEntity newStudent = new studentEntity();

        // assigns the student from user input to this
        newStudent = studentList.get(studentNumber);

        // shows list of available classes
        List<classEntity> classList = entityManager.createQuery("from classEntity").getResultList();

        // Prompts the user to select the class to add the student to
        System.out.println("Select class to add student to:");

        // resets the counter to 0
        count = 0;

        // Displays the classes
        for (classEntity temp : classList) {
            System.out.println(count + ". " + temp.getSchool() + " " + temp.getYearOffered() + " " + temp.getName() + " " + temp.getCode());
            count++;
        }

        int classNumber = input.nextInt();

        // Creates new class
        classEntity studentClass = new classEntity();

        // Takes class from the list and assigns it to new class
        studentClass = classList.get(classNumber);

        // creates the teacher entity
        teacherEntity newTeacherEntity = new teacherEntity();

        // sets values
        newTeacherEntity.setStudentEntity(newStudent);
        newTeacherEntity.setClassEntity(studentClass);
        newTeacherEntity.setIsCurrentTeacher(1);
        Date date = new Date();
        newTeacherEntity.setHireDate(date.toString());
        newTeacherEntity.setTeacher_id("5369984");

        entityManager.persist(newTeacherEntity);
        entityManager.flush();
        entityManager.getTransaction().commit();

        System.out.println("A new student has been added to the class");
        entityManager.close();
    }


    public static void  removeClass(EntityManagerFactory entityManagerF, Scanner input){
        EntityManager entityManager = entityManagerF.createEntityManager();
        entityManager.getTransaction().begin();

        // creates list of classes
        List<classEntity> classList = entityManager.createQuery("from classEntity").getResultList();

        System.out.println("Select the class to remove:");

        int count = 0;
        // Loops through list of classes
        for (classEntity temp : classList) {
            System.out.println(count + ". " + temp.getSchool() + " " + temp.getYearOffered() + " " + temp.getName() + " " + temp.getCode());
            count++;
        }

        // Prompts the user for class number
        int classNumber = input.nextInt();

        // Deletes FK Constraint
        Query q = entityManager.createQuery("delete teacherEntity where classEntity.id=:p").setParameter("p", classList.get(classNumber).getId());

        // Deletes class
        Query query = entityManager.createQuery(
                "DELETE classEntity c WHERE c.id = :p").setParameter("p", classList.get(classNumber).getId());

		// Updates table
        q.executeUpdate();
        query.executeUpdate();

        entityManager.getTransaction().commit();

        System.out.println("The class has been removed from the curriculum");
    }

    public static void  removeStudent(EntityManagerFactory entityManagerF, Scanner input){
        EntityManager entityManager = entityManagerF.createEntityManager();
        entityManager.getTransaction().begin();

        // Creates list of students
        List<studentEntity> studentList = entityManager.createQuery("from studentEntity").getResultList();

        System.out.println("Select the student to remove:");

        int count = 0;
        for (studentEntity temp : studentList) {
            System.out.println(count + ". " + temp.getFirstname() + " " + temp.getLastname());
            count++;
        }

        // Promps the user for student Number
        int studentNumber = input.nextInt();

        // Deletes the FK Constraint
        Query q = entityManager.createQuery("delete teacherEntity where studentEntity.id=:p").setParameter("p", studentList.get(studentNumber).getId());

        // Deletes the owner
        Query query = entityManager.createQuery(
                "DELETE studentEntity c WHERE c.id = :p").setParameter("p", studentList.get(studentNumber).getId());

        // Updates the table
        q.executeUpdate();
        query.executeUpdate();

        entityManager.getTransaction().commit();

        System.out.println("Student has been removed");

        entityManager.close();
    }

    public static void  removeStudentFromClass(EntityManagerFactory entityManagerF, Scanner input){
        EntityManager entityManager = entityManagerF.createEntityManager();
        entityManager.getTransaction().begin();

        // Creates list of students
        List<studentEntity> studentList = entityManager.createQuery("from studentEntity").getResultList();

        System.out.println("Please select the owner:");

        int count = 0;

        // Loops through all of the students
        for (studentEntity temp : studentList) {
            System.out.println(count + ". " + temp.getFirstname() + " " + temp.getLastname());
            count++;
        }

        // Prompts the user for student Number
        int studentNumber = input.nextInt();

        System.out.println("Select the class to remove from the student:");

        count = 0;

        // Creates list of the student's classes
        List<teacherEntity> carOwnerList = entityManager.createQuery("from teacherEntity WHERE studentEntity.id=:p").setParameter("p",studentList.get(studentNumber).getId()).getResultList();

        for (teacherEntity temp : carOwnerList) {
            System.out.println(count + ". " + temp.getClassEntity().getSchool() + " " + temp.getClassEntity().getYearOffered() + " " + temp.getClassEntity().getName() + " " + temp.getClassEntity().getCode());
            count++;
        }

        // Prompts the user for class to remove from the student
        int classNumber = input.nextInt();

        // Deletes FK Constraint
        Query query = entityManager.createQuery("delete teacherEntity where studentEntity.id=:p AND classEntity.id=:q").setParameter("p", studentList.get(studentNumber).getId()).setParameter("q", carOwnerList.get(classNumber).getClassEntity().getId());

        query.executeUpdate();
        entityManager.getTransaction().commit();

        System.out.println("Removed the student from the class");
        entityManager.close();
    }

}


@JoinTable(
        name="Customer",
        joinColumns =
                @JoinColumn(name="addressId", referencedColumnName = "id"),
        inverseJoinColumns =
                @JoinColumn(name="phoneId", referencedColumnName = "id")
)

