package com.datalogger.faults.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class FaultDateRangeDTO {
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromDateTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toDateTime;
    
    public FaultDateRangeDTO() {
    }
    
    public FaultDateRangeDTO(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }
    
    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }
    
    public void setFromDateTime(LocalDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }
    
    public LocalDateTime getToDateTime() {
        return toDateTime;
    }
    
    public void setToDateTime(LocalDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }
    
    @Override
    public String toString() {
        return "FaultDateRangeDTO{" +
                "fromDateTime=" + fromDateTime +
                ", toDateTime=" + toDateTime +
                '}';
    }
}