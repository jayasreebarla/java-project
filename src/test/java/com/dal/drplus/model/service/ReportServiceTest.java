package com.dal.drplus.model.service;
import com.dal.drplus.model.entity.Report;
import com.dal.drplus.model.service.ReportService;
import com.dal.drplus.repository.implementation.ReportRepositoryImpl;
import com.dal.drplus.repository.interfaces.IReportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
public class ReportServiceTest {
    private static Report report = new Report();
    private static ReportService reportService;
    private static IReportRepository reportRepository;

    @BeforeAll
    public static void init() throws FileNotFoundException {
        reportRepository = Mockito.mock(ReportRepositoryImpl.class);
        Mockito.when(reportRepository.uploadReport(report)).thenReturn(IReportRepository.StorageResult.FAILURE);
        reportService = new ReportService(reportRepository);
    }

    @Test
    public void uploadReportTest() throws Exception{
        Mockito.when(reportRepository.uploadReport(report)).thenReturn(IReportRepository.StorageResult.SUCCESS);
        Assertions.assertEquals(true, reportService.uploadReport(report));
    }

    @Test
    public void uploadReportTest2() throws Exception{
        Mockito.when(reportRepository.uploadReport(report)).thenReturn(IReportRepository.StorageResult.FAILURE);
        Assertions.assertEquals(false, reportService.uploadReport(report));
    }

    @Test
    public void downloadReportTestPass() throws Exception{
        Mockito.when(reportRepository.getReportbyId(1)).thenReturn(report);
        Assertions.assertEquals(report, reportService.downloadReport(1));
    }
}
