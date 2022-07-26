package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.*;
import com.dal.drplus.model.IBuilder.*;
import com.dal.drplus.model.IEntity.*;
import com.dal.drplus.model.entity.*;

public interface IModelFactory {
    public IDoctor createDoctor();
    public Doctor createDoctorUsingBuilder(IDoctorBuilder builder);

    public IDoctorBuilder createDoctorBuilder();

    public IPatient createPatient();
    public Patient createPatientUsingBuilder(IPatientBuilder builder);
    public IPatientBuilder createPatientBuilder();

    public ILabSchedule createLabSchedule();
    public LabSchedule createLabScheduleUsingBuilder(ILabScheduleBuilder builder);
    public ILabScheduleBuilder createLabScheduleBuilder();

    public IDoctorSchedule createDoctorSchedule();
    public DoctorSchedule createDoctorScheduleUsingBuilder(IDoctorScheduleBuilder builder);
    public IDoctorScheduleBuilder createDoctorScheduleBuilder();

    public IRatingLab createRatingLab();
    public RatingLab createRatingLabUsingBuilder(IRatingLabBuilder builder);
    public IRatingLabBuilder createRatingLabBuilder();

}
