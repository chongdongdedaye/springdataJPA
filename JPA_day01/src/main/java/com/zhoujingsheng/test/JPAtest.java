package com.zhoujingsheng.test;

import com.zhoujingsheng.entity.Customer;
import com.zhoujingsheng.util.JPAUtil;
import org.junit.Test;

import javax.persistence.*;
import java.security.PublicKey;
import java.util.List;

public class JPAtest {

    @Test
    public void test(){
        /**
         * 创建实体类管理工厂，借助persistence的静态方法获取
         *   其中传递的参数为持久化单元名称，需要jpa配置文件中指定
         *
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJPA");
        //创建实体管理类
        EntityManager em = factory.createEntityManager();
        //获取事务对象
        EntityTransaction tx = em.getTransaction();
        //开始事务
        tx.begin();
        Customer customer=new Customer();
        customer.setCustName("传智播客");
        //保存操作
        em.persist(customer);
        //提交事务
        tx.commit();
        //释放资源
        em.clear();
        factory.close();
    }

    @Test //保存实体
    public void testAdd(){
        //定义对象
        Customer c=new Customer();
        c.setCustName("传智学院");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("IT教育");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");

        EntityManager em=null;
        EntityTransaction tx=null;
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx=em.getTransaction();
            //开启事务
            tx.begin();
            //执行操作
            em.persist(c);
            //提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        }finally {
            //释放资源
            em.close();
        }
    }

    @Test  //修改实体类
    public void testMerge(){
        //定义对象
        EntityManager em=null;
        EntityTransaction tx=null;

         em = JPAUtil.getEntityManager();
         tx=em.getTransaction();

         tx.begin();
         //执行查询操作
        Customer customer = em.find(Customer.class, 4L);
        customer.setCustName("江苏传智学院");
       // em.merge(customer);  //有一级缓存和快照存在，缓存中的数据发生变化时，会自动同步到数据库
        //提交事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test  //删除实体类
    public void testRemove(){
        //定义对象
        EntityManager em=null;
        EntityTransaction tx=null;

        em=JPAUtil.getEntityManager();
        tx=em.getTransaction();

        //开启事务
        tx.begin();
        //执行操作
        Customer c = em.getReference(Customer.class, 2L); //延迟查询，结果C被调用时才发送SQL语句
        em.remove(c);
        tx.commit();

        //关闭资源
        em.close();
    }

    @Test //查询表中所有实体
    public void findAll(){

        //定义对象
        EntityManager em=null;
        EntityTransaction tx=null;

        em=JPAUtil.getEntityManager();
        tx=em.getTransaction();

        //开启事务
        tx.begin();
        //创建query对象
        String jpql="from Customer";  //写类名
        Query query = em.createQuery(jpql);
        //执行查询
        List list = query.getResultList();
        for (Object o : list) {

            System.out.println(o);
        }
        //提交事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test //分页查询
    public void findPage(){

        //定义对象
        EntityManager em=null;
        EntityTransaction tx=null;

        em=JPAUtil.getEntityManager();
        tx=em.getTransaction();
        //开启事务
        tx.begin();
        //定义query对象
        String jpql="from Customer";
        Query query = em.createQuery(jpql);
        //设置分页参数
        query.setFirstResult(0);
        query.setMaxResults(2);
        //指定查询
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        //提交事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test  //条件查询
    public void findCondition(){
        //对象构造
        EntityManager em=null;
        EntityTransaction tx=null;
        em=JPAUtil.getEntityManager();
        tx=em.getTransaction();
        //开启事务
        tx.begin();
        //创建query对象
        String jpql="from Customer where custName like ?";
        Query query = em.createQuery(jpql);
        //参数赋值
        query.setParameter(1,"江苏%");
        //执行查询
        Object o = query.getSingleResult(); //得到唯一的结果对象  多或少均会报错
        System.out.println(o);
        //提交事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test //排序查询
    public void testOrder(){

        EntityManager em=null;
        EntityTransaction tx=null;
        em=JPAUtil.getEntityManager();
        tx=em.getTransaction();
        //开启事务
        tx.begin();
        //定义query对象
        String jpql="from Customer order by custId desc";
        Query query = em.createQuery(jpql);
        //执行查询
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        //提交事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test //统计查询  函数查询
    public void testCount(){
        EntityManager em=null;
        EntityTransaction tx=null;
        em=JPAUtil.getEntityManager();
        tx=em.getTransaction();
        //开启事务
        tx.begin();
        //定义query对象
        String jpql ="select count(custId) from Customer";
        //执行查询
        Query query = em.createQuery(jpql);
        Object o = query.getSingleResult();//得到集合返回类型
        System.out.println(o);
        //提交事务
        tx.commit();
        //释放资源
        em.close();

    }

}
