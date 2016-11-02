package com.xuanfeng.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xuanfeng.entity.LoginUser;
import com.xuanfeng.entity.Role;
import com.xuanfeng.entity.UserInfo;
import com.xuanfeng.user.dao.UserDaoImpl;
import com.xuanfeng.util.Page;

@Service
@Transactional(readOnly=true)
public class UserServiceImol {

	/*
	 * 登陆
	 */
	@Resource
	private UserDaoImpl userDaoImpl;
	public LoginUser login(String name,String pwd,String email){
		return this.userDaoImpl.findByNamdAndPwd(name, pwd,email);
	}
	/*
	 * 注册
	 */
	@Transactional(readOnly=false) //只读,不是只读
	public String register(LoginUser loginUser){
		return this.userDaoImpl.register(loginUser);
	}
	@Transactional(readOnly=false) //只读,不是只读
	public String addUser(LoginUser loginUser){
		this.userDaoImpl.addUser(loginUser);
		return "ok";
	}
	/*
	 * 根据loginName得到RoleName
	 */
	public String getRoleName(String loginName){
		return this.userDaoImpl.getRoleName(loginName);
	}
	
	
	/**
	 * 打印LoginUser表中的表头
	 * 
	 */
	public List<String> printTableTitle(Object obj){
		return this.userDaoImpl.printTableTitle(obj);
	}
	
	
	/**
	 * 打印list User
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<LoginUser> listUsers(int pageNum,int pageSize,Object[] params){
		return this.userDaoImpl.findLoginUser(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly=true)
	public LoginUser getLoginUser(String loginName){
		return this.userDaoImpl.getLoginUser(loginName);
	}
	
	@Transactional(readOnly=true)
	public UserInfo getUserInfo(String name){
		return this.userDaoImpl.getUserInfo(name);
	}
	
	@Transactional(readOnly=false)
	public void addUserInfo(UserInfo userInfo){
		this.userDaoImpl.saveUserInfo(userInfo);
//		return this.userDaoImpl.getUserInfo(name);
	}
	
	@Transactional(readOnly=false)
	public void editUser(LoginUser lu){
		this.userDaoImpl.updateLoginUser(lu);
	}
	
	
	
	@Transactional(readOnly=false)
	public void dropUser(String loginName){
		LoginUser lu=this.userDaoImpl.getLoginUser(loginName);
		this.userDaoImpl.deleteLoginUser(lu);
	}
	
	public List<Role> findRole(){
		return this.userDaoImpl.fingRole();
	}
	
	public Role findRoleById(Integer roleId){
		return this.userDaoImpl.findRoleById(roleId);
	}
}
