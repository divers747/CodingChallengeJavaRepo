package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    
    @Autowired
    private CompensationRepository compRepo;
    private EmployeeRepository empRepo;

    @Override
    public Compensation create(String Id, Date effectiveDate, double Salary) {
        LOG.debug("Creating compensation [{}]", Id);

        Employee emp = empRepo.findByEmployeeId(Id);
        if (emp == null) {
            throw new RuntimeException("Compensation invalid employeeId: " + Id);
        }
        
        Compensation c = new Compensation();
        c.setEmployee(emp);
        c.setEffectiveDate(effectiveDate);
        c.setSalary(Salary);
        
        compRepo.insert(c);

        return c;
    }

    @Override
    public Compensation read(String Id) {
        LOG.debug("Creating compensation with employee id [{}]", Id);

        Compensation comp = compRepo.findByEmployeeId(Id);

        if (comp == null) {
            throw new RuntimeException("Invalid compensation employeeId: " + Id);
        }

        return comp;
    }    
}
