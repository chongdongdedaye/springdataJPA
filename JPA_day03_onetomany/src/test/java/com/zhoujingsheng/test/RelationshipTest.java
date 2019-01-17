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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class RelationshipTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存操作
     * 需求：保存要给客户和一个联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        Customer c=new Customer();
        c.setCustName("百度公司");
        c.setCustLevel("VIPk客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("重庆西永天街黑马专修学院");
        c.setCustPhone("023-84389340");

        LinkMan man=new LinkMan();
        man.setLkmName("淘宝联系人");
        man.setLkmGender("男");
        man.setLkmMobile("010-34785348");
        man.setLkmEmail("98354823@qq.com");
        man.setLkmPosition("老师");
        man.setLkmMemo("还行吗");

        c.getLinkMan().add(man);
        man.setCustomer(c);
        //执行保存
        customerDao.save(c);
        //由于建立了双向绑定，所以会执行两次insert操作和一次update操作
        linkManDao.save(man);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void testDelete(){
        //删除主表数据 失败，因为外键关键维护权在多的一方
        customerDao.delete(1L);
        //解决方式，在操作的一方添加级联更新 CascadeType.ALL
    }
}
