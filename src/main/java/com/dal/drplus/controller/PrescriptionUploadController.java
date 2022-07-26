package com.dal.drplus.controller;

import com.dal.drplus.model.IBuilder.IPrescriptionBuilder;
import com.dal.drplus.model.IEntity.IPrescription;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.PrescriptionRepositoryImpl;
import com.dal.drplus.model.service.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PrescriptionUploadController {
    private final PrescriptionService prescriptionService;
    private int appointmentId;

    public PrescriptionUploadController(PrescriptionRepositoryImpl prescriptionRepository) {
        this.prescriptionService = new PrescriptionService(prescriptionRepository);
    }

    @GetMapping("/upload_prescription/{id}")
    public String uploadFile(@PathVariable int id){
        appointmentId=id;
        return "prescription/prescription_upload";
    }

    @PostMapping("/upload_prescription")
    public RedirectView listUploadedFiles(HttpSession session,@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        IPrescriptionBuilder prescriptionBuilder = ModelFactory.instance().createPrescriptionBuilder();
        prescriptionBuilder
                .addPrescriptionDetails(fileName)
                .addPrescriptionFile(file.getBytes())
                .addAppointmentId(appointmentId)
                .build();

        IPrescription prescription = ModelFactory.instance().createPrescriptionUsingBuilder(prescriptionBuilder);
        boolean result = prescriptionService.uploadPrescription(prescription);
        String type = String.valueOf(session.getAttribute("Type"));
        return new RedirectView("/appointment_list/"+type);
    }

    @GetMapping("/download_file_prescription/{prescription_id}")
    public void showDownload(HttpServletResponse response, @PathVariable("prescription_id") int prescription_id)  {
        IPrescription prescription = prescriptionService.downloadPrescription(prescription_id);
        try {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = "+prescription.getPrescriptionDetails();
            response.setHeader(headerKey,headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(prescription.getPrescriptionFile());
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/download_prescription/{appointmentId}")
    public String showPrescriptionList(Model model, @PathVariable("appointmentId") int appointmentId){
        model.addAttribute("prescription",prescriptionService.findAllbyAppointment(appointmentId));
        return "/prescription/prescription_list";
    }
}