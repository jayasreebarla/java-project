package com.dal.drplus.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Report {
    private String reportId;
    private String AppointmentId;
    private String reportDetails;
    private Byte[] reportFile;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Byte[] getReportFile() {
        return reportFile;
    }

    public void setReportFile(Byte[] reportFile) {
        this.reportFile = reportFile;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(String AppointmentId) {
        this.AppointmentId = AppointmentId;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public FileInputStream getReport() throws FileNotFoundException {
        FileInputStream file;
        file = new FileInputStream(this.fileName);
        return file;
    }

}
