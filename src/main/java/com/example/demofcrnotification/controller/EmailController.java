package com.example.demofcrnotification.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demofcrnotification.dto.MailRequest;
import com.example.demofcrnotification.dto.MailResponse;
import com.example.demofcrnotification.service.EmailService;

@RestController
public class EmailController {
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendFcrMail")
    public MailResponse sendMailResponse(@RequestBody MailRequest mailRequest){

        // mailRequest.setEmpCode("SMP100062");
        // mailRequest.setEmpName("Davinder Singh");
        // mailRequest.setDesignation("Software Engineer");
        // mailRequest.setDept("Technology and Research");
        // mailRequest.setManager("Nadarjot Singh");
        // mailRequest.setHod("Varundeep Kaur");
        // mailRequest.setLocation("Mohali");

        List<MailRequest.Details>detailsList = new ArrayList<>();
        MailRequest.Details fcrData = new MailRequest.Details();
        fcrData.setFcrId("FCR-2020");
        fcrData.setApplicationName("Prepaid Card");
        fcrData.setSource("Mohali");
        fcrData.setSourceIp("172.25.14.140");
        fcrData.setDestination("Panvel");
        fcrData.setDestinationIp("192.168.148.19");
        fcrData.setPortNo("80.433");
        fcrData.setExpireDate("Apr-11-2023");
        detailsList.add(fcrData);
        // mailRequest.setFcrDetail(detailsList);

        Map<String, Object> details = new HashMap<>();

        details.put("empCode", "SMP100062");
        details.put("empName", "Davinder Singh");
        details.put("designation", "Software Engineer");
        details.put("dept", "Technology and Reserch");
        details.put("manager", "Nadarjot Singh");
        details.put("hod", "Varundeep Kaur");
        details.put("location", "Mohali");
        // details.put("fcrDetails", Arrays.asList(detailsList));
        details.put("fcrDetails", fcrData);
        mailRequest.setFcrDetailsMap(details);

        return emailService.sendMail(mailRequest, details);
    }
}
