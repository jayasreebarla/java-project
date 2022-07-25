package com.dal.drplus.service;
import com.dal.drplus.model.entity.Lab;
import com.dal.drplus.repository.implementation.LabRepositoryImpl;
import com.dal.drplus.repository.interfaces.ILabRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LabServiceTest {
    static Lab lab1 = new Lab ("lab1", "lab1", "lab1", "labtest1@gmail.com", "lab1", "123456", "123456");
    static Lab lab2 = new Lab ("lab2", "lab2", "lab2", "labtest2@gmail.com", "lab2", "123123", "123123");

    String labPassword = "lab1";
    String labPassword1 = "lab2";
    static List<Lab> unsortedList;

    private static LabService labService;
    private static ILabRepository labRepository;

    @BeforeAll
    public static void init(){
        labRepository = Mockito.mock(LabRepositoryImpl.class);
        labRepository = Mockito.mock(LabRepositoryImpl.class);
        lab1.setLabRating(3);
        lab2.setLabRating(4);
        unsortedList = new ArrayList<>();
        unsortedList.add(lab1);
        unsortedList.add(lab2);
        Mockito.when(labRepository.saveLab(lab1)).thenReturn(ILabRepository.StorageResult.SUCCESS);
        Mockito.when(labRepository.saveLab(lab2)).thenReturn(ILabRepository.StorageResult.FAILURE);
        Mockito.when(labRepository.getLabPasswordById("lab1")).thenReturn("lab1");
        Mockito.when(labRepository.getLabPasswordById("lab2")).thenReturn("lab2");
        Mockito.when(labRepository.findLabById("lab1")).thenReturn(lab1);
        Mockito.when(labRepository.findLabById("lab2")).thenReturn(null);
        Mockito.when(labRepository.deleteLabById("lab1")).thenReturn(ILabRepository.StorageResult.SUCCESS);
        Mockito.when(labRepository.deleteLabById("lab2")).thenReturn(ILabRepository.StorageResult.FAILURE);
        labService = new LabService(labRepository);
    }

    @Test
    public void listAllLabs(){
        List<Lab> lab = new ArrayList<>();
        lab.add(lab1);
        lab.add(lab2);
        Mockito.when(labRepository.findAll()).thenReturn(lab);
        List<Lab> lab_result = labService.listAllLabs();
        List<Lab> expectedLab = new ArrayList<>();
        expectedLab.add(lab1);
        expectedLab.add(lab2);
        assertIterableEquals(expectedLab,lab_result);
    }


    @Test
    public void listAllLabsWhenListIsNull(){
        Mockito.when(labRepository.findAll()).thenReturn(null);
        List<Lab> lab_result = labService.listAllLabs();
        assertIterableEquals(null,lab_result);
    }

    @Test
    public void listAllLabsWhenListIsEmpty(){
        List<Lab> lab = new ArrayList<>();
        Mockito.when(labRepository.findAll()).thenReturn(lab);
        assertEquals(0,lab.size());
    }

    @Test
    public void getLabByIdPass(){
        Lab lab_result =  labService.getLabById(lab1.getLabId());
        assertEquals(lab1,lab_result);
    }

    @Test
    public void getLabByIdFailNull(){
        Lab lab_result =  labService.getLabById(lab2.getLabId());
        assertNull(lab_result);
    }

    @Test
    public void deleteLabByIdPass(){
        boolean result = labService.deleteLabById(lab1.getLabId());
        assertTrue(result);
    }

    @Test
    public void deleteLabByIdFail(){
        boolean result = labService.deleteLabById(lab2.getLabId());
        assertFalse(result);
    }

    @Test
    public void filterLabOnPincodePass(){
        List<Lab> lab = new ArrayList<>();
        lab.add(lab1);
        lab.add(lab2);
        Mockito.when(labRepository.findAllLabsByPincode(lab1.getLabPincode())).thenReturn(lab);
        List<Lab> lab_result = labService.filterLabOnPincode(lab1.getLabPincode());
        List<Lab> expectedLab = new ArrayList<>();
        expectedLab.add(lab1);
        expectedLab.add(lab2);
        assertIterableEquals(expectedLab,lab_result);
    }

    @Test
    public void filterLabOnPincodeFailNull(){
        Mockito.when(labRepository.findAllLabsByPincode(lab1.getLabPincode())).thenReturn(null);
        List<Lab> lab_result = labService.filterLabOnPincode(lab1.getLabPincode());
        assertIterableEquals(null,lab_result);
    }

    @Test
    public void filterLabOnPincodeFailEmpty(){
        List<Lab> lab = new ArrayList<>();
        Mockito.when(labRepository.findAllLabsByPincode(lab1.getLabPincode())).thenReturn(lab);
        List<Lab> lab_result = labService.filterLabOnPincode(lab1.getLabPincode());;
        assertIterableEquals(lab,lab_result);
    }

    @Test
    public void filterLabOnPincodeFailIncorrectPincode(){
        List<Lab> lab = new ArrayList<>();
        Mockito.when(labRepository.findAllLabsByPincode(lab1.getLabPincode())).thenReturn(lab);
        List<Lab> lab_result = labService.filterLabOnPincode(lab2.getLabPincode());;
        assertIterableEquals(lab,lab_result);
    }

    @Test
    public void sortLabs(){
        List<Lab> result = labService.sortLabList(unsortedList);
        assertIterableEquals(unsortedList,result);
    }
}
