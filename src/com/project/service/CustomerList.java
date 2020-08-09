package com.project.service;

import com.project.bean.Customer;

public class CustomerList {
	private Customer[] customers;// 保存客户对象的数组
	private int total = 0; // 记录已保存客户对象的数量

	/**
	 * 初始化customer数组
	 * 
	 * @param totalCustomer :指定数组的长度
	 */

	public CustomerList(int totalCustomer) {
		customers = new Customer[totalCustomer];
	}

	/**
	 * 将指定的客户添加到数组中
	 * 
	 * @param customer
	 * @return true:添加成功 false：添加失败
	 */
	public boolean addCustomer(Customer customer) {
		if (total >= customers.length) {
			return false;
		}
		customers[total] = customer;
		total++;
		// customers[total++] = customer;
		return true;
	}

	/**
	 * 修改指定索引位置的客户信息
	 * 
	 * @param index
	 * @param cust
	 * @return true：修改成功 false：修改失败
	 */
	public boolean replaceCustomer(int index, Customer cust) {
		if (index < 0 || index >= total) {
			return false;
		}
		customers[index] = cust;
		return true;
	}

	/**
	 * 删除指定索引位置上的客户
	 * 
	 * @param index
	 * @return true：修改成功 false：修改失败
	 */
	public boolean deleteCustomer(int index) {
		if (index < 0 || index >= total) {
			return false;
		}
		for (int i = index; i < total - 1; i++) {
			customers[i] = customers[i + 1];
		}
		// 最后一个有数据的元素需要置空
		customers[total - 1] = null;
		total--;
		// customers[--total] = null;
		return true;
	}

	/**
	 * 获取所有客户信息
	 * 
	 * @return
	 */

	public Customer[] getAllCustomers() {
		Customer[] custs = new Customer[total];
		for (int i = 0; i < total; i++) {
			custs[i] = customers[i];
		}
		return custs;

	}

	/**
	 * 
	 * 获取指定索引位置上的客户
	 * 
	 * @param index
	 * @return 找到元素则返回，找不到则返回null
	 */

	public Customer getCustomer(int index) {

		if (index < 0 || index >= total) {
			return null;
		}
		return customers[index];
	}

	/**
	 * 获取存储的客户的数量
	 * 
	 * @return
	 */

	public int getTotal() {
		return total;
		// return customers.length; 错误的

	}

}
