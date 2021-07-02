package model;

import java.util.ArrayList;

public class StudentInclusion extends Student{

    public StudentInclusion(String pHasRelatives, Float pCost, String pName, String pLastName, Courses pCourse, String pId, String pTerapy, ArrayList<ExtraAsignatures> pAsignatures) {
        super(pName, pLastName, pCourse, pHasRelatives, pCost, pTerapy, pId, pAsignatures);
    }

}