package tr.com.batuhanvural.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.batuhanvural.complex.types.StokContractComplex;
import tr.com.batuhanvural.complex.types.StokContractTotalComplex;
import tr.com.batuhanvural.dal.MusteriDAL;
import tr.com.batuhanvural.dal.SatisDAL;
import tr.com.batuhanvural.dal.StokDAL;
import tr.com.batuhanvural.dal.UrunlerDAL;
import tr.com.batuhanvural.interfaces.FeInterfaces;
import tr.com.batuhanvural.types.PersonelContract;
import tr.com.batuhanvural.types.SatisContract;
import tr.com.batuhanvural.types.StokContract;
import tr.com.batuhanvural.types.UrunlerContract;
import tr.com.batuhanvural.utilities.MenulerCom;

public class AnaPencereFE extends JFrame implements FeInterfaces {

	public AnaPencereFE() {

		initPencere();

	}

	@Override
	public void initPencere() {

		JPanel panel = initPanel();
		JMenuBar bar = initBar();

		add(panel);
		setJMenuBar(bar);
		setTitle("Satış ve Stok Programı");
		setSize(600, 600);
		pack(); //icverisinde oluşturduğumuz alanlar otomatik size olsun demek.
		setVisible(true);
		setLocationRelativeTo(null); // pencere ilk açıldığında ortada durması için
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public JMenuBar initBar() {

		JMenuBar bar = MenulerCom.initBar();

		return bar;
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);

		return panel;
	}

	@Override
	public JTabbedPane initTabs() {

		JTabbedPane pane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("icons/stock.png");
		ImageIcon icon2 = new ImageIcon("icons/stock.png");

		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());

		/* Stock Items */

		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel stokSolAltPanel = new JPanel();

		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));
		Object[] stokKolonlar = { "Id", "Ürün Adı", "Personel Adı", "Adet", "Tarih" };
		DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
		JTable table = new JTable(model);
		JScrollPane stockTablePane = new JScrollPane(table);
		
		for(StokContractComplex contract : new StokDAL().GetAllStok()) {
			
			model.addRow(contract.getVeriler());
		}

		JLabel stokUrunAdiLabel = new JLabel("Ürün Adı:", JLabel.RIGHT);
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		stokSolUstPanel.add(stokUrunAdiBox);
		JLabel stokAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetTextField = new JTextField(10);
		stokSolUstPanel.add(stokAdetTextField);
		JLabel stokTarihiLabel = new JLabel("Tarih:", JLabel.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();
		stokSolUstPanel.add(stokTarihi);

		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		JButton stokYenileButton = new JButton("Stok Yenile");
		stokSolUstPanel.add(stokYenileButton);
		JButton stokTotalButton = new JButton("Stok Toplam Ürün");
		stokSolUstPanel.add(stokTotalButton);
		
		stokTotalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					
					model.setRowCount(0);
				}
				for(StokContractTotalComplex total : new StokDAL().GetTotalStok()) {
					
					model.addRow(total.getVeriler());
				}
				
			}
		});
		
		stokYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					
					model.setRowCount(0);
				}
				for(StokContractComplex compcontract : new StokDAL().GetAllStok()) {
					
					model.addRow(compcontract.getVeriler());
				}
				
			}
		});

		stokEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(stokTarihi.getDate());
				StokContract contract = new StokContract();
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date);
				contract.setAdet(Integer.parseInt(stokAdetTextField.getText()));

				new StokDAL().Insert(contract);
				
				JOptionPane.showMessageDialog(null, uContract.getAdi()+" adlı ürün eklenmiştir.");
				
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					
					model.setRowCount(0);
				}
				for(StokContractComplex compcontract : new StokDAL().GetAllStok()) {
					
					model.addRow(compcontract.getVeriler());
				}
			}
		});

		/* Seller Items */

		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel satisSagAltPanel = new JPanel();

		Object[] satisKolonlar = { "Id", "Personel Adı", "Ürün Adı", "Adet", "Tarih" };
		DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar, 0);
		JTable satisTable = new JTable(satisModel);
		JScrollPane satisTablePane = new JScrollPane(satisTable);

		JLabel musteriLabel = new JLabel("Müşteri Adı:",JLabel.RIGHT);
		satisSagUstPanel.add(musteriLabel);
		JComboBox musteriAdiBox = new JComboBox(new MusteriDAL().GetAll().toArray());
		satisSagUstPanel.add(musteriAdiBox);
		JLabel satisUrunAdiLabel = new JLabel("Ürün Adı:", JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdiBox);
		JLabel satisAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetTextField = new JTextField(10);
		satisSagUstPanel.add(satisAdetTextField);
		JLabel satisTarihiLabel = new JLabel("Tarih:", JLabel.RIGHT);
		satisSagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();
		satisSagUstPanel.add(satisTarihi);

		JButton satisEkleButton = new JButton("Satış Yap");
		satisSagUstPanel.add(satisEkleButton);
		JButton satisYenileButton = new JButton("Yenile");
		satisSagUstPanel.add(satisYenileButton);
		
		

		stokPanel.add(stokSolPanel, BorderLayout.WEST);
		stokPanel.add(stockTablePane, BorderLayout.CENTER);

		satisPanel.add(satisSagPanel, BorderLayout.EAST);
		satisPanel.add(satisTablePane, BorderLayout.CENTER);

		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);

		stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);
		pane.addTab("Stoklar", icon, stokPanel, "Does nothing");
		pane.addTab("Satışlar", icon2, satisPanel, "Yazı");
		return pane;
	}

}
