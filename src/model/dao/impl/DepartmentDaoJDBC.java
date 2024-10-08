package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection connection) {
		this.conn = connection;
	}


	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(Name) "
					+ "VALUES "
					+ "(?)"
					+st.RETURN_GENERATED_KEYS);
			
			st.setInt(1, department.getId());
			st.setString(2, department.getName());
			
			int rows = st.executeUpdate();
			if(rows> 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				}
			}
			else {
				throw new DBException("Unexpected error!");
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closePreparedStatement(st);
		}
	}
	

	@Override
	public void update(Department department) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE department "
					+"SET Name = ? "
					+"WHERE Id = ?");
			st.setString(1, department.getName());
			st.setInt(2, department.getId());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closePreparedStatement(st);
		}
	}
	

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			
			st.setInt(1, id);
			
			
			st.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closePreparedStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?;");
			
			st.setInt(1, id);
			
			
			ResultSet rs = st.executeQuery();
			
			Department dp = null;
			
			if(rs.next()) {
				dp = instantiateDepartment(rs);
			}
			return dp;
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closePreparedStatement(st);
		}
		return null;
	}

	
	private Department instantiateDepartment(ResultSet rs) throws SQLException{
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		
		return dep;
	}

	@Override
	public List<Department> findAll() {
		Statement st = null;
		ResultSet rs = null;
		List<Department> dep = new ArrayList<>();
		try {
			
			st = conn.createStatement();
			
			rs = st.executeQuery("SELECT * FROM department");
			
			Department dp = null;
			
			while (rs.next()) {
				dp = instantiateDepartment(rs);
			    dep.add(dp);
			}
			return dep;
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}
	}


