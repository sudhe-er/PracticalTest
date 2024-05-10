package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Jdbccon;
import model.Product;
import model.ServiceableRegion;

public class Dataaccess {
	private List<Product> productList = new ArrayList<Product>();

	public List<Product> getProductList() {
		rundta();
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<ServiceableRegion> getServiceableRegions() {
		List<ServiceableRegion> serviceableRegions = new ArrayList<>();
		System.out.println("Retrieving serviceable regions...");

		Jdbccon jconnect = new Jdbccon();
		Connection con = jconnect.getCon();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT srrg_id, srrg_name, srrg_pinfrom, srrg_pinto FROM ServiceableRegions_i216");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				serviceableRegions.add(new ServiceableRegion(rs.getInt("srrg_id"), rs.getString("srrg_name"),
						rs.getInt("srrg_pinfrom"), rs.getInt("srrg_pinto")));
			}
			System.out.println("Serviceable regions retrieved successfully.");
			System.out.println(serviceableRegions);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return serviceableRegions;
	}

	public void rundta() {
		productList.clear();
		System.out.println("running");

		Jdbccon jconnect = new Jdbccon();
		Connection con = jconnect.getCon();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT  pid, pname, price, hsn_code, pcatid, image FROM public.ashish_products; ");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				productList.add(new Product(rs.getInt("pid"), rs.getString("pname"), rs.getDouble("price"),
						rs.getString("hsn_code"), rs.getInt("pcatid"), rs.getString("image")));
			}
			System.out.println("rop..");
			System.out.println(productList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}