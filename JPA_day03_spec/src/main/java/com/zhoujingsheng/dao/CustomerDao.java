package com.zhoujingsheng.dao;

import com.zhoujingsheng.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.xml.ws.WebEndpoint;
import java.util.List;

/**
 *  JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 *  JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */
public interface CustomerDao extends JpaRepository<Customer,String>, JpaSpecificationExecutor<Customer> {

}
