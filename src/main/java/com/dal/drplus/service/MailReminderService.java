package com.dal.drplus.service;

import com.dal.drplus.model.IEntity.IPatient;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.repository.interfaces.IAppointmentRepository;
import com.dal.drplus.repository.interfaces.IDoctorScheduleRepository;
import com.dal.drplus.repository.interfaces.ILabScheduleRepository;
import com.dal.drplus.repository.interfaces.IPatientRepository;
import com.dal.drplus.service.interfaces.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class MailReminderService implements MailService {

    ILabScheduleRepository labScheduleRepository;
    IDoctorScheduleRepository doctorScheduleRepository;
    IPatientRepository patientRepository;
    IAppointmentRepository appointmentRepository;

    public MailReminderService(ILabScheduleRepository labScheduleRepository, IDoctorScheduleRepository doctorScheduleRepository,IAppointmentRepository appointmentRepository,IPatientRepository patientRepository) {
        this.labScheduleRepository = labScheduleRepository;
        this.doctorScheduleRepository = doctorScheduleRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public String returnTomorrowDate(){
        LocalDate today = LocalDate.now();
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);
        System.out.println("tomorrow : "+tomorrow);

        return tomorrow;
    }

//    @Override
//    public String populateMailMessageForDoctorAppointment(Patient patient,String date) {
//        StringBuilder appointmentDetails = new StringBuilder();
//        String patientName = patient.getPatientName();
//        int appointmentId =
//        return null;
//    }


    @Override
    public void sendMail() {

        String tomorrow = returnTomorrowDate();
        List<Patient> patients = getPatientIdsToMail(tomorrow);
        System.out.println("size"+patients.size());

        System.out.println("inside send mail");
        String message = "hello";
        String subject = "Regarding Appointment Mail";
        //String to = "connect.parulraich@gmail.com";
        //String to = patient.getPatientEmail();
        String from = "brainydeveloper4@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        for (Patient patient:patients){
            System.out.println("email"+patient.getPatientEmail());
        }

        Session session= Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("brainydeveloper4@gmail.com","kjllarntahxfzynv");
            }
        });

        session.setDebug(true);
        MimeMessage m = new MimeMessage(session);
        try {
            Iterator<Patient> iterator = patients.iterator();
            while(iterator.hasNext()) {
                Patient p = iterator.next();
                String to = p.getPatientEmail();
                System.out.println("to email"+to);
                m.setFrom(from);
                m.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
                //m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(subject);
                m.setText(message);
                Transport.send(m);
                System.out.println("Sent success");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Patient> getPatientIdsToMail(String tomorrow){
        List<Patient> patientList = new ArrayList<>();
        //get all slot ids where date equal to
        List<Integer> slotList = doctorScheduleRepository.getAllSlotIds(tomorrow);
        //System.out.println("size slot"+slotList.size());
        //Iterator<Integer> iterator = slotList.iterator();
        for (Integer slot:slotList) {
            System.out.println("slot value"+slot);
        }
        for (Integer slot:slotList){
            //System.out.println("slot iterator value"+iterator.next());
            //int iter = iterator.next();
            //int slotId=slotList.get(iter);
            System.out.println("inside service, slot id :"+slot);
            String patient_id = appointmentRepository.getPatientIdBySlotId(slot);
            System.out.println("inside get service patient id"+patient_id);
            IPatient patient = patientRepository.findPatientById(patient_id);
            patientList.add((Patient) patient);
        }
        return patientList;
    }


}
