/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.datalogger.faults.serv;

import com.datalogger.faults.dto.FaultDTO;
import com.datalogger.faults.repo.FaultRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class FaultService {
    @Autowired
    private ApplicationContext context;
    
    private Map<String, FaultRepository> getRepositories() {
        return context.getBean("faultRepositories", Map.class);
    }

    public List<FaultDTO> getAllFaults() {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.allFaults();
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }

    public List<FaultDTO> getNewFaultsSince(Timestamp lastCreatedTime) {
        ArrayList<FaultDTO> newFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findNewFaultsSince(lastCreatedTime);
            List<FaultDTO> faults = mapToDTO(results);
            newFaults.addAll(faults);
        }
        
        return newFaults;
    }

    private List<FaultDTO> mapToDTO(List<Object[]> results) {
        return results.stream().map(record -> {
            // Convert BigDecimal to Integer for numeric fields
            Integer sysTime = record[4] instanceof java.math.BigDecimal ? ((java.math.BigDecimal)record[4]).intValue() : (Integer)record[4];
            Integer sysYear = record[5] instanceof java.math.BigDecimal ? ((java.math.BigDecimal)record[5]).intValue() : (Integer)record[5];
            Integer dlTime = record[6] instanceof java.math.BigDecimal ? ((java.math.BigDecimal)record[6]).intValue() : (Integer)record[6];
            
            // Convert Timestamp to LocalDateTime
            LocalDateTime createdTime = record[9] != null ? ((Timestamp)record[9]).toLocalDateTime() : null;
            LocalDateTime sysTimestamp = record[10] != null ? ((Timestamp)record[10]).toLocalDateTime() : null;
            LocalDateTime dlTimestamp = record[11] != null ? ((Timestamp)record[11]).toLocalDateTime() : null;
            
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
                createdTime,
                sysTimestamp,
                dlTimestamp
            );
        }).collect(Collectors.toList());
    }

    public List<FaultDTO> getFaultById(long id) {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findByDlTime(id);
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }
    
    public List<FaultDTO> getRelayRoomDoorEvents() {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findRelayRoomDoorEvents();
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }
    
    public List<FaultDTO> getAllRelayRoomDoorEvents() {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findAllRelayRoomDoorEvents();
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }
    
    public List<FaultDTO> getYesterdayRelayRoomDoorEvents() {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findYesterdayRelayRoomDoorEvents();
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }
    
    public List<FaultDTO> getTodaysFaults() {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findTodaysFaults();
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }
    
    public List<FaultDTO> getYesterdaysFaults() {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findYesterdaysFaults();
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }
    
    public List<FaultDTO> getLast15MinutesFaults() {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findLast15MinutesFaults();
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }

    public List<FaultDTO> getLast15MinutesFaultsByDlTime() {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findLast15MinutesFaultsByDlTime();
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }

    public List<FaultDTO> getFaultsByDateRange(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        ArrayList<FaultDTO> combinedFaults = new ArrayList<>();
        
        for (FaultRepository repository : getRepositories().values()) {
            List<Object[]> results = repository.findFaultsByDateRange(fromDateTime, toDateTime);
            List<FaultDTO> faults = mapToDTO(results);
            combinedFaults.addAll(faults);
        }
        
        return combinedFaults;
    }
}
