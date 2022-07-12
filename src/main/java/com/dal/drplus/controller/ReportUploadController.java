package com.dal.drplus.controller;
import com.dal.drplus.model.Report;
import com.dal.drplus.repository.implementation.ReportRepositoryImpl;
import com.dal.drplus.repository.interfaces.IReportRepository;
import com.dal.drplus.service.ReportService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

//import com.example.uploadingfiles.storage.StorageFileNotFoundException;
//import com.example.uploadingfiles.storage.StorageService;

@Controller
public class ReportUploadController {



    private final ReportService reportService;
    private String appointmentId;

    public ReportUploadController(ReportRepositoryImpl reportRepository) {
        this.reportService = new ReportService(reportRepository);
    }

    @GetMapping("/upload/{id}")
    public String uploadFile(@PathVariable String id){
        System.out.println("report controller upload file appointment"+appointmentId);
        appointmentId=id;
        return "reports/report_upload";
    }

    @PostMapping("/upload")
    public RedirectView listUploadedFiles(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("list upload file"+appointmentId);
        String fileName = file.getOriginalFilename();
        Report report = new Report();
        report.setReportId(0);
        report.setReportDetails(fileName);
        report.setReportFile(file.getBytes());
        report.setAppointmentId(appointmentId);
        boolean result = reportService.uploadReport(report);
        return new RedirectView("appointment_list");
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


    @GetMapping("/download_file/{report_id}")
    public void showDownload(HttpServletResponse response, @PathVariable("report_id") int report_id)  {

        Report report = reportService.downloadReport(report_id);
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

    @GetMapping("/download/{appointment_id}")
    public String showReportList(Model model,@PathVariable("appointment_id") String appointment_id){
        model.addAttribute("reports",reportService.findAllbyAppointment(appointment_id));
        return "/reports/report_list";
    }
}
