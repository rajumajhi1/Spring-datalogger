/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonFormat
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  jakarta.persistence.Temporal
 *  jakarta.persistence.TemporalType
 *  lombok.Generated
 */
package com.datalogger.faults.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Generated;

public class FaultDTO {
    @JsonProperty(value="dlStation")
    private String dlStation;
    @JsonProperty(value="dlName")
    private String dlName;
    @JsonProperty(value="fltMsg")
    private String fltMsg;
    @JsonProperty(value="fltInfo")
    private String fltInfo;
    @JsonProperty(value="sysTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Integer sysTime;
    @JsonProperty(value="sysYear")
    private Integer sysYear;
    @JsonProperty(value="dlTime")
    private Integer dlTime;
    @JsonProperty(value="faultLevel")
    private String faultLevel;
    @JsonProperty(value="faultType")
    private String faultType;
    @JsonProperty(value="createdTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonProperty(value="sysTimestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sysTimestamp;
    @JsonProperty(value="dlTimestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dlTimestamp;

    public FaultDTO(String dlStation, String dlName, String fltMsg, String fltInfo, int sysTime, Integer sysYear, Integer dlTime, String faultLevel, String faultType, LocalDateTime createdTime, LocalDateTime sysTimestamp, LocalDateTime dlTimestamp) {
        this.dlStation = dlStation;
        this.dlName = dlName;
        this.fltMsg = fltMsg;
        this.fltInfo = fltInfo;
        this.sysTime = sysTime;
        this.sysYear = sysYear;
        this.dlTime = dlTime;
        this.faultLevel = faultLevel;
        this.faultType = faultType;
        this.createdTime = createdTime;
        this.sysTimestamp = sysTimestamp;
        this.dlTimestamp = dlTimestamp;
    }

    public FaultDTO(String dlStation, String dlName, String fltMsg, Integer sysTime, String fltInfo, Integer sysYear, Integer dlTime, String faultLevel, String faultType, LocalDateTime createdTime, LocalDateTime sysTimestamp, LocalDateTime dlTimestamp) {
        this.dlStation = dlStation;
        this.dlName = dlName;
        this.fltMsg = fltMsg;
        this.sysTime = sysTime;
        this.fltInfo = fltInfo;
        this.sysYear = sysYear;
        this.dlTime = dlTime;
        this.faultLevel = faultLevel;
        this.faultType = faultType;
        this.createdTime = createdTime;
        this.sysTimestamp = sysTimestamp;
        this.dlTimestamp = dlTimestamp;
    }

    public FaultDTO() {
    }

    public String getDlStation() {
        return this.dlStation;
    }

    public void setDlStation(String dlStation) {
        this.dlStation = dlStation;
    }

    public String getDlName() {
        return this.dlName;
    }

    public void setDlName(String dlName) {
        this.dlName = dlName;
    }

    public String getFltMsg() {
        return this.fltMsg;
    }

    public void setFltMsg(String fltMsg) {
        this.fltMsg = fltMsg;
    }

    public String getFltInfo() {
        return this.fltInfo;
    }

    public void setFltInfo(String fltInfo) {
        this.fltInfo = fltInfo;
    }

    public Integer getSysTime() {
        return this.sysTime;
    }

    public void setSysTime(Integer sysTime) {
        this.sysTime = sysTime;
    }

    public Integer getSysYear() {
        return this.sysYear;
    }

    public void setSysYear(Integer sysYear) {
        this.sysYear = sysYear;
    }

    public Integer getDlTime() {
        return this.dlTime;
    }

    public void setDlTime(Integer dlTime) {
        this.dlTime = dlTime;
    }

    public String getFaultLevel() {
        return this.faultLevel;
    }

    public void setFaultLevel(String faultLevel) {
        this.faultLevel = faultLevel;
    }

    public String getFaultType() {
        return this.faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getSysTimestamp() {
        return this.sysTimestamp;
    }

    public void setSysTimestamp(LocalDateTime sysTimestamp) {
        this.sysTimestamp = sysTimestamp;
    }

    public LocalDateTime getDlTimestamp() {
        return this.dlTimestamp;
    }

    public void setDlTimestamp(LocalDateTime dlTimestamp) {
        this.dlTimestamp = dlTimestamp;
    }

    public String toString() {
        return "FaultDTO{dlStation='" + this.dlStation + "', dlName='" + this.dlName + "', fltMsg='" + this.fltMsg + "', fltInfo='" + this.fltInfo + "', sysTime=" + this.sysTime + ", sysYear=" + this.sysYear + ", dlTime=" + this.dlTime + ", faultLevel='" + this.faultLevel + "', faultType='" + this.faultType + "', createdTime=" + String.valueOf(this.createdTime) + "}";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FaultDTO)) {
            return false;
        }
        FaultDTO other = (FaultDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Integer this$sysTime = this.getSysTime();
        Integer other$sysTime = other.getSysTime();
        if (this$sysTime == null ? other$sysTime != null : !((Object)this$sysTime).equals(other$sysTime)) {
            return false;
        }
        Integer this$sysYear = this.getSysYear();
        Integer other$sysYear = other.getSysYear();
        if (this$sysYear == null ? other$sysYear != null : !((Object)this$sysYear).equals(other$sysYear)) {
            return false;
        }
        Integer this$dlTime = this.getDlTime();
        Integer other$dlTime = other.getDlTime();
        if (this$dlTime == null ? other$dlTime != null : !((Object)this$dlTime).equals(other$dlTime)) {
            return false;
        }
        String this$dlStation = this.getDlStation();
        String other$dlStation = other.getDlStation();
        if (this$dlStation == null ? other$dlStation != null : !this$dlStation.equals(other$dlStation)) {
            return false;
        }
        String this$dlName = this.getDlName();
        String other$dlName = other.getDlName();
        if (this$dlName == null ? other$dlName != null : !this$dlName.equals(other$dlName)) {
            return false;
        }
        String this$fltMsg = this.getFltMsg();
        String other$fltMsg = other.getFltMsg();
        if (this$fltMsg == null ? other$fltMsg != null : !this$fltMsg.equals(other$fltMsg)) {
            return false;
        }
        String this$fltInfo = this.getFltInfo();
        String other$fltInfo = other.getFltInfo();
        if (this$fltInfo == null ? other$fltInfo != null : !this$fltInfo.equals(other$fltInfo)) {
            return false;
        }
        String this$faultLevel = this.getFaultLevel();
        String other$faultLevel = other.getFaultLevel();
        if (this$faultLevel == null ? other$faultLevel != null : !this$faultLevel.equals(other$faultLevel)) {
            return false;
        }
        String this$faultType = this.getFaultType();
        String other$faultType = other.getFaultType();
        if (this$faultType == null ? other$faultType != null : !this$faultType.equals(other$faultType)) {
            return false;
        }
        LocalDateTime this$createdTime = this.getCreatedTime();
        LocalDateTime other$createdTime = other.getCreatedTime();
        return !(this$createdTime == null ? other$createdTime != null : !this$createdTime.equals(other$createdTime));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof FaultDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $sysTime = this.getSysTime();
        result = result * 59 + ($sysTime == null ? 43 : ((Object)$sysTime).hashCode());
        Integer $sysYear = this.getSysYear();
        result = result * 59 + ($sysYear == null ? 43 : ((Object)$sysYear).hashCode());
        Integer $dlTime = this.getDlTime();
        result = result * 59 + ($dlTime == null ? 43 : ((Object)$dlTime).hashCode());
        String $dlStation = this.getDlStation();
        result = result * 59 + ($dlStation == null ? 43 : $dlStation.hashCode());
        String $dlName = this.getDlName();
        result = result * 59 + ($dlName == null ? 43 : $dlName.hashCode());
        String $fltMsg = this.getFltMsg();
        result = result * 59 + ($fltMsg == null ? 43 : $fltMsg.hashCode());
        String $fltInfo = this.getFltInfo();
        result = result * 59 + ($fltInfo == null ? 43 : $fltInfo.hashCode());
        String $faultLevel = this.getFaultLevel();
        result = result * 59 + ($faultLevel == null ? 43 : $faultLevel.hashCode());
        String $faultType = this.getFaultType();
        result = result * 59 + ($faultType == null ? 43 : $faultType.hashCode());
        LocalDateTime $createdTime = this.getCreatedTime();
        result = result * 59 + ($createdTime == null ? 43 : $createdTime.hashCode());
        return result;
    }
}
