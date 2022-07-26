package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.IBuilder.IBillingBuilder;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.IRatingDoctorBuilder;
import com.dal.drplus.model.IEntity.IDoctor;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.Builder.*;
import com.dal.drplus.model.IEntity.*;
import com.dal.drplus.model.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.print.Doc;

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
    public IRatingDoctor createRatingDoctor() {
        return new RatingDoctor();
    }

    @Override
    public RatingDoctor createRatingDoctorUsingBuilder(IRatingDoctorBuilder builder) {
        return new RatingDoctor(builder);
    }

    @Override
    public IRatingDoctorBuilder createRatingDoctorBuilder() {
        return new RatingDoctorBuilder();
    }

    @Override
    public IBilling createBilling() {
        return new Billing();
    }

    @Override
    public Billing createBillingUsingBuilder(IBillingBuilder builder) {
        return new Billing(builder);
    }

    @Override
    public IBillingBuilder createBillingBuilder() {
        return new BillingBuilder();
    }

    @Override
    public IPatient createPatient() {
        return new Patient();
    }

    @Override
    public IPatient createPatientBuilder(PatientBuilder builder) {
        return new Patient(builder);
    }

    @Override
    public ILabSchedule createLabSchedule() {
        return new LabSchedule();
    }

    @Override
    public ILabSchedule createLabScheduleBuilder(LabScheduleBuilder builder) {
        return new LabSchedule(builder);
    }

    @Override
    public IDoctorSchedule createDoctorSchedule() {
        return new DoctorSchedule();
    }

    @Override
    public IDoctorSchedule createDoctorScheduleBuilder(DoctorScheduleBuilder builder) {
        return new DoctorSchedule(builder);
    }

    @Override
    public IRatingLab createRatingLab() {
        return new RatingLab();
    }

    @Override
    public IRatingLab createRatingLabBuilder(RatingLabBuilder builder) {
        return new RatingLab(builder);
    }
}
