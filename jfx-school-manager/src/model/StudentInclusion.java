package model;

import java.util.Calendar;

public class StudentInclusion extends Student{

    public StudentInclusion(String pHasRelatives, long pCost, String pName, String pCourse, Calendar pPayDate) {
        super(pName, pCourse, pHasRelatives, pCost, pCourse);
    }

}