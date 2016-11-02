package com.xuanfeng.user.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeng.entity.LoginUser;
import com.xuanfeng.entity.Role;
import com.xuanfeng.entity.UserInfo;
import com.xuanfeng.util.BaseDao;
import com.xuanfeng.util.ChangeCode;
import com.xuanfeng.util.Page;

//��Щע�ⶼ����ҪSpring���
@Repository
public class UserDaoImpl extends BaseDao<LoginUser, String> {

	@Resource
	private SessionFactory sessionFactory;

	//
	public LoginUser findByNamdAndPwd(String name, String pwd, String email) {
		try {
			Query q = this.sessionFactory.getCurrentSession()
					.createQuery("from LoginUser lu where lu.loginName=? and lu.password=? and lu.email=?");
			q.setString(0, name);
			q.setString(1, pwd);
			q.setString(2, email);
			return (LoginUser) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String register(LoginUser loginUser) {
		try {
			super.save(loginUser);
			super.excuteBySql("insert into UserInfo(name,password,email)value(?,?,?)",
					new Object[] { loginUser.getLoginName(), loginUser.getPassword(), loginUser.getEmail() });
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			return "1";
		}

	}
	
	public void addUser(LoginUser loginUser) {
		try {
			super.save(loginUser);
			super.excuteBySql("insert into UserInfo(loginName,password,email)value(?,?,?)",
					new Object[] { loginUser.getLoginName(), loginUser.getPassword(), loginUser.getEmail() });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����loginName�õ�RoleName
	 * 
	 * @return
	 */
	public String getRoleName(String loginName) {
		try {
			// 1.�õ��˺ŵ�roleid
			return super.get(loginName).getRole().getName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��ӡ����������loginUser��
	 * 
	 * @return
	 * 
	 */
	public Page<LoginUser> printLoginUser() {
		try {
			return super.findByPage(1, 6, "from LoginUser", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��ӡ��ͷ��Ϣ
	 */
	public List<String> printTableTitle(Object obj) {
		try {
			// super.findByProperty("from LoginUser", null);
			return super.findTableTitle(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * �û��б�
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */

	public Page<LoginUser> findLoginUser(int pageNum, int pageSize, Object[] params) {

		String hql;
		if (params != null && params.length > 0) {
			hql = "from LoginUser l where l.loginName like ?";
			params[0] = "%" + params[0] + "%";
		} else {
			hql = "from LoginUser"; // ����param���ǿ�
		}
		try {
			Page<LoginUser> page = new Page<LoginUser>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page = this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * ����login�õ�loginUser
	 * 
	 * @param loginName
	 * @return
	 */
	public LoginUser getLoginUser(String loginName) {
		try {
			return super.get(loginName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ����name�õ�UserInfo����
	 * 
	 * @param name
	 * @return
	 */
	public UserInfo getUserInfo(String name) {
		try {

			Query query = this.sessionFactory.getCurrentSession()
					.createQuery("from UserInfo ui where ui.loginName='" + name + "'");
			return (UserInfo) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveUserInfo(UserInfo userInfo) {
		try {
			Date date =new Date(userInfo.getBirthday().getTime());
			super.excuteBySql("update UserInfo set password=?,email=?,realName=?,phone=?,birthday=? where loginName=? ",
					new Object[] { userInfo.getPassword(),userInfo.getEmail(),userInfo.getRealName(),userInfo.getPhone(),date,userInfo.getLoginName() });
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����LoginUser����Ϣ
	 * 
	 * @param lu
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
	public void updateLoginUser(LoginUser lu) {
		try {

			LoginUser loginUser = this.get(ChangeCode.changeCode(lu.getLoginName()));
			loginUser.setEmail(lu.getEmail());
			loginUser.setPassword(lu.getPassword());
			super.update(loginUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ɾ���û�����
	 * 
	 * @param lu
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
	public void deleteLoginUser(LoginUser lu) {
		try {
			super.delete(lu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �õ���ɫ�б�
	 * @return
	 */
	public List<Role> fingRole() {
		return this.sessionFactory.getCurrentSession().createQuery("from Role").list();
	}
	/**
	 * ͨ����ɫId�ҵ���ɫ
	 * @param roleId
	 * @return
	 */
	public Role findRoleById(Integer roleId) {
		List<Role> list = this.sessionFactory.getCurrentSession().createQuery("from Role where roleId='" + roleId + "'")
				.list();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
