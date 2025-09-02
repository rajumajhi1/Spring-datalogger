/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  lombok.Generated
 */
package com.datalogger.faults.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Generated;

@Entity
public class RhSetup {
    @Id
    @Column(name="DLNO")
    private Integer dlNo;
    @Column(name="DLNAME")
    private String dlname;
    @Column(name="DLSTATION")
    private String dlStation;

    public RhSetup() {
    }

    public RhSetup(Integer dlNo, String dlname, String dlStation) {
        this.dlNo = dlNo;
        this.dlname = dlname;
        this.dlStation = dlStation;
    }

    public Integer getDlNo() {
        return this.dlNo;
    }

    public void setDlNo(Integer dlNo) {
        this.dlNo = dlNo;
    }

    public String getDlStation() {
        return this.dlStation;
    }

    public void setDlStation(String dlStation) {
        this.dlStation = dlStation;
    }

    public String getDlname() {
        return this.dlname;
    }

    public void setDlname(String dlname) {
        this.dlname = dlname;
    }

    public String toString() {
        return "RhSetup{dlNo=" + this.dlNo + ", dlname='" + this.dlname + "', dlStation='" + this.dlStation + "'}";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RhSetup)) {
            return false;
        }
        RhSetup other = (RhSetup)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Integer this$dlNo = this.getDlNo();
        Integer other$dlNo = other.getDlNo();
        if (this$dlNo == null ? other$dlNo != null : !((Object)this$dlNo).equals(other$dlNo)) {
            return false;
        }
        String this$dlname = this.getDlname();
        String other$dlname = other.getDlname();
        if (this$dlname == null ? other$dlname != null : !this$dlname.equals(other$dlname)) {
            return false;
        }
        String this$dlStation = this.getDlStation();
        String other$dlStation = other.getDlStation();
        return !(this$dlStation == null ? other$dlStation != null : !this$dlStation.equals(other$dlStation));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof RhSetup;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $dlNo = this.getDlNo();
        result = result * 59 + ($dlNo == null ? 43 : ((Object)$dlNo).hashCode());
        String $dlname = this.getDlname();
        result = result * 59 + ($dlname == null ? 43 : $dlname.hashCode());
        String $dlStation = this.getDlStation();
        result = result * 59 + ($dlStation == null ? 43 : $dlStation.hashCode());
        return result;
    }
}
