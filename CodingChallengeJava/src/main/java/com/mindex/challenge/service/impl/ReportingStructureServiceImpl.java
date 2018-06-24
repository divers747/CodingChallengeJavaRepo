package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    
    @Autowired
    private EmployeeRepository empRepo;    

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Reading reporting structure with id [{}]", id);

        Employee keyEmp = empRepo.findByEmployeeId(id); //make sure employee exists
        
        if (keyEmp == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        
        if (keyEmp.getDirectReports().size() != 0) {
            List<Employee> allEmps = empRepo.FindAll(); //get a list of all employee records in the repository
            
            // assign direct reports for all employee records
            for( Employee curemp : allEmps ) {
            	if (curemp.getDirectReports().size() != 0) {
            		// initialize a list to be filled with the direct reports
            		List<Employee> dirrpts = new List<Employee>(); //curemp.getDirectReports().size());
            		// gather the direct reports
            		curemp.getDirectReports().forEach(rpt -> dirrpts.Add(empRepo.findByEmployeeId(rpt.getEmployeeId())));
            		// assign direct reports to current employee record
            		curemp.setDirectReports(dirrpts);
             	}        	
           		if (curemp.getEmployeeId() == id) keyEmp = curemp;
            }
        }        
        
    	ReportingStructure rpt = new ReportingStructure();
    	rpt.setEmployee(keyEmp);
    	return rpt;        
    }
    
}
