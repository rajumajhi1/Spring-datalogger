/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.datalogger.faults.serv;

import com.datalogger.faults.dto.FaultDTO;
import com.datalogger.faults.repo.Repository1;
import com.datalogger.faults.repo2.Repository2;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaultService {
    @Autowired
    private Repository1 repo;
    @Autowired
    private Repository2 repo2;

    public List<FaultDTO> getAllFaults() {
        List<Object[]> resultsFromRepo1 = this.repo.allFaults();
        List<Object[]> resultsFromRepo2 = this.repo2.allFaults();
        List<FaultDTO> faultsFromRepo1 = this.mapToDTO(resultsFromRepo1);
        List<FaultDTO> faultsFromRepo2 = this.mapToDTO(resultsFromRepo2);
        ArrayList<FaultDTO> combinedFaults = new ArrayList<FaultDTO>();
        combinedFaults.addAll(faultsFromRepo1);
        combinedFaults.addAll(faultsFromRepo2);
        return combinedFaults;
    }

    public List<FaultDTO> getNewFaultsSince(Timestamp lastCreatedTime) {
        List<Object[]> resultsFromRepo1 = this.repo.findNewFaultsSince(lastCreatedTime);
        List<Object[]> resultsFromRepo2 = this.repo2.findNewFaultsSince(lastCreatedTime);
        ArrayList<FaultDTO> newFaults = new ArrayList<FaultDTO>();
        newFaults.addAll(this.mapToDTO(resultsFromRepo1));
        newFaults.addAll(this.mapToDTO(resultsFromRepo2));
        return newFaults;
    }

    private List<FaultDTO> mapToDTO(List<Object[]> results) {
        return results.stream().map(record -> {
            // Convert BigDecimal to Integer for numeric fields
            Integer sysTime = record[4] instanceof java.math.BigDecimal ? ((java.math.BigDecimal)record[4]).intValue() : (Integer)record[4];
            Integer sysYear = record[5] instanceof java.math.BigDecimal ? ((java.math.BigDecimal)record[5]).intValue() : (Integer)record[5];
            Integer dlTime = record[6] instanceof java.math.BigDecimal ? ((java.math.BigDecimal)record[6]).intValue() : (Integer)record[6];
            
            return new FaultDTO(
                (String)record[0], 
                (String)record[1], 
                (String)record[2], 
                (String)record[3], 
                sysTime, 
                sysYear, 
                dlTime, 
                (String)record[7], 
                (String)record[8], 
                (Timestamp)record[9]
            );
        }).collect(Collectors.toList());
    }

    public List<FaultDTO> getFaultById(long id) {
        List<Object[]> getFaultById1 = this.repo2.findByDlTime(id);
        List<Object[]> getFaultById2 = this.repo.findByDlTime(id);
        List<FaultDTO> faultsFromRepo1 = this.mapToDTO(getFaultById1);
        List<FaultDTO> faultsFromRepo2 = this.mapToDTO(getFaultById2);
        ArrayList<FaultDTO> combinedFaults = new ArrayList<FaultDTO>();
        combinedFaults.addAll(faultsFromRepo1);
        combinedFaults.addAll(faultsFromRepo2);
        return combinedFaults;
    }
}
