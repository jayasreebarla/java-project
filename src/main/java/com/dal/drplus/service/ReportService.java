package com.dal.drplus.service;

import com.dal.drplus.model.Report;
import com.dal.drplus.repository.interfaces.IReportRepository;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Optional;

public class ReportService {

    private IReportRepository reportRepository;

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
