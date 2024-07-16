package com.example.demo.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.tbl_resource_pool;
import com.example.demo.entity.tbl_resource_pool_history;
import com.example.demo.repo.ExcelUploadEmployeeRepository;
import com.example.demo.repo.Tbl_resource_pool_Repository;
import com.example.demo.utils.ExcelUtils;

@Service
public class Tbl_resource_pool_Service {

	@Autowired
	private ExcelUploadEmployeeRepository ExcelEmpRepo;
	
	@Autowired
	private Tbl_resource_pool_Repository tbl_resource_pool_Repository;
	
	public void save(MultipartFile file) {
		
		try {
			List<tbl_resource_pool_history> ExcelEmp=ExcelUtils.convertExceltoListofEmployee(file.getInputStream());
			
			List<tbl_resource_pool> tbl_resource_pool= tbl_resource_pool_Repository.get_tbl_resource_pool();
			
			List<tbl_resource_pool_history> tbl_resource_poolNotMatch=new ArrayList<>(ExcelEmp.size());
			
			List<tbl_resource_pool> tbl_resource_poolNotMatch1=new ArrayList<>();
			
			List<tbl_resource_pool> tbl_resource_poolMatch=new ArrayList<>((tbl_resource_pool.size()));
			
			tbl_resource_poolNotMatch.addAll(ExcelEmp);
			tbl_resource_poolMatch.addAll(tbl_resource_pool);
			
			if(CollectionUtils.isNotEmpty(tbl_resource_pool)) {
				
				for(int i=0;i<ExcelEmp.size();i++)
			   {
				
				//tbl_resource_pool NotMatch=new tbl_resource_pool();
				
				for(int j=0;j<tbl_resource_pool.size();j++) {
				
					
				if(ExcelEmp.get(i).getVchResourceCode().equalsIgnoreCase(tbl_resource_pool.get(j).getVchResourceCode())) {
					
					//tbl_resource_poolNotMatch.remove(i);
					
					String ResourceCodeExcel=ExcelEmp.get(i).getVchResourceCode();
					String ResourceCodepool=tbl_resource_pool.get(j).getVchResourceCode();
					
					/*************************Updated Data that is not Present in Excel (Tagged Resources)******************/
					
					tbl_resource_poolMatch.removeIf(obj->obj.getVchResourceCode().equalsIgnoreCase(ResourceCodepool));
					
					/***************Excel Data Uploaded that is not Matched Current Data(New Resources)******************/				
					
					tbl_resource_poolNotMatch.removeIf(obj->obj.getVchResourceCode().equalsIgnoreCase(ResourceCodeExcel));
					
				} //else {
					
					/*
					 * NotMatch.setVchResourceName(ExcelEmp.get(i).getVchResourceName());
					 * NotMatch.setVchResourceCode(ExcelEmp.get(i).getVchResourceCode());
					 * NotMatch.setVchPlatform(ExcelEmp.get(i).getVchPlatform());
					 * NotMatch.setVchEmail(ExcelEmp.get(i).getVchEmail());
					 * NotMatch.setVchPhoneNo(ExcelEmp.get(i).getVchPhoneNo());
					 * NotMatch.setVchLocation(ExcelEmp.get(i).getVchLocation());
					 * NotMatch.setVchEngagementPlan(ExcelEmp.get(i).getVchEngagementPlan());
					 * NotMatch.setVchExperience(ExcelEmp.get(i).getVchExperience());
					 * NotMatch.setDtmAllocationDate(ExcelEmp.get(i).getDtmAllocationDate());
					 * NotMatch.setBitDeletedFlag("0"); NotMatch.setVchStatus("A");
					 */
					
					//tbl_resource_poolNotMatch.add(NotMatch);
					
				//}
				
				
				}
			
				
				}
				
				
				/***************************************Updated Data that is not Present in Excel (Tagged Resources)***************************************/
				
				   for(int j=0;j<tbl_resource_poolMatch.size();j++) {
				   		
					SimpleDateFormat formatter =new SimpleDateFormat();
					Date date =new Date();
					
					tbl_resource_poolMatch.get(j).setVchStatus("D");
					tbl_resource_poolMatch.get(j).setBitDeletedFlag("1");
					tbl_resource_poolMatch.get(j).setDtmUpdatedOn(date);
					
				
				
			     this.tbl_resource_pool_Repository.saveAll(tbl_resource_poolMatch);
				}
			
				
				/*******************************Excel Data Uploaded that is not Matched Current Data(New Resources)*************************************/
				
				for(int j=0;j<tbl_resource_poolNotMatch.size();j++) {
					tbl_resource_pool Emp=new tbl_resource_pool();	
					
					Emp.setVchResourceName(tbl_resource_poolNotMatch.get(j).getVchResourceName()); 
					Emp.setVchResourceCode(tbl_resource_poolNotMatch.get(j).getVchResourceCode());
					Emp.setVchPlatform(tbl_resource_poolNotMatch.get(j).getVchPlatform());
					Emp.setVchEmail(tbl_resource_poolNotMatch.get(j).getVchEmail());
					Emp.setVchPhoneNo(tbl_resource_poolNotMatch.get(j).getVchPhoneNo());
					Emp.setVchLocation(tbl_resource_poolNotMatch.get(j).getVchLocation());
					Emp.setVchEngagementPlan(tbl_resource_poolNotMatch.get(j).getVchEngagementPlan());
					Emp.setVchExperience(tbl_resource_poolNotMatch.get(j).getVchExperience());
					Emp.setDtmAllocationDate(tbl_resource_poolNotMatch.get(j).getDtmAllocationDate());
					Emp.setBitDeletedFlag("0");
					Emp.setVchStatus("A");
					tbl_resource_poolNotMatch1.add(Emp);
				
				
			this.tbl_resource_pool_Repository.saveAll(tbl_resource_poolNotMatch1);
			
			}
			
			}else{
				
				/*************For First Time Data is Uploaded in tbl_resource_pool***************/
				
				for(int j=0;j<ExcelEmp.size();j++) {
					tbl_resource_pool Emp=new tbl_resource_pool();	
					
					Emp.setVchResourceName(ExcelEmp.get(j).getVchResourceName()); 
					Emp.setVchResourceCode(ExcelEmp.get(j).getVchResourceCode());
					Emp.setVchPlatform(ExcelEmp.get(j).getVchPlatform());
					Emp.setVchEmail(ExcelEmp.get(j).getVchEmail());
					Emp.setVchPhoneNo(ExcelEmp.get(j).getVchPhoneNo());
					Emp.setVchLocation(ExcelEmp.get(j).getVchLocation());
					Emp.setVchEngagementPlan(ExcelEmp.get(j).getVchEngagementPlan());
					Emp.setVchExperience(ExcelEmp.get(j).getVchExperience());
					Emp.setDtmAllocationDate(ExcelEmp.get(j).getDtmAllocationDate());
					Emp.setBitDeletedFlag("0");
					Emp.setVchStatus("A");
					tbl_resource_poolNotMatch1.add(Emp);
				}
				
				this.tbl_resource_pool_Repository.saveAll(tbl_resource_poolNotMatch1);	
				
			}
			
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<tbl_resource_pool> getAllEmploye(){
		return this.tbl_resource_pool_Repository.findAll();
		
	}
	
	//For Updating Any Resource
	public String addorUpdateEmployee(tbl_resource_pool emp) {
		
		Integer empid=emp.getIntResourceId();
		System.out.println(emp);
		
		
		tbl_resource_pool_Repository.save(emp);
		System.out.println(emp);
		if (empid==null)
		{
		
		return "Record Inserted";}
		else{
		return "Record Updated";	
		}
	}
	
	
	// For Deleting Any Resource
	public String delete(Integer id) {
		// TODO Auto-generated method stub
		tbl_resource_pool_Repository.deleteById(id);
		return "Resource Deleted";
	}
	
	
	@SuppressWarnings("deprecation")
	public tbl_resource_pool getTalentById(Integer id) {
		// TODO Auto-generated method stub
		return tbl_resource_pool_Repository.findById(id).get();
		
	}
	
	
	
	

}
