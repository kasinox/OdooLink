package com.alltekusa.qbLink.Viewer.Console.cmd;

import java.io.Console;
import java.util.Arrays;
import java.io.IOException;

public class ConfigurationCMD {

	// list of commands
	// updateSO(int so_number)
	// insertSO(int so_number)
	// updateSOL(int sol_number)
	// insertSOL(int sol_number)

	// updateCustomer(int customer_odoo_id)
	// insertCustomer(int customer_odoo_id)

	// updateVendor(int vendor_odoo_id)
	// insertVendor(int vendor_odoo_id)

	// updateProduct(int product_odooID)
	// insertProduct(int product_odooID)

	// command prompt

	// please enter the so number you want to update

	// so already exist, do you want to over-write?
	// customer already exist, do you want to over-write?
	// vendor already exist, do you want to over-write?

	// menu
	// 1. update sales order
	// 2. update customer
	// 3. update vendor
	// 4. update product

	public static void main(String args[]) throws IOException {

		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}

		String login = c.readLine("Enter your login: ");
		char[] oldPassword = c.readPassword("Enter your old password: ");

		if (verify(login, oldPassword)) {
			boolean noMatch;
			do {
				char[] newPassword1 = c.readPassword("Enter your new password: ");
				char[] newPassword2 = c.readPassword("Enter new password again: ");
				noMatch = !Arrays.equals(newPassword1, newPassword2);
				if (noMatch) {
					c.format("Passwords don't match. Try again.%n");
				} else {
					change(login, newPassword1);
					c.format("Password for %s changed.%n", login);
				}
				Arrays.fill(newPassword1, ' ');
				Arrays.fill(newPassword2, ' ');
			} while (noMatch);
		}

		Arrays.fill(oldPassword, ' ');
	}

	// Dummy change method.
	static boolean verify(String login, char[] password) {
		// This method always returns
		// true in this example.
		// Modify this method to verify
		// password according to your rules.
		return true;
	}

	// Dummy change method.
	static void change(String login, char[] password) {
		// Modify this method to change
		// password according to your rules.
	}

}
