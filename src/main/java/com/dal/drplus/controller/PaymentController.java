package com.dal.drplus.controller;

import com.dal.drplus.model.entity.Billing;
import com.dal.drplus.model.entity.Patient;
import com.dal.drplus.repository.implementation.AppointmentRepositoryImpl;
import com.dal.drplus.repository.implementation.BillRepositoryImpl;
import com.dal.drplus.repository.implementation.PromotionsRepositoryImpl;
import com.dal.drplus.repository.implementation.WalletRepositoryImpl;
import com.dal.drplus.model.service.AppointmentService;
import com.dal.drplus.model.service.BillService;
import com.dal.drplus.model.service.PromotionsService;
import com.dal.drplus.model.service.WalletService;
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
    private WalletService walletService;
    private PromotionsService promotionsService;
    private AppointmentService appointmentService;
    private double finalAmount;

    public PaymentController(BillRepositoryImpl billRepository, WalletRepositoryImpl walletRepository, PromotionsRepositoryImpl promotionsRepository, AppointmentRepositoryImpl appointmentRepository) {
        this.walletService = new WalletService(walletRepository);
        this.billService = new BillService(billRepository);
        this.promotionsService = new PromotionsService(promotionsRepository);
        this.appointmentService = new AppointmentService(appointmentRepository);
    }

    @GetMapping("/payment")
    public String payment(HttpSession session, Model model, @ModelAttribute("bill")Billing bill){
        bill_process = bill;
        String bill_amount = Double.toString(bill.getBillAmount());
        model.addAttribute("billAmount", bill_amount);
        return "payment/payment";
    }

    @PostMapping("/confirm-payment")
    public RedirectView paymentConfirmation( @RequestParam("promotion") String promoCode,RedirectAttributes attributes, HttpSession session,Model model){
        int discount;
        if(promoCode.equals("")){
            discount=0;
        }else{
            discount = promotionsService.validatePromotionAndGetDiscountAmount(promoCode);
        }
        double billAmount = bill_process.getBillAmount();
        double billFinalAmount;
        if(billAmount-discount>0){
            billFinalAmount = billAmount-discount;
        }else{
            billFinalAmount =0;
        }
        billService.updateBill(bill_process.getBillId(), billFinalAmount);
        boolean res = appointmentService.updateAppointmentByBillId(bill_process.getBillId(), billFinalAmount);
        attributes.addFlashAttribute("billFinalAmount",billFinalAmount);
        return new RedirectView("/make-confirm-payment");
    }

    @GetMapping("/make-confirm-payment")
    public String makePaymentConfirmation(Model model,@ModelAttribute("billFinalAmount") double billFinalAmount ){
        finalAmount = billFinalAmount;
        model.addAttribute("billAmount",finalAmount);
        return "payment/confirm-payment";
    }

    @PostMapping("/payment")
    public RedirectView processPayment(RedirectAttributes attributes, HttpSession session){
        double billAmount = finalAmount;
        Patient currentPatient = (Patient) session.getAttribute("CurrentPatient");
        String patientEmail = currentPatient.getPatientEmail();
        if(walletService.getBalanceFromWallet(patientEmail)>=billAmount){
            boolean result = walletService.deductMoneyFromWallet(billAmount,patientEmail);
            if(result == true){
                return new RedirectView("/appointment_booked_page");
            }else {
                attributes.addFlashAttribute("bill",bill_process);
                return new RedirectView("/payment");
            }
        }else{
            bill_process.setBillAmount(finalAmount);
            attributes.addFlashAttribute("bill",bill_process);
            return new RedirectView("/add_money_in_wallet");
        }
    }
}
