package com.dal.drplus.controller;

import com.dal.drplus.model.Appointment;
import com.dal.drplus.model.Billing;
import com.dal.drplus.model.Patient;
import com.dal.drplus.repository.implementation.WalletRepositoryImpl;
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
public class WalletController {

    private WalletService walletService;
    private Billing bill_process;
    public WalletController(WalletRepositoryImpl walletRepository) {
        this.walletService = new WalletService(walletRepository);
    }

    @GetMapping("/add_money_in_wallet")
    public String AddMoneyToWalletForPayment(Model model,@ModelAttribute("bill")Billing bill){
        bill_process = bill;
        System.out.println("Bill amount in wallet controller "+ bill);
         model.addAttribute("billAmount",bill_process.getBillAmount());
        return "wallet/add-money-to-wallet";
    }

    @PostMapping("/add_money_in_wallet")
    public RedirectView AddMoneyForPayment(RedirectAttributes attributes, HttpSession session){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        String patientEmail = currentPatient.getPatientEmail();
        double billAmount = bill_process.getBillAmount();
        walletService.addMoneyToWallet(billAmount,patientEmail);
        System.out.println("Post mapping bill amount "+billAmount);
        attributes.addFlashAttribute("bill",bill_process);
        return new RedirectView("/payment");
    }

    @GetMapping("/add-payment")
    public String AddMoneyPage(Model model,HttpSession session){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        String patientEmail = currentPatient.getPatientEmail();
        double balance = walletService.getBalanceFromWallet(patientEmail);
        model.addAttribute("balance",Double.toString(balance));
        return "wallet/add-money";
    }

    @PostMapping("/add-payment")
    public RedirectView addMoneyToWallet(HttpSession session,@RequestParam("amount") String amount){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        String patientEmail = currentPatient.getPatientEmail();
        walletService.addMoneyToWallet(Double.parseDouble(amount),patientEmail);
        return new RedirectView("/payment-success");
    }

    @GetMapping("/payment-success")
    public String Balance(HttpSession session,Model model){
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        String patientEmail = currentPatient.getPatientEmail();
        double balance = walletService.getBalanceFromWallet(patientEmail);
        model.addAttribute("balance",Double.toString(balance));
        return "wallet/payment-success";
    }


}

