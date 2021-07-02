package model;

import java.util.ArrayList;

public class StudentBasic extends Student {


    public StudentBasic(String pName, String pLastName, Courses pCourse, String pHasRelatives, long pCost, String pTerapy, String pId, ArrayList<ExtraAsignatures> asignatures) {
        super(pName, pLastName, pCourse, pHasRelatives, pCost, pTerapy, pId, asignatures);
    }
    
}
