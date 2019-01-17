package com.zhoujingsheng;

import com.zhoujingsheng.dao.CustomerDao;
import com.zhoujingsheng.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CustomerTest {

    @Autowired
    private CustomerDao customerDao;

    @Test //条件模糊查询
    public void testSpecifications(){
        //使用匿名内部类的方式，创建一个specification的实现类
        Specification<Customer> spec=new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //cb:构建查询，添加查询方式  like模糊匹配
                //root 从实体customer对象中按照custName属性进行查询
                return cb.like(root.get("custName").as(String.class),"传智播客%");
            }
        };
        //调用JpaSpecificationExecutor接口中的方法
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    @Test //分页查询
    public void testPage(){
        //构建查询对象
        Specification<Customer> spec=new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("custName").as(String.class),"传智%");
            }
        };
        //构建分页对象  第一个参数：页码（从0开始）
        //		 * 				第二个参数：每页查询条数
        Pageable pageable=new PageRequest(0,2);

        //执行分页查询
        Page<Customer> page = customerDao.findAll(spec, pageable);
        //获取总页数
        System.out.println(page.getTotalPages());
        //获取总记录数
        System.out.println(page.getTotalElements());
        //获取列表数据
        List<Customer> list = page.getContent();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
