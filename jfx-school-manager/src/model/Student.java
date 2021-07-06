package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{

    //This variables are for parsing booleans into spanish Strings.
   // private final static String AFFIRMATION = "Sí";
    private final static String NEGATION = "No";
    
    String hasRelatives;  
    String name, lastName, fullName;
    Courses course;
    String terapy;
    String id;
    float cost;
    String ows;
    String extraAsignatures =  "---------";
    private final static String SEPARATOR_STRING = "/";

    //Not implemented yet.
    ArrayList<ExtraAsignatures> extraSubjects;

    public Student(String pName, String pLastName, Courses pCourse, String pHasRelatives, float pCost, String pTerapy, String pId, ArrayList<ExtraAsignatures> pAsignatures){
        extraSubjects = new ArrayList<ExtraAsignatures>();

        cost = pCost;
        name = pName;
        lastName = pLastName;
        fullName = pName + " " + pLastName;
        course = pCourse;
        terapy = pTerapy;
        id = pId;
        ows = NEGATION;
        hasRelatives = pHasRelatives;
        extraSubjects = pAsignatures;        
    }

    public void addSubject(ExtraAsignatures newSubject){
        extraSubjects.add(newSubject);
    }

    public void removeSubject(ExtraAsignatures subjectToRemove){
        extraSubjects.remove(subjectToRemove);
    }


    public ArrayList<ExtraAsignatures> getSubject(){
        return extraSubjects;
    }

    //------------------------------------------------------------- GETTERS AND SETTERS -------------------------------------------------------------

    public String getHasRelatives() {
        return this.hasRelatives;
    }

    public void setHasRelatives(String hasRelatives) {
        this.hasRelatives = hasRelatives;
    }

    public float getCost() {
        return this.cost;
    }

    public void setCost(float cost2) {
        this.cost = cost2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Courses getCourse() {
        return this.course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public String getId(){
        return id;
    }

    public void setId(String newId){
        id = newId;
    }

    public String getOws(){
        return ows;
    }

    public void setOws(String newState){
        ows = newState;
    }

    public String getExtrasString(){
        String msg = "";
        if(extraSubjects.size() != 0){
            for(int i = 0; i < extraSubjects.size() ; i++){
                msg += extraSubjects.get(i);
            }
            extraAsignatures = msg;
        }
        return extraAsignatures;
    }

    public String getAsignaturesString(){
        String msg = "";
        int index = 0;
        
        while(index < extraSubjects.size()){
            if(index <extraSubjects.size()-1){
                msg += extraSubjects.get(index) + ", ";
            }else{
                msg += extraSubjects.get(index);
            }
            index++;
        }
        return msg;
    }

    public String getHasTerapy(){
        return terapy;
    }

    public String getLastName(){
        return lastName;
    }

    public void setTerapy(String pTerapy) {
        terapy = pTerapy;
    }

    public void setExtraSubjects(ArrayList<ExtraAsignatures> pAsignatures) {
        extraSubjects = pAsignatures;
    }

    public void setLastName(String pLastNames) {
        lastName = pLastNames;
    }

    public String getFullName(){
        return fullName;
    }

    public String toString(){
        String msg = "";

        msg = name + SEPARATOR_STRING + lastName + SEPARATOR_STRING + course + SEPARATOR_STRING + id + SEPARATOR_STRING + terapy;
        msg += SEPARATOR_STRING + hasRelatives + SEPARATOR_STRING + ows + SEPARATOR_STRING + extraAsignatures.toString();

        return msg;
    }
}
