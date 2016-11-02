package com.xuanfeng.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="power")
public class Power {
	private Integer powerId;
	private String name;
	
	private Set<Role> roles=new HashSet<Role>(0); //power 同角色是多对多的关系，一种权利可以被多种角色使用
	private Menu menu;  //同power同menu是多对一的关系，一个菜单包括多种权限 ,具体原因，不知道为啥！！！！！！！
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getPowerId() {
		return powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * 这里是多对多关系
	 */
	@ManyToMany(mappedBy="powers")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/*
	 * 这里是多对一，至于为什么？不知道
	 */
	
	@ManyToOne
	@JoinColumn(name="menuid")
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
