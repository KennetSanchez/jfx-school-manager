package model;

import java.util.ArrayList;

public class StudentInclusion extends Student{

    public StudentInclusion(String pHasRelatives, long pCost, String pName, String pCourse, String pId, ArrayList<String> pAsignatures) {
        super(pName, pCourse, pHasRelatives, pCost, pCourse, pId, pAsignatures);
    }

}