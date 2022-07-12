package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Prescription;
import com.dal.drplus.repository.configuration.DatabaseConfiguration;
import com.dal.drplus.repository.configuration.DatabaseConfigurationImpl;
import com.dal.drplus.repository.interfaces.IPrescriptionRepository;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileNotFoundException;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class PrescriptionRepositoryImpl implements IPrescriptionRepository {

    String UPLOAD_PRESCRIPTION = "INSERT INTO Prescription ( prescription_details, Prescription, appointment_id) VALUES(?,?,?)";
    String SELECT_PRESCRIPTION_BY_ID = "SELECT * FROM Prescription WHERE prescription_id=?";
    String DELETE_PRESCRIPTION_BY_ID = "DELETE FROM Prescription WHERE prescription_id=?";
    String SELECT_PRESCRIPTION_BY_AID = "SELECT * FROM Prescription WHERE appointment_id=?";


    DatabaseConfiguration databaseConfiguration;

    public PrescriptionRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }
    private DatabaseConfiguration dbConfig() {
        return new DatabaseConfigurationImpl();
    }


    public StorageResult uploadPrescription(Prescription prescription) throws FileNotFoundException {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPLOAD_PRESCRIPTION);
            Blob blob_file = new SerialBlob(prescription.getPrescription());
            //statement.setString(1, prescription.getPrescriptionId());
            statement.setString(1, prescription.getAppointmentId());
            statement.setString(2, prescription.getPrescriptionDetails());
            statement.setBlob(3,blob_file);

            statement.executeUpdate();
            System.out.println("prescription uploaded ");
            return IPrescriptionRepository.StorageResult.SUCCESS;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return IPrescriptionRepository.StorageResult.FAILURE;
        }
    }


    private Prescription createPrescription(ResultSet rs) throws SQLException {

        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(rs.getInt(""));
        prescription.setPrescriptionDetails(rs.getString(""));
        prescription.setAppointmentId(rs.getString(""));
        prescription.setPrescription(rs.getBytes("Prescription"));
        //report.setReportFile(rs.getBytes("report"));
        return prescription;
    }

    public Prescription findById(String prescription_id) {
        Prescription prescription = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_PRESCRIPTION_BY_ID);
            statement.setString(1,prescription_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                prescription = createPrescription(rs);
            }
            return prescription;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prescription> findAllbyAppointment(String appointmentId) {
        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription prescription = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_PRESCRIPTION_BY_AID);
            statement.setString(1,appointmentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                prescription = createPrescription(rs);
                prescriptionList.add(prescription);
            }
            return prescriptionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public int deleteById(int prescription_id) {

        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(DELETE_PRESCRIPTION_BY_ID);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return -1;
            //throw new RuntimeException(e);
        }
    }



}
