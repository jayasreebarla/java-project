package com.dal.drplus.model.service;
import com.dal.drplus.model.IEntity.IAppointment;
import com.dal.drplus.model.entity.Appointment;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;

import java.util.List;

public class AppointmentService {
    IAppointmentRepository appointmentRepository;

    public AppointmentService(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public boolean isAppointmentConflict(IAppointment appointment) {
        String slotDate = appointment.getAppointmentDate();
        String slotTime = appointment.getAppointmentTime();
        String patientId = appointment.getPatientId();
        IAppointmentRepository.StorageResult resConflict = appointmentRepository.isAppointmentConflict(slotDate, slotTime,patientId);
        if(resConflict.equals(IAppointmentRepository.StorageResult.SUCCESS)) {
            return true;
        } else {
            return false;
        }
    }

    public IAppointment findAppointmentbyId(int appointmentId){
        return appointmentRepository.findAppointmentById(appointmentId);
    }

    public boolean rescheduleAppointment(int slotId, int appointmentId) {
        IAppointmentRepository.StorageResult result = appointmentRepository.updateSlotId(slotId, appointmentId);
        if(IAppointmentRepository.StorageResult.SUCCESS.equals(result)){
            return true;
        } else {
            return false;
        }
    }

    public boolean bookAppointment(IAppointment appointment) {
        IAppointmentRepository.StorageResult result = appointmentRepository.saveAppointment(appointment);
        if(IAppointmentRepository.StorageResult.SUCCESS.equals(result)){
            return true;
        } else {
            return false;
        }
    }

    public boolean cancelAppointment(int appointmentId) {
        IAppointmentRepository.StorageResult result = appointmentRepository.deleteAppointmentById(appointmentId);
        if(IAppointmentRepository.StorageResult.SUCCESS.equals(result)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isAppointmentexistsforLabId(String labId){
        List<Appointment> appointmentList = appointmentRepository.findAppointmentByLabId(labId);
        if(appointmentList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteAppointmentbyLabId(String labId){
        if(isAppointmentexistsforLabId(labId))
        {
            IAppointmentRepository.StorageResult result = appointmentRepository.deleteAppointmentbyLabID(labId);

            if(IAppointmentRepository.StorageResult.SUCCESS.equals(result))
            {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isAppointmentexistsforPatientId(String patientId){
        List<Appointment> appointmentList = appointmentRepository.findAppointmentByPatientId(patientId);
        if(appointmentList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteAppointmentbyPatientId(String patientId){
        if(isAppointmentexistsforPatientId(patientId))
        {
            IAppointmentRepository.StorageResult result = appointmentRepository.deleteAppointmentbyPatientID(patientId);
            if(IAppointmentRepository.StorageResult.SUCCESS.equals(result))
            {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isAppointmentexistsforDoctor(String doctorId){
        List<Appointment> appointmentList = appointmentRepository.findAppointmentByDoctorId(doctorId);
        if(appointmentList.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteAppointmentbyDoctorId(String doctorId){
        if(isAppointmentexistsforDoctor(doctorId))
        {
            IAppointmentRepository.StorageResult result = appointmentRepository.deleteAppointmentbyDoctorID(doctorId);
            if(IAppointmentRepository.StorageResult.SUCCESS.equals(result))
            {
                return true;
            } else {
            return false;
            }
        }
        return true;
    }

    public boolean updateAppointmentByBillId(int billId,double amount){
        IAppointmentRepository.StorageResult result = appointmentRepository.updateAppointmentFee(billId,amount);
        if(result.equals(IAppointmentRepository.StorageResult.SUCCESS)){
            return true;
        }else{
            return false;
        }
    }
}
