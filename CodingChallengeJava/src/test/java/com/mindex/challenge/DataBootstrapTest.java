package com.mindex.challenge;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBootstrapTest {

    @Autowired
    private DataBootstrap dataBootstrap;

    @Autowired
    private EmployeeRepository employeeRepository;
    private CompensationRepository compRepo;

    @Before
    public void init() {
        dataBootstrap.init();
    }

    @Test
    public void test() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Lennon", employee.getLastName());
        assertEquals("Development Manager", employee.getPosition());
        assertEquals("Engineering", employee.getDepartment());
        
        Compensation c = new Compensation();
        c.setEmployee(employee);
        c.setSalary(1000.50d);
        c.setEffectiveDate(new Date());
        compRepo.insert(c);
        
        Compensation comp = compRepo.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(comp);
        assertEquals("John", comp.getEmployee().getFirstName());
        assertEquals("Lennon", comp.getEmployee().getLastName());
        assertEquals("Development Manager", comp.getEmployee().getPosition());
        assertEquals("Engineering", comp.getEmployee().getDepartment());
        assertEquals(1000.50d, comp.getSalary());
        
        Compensation comp2 = compRepo.findByEmployeeId("nonexistant_id");
        assertNull(comp2);
    }
}
