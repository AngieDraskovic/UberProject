package com.example.easygo.dto;

import com.example.easygo.model.Rejection;

import java.time.LocalDateTime;

public class RejectionDTO {
    private String reason;
    private LocalDateTime timeOfRejection;

    public RejectionDTO(Rejection rejection) {
        this.reason = rejection.getReason();
        this.timeOfRejection = rejection.getTime();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimeOfRejection() {
        return timeOfRejection;
    }

    public void setTimeOfRejection(LocalDateTime timeOfRejection) {
        this.timeOfRejection = timeOfRejection;
    }
}
