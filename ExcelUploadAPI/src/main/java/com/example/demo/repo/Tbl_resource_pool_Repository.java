package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.tbl_resource_pool;

public interface Tbl_resource_pool_Repository extends JpaRepository<tbl_resource_pool, Integer> {

	
	
@Query(nativeQuery = true,value="SELECT intResourceId,vchResourceName,vchResourceCode,vchPlatform,vchLocation,vchEngagementPlan,vchExperience,dtmAllocationDate,vchPhoneNo,vchEmail ,intCreatedBy ,dtmCreatedOn,intUpdatedBy,dtmUpdatedOn,vchStatus,bitDeletedFlag FROM tbl_resource_pool")
public List<tbl_resource_pool> get_tbl_resource_pool();

//@Modifying
//@Query(nativeQuery = true,value="")
//public void update();


}
