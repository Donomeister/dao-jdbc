package application;

import java.util.Date;
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
		System.out.println("======================================");
		for(Seller l : list) {
			System.out.println(l);
		}
		System.out.println("======================================");

		Seller s = new Seller(null, "Luan", "Luan@gmail", new Date(), 80.000, d);
		
		sellerdao.insert(s);
		
		System.out.println("======================================");

		Seller newSeller = s;
		s.setName("Luanzeira");
		s.setId(1);
		
		sellerdao.update(newSeller);
		
	}

}
