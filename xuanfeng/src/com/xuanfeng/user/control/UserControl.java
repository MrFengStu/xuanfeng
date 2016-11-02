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
	 * ��½���÷�����
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
			// ��ȡtitle��Ϣ
			session.setAttribute("title", this.userServiceImol.printTableTitle(result)); // //
			session.setAttribute("product_title", this.userServiceImol.printTableTitle(new Product()));
			// is // ok
			//
			// �Լ�����д��
			Set<Power> set = result.getRole().getPowers();
			Iterator i = set.iterator();
			HashMap menus = new HashMap();
			
			while (i.hasNext()) {
				Menu menu = (Menu) ((Power) i.next()).getMenu();
				Menu pmenu = menu.getParentMenu();
				List list = new ArrayList();
				// ������һ���˵�
				if (pmenu == null) { // if pmenu ==null ��ôĿǰ������˵���ʾ��һ���˵���
					Iterator j = menu.getMenus().iterator();// j��ʾ�Ӳ˵��ĵ�����
					while (j.hasNext()) {
						Menu temp = (Menu) j.next();
						Integer pId = temp.getParentMenu().getMenuId();

						if (pId == menu.getMenuId()) {
							// System.out.println("pId"+pId);
							// System.out.println("name: :"+temp.getName());
							// //����
							// System.out.println();
							list.add(temp);
						}
					}
					menus.put(menu, list); // ��һ���˵������е��Ӳ˵����뵽������
				}
			}

			// ��ʦ�ķ���
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
	 * ע�����÷���
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
	 * ��ѯ�ķ������õ���Ӧ��user�б�
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
		// ��������ӵķ�ҳ
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
	 * ��ת��eidtҳ��
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
	 * �޸�loginUser����Ϣ����ת��listsҳ��
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
	 * �¼��û�����ϸ��Ϣ
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
	 * �����û�
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
		 //1.�ҵ�rolenName ��Ӧ��roleId
		 //2.��roleId��ֵ��user
		 Role role=this.userServiceImol.findRoleById(roleId);
		loginUser.setRole(role); //���ý�ɫ
		loginUser.setLoginName(loginName);
		loginUser.setEmail(email);
		loginUser.setPassword(password);
		this.userServiceImol.addUser(loginUser);
		return "redirect:lists";
	}

	/**
	 * �����û���toget����
	 * 
	 * @param loginUser
	 * @return
	 */
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String toAdd(HttpSession session) {
		session.setAttribute("action", "add");
		session.setAttribute("roles", this.userServiceImol.findRole()); // ��ӦȨ���б�
		return "user/user";
	}

	/**
	 * ɾ���û���
	 * 
	 * @param loginName
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("loginName") String loginName) {
		loginName = ChangeCode.changeCode(loginName);
		this.userServiceImol.dropUser(loginName);
		// ��ת��lists controller�����У�
		return "redirect:lists";
	}
}
