package application;

import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		Department db = new Department(10, "Luan");
		
		System.out.println(db);
	}

}
