package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerdao = DaoFactory.createSellerDao();
		
		Department d = new Department(2, null);
		
		List<Seller> list = sellerdao.findByDepartment(d);
		
		for(Seller l : list) {
			System.out.println(l);
		}
	}

}
