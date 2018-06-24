package com.mindex.challenge.service;

import java.util.Date;
import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation create(String Id, Date effectiveDate, double Salary);
    Compensation read(String id);
}
