package com.dal.drplus.service;

import com.dal.drplus.model.Report;
import com.dal.drplus.repository.interfaces.IReportRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class ReportService {

    private IReportRepository reportRepository;

    public ReportService(IReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public boolean uploadReport(Report report){
        try {
            IReportRepository.StorageResult result = reportRepository.uploadReport(report);
            if(result.equals(IReportRepository.StorageResult.SUCCESS)){
                return true;
            }else{
                return false;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReport(){

    }

    public Report downloadReport(int id){
        return reportRepository.getReportbyId(id);


    }

    public List<Report> findAllbyAppointment(String appointmentId){

        return reportRepository.findAllbyAppointment(appointmentId);

    }
//
//    public Optional<Object> loadAll() {
//    }
//
//    public Resource loadAsResource(String filename) {
//    }
//
//    public void store(MultipartFile file) {
//    }
}
