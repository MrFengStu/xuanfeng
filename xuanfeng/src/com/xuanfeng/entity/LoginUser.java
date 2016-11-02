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

	private UserInfo userInfo; // 在这里声明一个，为了表现，一对一的关系

	private Role role; // 一个账号只可以有一种角色，一种角色可以有多个账号，，所以账号和角色的关系是多对一
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
	 * 这里是一对一配置
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // ！！！！！！！
	@PrimaryKeyJoinColumn() // 这句话是指：这个字段作为这个表的主键，相对于另一个表示外键
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/*
	 * 这里是多对一配置
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
