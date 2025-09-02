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

@CrossOrigin(origins={"http://192.168.83.12:5173/", "http://localhost:5173"})
@RequestMapping(value={"/api"})
@RestController
public class FaultController {
    @Autowired
    private FaultService faultService;

    @CrossOrigin(origins={"http://192.168.83.12:5173/", "http://localhost:5173"})
    @GetMapping(value={"/"})
    public ResponseEntity<List<FaultDTO>> getAllFaults() {
        List<FaultDTO> faultDTOs = this.faultService.getAllFaults();
        return ResponseEntity.ok(faultDTOs);
    }

    @GetMapping(value={"/fault/{id}"})
    public ResponseEntity<List<FaultDTO>> getFaultById(@PathVariable long id) {
        List<FaultDTO> faultById = this.faultService.getFaultById(id);
        return ResponseEntity.ok(faultById);
    }
}
