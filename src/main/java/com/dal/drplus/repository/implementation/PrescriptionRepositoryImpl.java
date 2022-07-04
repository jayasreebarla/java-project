package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Prescription;
import com.dal.drplus.model.Report;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IPrescriptionRepository;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class PrescriptionRepositoryImpl {
//implements IPrescriptionRepository
//    String INSERT_PRESCRIPTION = "INSERT INTO Prescription (prescription_id, prescription_details, Prescription, appointment_id) VALUES(?,?,?,?)";
//    String UPDATE_PRESCRIPTION_BY_ID = "UPDATE Prescription SET  prescription_details=?, Prescription=?, appointment_id=? WHERE prescription_id=? ";
//    String SELECT_PRESCRIPTION_BY_ID = "SELECT * FROM Prescription WHERE prescription_id=?";
//    String DELETE_PRESCRIPTION_BY_ID = "DELETE FROM Prescription WHERE prescription_id=?";
//
//
//    DatabaseConfiguration databaseConfiguration;
//
//    public PrescriptionRepositoryImpl() {
//        this.databaseConfiguration = dbConfig();
//    }
//    private DatabaseConfiguration dbConfig() {
//        return new DatabaseConfigurationImpl();
//    }
//
//
//    public StorageResult savePrescription(Prescription prescription) throws FileNotFoundException {
//        try {
//            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(INSERT_PRESCRIPTION);
//            statement.setString(1, prescription.getPrescriptionId());
//            statement.setString(2, prescription.getAppointmentId());
//            statement.setString(3, prescription.getPrescriptionDetails());
//            statement.setBlob(4, prescription.getPrescription());
//
//            statement.executeUpdate();
//            System.out.println("report uploaded ");
//            return IPrescriptionRepository.StorageResult.SUCCESS;
//        } catch (SQLException e) {
////            throw new RuntimeException(e);
//            return IPrescriptionRepository.StorageResult.FAILURE;
//        }
//    }
//
//    @Override
//    public int updatePrescription(Prescription prescription) throws FileNotFoundException {
//
//        return 0;
//    }
//
//    private Report createReport(ResultSet rs) throws SQLException {
//
//        Report report= new Report();
//        report.setReportId(rs.getString(""));
//        report.setReportDetails(rs.getString(""));
//        report.setAppointmentId(rs.getString(""));
//        report.setReportFile(rs.getBlob(" "));
//        return report;
//    }
//
//    public Prescription findById(Long prescription_id) {
//        return null;
//    }
//
//    @Override
//    public int deleteById(Long prescription_id) {
//
//        return 0;
//    }



}
