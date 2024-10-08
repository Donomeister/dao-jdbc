package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		DepartmentDao departmentdao = DaoFactory.createDepartmentDao();
		
		List<Department> dep = departmentdao.findAll();
		
		for(Department d : dep) {
			System.out.println(d);
		}
		
	}

}
