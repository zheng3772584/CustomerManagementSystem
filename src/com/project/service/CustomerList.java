package com.project.service;

import com.project.bean.Customer;

public class CustomerList {
	private Customer[] customers;// ����ͻ����������
	private int total = 0; // ��¼�ѱ���ͻ����������

	/**
	 * ��ʼ��customer����
	 * 
	 * @param totalCustomer :ָ������ĳ���
	 */

	public CustomerList(int totalCustomer) {
		customers = new Customer[totalCustomer];
	}

	/**
	 * ��ָ���Ŀͻ���ӵ�������
	 * 
	 * @param customer
	 * @return true:��ӳɹ� false�����ʧ��
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
	 * �޸�ָ������λ�õĿͻ���Ϣ
	 * 
	 * @param index
	 * @param cust
	 * @return true���޸ĳɹ� false���޸�ʧ��
	 */
	public boolean replaceCustomer(int index, Customer cust) {
		if (index < 0 || index >= total) {
			return false;
		}
		customers[index] = cust;
		return true;
	}

	/**
	 * ɾ��ָ������λ���ϵĿͻ�
	 * 
	 * @param index
	 * @return true���޸ĳɹ� false���޸�ʧ��
	 */
	public boolean deleteCustomer(int index) {
		if (index < 0 || index >= total) {
			return false;
		}
		for (int i = index; i < total - 1; i++) {
			customers[i] = customers[i + 1];
		}
		// ���һ�������ݵ�Ԫ����Ҫ�ÿ�
		customers[total - 1] = null;
		total--;
		// customers[--total] = null;
		return true;
	}

	/**
	 * ��ȡ���пͻ���Ϣ
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
	 * ��ȡָ������λ���ϵĿͻ�
	 * 
	 * @param index
	 * @return �ҵ�Ԫ���򷵻أ��Ҳ����򷵻�null
	 */

	public Customer getCustomer(int index) {

		if (index < 0 || index >= total) {
			return null;
		}
		return customers[index];
	}

	/**
	 * ��ȡ�洢�Ŀͻ�������
	 * 
	 * @return
	 */

	public int getTotal() {
		return total;
		// return customers.length; �����

	}

}
