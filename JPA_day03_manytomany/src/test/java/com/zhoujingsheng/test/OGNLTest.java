package com.zhoujingsheng.test;

import com.zhoujingsheng.dao.CustomerDao;
import com.zhoujingsheng.dao.LinkManDao;
import com.zhoujingsheng.entity.Customer;
import com.zhoujingsheng.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class OGNLTest {

        @Autowired
        private CustomerDao customerDao;

        @Autowired
        private LinkManDao linkManDao;

        //查询一个客户，获取该客户下的所有联系人
        @Test
        @Transactional
        public void testFind(){
                Customer customer = customerDao.findOne(1L);
                System.out.println(customer);
                //对象导航查询
                Set<LinkMan> linkMans = customer.getLinkMan();
                System.out.println(linkMans);
        }
        @Transactional
        @Test
        @Rollback(false)
        public void testSave(){
                Customer customer=new Customer();
                customer.setCustName("王伟");
                customer.setCustLevel("VIP客户");
                customerDao.save(customer);
        }
}
