package com.dal.drplus.controller;

import com.dal.drplus.model.Prescription;
import com.dal.drplus.model.Report;
import com.dal.drplus.repository.implementation.PrescriptionRepositoryImpl;
import com.dal.drplus.service.PrescriptionService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrescriptionUploadController {

    private final PrescriptionService prescriptionService;
    private String appointmentId;

    public PrescriptionUploadController(PrescriptionRepositoryImpl prescriptionRepository) {
        this.prescriptionService = new PrescriptionService(prescriptionRepository);
    }

    @GetMapping("/upload/{id}")
    public String uploadFile(@PathVariable String id){
        System.out.println("prescription controller upload file appointment"+appointmentId);
        appointmentId=id;
        return "precription/prescription_upload";
    }

    @PostMapping("/upload")
    public RedirectView listUploadedFiles(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("list upload file"+appointmentId);
        String fileName = file.getOriginalFilename();
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(0);
        //prescription.setReportDetails(fileName);
        prescription.setPrescriptionDetails(fileName);
        //prescription.setReportFile(file.getBytes());
        prescription.setPrescription(file.getBytes());
        prescription.setAppointmentId(appointmentId);
        boolean result = prescriptionService.uploadPrescription(prescription);
        return new RedirectView("appointment_list");
    }

    @GetMapping("/download_file/{prescription_id}")
    public void showDownload(HttpServletResponse response, @PathVariable("prescription_id") int prescription_id)  {

        Prescription prescription = prescriptionService.downloadPrescription(prescription_id);
        try {
            System.out.println("inside download "+prescription.getPrescriptionId());
            System.out.println("inside download "+prescription.getPrescriptionDetails());
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = "+prescription.getPrescriptionDetails();
            response.setHeader(headerKey,headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(prescription.getPrescription());
            outputStream.close();
//            response.setContentType("application/pdf");
//            response.getOutputStream().write(report.getReportFile());
//            response.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/download/{appointment_id}")
    public String showReportList(Model model, @PathVariable("appointment_id") String appointment_id){
        model.addAttribute("prescription",prescriptionService.findAllbyAppointment(appointment_id));
        return "/prescription/prescription_list";
    }
}
