package com.zhoujingsheng.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    //构建实体管理器工厂
    private static EntityManagerFactory em;
    static {
        //静态代码块赋值
        em= Persistence.createEntityManagerFactory("myJPA");
    }
    //使用管理器工厂生产一个管理器对象
    public static EntityManager getEntityManager(){
        return em.createEntityManager();
    }
}
