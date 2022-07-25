package com.dal.drplus.model.IEntity;

public abstract class IReport {
    protected int reportId;
    protected int appointmentId;
    protected String reportDetails;
    protected byte[] reportFile;
    protected String fileName;

    public IReport(){

    }

    public IReport(int reportId, int appointmentId, String reportDetails, byte[] reportFile, String fileName) {
        this.reportId = reportId;
        this.appointmentId = appointmentId;
        this.reportDetails = reportDetails;
        this.reportFile = reportFile;
        this.fileName = fileName;
    }

    abstract public int getReportId();

    abstract public void setReportId(int reportId);

    abstract public int getAppointmentId();

    abstract public void setAppointmentId(int appointmentId);

    abstract public String getReportDetails();

    abstract public void setReportDetails(String reportDetails);

    abstract public byte[] getReportFile();

    abstract public void setReportFile(byte[] reportFile);

    abstract public String getFileName();

    abstract public void setFileName(String fileName);

}
