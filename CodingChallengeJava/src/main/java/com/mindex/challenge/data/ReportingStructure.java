package com.mindex.challenge.data;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.service.impl.Autowired;

public class ReportingStructure {

    private Employee _employee = null;
    private EmployeeRepository empRepo;    
	
	public ReportingStructure() {

	}

    public void setEmployee(Employee emp) {
        this._employee = emp;
    }

    public int getnumberOfReports() {
    	int numrpts = 0;
        // determine number of reports based on this _employee directreports structure
    	
    	if (_employee != null) {
    		numrpts = CountDirectReports(_employee);
    	}
    	
        return numrpts;
    }

    private int CountDirectReports(Employee empl) {
    	int iret = 0;
    	
    	if (empl == null || empl.getDirectReports().size() == 0) return 0;
    	
    	for( Employee thisDR : empl.getDirectReports() ) {
    		iret += 1 + CountDirectReports(empRepo.findByEmployeeId(thisDR.getEmployeeId()));
    	}
    	
    	return iret;
    }
}
