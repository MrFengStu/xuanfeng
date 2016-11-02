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
//	private Set<LoginUser> loginUser=new HashSet<LoginUser>(0); //û�����������ǿ������ֽ�ɫ��ʲô��������û������ҵ���߼�������

	private Set<Power>powers =new HashSet<Power>(0); //ͬȨ���Ƕ�Զ�Ĺ�ϵ��һ����ɫ�����ж��Ȩ��ʹ��
	
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
