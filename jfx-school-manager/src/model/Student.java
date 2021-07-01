package model;

import java.util.ArrayList;

public abstract class Student {

    //This variables are for parsing booleans into spanish Strings.
   // private final static String AFFIRMATION = "Sí";
    private final static String NEGATION = "No";
    
    String hasRelatives;  
    String name, lastName, fullName;
    String course;
    String terapy;
    String id;
    long cost;
    String ows;
    String extraAsignatures =  "---------";

    //Not implemented yet.
    ArrayList<ExtraAsignatures> extraSubjects;
    ArrayList<Long> extraSubjectCost;

    public Student(String pName, String pLastName, String pCourse, String pHasRelatives, long pCost, String pTerapy, String pId, ArrayList<ExtraAsignatures> pAsignatures){
        extraSubjects = new ArrayList<ExtraAsignatures>();
        extraSubjectCost = new ArrayList<Long>();

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

    public void addSubjectCost(Long newCost){
        extraSubjectCost.add(newCost);
    }

    public void removeSubject(ExtraAsignatures subjectToRemove){
        extraSubjects.remove(subjectToRemove);
    }

    public void removeSubjectCost(Long newCost){
        extraSubjectCost.remove(newCost);
    }

    public ArrayList<ExtraAsignatures> getSubject(){
        return extraSubjects;
    }

    public ArrayList<Long> getSubjectCost(){
        return extraSubjectCost;
    }

    //------------------------------------------------------------- GETTERS AND SETTERS -------------------------------------------------------------

    public String getHasRelatives() {
        return this.hasRelatives;
    }

    public void setHasRelatives(String hasRelatives) {
        this.hasRelatives = hasRelatives;
    }

    public long getCost() {
        return this.cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
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
}
