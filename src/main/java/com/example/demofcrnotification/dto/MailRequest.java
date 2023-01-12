package com.example.demofcrnotification.dto;
// import java.util.Map;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MailRequest {
    
    private String to;
    private String from;
    private String subject;
    private String empCode;
    private String empName;
    private String designation;
    private String dept;
    private String manager;
    private String hod;
    private String location;
    private Map<String, Object> fcrDetailsMap;
    private List<Details> fcrDetail;

    @Data
    public static class Details{
        private String fcrId;
        private String applicationName;
        private String source;
        private String sourceIp;
        private String destination;
        private String destinationIp;
        private String portNo;
        private String expireDate;
    }
}
