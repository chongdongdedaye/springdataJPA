package com.zhoujingsheng.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "sys_role")
@Entity
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_memo")
    private String roleMemo;

    //多对多关系映射
    @ManyToMany
    @JoinTable(name = "user_role_rel", //中间表名称
            joinColumns = {
            //中间表的role_id关联当前role表的role_id
            @JoinColumn(name = "role_id",referencedColumnName = "role_id")},
            //中间表的user_id关联对方表user的user_id
            inverseJoinColumns = {
            @JoinColumn(
                    name = "user_id",referencedColumnName = "user_id")}
    )
    private Set<SysUser> users=new HashSet<SysUser>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }

    public Set<SysUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SysUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleMemo='" + roleMemo + '\'' +
                ", users=" + users +
                '}';
    }
}
