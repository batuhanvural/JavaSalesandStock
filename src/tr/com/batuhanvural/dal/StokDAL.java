package tr.com.batuhanvural.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.batuhanvural.complex.types.StokContractComplex;
import tr.com.batuhanvural.complex.types.StokContractTotalComplex;
import tr.com.batuhanvural.core.ObjectHelper;
import tr.com.batuhanvural.interfaces.DALInterfaces;
import tr.com.batuhanvural.types.StokContract;

public class StokDAL extends ObjectHelper implements DALInterfaces<StokContract> {

	@Override
	public void Insert(StokContract entity) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Stok (PersonelId, UrunId,Tarih,Adet) VALUES (" + entity.getPersonelId()
					+ "," + entity.getUrunId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + ")");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * SELECT Stok.Id, AdiSoyadi, Adi, Adet, Stok.Tarih FROM Stok LEFT JOIN Urunler
	 * ON Stok.UrunId = Urunler.Id LEFT JOIN Personel ON Stok.PersonelId
	 * =Personel.Id
	 */

	public List<StokContractComplex> GetAllStok() {

		List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
		Connection connection = getConnection();
		StokContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT Stok.Id, AdiSoyadi, Adi, Adet, Stok.Tarih FROM Stok "
					+ "	  LEFT JOIN Urunler ON Stok.UrunId = Urunler.Id "
					+ "	  LEFT JOIN Personel ON Stok.PersonelId =Personel.Id ORDER BY stok.Id DESC");
			while (resultSet.next()) {

				contract = new StokContractComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setTarih(resultSet.getString("Stok.Tarih"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;

	}

	public List<StokContractTotalComplex> GetTotalStok() {

		List<StokContractTotalComplex> datacontract = new ArrayList<StokContractTotalComplex>();
		Connection connection = getConnection();
		StokContractTotalComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT SUM(Adet) as toplam, Stok.Id, AdiSoyadi, Adi, Adet, Stok.Tarih FROM Stok "
					+ "LEFT JOIN Urunler ON Stok.UrunId = Urunler.Id "
					+ "LEFT JOIN Personel ON Stok.PersonelId =Personel.Id GROUP BY UrunId");
			while (resultSet.next()) {

				contract = new StokContractTotalComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setTarih(resultSet.getString("Stok.Tarih"));
				contract.setToplam(resultSet.getInt("toplam"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;

	}

	
	@Override
	public List<StokContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokContract Delete(StokContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StokContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
