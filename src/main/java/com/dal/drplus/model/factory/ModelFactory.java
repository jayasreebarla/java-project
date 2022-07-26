package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.DoctorBuilder;
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

}
