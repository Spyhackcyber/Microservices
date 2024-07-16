package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.tbl_resource_pool;
import com.example.demo.entity.tbl_resource_pool_history;
import com.example.demo.service.ExcelUploadEmployeeService;
import com.example.demo.service.Tbl_resource_pool_Service;
import com.example.demo.utils.ExcelUtils;


@RestController
@CrossOrigin("*")
public class ExcelUploadEmployeeController {

	@Autowired
	private ExcelUploadEmployeeService excelempservice;
	
	@Autowired
	private Tbl_resource_pool_Service tbl_resource_pool_Service;
	
	@GetMapping("/welcome")
	public String hello(){
		return "Amit Kumar Mittal";
		
	}
	
	@PostMapping("/emp/upload")
	public ResponseEntity<?> UploadExcel (@RequestParam("file") MultipartFile file){
		
		if(ExcelUtils.CheckExcelFormat(file)) {
			this.excelempservice.save(file);
			this.tbl_resource_pool_Service.save(file);
			return ResponseEntity.ok(Map.of("message","File is Uploaded successfully and Saved in Database"));
		}
		
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel File Only");
	}
	
	@GetMapping("/emp/uploadedDatahistory")
	public List<tbl_resource_pool_history> gettbl_resource_pool_history(){
		return this.excelempservice.getAllEmploye();
		
	}
	
	@GetMapping("/emp/uploadedData")
	public List<tbl_resource_pool> gettbl_resource_pool(){
		return this.tbl_resource_pool_Service.getAllEmploye();
		
	}
	
	//Get Particular Resource From Talent Resource Pool
	@GetMapping("/emp/talent/{id}")
	public tbl_resource_pool getTalentById(@PathVariable Integer id){ 
		System.out.println(id);
		return tbl_resource_pool_Service.getTalentById(id);
		//return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	//For Updating Talent Pool Resource
	@PutMapping("/emp/talent")
	public ResponseEntity<String> updateEmployee (@RequestBody tbl_resource_pool emp){ 
		
		String msg =tbl_resource_pool_Service.addorUpdateEmployee(emp);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	
	//For Deleting Talent Pool Resource
	@DeleteMapping("/emp/talent/{id}")
	public ResponseEntity<String> deleteEmployee (@PathVariable Integer id){ 
	
		String msg=tbl_resource_pool_Service.delete(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	
	
	
}
