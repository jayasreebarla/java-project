package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.IBuilder.IPrescriptionBuilder;
import com.dal.drplus.model.IEntity.IPrescription;
import com.dal.drplus.model.entity.Prescription;
import com.dal.drplus.model.factory.ModelFactory;
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
    String SELECT_PRESCRIPTION_BY_AID = "SELECT * FROM Prescription WHERE appointment_id=?";

    DatabaseConfiguration databaseConfiguration;

    public PrescriptionRepositoryImpl() {
        this.databaseConfiguration = dbConfig();
    }
    private DatabaseConfiguration dbConfig() {
        return new DatabaseConfigurationImpl();
    }

    public StorageResult uploadPrescription(IPrescription prescription) throws FileNotFoundException {
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(UPLOAD_PRESCRIPTION);
            Blob blob_file = new SerialBlob(prescription.getPrescriptionFile());
            statement.setInt(3, prescription.getAppointmentId());
            statement.setString(1, prescription.getPrescriptionDetails());
            statement.setBlob(2,blob_file);

            int res = statement.executeUpdate();

            if(res == 1) {
                return IPrescriptionRepository.StorageResult.SUCCESS;
            } else {
                return StorageResult.FAILURE;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Prescription createPrescription(ResultSet rs) throws SQLException {

        Prescription prescription;
        IPrescriptionBuilder prescriptionBuilder = ModelFactory.instance().createPrescriptionBuilder();
        prescription = prescriptionBuilder
                .addPrescriptionId(rs.getInt("prescription_id"))
                .addPrescriptionDetails(rs.getString("prescription_details"))
                .addAppointmentId(rs.getInt("appointment_id"))
                .addPrescriptionFile(rs.getBytes("Prescription")).build();

        return prescription;
    }

    public Prescription findById(int prescription_id) {
        Prescription prescription = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_PRESCRIPTION_BY_ID);
            statement.setInt(1,prescription_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                prescription = createPrescription(rs);
            }
            return prescription;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prescription> findAllbyAppointment(int appointmentId) {
        List<Prescription> prescriptionList = new ArrayList<>();
        Prescription prescription = null;
        try {
            PreparedStatement statement = databaseConfiguration.getDBConnection().prepareStatement(SELECT_PRESCRIPTION_BY_AID);
            statement.setInt(1,appointmentId);
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

}