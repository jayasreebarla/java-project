package com.dal.drplus.model.factory;

import com.dal.drplus.model.Builder.*;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.ILabBuilder;
import com.dal.drplus.model.IBuilder.IPrescriptionBuilder;
import com.dal.drplus.model.IBuilder.IPromotionsBuilder;
import com.dal.drplus.model.IBuilder.*;
import com.dal.drplus.model.IBuilder.IBillingBuilder;
import com.dal.drplus.model.IBuilder.IDoctorBuilder;
import com.dal.drplus.model.IBuilder.IRatingDoctorBuilder;
import com.dal.drplus.model.IBuilder.IWalletBuilder;
import com.dal.drplus.model.IEntity.*;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.model.entity.Promotions;
import com.dal.drplus.model.entity.*;
import com.dal.drplus.model.entity.Billing;
import com.dal.drplus.model.entity.Doctor;
import com.dal.drplus.model.entity.RatingDoctor;
import com.dal.drplus.model.entity.Wallet;

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

    public IWallet createWallet();
    public Wallet createWalletUsingBuilder(IWalletBuilder builder);
    public IWalletBuilder createWalletBuilder();

    public IPatient createPatient();
    public Patient createPatientUsingBuilder(IPatientBuilder builder);
    public IPatientBuilder createPatientBuilder();

    public ILab createLab();
    public ILab createLabUsingBuilder(ILabBuilder builder);

    public ILabBuilder createLabBuilder();

    public ILabSchedule createLabSchedule();
    public LabSchedule createLabScheduleUsingBuilder(ILabScheduleBuilder builder);
    public ILabScheduleBuilder createLabScheduleBuilder();

    public IDoctorSchedule createDoctorSchedule();
    public DoctorSchedule createDoctorScheduleUsingBuilder(IDoctorScheduleBuilder builder);
    public IDoctorScheduleBuilder createDoctorScheduleBuilder();

    public IRatingLab createRatingLab();
    public RatingLab createRatingLabUsingBuilder(IRatingLabBuilder builder);
    public IRatingLabBuilder createRatingLabBuilder();

    public IPromotions createPromotions();
    public Promotions createPromotionsUsingBuilder(IPromotionsBuilder builder);

    public IPromotionsBuilder createPromotionsBuilder();

    public IPrescription createPrescription();
    public IPrescription createPrescriptionUsingBuilder(IPrescriptionBuilder prescriptionBuilder);
    public IPrescriptionBuilder createPrescriptionBuilder();
}
