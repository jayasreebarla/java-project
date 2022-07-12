package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Report;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IReportRepository;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ReportRepositoryImpl implements IReportRepository {


    String INSERT_REPORT = "INSERT INTO Report (appointment_id, report_desc, report) VALUES (?,?,?)";
    String SELECT_REPORT_BY_ID = "Select * FROM Report WHERE report_id = ?";
    String DELETE_REPORT_BY_ID = "DELETE FROM Report WHERE report_id = ?";
    String SELECT_REPORT_BY_AID = "Select * from Report where appointment_id=?";
    DatabaseConfiguration databaseConfiguration;
    public ReportRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }

    private DatabaseConfiguration dbConfig() {
        return new DatabaseConfigurationImpl();
    }

    @Override
    public StorageResult uploadReport(Report report) {

        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_REPORT);
            Blob blob_file = new SerialBlob(report.getReportFile());
            //statement.setString(1, report.getReportId());
            // Patient currentPatient = session.getAttributeNames("current")
            statement.setString(1, report.getAppointmentId());
            statement.setString(2, report.getReportDetails());
            statement.setBlob(3, blob_file);
            statement.executeUpdate();
            System.out.println("report uploaded ");
            return IReportRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
//           throw new RuntimeException(e);
            return IReportRepository.StorageResult.FAILURE;
        }
    }

    @Override
    public int deleteReport(Report report) {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_REPORT_BY_ID);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return -1;
            //throw new RuntimeException(e);
        }

    }

    @Override
    public Report getReportbyId(int reportId) {
        Report report = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_REPORT_BY_ID);
            statement.setInt(1,reportId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                report = createReport(rs);
            }
            return report;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Report createReport(ResultSet rs) throws SQLException {

        Report report= new Report();
        report.setReportId(rs.getInt("report_id"));
        report.setReportDetails(rs.getString("report_desc"));
        report.setAppointmentId(rs.getString("appointment_id"));
        report.setReportFile(rs.getBytes("report"));
        return report;
    }

    @Override
    public List<Report> findAllbyAppointment(String appointmentId) {
        List<Report> reportList = new ArrayList<>();
        Report report = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_REPORT_BY_AID);
            statement.setString(1,appointmentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                report = createReport(rs);
                reportList.add(report);
            }
            return reportList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
