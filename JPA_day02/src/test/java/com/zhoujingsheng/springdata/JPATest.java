package com.zhoujingsheng.springdata;

import com.zhoujingsheng.dao.CustomerDao;
import com.zhoujingsheng.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:spring/applicationContext.xml")
public class JPATest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试保存 ，调用save方法
     */
    @Test
    public  void testSave(){
        Customer c=new Customer();
        //对象中不包含id，执行插入数据操作
        c.setCustName("传智播客");
        customerDao.save(c);
    }

    @Test
    public  void testUpdate(){
        Customer c=new Customer();
        //对象中包含id，且数据库中有此条数据，执行更新操作
        c.setCustId("7");
        c.setCustName("传智播客哈哈");
        customerDao.save(c);
    }

    @Test
    public void testDelete(){
        //根据id删除  不包含此数据会报错
        customerDao.delete("1");
    }
    @Test
    public void testFindById(){
        Customer c=customerDao.findOne("3");
        System.out.println(c);
    }

    @Test
    public void testFindAllCustomer(){
        //调用接口的jpql注解查询
        List<Customer> list = customerDao.findAllCustomer();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void testFindCustomer(){
        Customer customer = customerDao.findByCustName("传智播客");
        System.out.println(customer);
    }

    @Test
    @Transactional   //测试时需要加事务的支持
    @Rollback(value = false) //设置是否自动回滚
    public void testUpdateCustomer(){
        //调用带修改功能的jpql查询
        customerDao.updateCustomer("传智播客","2");
    }
    @Test //测试调用原生sql语句查询
    public void testFindSql(){
        List<Customer> list = customerDao.findSql();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test //测试调用方法命名查询
    public void testFindByCustName(){
        Customer customer = customerDao.findByCustName("传智播客");
        System.out.println(customer);
    }

    @Test  //测试方法命名规则的查询
    public void testFindByCustNameLikeAndCustIndustry(){
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("传智播客", "IT教育");
        System.out.println(customer);
    }

}
