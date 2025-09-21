/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.datalogger.faults.controll;

import com.datalogger.faults.dto.FaultDTO;
import com.datalogger.faults.serv.FaultService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value={"/api"})
@RestController
public class FaultController {
    @Autowired
    private FaultService faultService;

    @GetMapping(value={"/"})
    public ResponseEntity<List<FaultDTO>> getAllFaults() {
        List<FaultDTO> faultDTOs = this.faultService.getAllFaults();
        return ResponseEntity.ok(faultDTOs);
    }
    
    @GetMapping(value={"/todays-faults"})
    public ResponseEntity<List<FaultDTO>> getTodaysFaults() {
        List<FaultDTO> todaysFaults = this.faultService.getTodaysFaults();
        return ResponseEntity.ok(todaysFaults);
    }
    
    @GetMapping(value={"/yesterdays-faults"})
    public ResponseEntity<List<FaultDTO>> getYesterdaysFaults() {
        List<FaultDTO> yesterdaysFaults = this.faultService.getYesterdaysFaults();
        return ResponseEntity.ok(yesterdaysFaults);
    }

    @GetMapping(value={"/fault/{id}"})
    public ResponseEntity<List<FaultDTO>> getFaultById(@PathVariable long id) {
        List<FaultDTO> faultById = this.faultService.getFaultById(id);
        return ResponseEntity.ok(faultById);
    }
    
    @GetMapping(value={"/relay-room-door-events"})
    public ResponseEntity<List<FaultDTO>> getRelayRoomDoorEvents() {
        List<FaultDTO> doorEvents = this.faultService.getRelayRoomDoorEvents();
        return ResponseEntity.ok(doorEvents);
    }
    
    @GetMapping(value={"/yesterday-relay-room-door-events"})
    public ResponseEntity<List<FaultDTO>> getYesterdayRelayRoomDoorEvents() {
        List<FaultDTO> yesterdayDoorEvents = this.faultService.getYesterdayRelayRoomDoorEvents();
        return ResponseEntity.ok(yesterdayDoorEvents);
    }
    
    @GetMapping(value={"/all-relay-room-door-events"})
    public ResponseEntity<List<FaultDTO>> getAllRelayRoomDoorEvents() {
        List<FaultDTO> allDoorEvents = this.faultService.getAllRelayRoomDoorEvents();
        return ResponseEntity.ok(allDoorEvents);
    }
    
    @GetMapping(value={"/sms"})
    public ResponseEntity<List<FaultDTO>> getLast15MinutesFaults() {
        List<FaultDTO> last15MinutesFaults = this.faultService.getLast15MinutesFaults();
        return ResponseEntity.ok(last15MinutesFaults);
    }

    @GetMapping(value={"/sms-dltime"})
    public ResponseEntity<List<FaultDTO>> getLast15MinutesFaultsByDlTime() {
        List<FaultDTO> last15MinutesFaults = this.faultService.getLast15MinutesFaultsByDlTime();
        return ResponseEntity.ok(last15MinutesFaults);
    }
}
