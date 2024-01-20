package com.local.sdp.Utils;

import com.local.sdp.Entity.Projects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class project implements Comparable<project> {
    int groupId;
    int rank;
    Map<Integer, Projects> projects = new HashMap<>();
    public project(int groupId, int rank, Map<Integer, Projects> projects) {
        this.groupId = groupId;
        this.rank = rank;
        this.projects = projects;
    }

    @Override
    public int compareTo(project o) {
        return this.rank - o.rank;
    }
}
