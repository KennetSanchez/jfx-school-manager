package model;

import java.util.ArrayList;

public abstract class Student {

    //This variables are for parsing booleans into spanish Strings.
   // private final static String AFFIRMATION = "Sí";
    private final static String NEGATION = "No";
    
    String hasRelatives;  
    String name;
    String course;
    String terapy;
    String id;
    long cost;
    String ows;
    String extraAsignatures =  "---------";

    ArrayList<String> asignatures;

    //Not implemented yet.
    ArrayList<String> extraSubjects;
    ArrayList<Long> extraSubjectCost;

    public Student(String pName, String pCourse, String pHasRelatives, long pCost, String pTerapy, String pId, ArrayList<String> pAsignatures){
        cost = pCost;
        name = pName;
        course = pCourse;
        terapy = pTerapy;
        id = pId;
        ows = NEGATION;
        hasRelatives = pHasRelatives;
        asignatures = pAsignatures;

        extraSubjects = new ArrayList<String>();
        extraSubjectCost = new ArrayList<Long>();
    }

    public void addSubject(String newSubject){
        extraSubjects.add(newSubject);
    }

    public void addSubjectCost(Long newCost){
        extraSubjectCost.add(newCost);
    }

    public void removeSubject(String subjectToRemove){
        extraSubjects.remove(subjectToRemove);
    }

    public void removeSubjectCost(Long newCost){
        extraSubjectCost.remove(newCost);
    }

    public ArrayList<String> getSubject(){
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
        
        while(index < asignatures.size()){
            if(index <asignatures.size()-1){
                msg += asignatures.get(index) + ", ";
            }else{
                msg += asignatures.get(index);
            }
            index++;
        }


        return msg;
    }
}
