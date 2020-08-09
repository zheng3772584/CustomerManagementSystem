package com.project.ui;

import com.project.bean.Customer;
import com.project.service.CustomerList;
import com.project.util.CMUtility;

public class CustomerView {
	// ��װ �����������10���ͻ������CustomerList�����ó�Ա����ʹ��
	private CustomerList customerList = new CustomerList(10);

	// �������
	public CustomerView() {
		Customer customer = new Customer("����", '��', 24, "13312345678", "aaaaa.ddddd@qq.com");

		customerList.addCustomer(customer);
	}

	// ����

	/**
	 * ��ʾ�������ķ���
	 */
	public void enterMainMenu() {
		boolean isFlag = true;
		while (isFlag) {
			System.out.println(
					"-----------------------�ͻ���Ϣ����ϵͳ---------------------------------------------------------------");
			System.out.println("\t\t\t    1��ӿͻ�");
			System.out.println("\t\t\t    2�޸Ŀͻ�");
			System.out.println("\t\t\t    3ɾ���ͻ�");
			System.out.println("\t\t\t    4�ͻ��б�");
			System.out.println("\t\t\t    5�˳�\n");
			System.out.println("\t\t    ��ѡ��(1-5)��");

			char menu = CMUtility.readMenuSelection();
			switch (menu) {
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomers();
				break;
			case '5':
				// System.out.println("�˳�");
				System.out.println("ȷ���Ƿ��˳���Y/N����");
				char isExit = CMUtility.readConfirmSelection();
				if (isExit == 'Y') {
					isFlag = false;
				}

				break;

			}

		}
	}

	/**
	 * ��ӿͻ��Ĳ���
	 * 
	 */
	public void addNewCustomer() {
		System.out.println("----------------��ӿͻ�----------------------------");
		System.out.print("������");
		String name = CMUtility.readString(10);

		System.out.print("�Ա�");
		char gender = CMUtility.readChar();

		System.out.print("���䣺");
		int age = CMUtility.readInt();

		System.out.print("�绰��");
		String phone = CMUtility.readString(13);

		System.out.print("���䣺");
		String email = CMUtility.readString(30);

		// ���������ݷ�װ��������
		Customer customer = new Customer(name, gender, age, phone, email);
		boolean isSuccess = customerList.addCustomer(customer);
		if (isSuccess) {
			System.out.println("--------------������---------------------");
		} else {
			System.out.println("--------------�ͻ�Ŀ¼���������ʧ��---------------------");
		}
	}

	/**
	 * �޸Ŀͻ��Ĳ���
	 * 
	 */
	public void modifyCustomer() {
		System.out.println("--------------�޸Ŀͻ���Ϣ---------------------");
		Customer cust;
		int number;

		for (;;) {
			System.out.println("��ѡ��Ҫ�޸ĵĿͻ���ţ�-1�˳�����");
			number = CMUtility.readInt();

			if (number == -1) {
				return;
			}
			cust = customerList.getCustomer(number - 1);
			if (cust == null) {
				System.out.println("�޷��ҵ�ָ���ͻ���");
			} else {// �ҵ���Ӧ��ŵĿͻ�
				break;// ����if ���޸Ŀͻ���Ϣ
			}
		}
		// �޸Ŀͻ���Ϣ
		System.out.print("����(" + cust.getName() + "):");
		String name = CMUtility.readString(10, cust.getName());

		System.out.print("�Ա�(" + cust.getGender() + "):");
		char gender = CMUtility.readChar(cust.getGender());

		System.out.print("����(" + cust.getAge() + "):");
		int age = CMUtility.readInt(cust.getAge());

		System.out.print("�绰(" + cust.getPhone() + "):");
		String phone = CMUtility.readString(13, cust.getPhone());

		System.out.print("����(" + cust.getEmail() + "):");
		String email = CMUtility.readString(30, cust.getEmail());

		Customer newCust = new Customer(name, gender, age, phone, email);
		boolean isRepalaced = customerList.replaceCustomer(number - 1, newCust);
		if (isRepalaced) {
			System.out.println("----------------------�޸����----------------------------");
		} else {
			System.out.println("------------------------�޸�ʧ��-----------------------------");
		}

	}

	/**
	 * ɾ���ͻ��Ĳ���
	 * 
	 */
	public void deleteCustomer() {
		System.out.println("------------------ɾ���ͻ�-----------------------------------------");
		int number;
		for (;;) {
			System.out.println("��ѡ��Ҫɾ���Ŀͻ���ţ�-1�˳�����");
			number = CMUtility.readInt();

			if (number == -1) {
				return;
			}
			Customer customer = customerList.getCustomer(number - 1);
			if (customer == null) {
				System.out.println("�޷��ҵ�ָ���ͻ���");
			} else {
				break;
			}
		}

		// �ҵ�ָ���ͻ�
		System.out.println("�Ƿ�ɾ����Y/N��");
		char isDelete = CMUtility.readConfirmSelection();
		if (isDelete == 'Y') {
			boolean deleteSuccess = customerList.deleteCustomer(number - 1);
			if (deleteSuccess) {
				System.out.println("----------------ɾ���ɹ�--------------------------");
			} else {
				System.out.println("----------------ɾ��ʧ��--------------------------");
			}
		} else {
			return;
		}
	}

	/**
	 * ��ʾ�ͻ��б�Ĳ���
	 * 
	 */
	public void listAllCustomers() {
		System.out.println("-------------�ͻ��б�------------");
		int total = customerList.getTotal();
		if (total == 0) {
			System.out.println("û�пͻ���¼");
		} else {
			System.out.println("���\t����\t�Ա�\t����\t�绰\t\t����");
			Customer[] custs = customerList.getAllCustomers();
			for (int i = 0; i < custs.length; i++) {
				Customer cust = custs[i];
				System.out.println((i + 1) + "\t" + cust.getName() + "\t" + cust.getGender() + "\t" + cust.getAge()
						+ "\t" + cust.getPhone() + "\t" + cust.getEmail());

			}

		}

		System.out.println("-------------�ͻ��б����-----------------------");
	}

	public static void main(String[] args) {
		CustomerView view = new CustomerView();
		view.enterMainMenu();
	}

}
