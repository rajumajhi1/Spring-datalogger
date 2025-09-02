/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.data.repository.query.Param
 *  org.springframework.stereotype.Repository
 */
package com.datalogger.faults.repo;

import com.datalogger.faults.model.Fault;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Repository1
extends JpaRepository<Fault, Integer> {
    @Query(value="SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo WHERE f.faultPseudoType NOT IN ('VOLTAGE LOW', 'VOLTAGE HIGH','TEMPERATURE HIGH') ORDER BY f.sysTime DESC ", nativeQuery=true)
    public List<Object[]> allFaults();

    @Query(value="SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo WHERE f.createdTime > :lastCreatedTime ORDER BY f.createdTime DESC FETCH FIRST 100 ROWS ONLY", nativeQuery=true)
    public List<Object[]> findNewFaultsSince(@Param(value="lastCreatedTime") Timestamp var1);

    @Query(value="SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo WHERE f.dlTime = :id", nativeQuery=true)
    public List<Object[]> findByDlTime(@Param(value="id") long var1);
}
