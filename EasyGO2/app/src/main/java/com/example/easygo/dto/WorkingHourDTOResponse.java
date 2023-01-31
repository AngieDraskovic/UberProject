package com.example.easygo.dto;

import java.time.LocalDateTime;

public class WorkingHourDTOResponse {

    private Integer id;
    private int[] start;
    private int[] end;

    public WorkingHourDTOResponse(){}

    public WorkingHourDTOResponse(LocalDateTime start, LocalDateTime end){
        this.start = new int[6];
        this.end = new int[6];
    }

    public WorkingHourDTOResponse(WorkingHourDTOResponse body) {
        this.id = body.id;
        this.start = body.start;
        this.end = body.end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //    public LocalDateTime getStart() {
//        return start;
//    }

    public void setStart(LocalDateTime start) {
        this.start = new int[6];
        this.start[0] = start.getYear();
        this.start[1] = start.getMonthValue();
        this.start[2] = start.getDayOfMonth();
        this.start[3] = start.getHour();
        this.start[4] = start.getMinute();
        this.start[5] = start.getSecond();
    }

//    public LocalDateTime getEnd() {
//        return end;
//    }

    public void setEnd(LocalDateTime end) {
        this.end = new int[6];
        this.end[0] = end.getYear();
        this.end[1] = end.getMonthValue();
        this.end[2] = end.getDayOfMonth();
        this.end[3] = end.getHour();
        this.end[4] = end.getMinute();
        this.end[5] = end.getSecond();
    }
}
