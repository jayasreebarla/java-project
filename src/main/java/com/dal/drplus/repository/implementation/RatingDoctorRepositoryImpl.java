package com.dal.drplus.repository.implementation;

import com.dal.drplus.model.Patient;
import com.dal.drplus.model.RatingDoctor;
import com.dal.drplus.repository.interfaces.IRatingDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingDoctorRepositoryImpl implements IRatingDoctorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveDoctorRating(RatingDoctor ratingDoctor) {
        return jdbcTemplate.update("INSERT into RatingDoctor (rating_id,patient_id,doctor_id,doctor_rating) VALUES (?,?,?,?)"
                , new Object[] {ratingDoctor.getRatingId(),ratingDoctor.getPatientId(),ratingDoctor.getDoctorId(),ratingDoctor.getDoctorRating()});

    }

    @Override
    public int updateDoctorRating(RatingDoctor ratingDoctor) {
        return jdbcTemplate.update("UPDATE RatingDoctor SET doctor_rating=? WHERE rating_id=? and doctor_id=?"
                , new Object[] {ratingDoctor.getDoctorRating(),ratingDoctor.getRatingId(),ratingDoctor.getDoctorId()});

    }

    @Override
    public RatingDoctor findDoctorRatingById(String ratingId, String doctorId) {
        RatingDoctor ratingDoctor = jdbcTemplate.queryForObject("SELECT * FROM RatingDoctor WHERE rating_id=?", BeanPropertyRowMapper.newInstance(RatingDoctor.class),ratingId );
        return ratingDoctor;
    }

    @Override
    public List<RatingDoctor> findDoctorRatingByDoctorId(String doctorId) {
        return jdbcTemplate.query("SELECT * FROM RatingDoctor WHERE rating_id=?", BeanPropertyRowMapper.newInstance(RatingDoctor.class),doctorId);
    }

    @Override
    public int deleteDoctorRatingById(String ratingId, String doctorId) {
        return jdbcTemplate.update("DELETE FROM RatingDoctor WHERE rating_id=?", ratingId);
    }

    @Override
    public List<RatingDoctor> findAll() {
        return jdbcTemplate.query("SELECT * from RatingDoctor", BeanPropertyRowMapper.newInstance(RatingDoctor.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from RatingDoctor");
    }
}
