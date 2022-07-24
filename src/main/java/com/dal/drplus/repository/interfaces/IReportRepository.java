package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.entity.Report;

import java.io.FileNotFoundException;
import java.util.List;

public interface IReportRepository {

    enum StorageResult{
        SUCCESS,
        FAILURE
    }
    public StorageResult uploadReport(Report report) throws FileNotFoundException;

    public int deleteReport(Report report);

    public Report getReportbyId (int reportId);

    public List<Report> findAllbyAppointment(int appointmentId);

}
