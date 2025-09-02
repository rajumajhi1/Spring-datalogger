/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonFormat
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  lombok.Generated
 */
package com.datalogger.faults.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.Generated;

@Entity
public class Fault {
    @Id
    @Column(name="DLNO")
    private Integer dlNo;
    @Column(name="FAULTNO")
    private Integer faultNo;
    @Column(name="FLTMSG")
    private String fltMsg;
    @Column(name="FLTINFO")
    private String fltInfo;
    @Column(name="RPERSONID")
    private String rPersonId;
    @Column(name="CPERSONID")
    private String cPersonId;
    @Column(name="SYSTIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Integer sysTime;
    @Column(name="SYSYEAR")
    private Integer sysYear;
    @Column(name="DLTIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Integer dlTime;
    @Column(name="DLYEAR")
    private Integer dlYear;
    @Column(name="NOTICEDTIME")
    private Integer noticedTime;
    @Column(name="NOTICEDYEAR")
    private Integer noticedYear;
    @Column(name="CLEARED")
    private Integer cleared;
    @Column(name="CLEAREDYEAR")
    private Integer clearedYear;
    @Column(name="FAULTLEVEL")
    private String faultLevel;
    @Column(name="FAULTTYPE")
    private String faultType;
    @Column(name="FAULTPSEUDOTYPE")
    private String faultPseudoType;
    @Column(name="CKTFILENAME")
    private String cktFileName;
    @Column(name="CKTSTATFILENAME")
    private String cktStatFileName;
    @Column(name="LINKMESSAGE")
    private String linkMessage;
    @Column(name="FAULTSTATUS")
    private String faultStatus;
    @Column(name="DEPT")
    private String dept;
    @Column(name="CATEGORY")
    private Integer category;
    @Column(name="CLASSIFICATION")
    private Integer classification;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="CREATEDTIME")
    private Timestamp createdTime;
    @Column(name="FLTREASON")
    private String fltReason;
    @Column(name="REMARKS")
    private String remarks;
    @Column(name="CLIENT_ID")
    private Integer clientId;
    @Column(name="EFLTINFO")
    private String eFltInfo;
    @Column(name="TRAINNO")
    private String trainNo;
    @Column(name="ASMNAME")
    private String asmName;
    @Column(name="REASON_DELAY_OR_NOTSET")
    private String reasonDelayOrNotSet;

    public Fault() {
    }

    public Fault(Integer dlNo, Integer faultNo, String fltMsg, String fltInfo, String rPersonId, String cPersonId, Integer sysTime, Integer sysYear, Integer dlTime, Integer dlYear, Integer noticedTime, Integer noticedYear, Integer cleared, Integer clearedYear, String faultLevel, String faultType, String faultPseudoType, String cktFileName, String cktStatFileName, String linkMessage, String faultStatus, String dept, Integer category, Integer classification, Timestamp createdTime, String fltReason, String remarks, Integer clientId, String eFltInfo, String trainNo, String asmName, String reasonDelayOrNotSet) {
        this.dlNo = dlNo;
        this.faultNo = faultNo;
        this.fltMsg = fltMsg;
        this.fltInfo = fltInfo;
        this.rPersonId = rPersonId;
        this.cPersonId = cPersonId;
        this.sysTime = sysTime;
        this.sysYear = sysYear;
        this.dlTime = dlTime;
        this.dlYear = dlYear;
        this.noticedTime = noticedTime;
        this.noticedYear = noticedYear;
        this.cleared = cleared;
        this.clearedYear = clearedYear;
        this.faultLevel = faultLevel;
        this.faultType = faultType;
        this.faultPseudoType = faultPseudoType;
        this.cktFileName = cktFileName;
        this.cktStatFileName = cktStatFileName;
        this.linkMessage = linkMessage;
        this.faultStatus = faultStatus;
        this.dept = dept;
        this.category = category;
        this.classification = classification;
        this.createdTime = createdTime;
        this.fltReason = fltReason;
        this.remarks = remarks;
        this.clientId = clientId;
        this.eFltInfo = eFltInfo;
        this.trainNo = trainNo;
        this.asmName = asmName;
        this.reasonDelayOrNotSet = reasonDelayOrNotSet;
    }

    public Fault(long l, String s, String s1, String s2, String s3, String s4, String s5, String s6) {
    }

    public Integer getDlNo() {
        return this.dlNo;
    }

    public void setDlNo(Integer dlNo) {
        this.dlNo = dlNo;
    }

    public Integer getFaultNo() {
        return this.faultNo;
    }

    public void setFaultNo(Integer faultNo) {
        this.faultNo = faultNo;
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

    public String getrPersonId() {
        return this.rPersonId;
    }

    public void setrPersonId(String rPersonId) {
        this.rPersonId = rPersonId;
    }

    public String getcPersonId() {
        return this.cPersonId;
    }

    public void setcPersonId(String cPersonId) {
        this.cPersonId = cPersonId;
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

    public Integer getDlYear() {
        return this.dlYear;
    }

    public void setDlYear(Integer dlYear) {
        this.dlYear = dlYear;
    }

    public Integer getNoticedTime() {
        return this.noticedTime;
    }

    public void setNoticedTime(Integer noticedTime) {
        this.noticedTime = noticedTime;
    }

    public Integer getNoticedYear() {
        return this.noticedYear;
    }

    public void setNoticedYear(Integer noticedYear) {
        this.noticedYear = noticedYear;
    }

    public Integer getCleared() {
        return this.cleared;
    }

    public void setCleared(Integer cleared) {
        this.cleared = cleared;
    }

    public Integer getClearedYear() {
        return this.clearedYear;
    }

    public void setClearedYear(Integer clearedYear) {
        this.clearedYear = clearedYear;
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

    public String getFaultPseudoType() {
        return this.faultPseudoType;
    }

    public void setFaultPseudoType(String faultPseudoType) {
        this.faultPseudoType = faultPseudoType;
    }

    public String getCktFileName() {
        return this.cktFileName;
    }

    public void setCktFileName(String cktFileName) {
        this.cktFileName = cktFileName;
    }

    public String getCktStatFileName() {
        return this.cktStatFileName;
    }

    public void setCktStatFileName(String cktStatFileName) {
        this.cktStatFileName = cktStatFileName;
    }

    public String getLinkMessage() {
        return this.linkMessage;
    }

    public void setLinkMessage(String linkMessage) {
        this.linkMessage = linkMessage;
    }

    public String getFaultStatus() {
        return this.faultStatus;
    }

    public void setFaultStatus(String faultStatus) {
        this.faultStatus = faultStatus;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getCategory() {
        return this.category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getClassification() {
        return this.classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public Timestamp getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getFltReason() {
        return this.fltReason;
    }

    public void setFltReason(String fltReason) {
        this.fltReason = fltReason;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String geteFltInfo() {
        return this.eFltInfo;
    }

    public void seteFltInfo(String eFltInfo) {
        this.eFltInfo = eFltInfo;
    }

    public String getTrainNo() {
        return this.trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getAsmName() {
        return this.asmName;
    }

    public void setAsmName(String asmName) {
        this.asmName = asmName;
    }

    public String getReasonDelayOrNotSet() {
        return this.reasonDelayOrNotSet;
    }

    public void setReasonDelayOrNotSet(String reasonDelayOrNotSet) {
        this.reasonDelayOrNotSet = reasonDelayOrNotSet;
    }

    public String toString() {
        return "FaultService{dlNo=" + this.dlNo + ", faultNo=" + this.faultNo + ", fltMsg='" + this.fltMsg + "', fltInfo='" + this.fltInfo + "', rPersonId='" + this.rPersonId + "', cPersonId='" + this.cPersonId + "', sysTime=" + this.sysTime + ", sysYear=" + this.sysYear + ", dlTime=" + this.dlTime + ", dlYear=" + this.dlYear + ", noticedTime=" + this.noticedTime + ", noticedYear=" + this.noticedYear + ", cleared=" + this.cleared + ", clearedYear=" + this.clearedYear + ", faultLevel='" + this.faultLevel + "', faultType='" + this.faultType + "', faultPseudoType='" + this.faultPseudoType + "', cktFileName='" + this.cktFileName + "', cktStatFileName='" + this.cktStatFileName + "', linkMessage='" + this.linkMessage + "', faultStatus='" + this.faultStatus + "', dept='" + this.dept + "', category=" + this.category + ", classification=" + this.classification + ", createdTime=" + String.valueOf(this.createdTime) + ", fltReason='" + this.fltReason + "', remarks='" + this.remarks + "', clientId=" + this.clientId + ", eFltInfo='" + this.eFltInfo + "', trainNo='" + this.trainNo + "', asmName='" + this.asmName + "', reasonDelayOrNotSet='" + this.reasonDelayOrNotSet + "'}";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Fault)) {
            return false;
        }
        Fault other = (Fault)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Integer this$dlNo = this.getDlNo();
        Integer other$dlNo = other.getDlNo();
        if (this$dlNo == null ? other$dlNo != null : !((Object)this$dlNo).equals(other$dlNo)) {
            return false;
        }
        Integer this$faultNo = this.getFaultNo();
        Integer other$faultNo = other.getFaultNo();
        if (this$faultNo == null ? other$faultNo != null : !((Object)this$faultNo).equals(other$faultNo)) {
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
        Integer this$dlYear = this.getDlYear();
        Integer other$dlYear = other.getDlYear();
        if (this$dlYear == null ? other$dlYear != null : !((Object)this$dlYear).equals(other$dlYear)) {
            return false;
        }
        Integer this$noticedTime = this.getNoticedTime();
        Integer other$noticedTime = other.getNoticedTime();
        if (this$noticedTime == null ? other$noticedTime != null : !((Object)this$noticedTime).equals(other$noticedTime)) {
            return false;
        }
        Integer this$noticedYear = this.getNoticedYear();
        Integer other$noticedYear = other.getNoticedYear();
        if (this$noticedYear == null ? other$noticedYear != null : !((Object)this$noticedYear).equals(other$noticedYear)) {
            return false;
        }
        Integer this$cleared = this.getCleared();
        Integer other$cleared = other.getCleared();
        if (this$cleared == null ? other$cleared != null : !((Object)this$cleared).equals(other$cleared)) {
            return false;
        }
        Integer this$clearedYear = this.getClearedYear();
        Integer other$clearedYear = other.getClearedYear();
        if (this$clearedYear == null ? other$clearedYear != null : !((Object)this$clearedYear).equals(other$clearedYear)) {
            return false;
        }
        Integer this$category = this.getCategory();
        Integer other$category = other.getCategory();
        if (this$category == null ? other$category != null : !((Object)this$category).equals(other$category)) {
            return false;
        }
        Integer this$classification = this.getClassification();
        Integer other$classification = other.getClassification();
        if (this$classification == null ? other$classification != null : !((Object)this$classification).equals(other$classification)) {
            return false;
        }
        Integer this$clientId = this.getClientId();
        Integer other$clientId = other.getClientId();
        if (this$clientId == null ? other$clientId != null : !((Object)this$clientId).equals(other$clientId)) {
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
        String this$rPersonId = this.getrPersonId();
        String other$rPersonId = other.getrPersonId();
        if (this$rPersonId == null ? other$rPersonId != null : !this$rPersonId.equals(other$rPersonId)) {
            return false;
        }
        String this$cPersonId = this.getcPersonId();
        String other$cPersonId = other.getcPersonId();
        if (this$cPersonId == null ? other$cPersonId != null : !this$cPersonId.equals(other$cPersonId)) {
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
        String this$faultPseudoType = this.getFaultPseudoType();
        String other$faultPseudoType = other.getFaultPseudoType();
        if (this$faultPseudoType == null ? other$faultPseudoType != null : !this$faultPseudoType.equals(other$faultPseudoType)) {
            return false;
        }
        String this$cktFileName = this.getCktFileName();
        String other$cktFileName = other.getCktFileName();
        if (this$cktFileName == null ? other$cktFileName != null : !this$cktFileName.equals(other$cktFileName)) {
            return false;
        }
        String this$cktStatFileName = this.getCktStatFileName();
        String other$cktStatFileName = other.getCktStatFileName();
        if (this$cktStatFileName == null ? other$cktStatFileName != null : !this$cktStatFileName.equals(other$cktStatFileName)) {
            return false;
        }
        String this$linkMessage = this.getLinkMessage();
        String other$linkMessage = other.getLinkMessage();
        if (this$linkMessage == null ? other$linkMessage != null : !this$linkMessage.equals(other$linkMessage)) {
            return false;
        }
        String this$faultStatus = this.getFaultStatus();
        String other$faultStatus = other.getFaultStatus();
        if (this$faultStatus == null ? other$faultStatus != null : !this$faultStatus.equals(other$faultStatus)) {
            return false;
        }
        String this$dept = this.getDept();
        String other$dept = other.getDept();
        if (this$dept == null ? other$dept != null : !this$dept.equals(other$dept)) {
            return false;
        }
        Timestamp this$createdTime = this.getCreatedTime();
        Timestamp other$createdTime = other.getCreatedTime();
        if (this$createdTime == null ? other$createdTime != null : !((Object)this$createdTime).equals(other$createdTime)) {
            return false;
        }
        String this$fltReason = this.getFltReason();
        String other$fltReason = other.getFltReason();
        if (this$fltReason == null ? other$fltReason != null : !this$fltReason.equals(other$fltReason)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        if (this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks)) {
            return false;
        }
        String this$eFltInfo = this.geteFltInfo();
        String other$eFltInfo = other.geteFltInfo();
        if (this$eFltInfo == null ? other$eFltInfo != null : !this$eFltInfo.equals(other$eFltInfo)) {
            return false;
        }
        String this$trainNo = this.getTrainNo();
        String other$trainNo = other.getTrainNo();
        if (this$trainNo == null ? other$trainNo != null : !this$trainNo.equals(other$trainNo)) {
            return false;
        }
        String this$asmName = this.getAsmName();
        String other$asmName = other.getAsmName();
        if (this$asmName == null ? other$asmName != null : !this$asmName.equals(other$asmName)) {
            return false;
        }
        String this$reasonDelayOrNotSet = this.getReasonDelayOrNotSet();
        String other$reasonDelayOrNotSet = other.getReasonDelayOrNotSet();
        return !(this$reasonDelayOrNotSet == null ? other$reasonDelayOrNotSet != null : !this$reasonDelayOrNotSet.equals(other$reasonDelayOrNotSet));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Fault;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $dlNo = this.getDlNo();
        result = result * 59 + ($dlNo == null ? 43 : ((Object)$dlNo).hashCode());
        Integer $faultNo = this.getFaultNo();
        result = result * 59 + ($faultNo == null ? 43 : ((Object)$faultNo).hashCode());
        Integer $sysTime = this.getSysTime();
        result = result * 59 + ($sysTime == null ? 43 : ((Object)$sysTime).hashCode());
        Integer $sysYear = this.getSysYear();
        result = result * 59 + ($sysYear == null ? 43 : ((Object)$sysYear).hashCode());
        Integer $dlTime = this.getDlTime();
        result = result * 59 + ($dlTime == null ? 43 : ((Object)$dlTime).hashCode());
        Integer $dlYear = this.getDlYear();
        result = result * 59 + ($dlYear == null ? 43 : ((Object)$dlYear).hashCode());
        Integer $noticedTime = this.getNoticedTime();
        result = result * 59 + ($noticedTime == null ? 43 : ((Object)$noticedTime).hashCode());
        Integer $noticedYear = this.getNoticedYear();
        result = result * 59 + ($noticedYear == null ? 43 : ((Object)$noticedYear).hashCode());
        Integer $cleared = this.getCleared();
        result = result * 59 + ($cleared == null ? 43 : ((Object)$cleared).hashCode());
        Integer $clearedYear = this.getClearedYear();
        result = result * 59 + ($clearedYear == null ? 43 : ((Object)$clearedYear).hashCode());
        Integer $category = this.getCategory();
        result = result * 59 + ($category == null ? 43 : ((Object)$category).hashCode());
        Integer $classification = this.getClassification();
        result = result * 59 + ($classification == null ? 43 : ((Object)$classification).hashCode());
        Integer $clientId = this.getClientId();
        result = result * 59 + ($clientId == null ? 43 : ((Object)$clientId).hashCode());
        String $fltMsg = this.getFltMsg();
        result = result * 59 + ($fltMsg == null ? 43 : $fltMsg.hashCode());
        String $fltInfo = this.getFltInfo();
        result = result * 59 + ($fltInfo == null ? 43 : $fltInfo.hashCode());
        String $rPersonId = this.getrPersonId();
        result = result * 59 + ($rPersonId == null ? 43 : $rPersonId.hashCode());
        String $cPersonId = this.getcPersonId();
        result = result * 59 + ($cPersonId == null ? 43 : $cPersonId.hashCode());
        String $faultLevel = this.getFaultLevel();
        result = result * 59 + ($faultLevel == null ? 43 : $faultLevel.hashCode());
        String $faultType = this.getFaultType();
        result = result * 59 + ($faultType == null ? 43 : $faultType.hashCode());
        String $faultPseudoType = this.getFaultPseudoType();
        result = result * 59 + ($faultPseudoType == null ? 43 : $faultPseudoType.hashCode());
        String $cktFileName = this.getCktFileName();
        result = result * 59 + ($cktFileName == null ? 43 : $cktFileName.hashCode());
        String $cktStatFileName = this.getCktStatFileName();
        result = result * 59 + ($cktStatFileName == null ? 43 : $cktStatFileName.hashCode());
        String $linkMessage = this.getLinkMessage();
        result = result * 59 + ($linkMessage == null ? 43 : $linkMessage.hashCode());
        String $faultStatus = this.getFaultStatus();
        result = result * 59 + ($faultStatus == null ? 43 : $faultStatus.hashCode());
        String $dept = this.getDept();
        result = result * 59 + ($dept == null ? 43 : $dept.hashCode());
        Timestamp $createdTime = this.getCreatedTime();
        result = result * 59 + ($createdTime == null ? 43 : ((Object)$createdTime).hashCode());
        String $fltReason = this.getFltReason();
        result = result * 59 + ($fltReason == null ? 43 : $fltReason.hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        String $eFltInfo = this.geteFltInfo();
        result = result * 59 + ($eFltInfo == null ? 43 : $eFltInfo.hashCode());
        String $trainNo = this.getTrainNo();
        result = result * 59 + ($trainNo == null ? 43 : $trainNo.hashCode());
        String $asmName = this.getAsmName();
        result = result * 59 + ($asmName == null ? 43 : $asmName.hashCode());
        String $reasonDelayOrNotSet = this.getReasonDelayOrNotSet();
        result = result * 59 + ($reasonDelayOrNotSet == null ? 43 : $reasonDelayOrNotSet.hashCode());
        return result;
    }
}
