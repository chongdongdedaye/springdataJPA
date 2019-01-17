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

    //使用jpql的方式查询
    @Query(value = "from Customer")
    public List<Customer> findAllCustomer();


    @Query(value = "from Customer where custName =?")
    public Customer findCustomer(String custName);

    /**
     * 执行了修改操作  需要加此标示
     * @param custName
     * @param custId
     */
    @Query(value = "update Customer set custName=?1 where custId =?2")
    @Modifying
    public void updateCustomer(String custName,String custId);


    /**
     * nativeQuery：使用本地sql的方式的查询
     */
    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Customer> findSql();

    /**
     * 方法命名方式查询 根据客户名称查询客户
     * @param custName
     * @return
     */
    public Customer findByCustName(String custName);

    //使用客户名称模糊匹配和客户所属行业精准匹配的查询
    public Customer findByCustNameLikeAndCustIndustry(String custName,String industry);

}
