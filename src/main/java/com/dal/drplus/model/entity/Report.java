package com.dal.drplus.model.entity;

import com.dal.drplus.model.IBuilder.IReportBuilder;
import com.dal.drplus.model.IEntity.IReport;

public class Report extends IReport {
    public Report(){
    }

    public Report(int reportId, int appointmentId, String reportDetails, byte[] reportFile, String fileName) {
        this.reportId = reportId;
        this.appointmentId = appointmentId;
        this.reportDetails = reportDetails;
        this.reportFile = reportFile;
        this.fileName = fileName;
    }

    public Report(IReportBuilder reportBuilder){
        this.reportId = reportBuilder.getReportId();
        this.appointmentId = reportBuilder.getAppointmentId();
        this.reportDetails = reportBuilder.getReportDetails();
        this.reportFile = reportBuilder.getReportFile();
        this.fileName = reportBuilder.getFileName();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getReportFile() {
        return reportFile;
    }

    public void setReportFile(byte[] reportFile) {
        this.reportFile = reportFile;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }



}
