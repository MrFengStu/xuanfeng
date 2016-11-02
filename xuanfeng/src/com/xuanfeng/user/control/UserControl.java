package com.xuanfeng.user.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuanfeng.entity.LoginUser;
import com.xuanfeng.entity.Menu;
import com.xuanfeng.entity.Power;
import com.xuanfeng.entity.Product;
import com.xuanfeng.entity.Role;
import com.xuanfeng.entity.UserInfo;
import com.xuanfeng.user.service.UserServiceImol;
import com.xuanfeng.util.ChangeCode;
import com.xuanfeng.util.Page;

@Controller
@RequestMapping("user")
public class UserControl {

	@Resource
	private UserServiceImol userServiceImol;

	/**
	 * 登陆所用方法，
	 * 
	 * @param loginName
	 * @param password
	 * @param email
	 * @param codeValue
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("loginName") String loginName, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("codeValue") String codeValue, HttpSession session) {
		System.out.println(loginName);
//		loginName=ChangeCode.changeCode(loginName);
		if (!codeValue.equalsIgnoreCase((String) session.getAttribute("post_validate_code"))) {
			return "-1";
		}
		System.out.println(loginName);
		LoginUser result = this.userServiceImol.login(loginName, password, email);
		if (result == null) {
			return "14";
		} else {
			session.setAttribute("loginRealName_Compare", loginName);
			session.setAttribute("loginRoleName", this.userServiceImol.getRoleName(loginName)); // this
			// 获取title信息
			session.setAttribute("title", this.userServiceImol.printTableTitle(result)); // //
			session.setAttribute("product_title", this.userServiceImol.printTableTitle(new Product()));
			// is // ok
			//
			// 自己动手写的
			Set<Power> set = result.getRole().getPowers();
			Iterator i = set.iterator();
			HashMap menus = new HashMap();
			
			while (i.hasNext()) {
				Menu menu = (Menu) ((Power) i.next()).getMenu();
				Menu pmenu = menu.getParentMenu();
				List list = new ArrayList();
				// 这里是一级菜单
				if (pmenu == null) { // if pmenu ==null 那么目前的这个菜单表示的一级菜单，
					Iterator j = menu.getMenus().iterator();// j表示子菜单的迭代器
					while (j.hasNext()) {
						Menu temp = (Menu) j.next();
						Integer pId = temp.getParentMenu().getMenuId();

						if (pId == menu.getMenuId()) {
							// System.out.println("pId"+pId);
							// System.out.println("name: :"+temp.getName());
							// //这里
							// System.out.println();
							list.add(temp);
						}
					}
					menus.put(menu, list); // 把一级菜单及所有的子菜单加入到内容中
				}
			}

			// 老师的方法
			// Set<Power> set=result.getRole().getPowers();
			// Iterator i=set.iterator();
			// HashMap menus=new HashMap();
			//
			// while(i.hasNext()){
			// Menu menu=(Menu)((Power)i.next()).getMenu();
			// Menu pmenu=menu.getParentMenu();
			// if(!menus.containsKey(pmenu)){
			// List list=new ArrayList();
			// menus.put(pmenu, list);
			// }
			// ((List)menus.get(pmenu)).add(menu);
			// }
			session.setAttribute("menus", menus);
			return "0";
		}

	}

	/**
	 * 注册所用方法
	 * 
	 * @param loginUser
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public String register(LoginUser loginUser) {
		String result = this.userServiceImol.register(loginUser);
		return result;
	}

	/**
	 * 查询的方法，得到对应的user列表
	 * 
	 * @param pageNum
	 * @param searchParam
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "searchParam", defaultValue = "") String searchParam, HttpSession session) {
		searchParam = ChangeCode.changeCode(searchParam);
		Page<LoginUser> page;
		// 带检索添加的分页
		if (searchParam == null || "".equals(searchParam)) {
			page = this.userServiceImol.listUsers(pageNum, 6, null);
		} else {
			page = this.userServiceImol.listUsers(pageNum, 6, new Object[] { searchParam });
		}
		session.setAttribute("page", page);
		session.setAttribute("searchParam", searchParam);
		return "user/lists";
	}

	/**
	 * 跳转到eidt页面
	 * 
	 * @param loginName
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam("loginName") String loginName, HttpSession session) {
		loginName = ChangeCode.changeCode(loginName);
		LoginUser lu = this.userServiceImol.getLoginUser(loginName);
		session.setAttribute("loginUser", lu);
		session.setAttribute("action", "edit");
		return "user/user";
	}

	/**
	 * 修改loginUser的信息，跳转到lists页面
	 * 
	 * @param lu
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(LoginUser lu, HttpServletRequest request) {
		this.userServiceImol.editUser(lu);
		return "redirect:lists";
	}
	
	@RequestMapping(value = "editInfo", method = RequestMethod.GET)
	public String toEditInfo(@RequestParam(value="name",required=false) String name,HttpSession session){
		name=ChangeCode.changeCode(name);
		UserInfo userInfo =this.userServiceImol.getUserInfo(name);
		
		session.setAttribute("userInfo", userInfo);
		System.out.println(userInfo.getEmail());
		System.out.println("session:userInfo"+name);
		return "user/userInfo";
	}

	/**
	 * 新加用户的详细信息
	 * @param userInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "editInfoSave", method = RequestMethod.POST)
	public String editInfo(@RequestParam(value="loginName",required=false) String loginName,@RequestParam(value="password",required=false) String password,
			@RequestParam(value="email",required=false) String email,@RequestParam(value="realName",required=false) String realName,@RequestParam(value="phone",required=false) String phone,
			@RequestParam(value="birthday",required=false) String birth){
		loginName=ChangeCode.changeCode(loginName);
		if(realName!=null){
			realName=ChangeCode.changeCode(realName);
		}
//		System.out.println(birth);
		
		Date birthday = null;
		if(birth!=null){
			birthday=ChangeCode.strToDate(birth);
		}
//		System.out.println("password"+password);
//		System.out.println("birthday:"+birthday);
//		System.out.println("email:"+email);
//		System.out.println("phone:"+phone);
//		System.out.println("realName:"+realName);
		LoginUser lu=this.userServiceImol.getLoginUser(loginName);
		UserInfo userInfo=new UserInfo();
		userInfo.setPassword(password);
		userInfo.setBirthday(birthday);
		userInfo.setEmail(email);
		userInfo.setPhone(phone);
		userInfo.setRealName(realName);
		userInfo.setLoginName(loginName);
		userInfo.setLoginUser(lu);
//		System.out.println("loginName:"+loginName);
		this.userServiceImol.addUserInfo(userInfo);
		return "redirect:lists";
	}
	/**
	 * 增加用户
	 * @param email
	 * @param password
	 * @param roleName
	 * @param loginName
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	public String add(@RequestParam(value="email",required=false) String email, @RequestParam(value="password",required=false) String password,@RequestParam(value="roleId",required=false) Integer roleId,
			@RequestParam(value="loginName",required=false) String loginName) {
		LoginUser loginUser = new LoginUser();
		loginName=ChangeCode.changeCode(loginName);
		 //1.找到rolenName 对应的roleId
		 //2.将roleId赋值给user
		 Role role=this.userServiceImol.findRoleById(roleId);
		loginUser.setRole(role); //设置角色
		loginUser.setLoginName(loginName);
		loginUser.setEmail(email);
		loginUser.setPassword(password);
		this.userServiceImol.addUser(loginUser);
		return "redirect:lists";
	}

	/**
	 * 增加用户，toget方法
	 * 
	 * @param loginUser
	 * @return
	 */
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String toAdd(HttpSession session) {
		session.setAttribute("action", "add");
		session.setAttribute("roles", this.userServiceImol.findRole()); // 对应权限列表
		return "user/user";
	}

	/**
	 * 删除用户，
	 * 
	 * @param loginName
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("loginName") String loginName) {
		loginName = ChangeCode.changeCode(loginName);
		this.userServiceImol.dropUser(loginName);
		// 跳转到lists controller方法中！
		return "redirect:lists";
	}
}
