package model;

import java.util.ArrayList;

public class Manager {
    ArrayList<Student> allStudents;

    public Manager(){
        allStudents = new ArrayList<Student>();
    }

    public ArrayList<Student> searchStudent(String name){
        ArrayList<Student> founded = new ArrayList<Student>();
        
        for(int i = 0 ; i < allStudents.size() ; i++){
            if(allStudents.get(i).getName().equals(name)){
                Student studentFounded = allStudents.get(i);
                founded.add(studentFounded);
            }
        }

        return founded;
    }

    public void addStudent(String name, String course, String hasRelatives, long cost, String terapy){
        StudentBasic newStudent = new StudentBasic(name, course, hasRelatives, cost, terapy);
        allStudents.add(newStudent);
    }
}
