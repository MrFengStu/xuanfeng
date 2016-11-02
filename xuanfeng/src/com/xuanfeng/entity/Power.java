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
	
	private Set<Role> roles=new HashSet<Role>(0); //power ͬ��ɫ�Ƕ�Զ�Ĺ�ϵ��һ��Ȩ�����Ա����ֽ�ɫʹ��
	private Menu menu;  //ͬpowerͬmenu�Ƕ��һ�Ĺ�ϵ��һ���˵���������Ȩ�� ,����ԭ�򣬲�֪��Ϊɶ��������������
	
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
	 * �����Ƕ�Զ��ϵ
	 */
	@ManyToMany(mappedBy="powers")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/*
	 * �����Ƕ��һ������Ϊʲô����֪��
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
