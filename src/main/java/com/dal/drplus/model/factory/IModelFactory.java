package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.*;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.ILabBuilder;
import com.dal.drplus.model.IBuilder.IPrescriptionBuilder;
import com.dal.drplus.model.IBuilder.IPromotionsBuilder;
import com.dal.drplus.model.IEntity.*;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.entity.Promotions;

public interface IModelFactory {
    public IDoctor createDoctor();
    public Doctor createDoctorUsingBuilder(IDoctorBuilder builder);

    public IDoctorBuilder createDoctorBuilder();

    public IPatient createPatient();
    public IPatient createPatientBuilder(PatientBuilder builder);

    public ILab createLab();
    public ILab createLabUsingBuilder(ILabBuilder builder);

    public ILabBuilder createLabBuilder();

    public ILabSchedule createLabSchedule();
    public ILabSchedule createLabScheduleBuilder(LabScheduleBuilder builder);

    public IDoctorSchedule createDoctorSchedule();
    public IDoctorSchedule createDoctorScheduleBuilder(DoctorScheduleBuilder builder);

    public IRatingLab createRatingLab();
    public IRatingLab createRatingLabBuilder(RatingLabBuilder builder);

    public IPromotions createPromotions();
    public Promotions createPromotionsUsingBuilder(IPromotionsBuilder builder);

    public IPromotionsBuilder createPromotionsBuilder();

    public IPrescription createPrescription();
    public IPrescription createPrescriptionUsingBuilder(IPrescriptionBuilder prescriptionBuilder);
    public IPrescriptionBuilder createPrescriptionBuilder();
}
