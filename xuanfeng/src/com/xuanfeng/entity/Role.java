package com.xuanfeng.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {

	private Integer roleId;
	private String name;
//	private Set<LoginUser> loginUser=new HashSet<LoginUser>(0); //没有这种需求是看看这种角色有什么！！！，没有这种业务逻辑！！！

	private Set<Power>powers =new HashSet<Power>(0); //同权限是多对多的关系，一个角色可以有多个权限使用
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="role_power_all",
	joinColumns={@JoinColumn(name="roleid",referencedColumnName="roleid")},
	inverseJoinColumns={@JoinColumn(name="powerid",referencedColumnName="powerid")}
	)	
	public Set<Power> getPowers() {
		return powers;
	}
	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}
}
