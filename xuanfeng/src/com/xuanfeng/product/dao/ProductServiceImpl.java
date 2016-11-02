package com.xuanfeng.product.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeng.entity.Product;
import com.xuanfeng.product.service.ProductDaoImpl;
import com.xuanfeng.util.Page;

@Service
@Transactional(readOnly=true)
public class ProductServiceImpl {

	@Resource
	private ProductDaoImpl productDaoImpl;
	
	@Transactional(readOnly=false)
	public void addProduct(Product p){
		this.productDaoImpl.saveProduct(p);
	}

	@Transactional(readOnly=true)
	public Page<Product> listProduct(int pageNum,int pageSize,Object[] params){
		return this.productDaoImpl.findProduct(pageNum, pageSize, params);
	}
	
	@Transactional(readOnly=true)
	public Product getProduct(int productId){
		return this.productDaoImpl.getProduct(productId);
	}
	@Transactional(readOnly=false)
	public void editProduct(Product p){
		Product pdb=this.productDaoImpl.getProduct(p.getProductId());
		pdb.setName(p.getName());
		pdb.setPrice(p.getPrice());
		this.productDaoImpl.updateProduct(pdb);
	}
	@Transactional(readOnly=false)
	public void dropProduct(int productId){
		this.productDaoImpl.deleteProduct(productId);
	}
}
