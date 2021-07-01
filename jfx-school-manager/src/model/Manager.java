package model;

import java.util.ArrayList;

public class Manager {
    ArrayList<Student> allStudents;       
    ArrayList<ExtraAsignatures> testAsignatures = new ArrayList<ExtraAsignatures>();

    public Manager(){
        allStudents = new ArrayList<Student>();
        testAsignatures.add(ExtraAsignatures.DANZA);
        addStudent("Jhon", "Doe", "-", "-", 0L, "NO", "000000", testAsignatures);      
    }

    public Student searchStudent(String id){
        Student founded = null;
        
        for(Student student : allStudents){
            if(student.getId().equals(id)){
                founded = student;
                return founded;
            }
        }
        return null;       
    }

    public void addStudent(String name, String lastName, String course, String hasRelatives, long cost, String terapy, String id, ArrayList<ExtraAsignatures> asignatures){
        StudentBasic newStudent = new StudentBasic(name, lastName, course, hasRelatives, cost, terapy, id, asignatures);
        allStudents.add(newStudent);
    }

    public void removeSubject(Student selectedStudent, ExtraAsignatures asignatureToRemove){
        if(selectedStudent != null){
            selectedStudent.removeSubject(asignatureToRemove);
        }
    }
    //-------------------------------------------------- GETTERS FOR UI --------------------------------------------------
    
    public ArrayList<Courses> getCourses(){
        ArrayList<Courses> avaibleCourses = new ArrayList<Courses>();
        
        for(Courses c : Courses.values()){
            avaibleCourses.add(c);
        }

        return avaibleCourses;
    }

    public ArrayList<ExtraAsignatures> getAsignatures(){
        ArrayList<ExtraAsignatures> avaibleAsignatures = new ArrayList<ExtraAsignatures>();
        
        for(ExtraAsignatures extra : ExtraAsignatures.values()){
            avaibleAsignatures.add(extra);
        }

        return avaibleAsignatures;
    }

    public ArrayList<Student> getStudents(){
        return allStudents;
    }
}
