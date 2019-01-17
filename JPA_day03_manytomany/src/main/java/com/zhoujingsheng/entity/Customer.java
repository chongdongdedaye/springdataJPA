package com.zhoujingsheng.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
//@Data  //免写getset方法
//@ToString
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自动增长
    @Column(name = "cust_id")
     private Long custId;

    @Column(name = "cust_name")
     private String custName;

    @Column(name = "cust_source")
     private String custSource;

    @Column(name = "cust_industry")
     private String custIndustry;

    @Column(name = "cust_level")
     private String custLevel;

    @Column(name = "cust_address")
     private String custAddress;

    @Column(name = "cust_phone")
     private String custPhone;

    //配置客户和联系人的一对多关系
    //mappedBy：指定从表实体类中引用主表对象的名称。
    //fetch  一对多时建议延迟查询关联的多个对象
    @OneToMany(targetEntity =LinkMan.class,mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //@JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")   //放弃关联维护
    private Set<LinkMan> linkMan=new HashSet<LinkMan>();

    @Override
    public String toString() {
        return "";
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Set<LinkMan> getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(Set<LinkMan> linkMan) {
        this.linkMan = linkMan;
    }
}
