package com.example.spring88x2.service.dto;

import java.util.List;

public class IncrementAgeDTO {
    private List<Integer> userIds;

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
