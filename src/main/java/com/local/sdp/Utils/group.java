package com.local.sdp.Utils;

import com.local.sdp.Entity.Result;
import com.local.sdp.Entity.Student;

import java.util.*;

public class group implements Comparable<group> {
    public int groupId;
    public List<Student> groupMembers = new ArrayList<>();
    public double highestCPI;

    public group(){}
    public group(int groupId, List<Student> groupMembers){
        this.groupId = groupId;
        this.groupMembers = groupMembers;
        calculateHighestCPI();
    }

    private void calculateHighestCPI() {
        highestCPI = 0.0;

        for (Student member : groupMembers) {
            for (Result result : member.getResultList()) {
                if (result.getSemNo() == 5 && result.getCpi() > highestCPI) {
                    highestCPI = result.getCpi();
                }
            }
        }
    }

    @Override
    public int compareTo(group o) {
        return Double.compare(o.highestCPI, this.highestCPI);
    }
}