package model;

import java.util.ArrayList;

public class Manager {
    ArrayList<Student> allStudents;

    public Manager(){
        allStudents = new ArrayList<Student>();
    }

    public ArrayList<Student> searchStudent(String id){
        ArrayList<Student> founded = new ArrayList<Student>();
        
        for(int i = 0 ; i < allStudents.size() ; i++){
            if(allStudents.get(i).getId().equals(id)){
                Student studentFounded = allStudents.get(i);
                founded.add(studentFounded);
            }
        }

        return founded;
    }

    public void addStudent(String name, String lastName, String course, String hasRelatives, long cost, String terapy, String id, ArrayList<String> asignatures){
        StudentBasic newStudent = new StudentBasic(name, lastName, course, hasRelatives, cost, terapy, id, asignatures);
        allStudents.add(newStudent);
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
