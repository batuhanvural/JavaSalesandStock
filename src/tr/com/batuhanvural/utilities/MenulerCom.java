package tr.com.batuhanvural.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import tr.com.batuhanvural.dal.AccountDAL;
import tr.com.batuhanvural.fe.KategoriEkleFE;
import tr.com.batuhanvural.fe.LoginFE;
import tr.com.batuhanvural.fe.MusteriEkleFE;
import tr.com.batuhanvural.fe.PersonelEkleFE;
import tr.com.batuhanvural.fe.SehirEkleFE;
import tr.com.batuhanvural.fe.SifreIslemleriFE;
import tr.com.batuhanvural.fe.UrunEkleFE;
import tr.com.batuhanvural.fe.YetkiEkleFE;
import tr.com.batuhanvural.types.PersonelContract;

public class MenulerCom {
	

public static JMenuBar initBar() {
		
		JMenuBar bar = new JMenuBar();
		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);
		JMenuItem cikisItem = new JMenuItem("Çıkış");
		dosyaMenu.add(cikisItem);
		/* Ürünler Menüsü */
		JMenu urunlerMenu = new JMenu("Ürünler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();
		JMenuItem urunDuzenleItem = new JMenuItem("Ürünü Düzenle");
		urunlerMenu.add(urunDuzenleItem);
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategori Düzenle");
		urunlerMenu.add(kategoriDuzenleItem);
		/* Personel Menüsü */
		JMenu personellerMenu = new JMenu("Personel İşlemleri");		
		bar.add(personellerMenu);
		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);
		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		JMenuItem sifreBelirleItem = new JMenuItem("Şifre Belirleme");
		personellerMenu.add(sifreBelirleItem);
		personellerMenu.addSeparator();		
		JMenuItem personelDuzenleItem = new JMenuItem("Personel Düzenle");
		personellerMenu.add(personelDuzenleItem);
		JMenuItem yetkiDuzenleItem = new JMenuItem("Yetki Düzenle");
		personellerMenu.add(yetkiDuzenleItem);
		JMenuItem sifreDuzenleItem = new JMenuItem("Şifre Düzenle");
		personellerMenu.add(sifreDuzenleItem);
		/*Müşteri Menü*/
		JMenu musterilerMenu = new JMenu("Müşteriler");
		bar.add(musterilerMenu);
		JMenuItem musteriEkleItem = new JMenuItem("Müşteri Ekle");
		musterilerMenu.add(musteriEkleItem);
		JMenuItem sehirEkleItem = new JMenuItem("Şehir Ekle");
		musterilerMenu.add(sehirEkleItem);
		musterilerMenu.addSeparator();
		
		JMenuItem musteriDuzenleItem = new JMenuItem("Müşteri Düzenle");
		musterilerMenu.add(musteriDuzenleItem);
		JMenuItem sehirDuzenleItem = new JMenuItem("Şehir Düzenle");
		musterilerMenu.add(sehirDuzenleItem);
		PersonelContract contract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
		System.out.println(contract.getAdiSoyadi());
		
		if (new AccountDAL().GetYetkiId(contract.getId()).getYetkiId()==2) {
			
			personellerMenu.hide();			
		}else if (new AccountDAL().GetYetkiId(contract.getId()).getYetkiId()==3) {
			personellerMenu.hide();
			musterilerMenu.hide();
			urunlerMenu.hide();
			
		}

		urunEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new UrunEkleFE();
						
					}
				});
				// TODO Auto-generated method stub
				
			}
		});
			
			kategoriEkleItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new KategoriEkleFE();
					
				}
			});
		
			personelEkleItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					

					
				}
			});
			
			personelEkleItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							
							new PersonelEkleFE();
							
						}
					});
					
				}
			});
		
			yetkiEkleItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							
							new YetkiEkleFE();

							
						}
					});

					
				}
			});
			
			sifreBelirleItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					new SifreIslemleriFE();
					
				}
			});
			
			musteriEkleItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							
							new MusteriEkleFE();

							
						}
					});
					
			sehirEkleItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							new SehirEkleFE();
							
						}
					});

					
				}
			});					
				}
			});
		
		return bar;
	}}

