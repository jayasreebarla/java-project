package com.dal.drplus.controller;

import com.dal.drplus.model.IBuilder.IReportBuilder;
import com.dal.drplus.model.IEntity.IReport;
import com.dal.drplus.model.factory.ModelFactory;
import com.dal.drplus.repository.implementation.ReportRepositoryImpl;
import com.dal.drplus.model.service.ReportService;
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
public class ReportUploadController {
    private final ReportService reportService;
    private int appointmentId;

    public ReportUploadController(ReportRepositoryImpl reportRepository) {
        this.reportService = new ReportService(reportRepository);
    }

    @GetMapping("/upload_report/{id}")
    public String uploadFile(@PathVariable int id){
        appointmentId=id;
        return "reports/report_upload";
    }

    @PostMapping("/upload_report")
    public RedirectView listUploadedFiles(HttpSession session, @RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        IReportBuilder reportBuilder = ModelFactory.instance().createReportBuilder();
        reportBuilder
                .addReportDetails(fileName)
                .addReportFile(file.getBytes())
                .addAppointmentId(appointmentId)
                .build();
        IReport report = ModelFactory.instance().createReportUsingBuilder(reportBuilder);
        if(report.validateReportDetails(fileName)) {
            boolean result = reportService.uploadReport(report);
        } else {
            return new RedirectView("upload_report/"+appointmentId);
        }
        String type = String.valueOf(session.getAttribute("Type"));
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

    @GetMapping("/download_file_report/{report_id}")
    public void showDownload(HttpServletResponse response, @PathVariable("report_id") int report_id)  {
        IReport report = reportService.downloadReport(report_id);
        try {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = "+report.getReportDetails();
            response.setHeader(headerKey,headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(report.getReportFile());
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/download_report/{appointmentId}")
    public String showReportList(Model model,@PathVariable("appointmentId") int appointmentId){
        model.addAttribute("reports",reportService.findAllbyAppointment(appointmentId));
        return "reports/report_list";
    }
}
