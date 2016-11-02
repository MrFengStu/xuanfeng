package com.xuanfeng.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "loginUser")
public class LoginUser {

	// @Column(name="loginName",length=30)

	private String loginName;

	private String password;
	private String email;

	private UserInfo userInfo; // ����������һ����Ϊ�˱��֣�һ��һ�Ĺ�ϵ

	private Role role; // һ���˺�ֻ������һ�ֽ�ɫ��һ�ֽ�ɫ�����ж���˺ţ��������˺źͽ�ɫ�Ĺ�ϵ�Ƕ��һ
	// set/get

	@Id
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * ������һ��һ����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // ��������������
	@PrimaryKeyJoinColumn() // ��仰��ָ������ֶ���Ϊ�������������������һ����ʾ���
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/*
	 * �����Ƕ��һ����
	 */
	@ManyToOne
	@JoinColumn(name = "roleId", referencedColumnName = "roleid")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
