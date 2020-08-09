package com.project.ui;

import com.project.bean.Customer;
import com.project.service.CustomerList;
import com.project.util.CMUtility;

public class CustomerView {
	// 封装 创建包含最大10个客户对象的CustomerList对象，让成员方法使用
	private CustomerList customerList = new CustomerList(10);

	// 添加数据
	public CustomerView() {
		Customer customer = new Customer("张三", '男', 24, "13312345678", "aaaaa.ddddd@qq.com");

		customerList.addCustomer(customer);
	}

	// 方法

	/**
	 * 显示软件界面的方法
	 */
	public void enterMainMenu() {
		boolean isFlag = true;
		while (isFlag) {
			System.out.println(
					"-----------------------客户信息管理系统---------------------------------------------------------------");
			System.out.println("\t\t\t    1添加客户");
			System.out.println("\t\t\t    2修改客户");
			System.out.println("\t\t\t    3删除客户");
			System.out.println("\t\t\t    4客户列表");
			System.out.println("\t\t\t    5退出\n");
			System.out.println("\t\t    请选择(1-5)：");

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
				// System.out.println("退出");
				System.out.println("确认是否退出（Y/N）：");
				char isExit = CMUtility.readConfirmSelection();
				if (isExit == 'Y') {
					isFlag = false;
				}

				break;

			}

		}
	}

	/**
	 * 添加客户的操作
	 * 
	 */
	public void addNewCustomer() {
		System.out.println("----------------添加客户----------------------------");
		System.out.print("姓名：");
		String name = CMUtility.readString(10);

		System.out.print("性别：");
		char gender = CMUtility.readChar();

		System.out.print("年龄：");
		int age = CMUtility.readInt();

		System.out.print("电话：");
		String phone = CMUtility.readString(13);

		System.out.print("邮箱：");
		String email = CMUtility.readString(30);

		// 将上述数据封装到对象中
		Customer customer = new Customer(name, gender, age, phone, email);
		boolean isSuccess = customerList.addCustomer(customer);
		if (isSuccess) {
			System.out.println("--------------添加完成---------------------");
		} else {
			System.out.println("--------------客户目录已满，添加失败---------------------");
		}
	}

	/**
	 * 修改客户的操作
	 * 
	 */
	public void modifyCustomer() {
		System.out.println("--------------修改客户信息---------------------");
		Customer cust;
		int number;

		for (;;) {
			System.out.println("请选择要修改的客户编号（-1退出）：");
			number = CMUtility.readInt();

			if (number == -1) {
				return;
			}
			cust = customerList.getCustomer(number - 1);
			if (cust == null) {
				System.out.println("无法找到指定客户！");
			} else {// 找到相应编号的客户
				break;// 跳出if 到修改客户信息
			}
		}
		// 修改客户信息
		System.out.print("姓名(" + cust.getName() + "):");
		String name = CMUtility.readString(10, cust.getName());

		System.out.print("性别(" + cust.getGender() + "):");
		char gender = CMUtility.readChar(cust.getGender());

		System.out.print("年龄(" + cust.getAge() + "):");
		int age = CMUtility.readInt(cust.getAge());

		System.out.print("电话(" + cust.getPhone() + "):");
		String phone = CMUtility.readString(13, cust.getPhone());

		System.out.print("邮箱(" + cust.getEmail() + "):");
		String email = CMUtility.readString(30, cust.getEmail());

		Customer newCust = new Customer(name, gender, age, phone, email);
		boolean isRepalaced = customerList.replaceCustomer(number - 1, newCust);
		if (isRepalaced) {
			System.out.println("----------------------修改完成----------------------------");
		} else {
			System.out.println("------------------------修改失败-----------------------------");
		}

	}

	/**
	 * 删除客户的操作
	 * 
	 */
	public void deleteCustomer() {
		System.out.println("------------------删除客户-----------------------------------------");
		int number;
		for (;;) {
			System.out.println("请选择要删除的客户编号（-1退出）：");
			number = CMUtility.readInt();

			if (number == -1) {
				return;
			}
			Customer customer = customerList.getCustomer(number - 1);
			if (customer == null) {
				System.out.println("无法找到指定客户！");
			} else {
				break;
			}
		}

		// 找到指定客户
		System.out.println("是否删除（Y/N）");
		char isDelete = CMUtility.readConfirmSelection();
		if (isDelete == 'Y') {
			boolean deleteSuccess = customerList.deleteCustomer(number - 1);
			if (deleteSuccess) {
				System.out.println("----------------删除成功--------------------------");
			} else {
				System.out.println("----------------删除失败--------------------------");
			}
		} else {
			return;
		}
	}

	/**
	 * 显示客户列表的操作
	 * 
	 */
	public void listAllCustomers() {
		System.out.println("-------------客户列表------------");
		int total = customerList.getTotal();
		if (total == 0) {
			System.out.println("没有客户记录");
		} else {
			System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
			Customer[] custs = customerList.getAllCustomers();
			for (int i = 0; i < custs.length; i++) {
				Customer cust = custs[i];
				System.out.println((i + 1) + "\t" + cust.getName() + "\t" + cust.getGender() + "\t" + cust.getAge()
						+ "\t" + cust.getPhone() + "\t" + cust.getEmail());

			}

		}

		System.out.println("-------------客户列表完成-----------------------");
	}

	public static void main(String[] args) {
		CustomerView view = new CustomerView();
		view.enterMainMenu();
	}

}
