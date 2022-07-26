package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.*;
import com.dal.drplus.model.IBuilder.IBillingBuilder;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.IRatingDoctorBuilder;
import com.dal.drplus.model.IEntity.*;
import com.dal.drplus.model.entity.Billing;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.RatingDoctor;

public interface IModelFactory {
    public IDoctor createDoctor();
    public Doctor createDoctorUsingBuilder(IDoctorBuilder builder);
    public IDoctorBuilder createDoctorBuilder();

    public IRatingDoctor createRatingDoctor();
    public RatingDoctor createRatingDoctorUsingBuilder(IRatingDoctorBuilder builder);
    public IRatingDoctorBuilder createRatingDoctorBuilder();

    public IBilling createBilling();
    public Billing createBillingUsingBuilder(IBillingBuilder builder);
    public IBillingBuilder createBillingBuilder();

    public IPatient createPatient();
    public IPatient createPatientBuilder(PatientBuilder builder);

    public ILabSchedule createLabSchedule();
    public ILabSchedule createLabScheduleBuilder(LabScheduleBuilder builder);

    public IDoctorSchedule createDoctorSchedule();
    public IDoctorSchedule createDoctorScheduleBuilder(DoctorScheduleBuilder builder);

    public IRatingLab createRatingLab();
    public IRatingLab createRatingLabBuilder(RatingLabBuilder builder);

}
