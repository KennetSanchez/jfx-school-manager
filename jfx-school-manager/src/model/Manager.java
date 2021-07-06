package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Manager {

    //This are the constants to calculate the prices.
    private final static float ELEMENTARY_SCHOOL_COST = 150000f;
    private final static float HIGH_SCHOOL_COST = 165000f;
    private final static float INCLUSION_COST = 225000f;
    private final static float FAMILY_DISCOUNT = 0.1f;

    //This is the cost of each extra asignatures in the enum "ExtraAsignatures"
    private final static float EXTRA_ASIGNATURE_COST = 40000f;

    //This is the position of the last primary course of the school in the enum "Courses", minus 1.
    private final static int LAST_ELEMENTARY_COURSE_INDEX = 5;
    private final static Courses LAST_ELEMENTARY_COURSE = Courses.values()[LAST_ELEMENTARY_COURSE_INDEX];

    //This variables are the negation/afirmation in spanish.
    private final static String AFFIRMATION ="Sí";

    //Serialization file path.
    private final static String filePath = "data/students.txt";

    ArrayList<Student> allStudents;       
    ArrayList<ExtraAsignatures> testAsignatures = new ArrayList<ExtraAsignatures>();

    public Manager(){
        allStudents = new ArrayList<Student>();    
    }


    public void saveData() throws FileNotFoundException, IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath));
        os.writeObject(allStudents);
        os.close();  
    }

    @SuppressWarnings({ "unchecked"})
    public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException{
        File f = new File(filePath);
        if(f.exists()){
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(f));
            allStudents = (ArrayList<Student>) is.readObject();
            is.close();   
        }
       
        
    }

    private float calculateCost(Courses course, ArrayList<ExtraAsignatures> extraAsignatures, String hasTerapy, String hasRelatives){
        float cost = 0;

        //This calculates the cost of the basic asignatures. 
        //There's a special case because inclusion have another price, no matter if it's a high or elementary course.
        if(LAST_ELEMENTARY_COURSE.compareTo(course)>= 0){
            cost = ELEMENTARY_SCHOOL_COST;
        }else if(course == Courses.INCLUSIÓN || hasTerapy.equals(AFFIRMATION)){
            cost = INCLUSION_COST;
        }else{
            cost = HIGH_SCHOOL_COST;
        }

        if(hasRelatives.equals(AFFIRMATION)){
            cost = cost*(1-FAMILY_DISCOUNT);
        }

        //This calculates the extra cost for each extra asignature
        cost += (extraAsignatures.size()*EXTRA_ASIGNATURE_COST);
        return cost;
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

    public void addStudent(String name, String lastName, Courses course, String hasRelatives, String terapy, String id, ArrayList<ExtraAsignatures> asignatures){
        float cost = calculateCost(course, asignatures, terapy, hasRelatives);
        Student newStudent = new Student(name, lastName, course, hasRelatives, cost, terapy, id, asignatures);
        allStudents.add(newStudent);
    }

    public void removeSubject(Student selectedStudent, ExtraAsignatures asignatureToRemove){
        if(selectedStudent != null){
            selectedStudent.removeSubject(asignatureToRemove);
        }
    }

    public void editStudent(Student selectedStudent, String names, String lastNames, Courses course, String hasRelatives, String terapy, String id, ArrayList<ExtraAsignatures> asignatures) {
        float cost = calculateCost(course, asignatures, terapy, hasRelatives);
        selectedStudent.setName(names);
        selectedStudent.setLastName(lastNames);
        selectedStudent.setCost(cost);
        selectedStudent.setCourse(course);
        selectedStudent.setTerapy(terapy);
        selectedStudent.setHasRelatives(hasRelatives);
        selectedStudent.setExtraSubjects(asignatures);
        selectedStudent.setId(id);
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


    public void removeStudent(Student selectedStudent) {
        allStudents.remove(selectedStudent);
    }

}
