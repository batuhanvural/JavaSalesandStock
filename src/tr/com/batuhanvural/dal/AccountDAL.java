package tr.com.batuhanvural.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.batuhanvural.core.ObjectHelper;
import tr.com.batuhanvural.interfaces.DALInterfaces;
import tr.com.batuhanvural.types.AccountContact;

public class AccountDAL extends ObjectHelper implements DALInterfaces<AccountContact> {

	@Override
	
	public void Insert(AccountContact entity) {
		
	
	Connection connection = getConnection();
	try {
		Statement statement = connection.createStatement();
		statement.executeUpdate("INSERT INTO Accounts (PersonelId, YetkiId,Sifre) VALUES ("
				+entity.getPersonelId()
				+","
				+entity.getYetkiId()
				+",'"
				+entity.getSifre() + "')");
		statement.close();
		connection.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
	public AccountContact GetPersonelIdveSifre(int personelId,String sifre){
		
		AccountContact contract = new AccountContact();
		List<AccountContact> listele = new ArrayList<AccountContact>();
		Connection baglanti = getConnection();
		
		try {
			
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId ="+personelId+" AND Sifre='"+sifre.trim()+"'");
			
			while(rs.next()) {
				
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));				
			}
			sorgu.close();
			baglanti.close();
			
		} catch (Exception e) {
			System.out.println(e);
			}		
		
		return contract;		
	}
	
public AccountContact GetYetkiId(int personelId){
		
		AccountContact contract = new AccountContact();
		List<AccountContact> listele = new ArrayList<AccountContact>();
		Connection baglanti = getConnection();
		
		try {
			
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId ="+personelId+"");
			
			while(rs.next()) {
				
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));				
			}
			sorgu.close();
			baglanti.close();
			
		} catch (Exception e) {
			System.out.println(e);
			}		
		
		return contract;		
	}
	
	@Override
	public List<AccountContact> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountContact Delete(AccountContact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountContact entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AccountContact> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
