package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.tbl_resource_pool_history;
import com.example.demo.repo.ExcelUploadEmployeeRepository;
import com.example.demo.utils.ExcelUtils;

@Service
public class ExcelUploadEmployeeService {

	@Autowired
	private ExcelUploadEmployeeRepository ExcelEmpRepo;
	
	public void save(MultipartFile file) {
		
		try {
			List<tbl_resource_pool_history> ExcelEmp=ExcelUtils.convertExceltoListofEmployee(file.getInputStream());
			this.ExcelEmpRepo.saveAll(ExcelEmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<tbl_resource_pool_history> getAllEmploye(){
		return this.ExcelEmpRepo.findAll();
		
	}
	
	
	
	
	
	
	
	
	
	

}
