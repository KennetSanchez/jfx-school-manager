package model;

import java.util.ArrayList;

public class StudentInclusion extends Student{

    public StudentInclusion(String pHasRelatives, long pCost, String pName, String pLastName, String pCourse, String pId, ArrayList<ExtraAsignatures> pAsignatures) {
        super(pName, pLastName, pCourse, pHasRelatives, pCost, pCourse, pId, pAsignatures);
    }

}