package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.*;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IEntity.*;

public interface IModelFactory {
    public IDoctor createDoctor();
    public IDoctor createDoctorUsingBuilder(IDoctorBuilder builder);

    public IDoctorBuilder createDoctorBuilder();

    public IPatient createPatient();
    public IPatient createPatientBuilder(PatientBuilder builder);

    public ILabSchedule createLabSchedule();
    public ILabSchedule createLabScheduleBuilder(LabScheduleBuilder builder);

    public IDoctorSchedule createDoctorSchedule();
    public IDoctorSchedule createDoctorScheduleBuilder(DoctorScheduleBuilder builder);

    public IRatingLab createRatingLab();
    public IRatingLab createRatingLabBuilder(RatingLabBuilder builder);
}
