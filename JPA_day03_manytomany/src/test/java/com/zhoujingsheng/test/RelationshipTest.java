package com.zhoujingsheng.test;

import com.zhoujingsheng.dao.RoleDao;
import com.zhoujingsheng.dao.UserDao;
import com.zhoujingsheng.entity.SysRole;
import com.zhoujingsheng.entity.SysUser;
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
        private RoleDao roleDao;

        @Autowired
        private UserDao userDdao;
    /**
     * 需求：保存用户和角色 要求：
     * 	 * 	创建2个用户和3个角色
     * 	 * 	让1号用户具有1号和2号角色(双向的)
     * 	 * 	让2号用户具有2号和3号角色(双向的)
     * 	 *  保存用户和角色
     * 	 * 问题：
     * 	 *  在保存时，会出现主键重复的错误，因为都是要往中间表中保存数据造成的。
     * 	 解决办法：
     * 	 * 	让任意一方放弃维护关联关系的权利
     */
    @Test
    @Transactional
    @Rollback(false)
    public  void test1(){
        //创建对象
        SysUser u=new SysUser();
        u.setUserName("用户1");
        SysRole r=new SysRole();
        r.setRoleName("角色1");
        //建立关联
        u.getRols().add(r);
        r.getUsers().add(u);
        //保存
        roleDao.save(r);
        userDdao.save(u);
    }

    /**
     * 删除操作
     * 	在多对多的删除时，双向级联删除根本不能配置
     * 禁用
     *	如果配了的话，如果数据之间有相互引用关系，可能会清空所有数据
     */
    @Test
    @Transactional
    @Rollback(false)
    public void test2(){

        //中间表有外键指向此表，无法删除
        userDdao.delete(1L);
    }
}
