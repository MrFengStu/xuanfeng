package com.xuanfeng.product.control;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuanfeng.entity.Product;
import com.xuanfeng.product.dao.ProductServiceImpl;
import com.xuanfeng.util.ChangeCode;
import com.xuanfeng.util.Page;

@Controller
@RequestMapping("product")
public class ProductControl {

	@Resource
	private ProductServiceImpl productServiceImpl;

	/**
	 * 添加商品
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping("add")
	public String add(Product product) {
		product.setName(ChangeCode.changeCode(product.getName()));
		this.productServiceImpl.addProduct(product);
		return "redirect:list";
	}

	/**
	 * 跳转到form页面
	 * 
	 * @return
	 */
	@RequestMapping("form")
	public String toAdd(HttpServletRequest request) {
		request.setAttribute("action", "add");
		return "product/form";
	}

	/**
	 * 带参的跳转到form页面
	 * 
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam("productId") int productId, HttpServletRequest request) {
		Product p = this.productServiceImpl.getProduct(productId);
		request.setAttribute("pro", p);
		request.setAttribute("action", "edit");
		return "product/form";
	}

	/**
	 * form页面提交
	 * 
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(Product p, HttpServletRequest request) {
		p.setName(ChangeCode.changeCode(p.getName()));
		this.productServiceImpl.editProduct(p);
		return "redirect:list";
	}

	/**
	 * 删除商品
	 * 
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("productId") int productId, HttpServletRequest request) {
		this.productServiceImpl.dropProduct(productId);
		return "redirect:list";
	}

	/**
	 * 得到list.jsp列表
	 * 
	 * @param pageNum
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String toList(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "product_searchParam", defaultValue = "") String searchParam, HttpServletRequest request) {
		searchParam=ChangeCode.changeCode(searchParam);
		Page<Product> page;
		if(searchParam==null || "".equals(searchParam)){
			page=this.productServiceImpl.listProduct(pageNum, 6, null);	
		}else{
			page=this.productServiceImpl.listProduct(pageNum, 6, new Object[]{searchParam});
		}
		request.setAttribute("product_page", page);
		request.setAttribute("product_searchParam", searchParam);
		return "product/list";
	}

}
