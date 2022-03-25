package design;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.event.ListSelectionEvent;

import uml.Expense;
import uml.Revenue;
import uml.User;

public class InputOutput {
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<String> positions = new ArrayList<String>();

	// getter for users arraylist
	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<String> getPositions() {
		return positions;
	}

	// this method here reads the positions of the users
	public void readPositions() {
		File positionsFile = new File("src\\positions.txt");
		try {
			Scanner sc = new Scanner(positionsFile);
			while (sc.hasNext()) {
				String s = sc.nextLine();
				if (!s.isEmpty()) {
					positions.add(s);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No positions file found!");
		}
	}

//a method that reads data from text files and gathers them in arraylists. Plus it classifies the items of arraylist as variables
	public void readUsers() {
		File usersFile = new File("src\\users.txt");
		int i = 0;
		try {
			Scanner sc = new Scanner(usersFile);
			while (sc.hasNext()) {
				String s = sc.nextLine();
				if (!s.isEmpty()) {
					String[] arr = s.split(",");
					int id = Integer.parseInt(arr[0].trim());
					String username = arr[1].trim();
					String password = arr[2].trim();
					String name = arr[3].trim();
					String lastname = arr[4].trim();
					double balance = Double.parseDouble(arr[5].trim());
					String position = arr[6].trim();
					// create object of user in memory
					User user = new User(id, username, password, name, lastname, balance, position);
					users.add(user);
					i++;
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No user file found!");
		}

	}

	public void readRevenue() {
		File revenueFile = new File("src\\revenue.txt");
		try {
			Scanner sc = new Scanner(revenueFile);
			while (sc.hasNext()) {
				String s = sc.nextLine();
				if (!s.isEmpty()) {
					String[] arr = s.split(",");
					if (arr.length == 4) {
						int id = Integer.parseInt(arr[0].trim());
						String name = arr[1].trim();
						String dateString = arr[2].trim();
						Date date = Date.valueOf(dateString);
						double amount = Double.parseDouble(arr[3].trim());
						int index = findUserById(id);
						if (index != -1) {
							Revenue rev = new Revenue(name, date, amount);
							users.get(index).addRevenue(rev);

						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Expenses file not found!");
		}
	}

	// this method reads the expenses file and divides the parts of it into items of
	// arraylist
	public void readExpenses() {
		File expensesFile = new File("src\\expenses.txt");
		try {
			Scanner sc = new Scanner(expensesFile);
			while (sc.hasNext()) {
				String s = sc.nextLine();
				if (!s.isEmpty()) {
					String[] arr = s.split(",");
					if (arr.length == 5) {
						int id = Integer.parseInt(arr[0].trim());
						// System.out.println("id="+id+". user="+users.get(id-1));
						String name = arr[1].trim();
						String dateString = arr[2].trim();
						Date date = Date.valueOf(dateString);
						double amount = Double.parseDouble(arr[3].trim());
						boolean status = Boolean.parseBoolean(arr[4].trim());
						int index = findUserById(id);
						if (index != -1) {
							Expense ex = new Expense(name, date, amount, status);
							users.get(index).addExpenses(ex);

						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Expenses file not found!");
		}
	}

	// this method finds relevant users based on name or lastname in the arraylist
	public ArrayList<User> findUserById(String s) {
		ArrayList<User> result = new ArrayList<User>();
		for (int i = 0; i < users.size(); i++) {
			// I use contains method to return values even if user didnt input the name or
			// last name fully
			if (users.get(i).getName().contains(s) || users.get(i).getLastname().contains(s)) {
				result.add(users.get(i));
			}
		}
		return result;
	}

	// overloading previous method for returning index of needed user (found by id)
	public int findUserById(int id) {
		int[] arr = new int[users.size()];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = users.get(i).getId();
		}
		int res = binarySearch(arr, 0, arr.length - 1, id);
		return res; // res will be -1 in case where such id is not found
	}

	public int binarySearch(int array[], int leftIndex, int rightIndex, int id) {
		// check if we didnt reach the middle yet
		if (rightIndex >= leftIndex) {
			int mid = leftIndex + (rightIndex - leftIndex) / 2;
			// if this element if the one we need
			if (array[mid] == id) {
				return mid;
			} else if (array[mid] > id) {
				// we start searching recursively on the left side of array
				return binarySearch(array, leftIndex, mid - 1, id);
			} else {
				// we start searching recursively on the right side of array
				return binarySearch(array, mid + 1, rightIndex, id);
			}
		}
		return -1;

	}
}
