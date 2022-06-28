package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Prescription;
import com.dal.drplus.repository.interfaces.IPrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.util.List;

@Repository
public class PrescriptionRepositoryImpl implements IPrescriptionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int savePrescription(Prescription prescription) throws FileNotFoundException {
        return jdbcTemplate.update("INSERT INTO Prescription (prescription_id, prescription_details, Prescription, appointment_id) VALUES(?,?,?,?)",
                new Object[] { prescription.getPrescriptionId(), prescription.getPrescriptionDetails(), prescription.getPrescription(), prescription.getAppointmentId() });
    }

    @Override
    public int updateBill(Prescription prescription) throws FileNotFoundException {
        return jdbcTemplate.update("UPDATE Prescription SET  prescription_details=?, Prescription=?, appointment_id=? WHERE prescription_id=?",
                new Object[] { prescription.getPrescriptionDetails(), prescription.getPrescription(), prescription.getAppointmentId(),prescription.getPrescriptionId()});
    }

    public Prescription findById(Long prescription_id) {
        try {
            Prescription prescription = jdbcTemplate.queryForObject("SELECT * FROM Prescription WHERE prescription_id=?",
                    BeanPropertyRowMapper.newInstance(Prescription.class), prescription_id);
            return prescription;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long prescription_id) {
        return jdbcTemplate.update("DELETE FROM Prescription WHERE prescription_id=?", prescription_id);
    }

    public List<Prescription> findAll() {
        return jdbcTemplate.query("SELECT * from Prescription", BeanPropertyRowMapper.newInstance(Prescription.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from Prescription");
    }

}
