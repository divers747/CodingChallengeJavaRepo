package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {

    private Employee _employee = null;
    private double _salary = 0.0d;
    private Date _effDate;
    
	public Compensation() {		
		
	}	

    public void setEmployee(Employee emp) {
        this._employee = emp;
    }
    
    public Employee getEmployee() {
    	return _employee;
    }

	public double getSalary() {
		return _salary;
	}

	public void setSalary(double _salary) {
		this._salary = _salary;
	}

	public Date getEffectiveDate() {
		return _effDate;
	}

	public void setEffectiveDate(Date _effDate) {
		this._effDate = _effDate;
	}
}
