package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_resource_pool")
public class tbl_resource_pool {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="intResourceId")
	    private Integer intResourceId;

	    @Column(name="vchResourceName")
	    private String vchResourceName;

	    @Column(name="vchResourceCode")
	    private String vchResourceCode;
	    
	    @Column(name="vchPlatform")
	    private String vchPlatform;
	    
	    @Column(name="vchLocation")
	    private String vchLocation;
	    
	    @Column(name="vchEngagementPlan")
	    private String vchEngagementPlan;
	    
	    @Column(name="vchExperience")
	    private String vchExperience;  
	    
	    @Column(name="dtmAllocationDate")
	    private Date dtmAllocationDate;
	    
	    @Column(name="vchPhoneNo")
	    private String vchPhoneNo;  
	    
	    @Column(name="vchEmail")
	    private String vchEmail;
	    
	    @Column(name="intCreatedBy")
	    private String intCreatedBy;  
	    
	    @Column(name="dtmCreatedOn")
	    private String dtmCreatedOn;
	    
	    @Column(name="intUpdatedBy")
	    private String intUpdatedBy;
	    
        
	    @Column(name="dtmUpdatedOn")
	    private Date dtmUpdatedOn;
	    
	    @Column(name="vchStatus")
	    private String vchStatus;
	    
	    @Column(name="bitDeletedFlag")
	    private String bitDeletedFlag;  
	    
	    
    
	
}

