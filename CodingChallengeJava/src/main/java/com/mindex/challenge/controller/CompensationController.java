package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compSvc;

    @PostMapping("/comp")
    public Compensation create(@RequestBody String Id, Date effDate, double Salary) {
        LOG.debug("Received compensation create request for employee id [{}]", Id);

        return compSvc.create(Id, effDate, Salary);
    }

    @GetMapping("/comp/{id}")
    public Compensation read(@PathVariable String Id) {
        LOG.debug("Received compensation read request for employee id [{}]", Id);

        return compSvc.read(Id);
    }    
    
}
