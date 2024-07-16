package com.example.demo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Entity
@Table(name = "ExcelUploadEmployee")

	public class ExcelUploadEmployee {


		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    @Column(name="ID")
		    private Integer id;

		    @Column(name="EMP_NAME")
		    private String emp_name;

		    @Column(name="EMP_EMAIL")
		    private String emp_email;
		    
		    @Column(name="EMP_PHONE")
		    private String emp_phone;
		    
		    @Column(name="EMP_LOCATION")
		    private String emp_location;
		
	

}
