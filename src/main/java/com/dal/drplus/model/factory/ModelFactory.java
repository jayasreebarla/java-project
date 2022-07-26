package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.ILabBuilder;
import com.dal.drplus.model.IBuilder.IPrescriptionBuilder;
import com.dal.drplus.model.IBuilder.IPromotionsBuilder;
import com.dal.drplus.model.IBuilder.*;
import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.Builder.*;
import com.dal.drplus.model.IEntity.*;
import com.dal.drplus.model.entity.*;

public class ModelFactory implements IModelFactory{
    private static ModelFactory theOneInstance = null;
    public static ModelFactory instance()
    {
        if (null == theOneInstance)
        {
            theOneInstance = new ModelFactory();
        }
        return theOneInstance;
    }

//    @Bean
    @Override
    public IDoctor createDoctor() {
        return new Doctor();
    }

    public Doctor createDoctorUsingBuilder(IDoctorBuilder builder){return new Doctor(builder);}
    public IDoctorBuilder createDoctorBuilder(){
        return new DoctorBuilder();
    }

    @Override
    public IPatient createPatient() {
        return new Patient();
    }

    @Override
    public Patient createPatientUsingBuilder(IPatientBuilder builder) {
        return new Patient(builder);
    }

    public ILab createLab() {
        return new Lab();
    }

    public Lab createLabUsingBuilder(ILabBuilder builder){return new Lab(builder);}
    public ILabBuilder createLabBuilder(){
        return new LabBuilder();
    }

    public IPromotions createPromotions() {
        return new Promotions();
    }

    public Promotions createPromotionsUsingBuilder(IPromotionsBuilder builder){return new Promotions(builder);}

    public IPromotionsBuilder createPromotionsBuilder(){
        return new PromotionsBuilder();
    }

    @Override
    public IPatientBuilder createPatientBuilder() {
        return new PatientBuilder();
    }

    @Override
    public ILabSchedule createLabSchedule() {
        return new LabSchedule();
    }

    @Override
    public LabSchedule createLabScheduleUsingBuilder(ILabScheduleBuilder builder) {
        return new LabSchedule(builder);
    }

    @Override
    public ILabScheduleBuilder createLabScheduleBuilder() {
        return new LabScheduleBuilder();
    }

    @Override
    public IDoctorSchedule createDoctorSchedule() {
        return new DoctorSchedule();
    }

    @Override
    public DoctorSchedule createDoctorScheduleUsingBuilder(IDoctorScheduleBuilder builder) {
        return new DoctorSchedule(builder);
    }

    @Override
    public IDoctorScheduleBuilder createDoctorScheduleBuilder() {
        return new DoctorScheduleBuilder();
    }

    @Override
    public IRatingLab createRatingLab() {
        return new RatingLab();
    }

    @Override
    public RatingLab createRatingLabUsingBuilder(IRatingLabBuilder builder) {
        return new RatingLab(builder);
    }

    @Override
    public IRatingLabBuilder createRatingLabBuilder() {
        return new RatingLabBuilder();
    }


    @Override
    public IPrescription createPrescription() {
        return new Prescription();
    }

    @Override
    public Prescription createPrescriptionUsingBuilder(IPrescriptionBuilder prescriptionBuilder) {
        return new Prescription(prescriptionBuilder);
    }

    @Override
    public IPrescriptionBuilder createPrescriptionBuilder() {
        return new PrescriptionBuilder();
    }
}
