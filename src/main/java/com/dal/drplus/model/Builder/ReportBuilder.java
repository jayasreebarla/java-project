package com.dal.drplus.model.Builder;

import com.dal.drplus.model.IBuilder.IReportBuilder;
import com.dal.drplus.model.entity.Report;
import com.dal.drplus.model.factory.ModelFactory;

public class ReportBuilder implements IReportBuilder {

    private int reportId;
    private int appointmentId;
    private String reportDetails;
    private byte[] reportFile;
    private String fileName;

    @Override
    public IReportBuilder addReportId(int reportId) {
        this.reportId = reportId;
        return this;
    }

    @Override
    public IReportBuilder addAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    @Override
    public IReportBuilder addReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
        return this;
    }

    @Override
    public IReportBuilder addReportFile(byte[] reportFile) {
        this.reportFile = reportFile;
        return this;
    }

    @Override
    public IReportBuilder addFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public Report build() {
        Report report = ModelFactory.instance().createReportUsingBuilder(this);
        return report;
    }

    @Override
    public int getReportId() {
        return reportId;
    }

    @Override
    public int getAppointmentId() {
        return appointmentId;
    }

    @Override
    public String getReportDetails() {
        return reportDetails;
    }

    @Override
    public byte[] getReportFile() {
        return reportFile;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
