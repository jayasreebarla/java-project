package com.dal.drplus.repository.interfaces;

import com.dal.drplus.model.IEntity.IRatingDoctor;
import com.dal.drplus.model.entity.RatingDoctor;
import java.util.List;

public interface IRatingDoctorRepository {
    enum StorageResult{
        SUCCESS,
        FAILURE
    }

    StorageResult saveDoctorRating(IRatingDoctor ratingDoctor);
    boolean findDoctorRatingByDoctorIdAndPatientID(String doctorId, String patientId);
    List<RatingDoctor> findDoctorRatingByDoctorId(String doctorId);
    List<RatingDoctor> findDoctorRatingByPatientId(String patientId);
    List<String> findDoctorReviewsByDoctorId(String doctorId);
    List<Integer> findDoctorRatingListByDoctorId(String doctorId);
    StorageResult deleteDoctorRatingByDoctorId(String doctorId);
    StorageResult deleteDoctorRatingByPatientId(String patientId);
    List<RatingDoctor> findAll();
    int deleteAll();
}
