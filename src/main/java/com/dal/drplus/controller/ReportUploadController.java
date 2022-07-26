package com.dal.drplus.controller;
import com.dal.drplus.model.IBuilder.IReportBuilder;
import com.dal.drplus.model.IEntity.IReport;
import com.dal.drplus.model.entity.Report;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.ReportRepositoryImpl;
import com.dal.drplus.service.ReportService;
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

//import com.example.uploadingfiles.storage.StorageFileNotFoundException;
//import com.example.uploadingfiles.storage.StorageService;

@Controller
public class ReportUploadController {
    private final ReportService reportService;
    private int appointmentId;

    public ReportUploadController(ReportRepositoryImpl reportRepository) {
        this.reportService = new ReportService(reportRepository);
    }


    @GetMapping("/upload_report/{id}")
    public String uploadFile(@PathVariable int id){
        System.out.println("report controller upload file appointment"+appointmentId);
        appointmentId=id;
        return "reports/report_upload";
    }

    @PostMapping("/upload_report")
    public RedirectView listUploadedFiles(HttpSession session, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("list upload file"+appointmentId);
        String fileName = file.getOriginalFilename();

        IReportBuilder reportBuilder = ModelFactory.instance().createReportBuilder();
        reportBuilder
                .addReportDetails(fileName)
                .addReportFile(file.getBytes())
                .addAppointmentId(appointmentId)
                .build();
        IReport report = ModelFactory.instance().createReportUsingBuilder(reportBuilder);
//        report.setReportId(0);
//        report.setReportDetails(fileName);
//        report.setReportFile(file.getBytes());
//        report.setAppointmentId(appointmentId);
        boolean result = reportService.uploadReport(report);
        //boolean result2 = labService.
        String type = String.valueOf(session.getAttribute("Type"));
        System.out.print("type of appointment"+type);
        return new RedirectView("/appointment_list/"+type);

    }

    @GetMapping("/appointment_list/D")
    public RedirectView doctorAppointmentList(){
        return new RedirectView("/auth_doctor/doctor_home");
    }

    @GetMapping("/appointment_list/L")
    public RedirectView labAppointmentList(){
        return new RedirectView("/auth_lab/lab_home");
    }

    @GetMapping("/appointment_list/P")
    public RedirectView errorPage(){
        return new RedirectView("/appointment_list");
    }

//    @GetMapping("/download")
//    public void downloadFile(@Param("id") Long id , Model model, HttpServletResponse response) throws IOException {
//        Optional<Student> temp = service.findStudentById(id);
//        if(temp!=null) {
//            Student student = temp.get();
//            response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
//            String headerKey = "Content-Disposition";
//            String headerValue = "attachment; filename = "+student.getProfilePicture();
//            response.setHeader(headerKey, headerValue);
//            ServletOutputStream outputStream = response.getOutputStream();
//            outputStream.write(student.getContent());
//            outputStream.close();
//        }
//    }


    @GetMapping("/download_file_report/{report_id}")
    public void showDownload(HttpServletResponse response, @PathVariable("report_id") int report_id)  {

        IReport report = reportService.downloadReport(report_id);
        try {
            System.out.println("inside download "+report.getReportId());
            System.out.println("inside download "+report.getReportDetails());
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = "+report.getReportDetails();
            response.setHeader(headerKey,headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(report.getReportFile());
            outputStream.close();
//            response.setContentType("application/pdf");
//            response.getOutputStream().write(report.getReportFile());
//            response.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/download_report/{appointmentId}")
    public String showReportList(Model model,@PathVariable("appointmentId") int appointmentId){
        model.addAttribute("reports",reportService.findAllbyAppointment(appointmentId));
        //model.addAttribute("reports",Service.findAllbyAppointment(appointmentId));
        return "/reports/report_list";
    }
}
