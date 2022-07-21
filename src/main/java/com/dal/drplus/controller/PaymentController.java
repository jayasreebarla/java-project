package com.dal.drplus.controller;

import com.dal.drplus.model.Appointment;
import com.dal.drplus.model.Billing;
import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.implementation.BillRepositoryImpl;
import com.dal.drplus.repository.implementation.WalletRepositoryImpl;
import com.dal.drplus.service.BillService;
import com.dal.drplus.service.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class PaymentController {

    private BillService billService;
    private Billing bill_process;
    WalletService walletService;

    public PaymentController(BillRepositoryImpl billRepository, WalletRepositoryImpl walletRepository) {

        this.walletService = new WalletService(walletRepository);
        this.billService = new BillService(billRepository);
    }

    @GetMapping("/payment")
    public String payment(HttpSession session, Model model, @ModelAttribute("bill")Billing bill){
        bill_process = bill;
        //System.out.println("controller payment appointment details : "+appointment.getBillId()+appointment.getPatientId()+appointment.getDoctorId());
        System.out.println("payment controller , bill amount :"+bill.getBillAmount());
        //int amount = billService.getBillAmount(billId);
//        model.addAttribute("appointment",appointment);
        //model.addAttribute("bill_id",billId);
        String bill_amount = Double.toString(bill.getBillAmount());
        model.addAttribute("billAmount", bill_amount);
        return "payment/payment";
    }

    @PostMapping("/payment")
    public RedirectView processPayment(RedirectAttributes attributes, HttpSession session){
        double billAmount = bill_process.getBillAmount();
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        String patientEmail = currentPatient.getPatientEmail();
        System.out.println("bill amount inside post mapping"+billAmount);
        System.out.println("wallet money for patient : "+walletService.getBalanceFromWallet(patientEmail));
        if(walletService.getBalanceFromWallet(patientEmail)>=billAmount){
            boolean result = walletService.deductMoneyFromWallet(billAmount,patientEmail);
            if(result == true){
                return new RedirectView("/appointment_booked_page");
            }else {
                attributes.addFlashAttribute("bill",bill_process);
                return new RedirectView("/payment");
            }
        }else{
            attributes.addFlashAttribute("bill",bill_process);
            return new RedirectView("/add_money_in_wallet");
        }
    }
}
