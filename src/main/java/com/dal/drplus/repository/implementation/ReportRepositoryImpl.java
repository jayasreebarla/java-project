package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Report;
import com.dal.drplus.repository.interfaces.IReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.util.List;

@Repository
public class ReportRepositoryImpl implements IReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int uploadReport(Report report) throws FileNotFoundException {
        String query = "INSERT INTO Report (Report_id, Patient_id, Doctor_id, Report_desc, Report) VALUES (?,?,?,?,?)";

        return jdbcTemplate.update (query, new Object[] {report.getReportId(),
                                            report.getAppointmentId(),
                                            report.getReportDetails(),
                                            report.getReport()});
    }

    @Override
    public int deleteReport(Report report) {
        String query = "DELETE FROM Report WHERE Report_id = ?";

        return jdbcTemplate.update(query, new Object[] {report.getReportId()});
    }

    @Override
    public Report getReportbyId(String reportId) {
        String query = "Select * FROM Report WHERE Report_id = ?";
        Report report = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Report.class), reportId);

        return report;
    }

    @Override
    public List<Report> findAllbyAppointment(String patientId) {
        return jdbcTemplate.query("Select * from Report where appointment_id=?",
                BeanPropertyRowMapper.newInstance(Report.class), patientId);
    }

}
