package com.dal.drplus.service;
import com.dal.drplus.model.Report;
import com.dal.drplus.repository.implementation.ReportRepositoryImpl;
import com.dal.drplus.repository.interfaces.IReportRepository;
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
        reportRepository = Mockito.mock(ReportRepositoryImpl.class);
        Mockito.when(reportRepository.uploadReport(report)).thenReturn(IReportRepository.StorageResult.SUCCESS);
        Mockito.when(reportRepository.uploadReport(report)).thenReturn(IReportRepository.StorageResult.FAILURE);
        reportService = new ReportService(reportRepository);
    }
}
