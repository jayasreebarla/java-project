package com.dal.drplus.model.IBuilder;

import com.dal.drplus.model.entity.Report;

public interface IReportBuilder {

    IReportBuilder addReportId(int reportId);
    IReportBuilder addAppointmentId(int appointmentId);
    IReportBuilder addReportDetails(String reportDetails);
    IReportBuilder addReportFile(byte[] reportFile);
    IReportBuilder addFileName(String fileName);

    public Report build();

    public int getReportId();
    public int getAppointmentId();
    public String getReportDetails();
    public byte[] getReportFile();
    public String getFileName();
}
