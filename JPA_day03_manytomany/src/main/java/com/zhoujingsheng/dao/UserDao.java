package com.zhoujingsheng.dao;

import com.zhoujingsheng.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor<SysUser> {
}
