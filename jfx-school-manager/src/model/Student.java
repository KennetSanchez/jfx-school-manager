package model;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class Student {
    String hasRelatives;
    long cost;
    String name;
    String course;
    Calendar payDate;

    ArrayList<String> extraSubjects;
    ArrayList<Long> extraSubjectCost;

    public Student(boolean pHasRelatives, long pCost, String pName, String pCourse, Calendar pPayDate){
        if(pHasRelatives){
            hasRelatives = "Sí";
        }else{
            hasRelatives = "No";
        }

        cost = pCost;
        name = pName;
        course = pCourse;
        payDate = pPayDate;

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

    public Calendar getPayDate() {
        return this.payDate;
    }

    public void setPayDate(Calendar payDate) {
        this.payDate = payDate;
    }
}
