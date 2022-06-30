package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.Report;

import java.io.FileNotFoundException;
import java.util.List;

public interface IReportRepository {
    public int uploadReport(Report report) throws FileNotFoundException;

    public int deleteReport(Report report);

    public Report getReportbyId (String reportId);

    public List<Report> findAllbyAppointment(String patientId);

}
