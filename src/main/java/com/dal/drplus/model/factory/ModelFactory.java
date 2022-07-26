package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.DoctorBuilder;
import com.dal.drplus.model.IBuilder.IAdminBuilder;
import com.dal.drplus.model.IBuilder.IAppointmentBuilder;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.IReportBuilder;
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
    public Doctor createDoctorUsingBuilder(IDoctorBuilder builder){
        return new Doctor(builder);
    }
    public IDoctorBuilder createDoctorBuilder(){
        return new DoctorBuilder();
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

    @Override
    public IAdmin createAdmin() {
        return new Admin();
    }

    @Override
    public Admin createAdminUsingBuilder(IAdminBuilder adminBuilder) {
        return new Admin(adminBuilder);
    }

    @Override
    public IAdminBuilder createAdminBuilder() {
        return new AdminBuilder();
    }

    @Override
    public IReport createReport() {
        return new Report();
    }

    @Override
    public Report createReportUsingBuilder(IReportBuilder reportBuilder) {
        return new Report(reportBuilder);
    }

    @Override
    public IReportBuilder createReportBuilder() {
        return new ReportBuilder();
    }

    @Override
    public IAppointment createAppointment() {
        return new Appointment();
    }

    @Override
    public Appointment createAppointmentUsingBuilder(IAppointmentBuilder appointmentBuilder) {
        return new Appointment(appointmentBuilder);
    }

    @Override
    public IAppointmentBuilder createAppointmentBuilder() {
        return new AppointmentBuilder();
    }
}
