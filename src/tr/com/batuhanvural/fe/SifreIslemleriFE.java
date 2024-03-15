package tr.com.batuhanvural.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import tr.com.batuhanvural.dal.AccountDAL;
import tr.com.batuhanvural.dal.PersonelDAL;
import tr.com.batuhanvural.dal.YetkilerDAL;
import tr.com.batuhanvural.interfaces.FeInterfaces;
import tr.com.batuhanvural.types.AccountContact;
import tr.com.batuhanvural.types.PersonelContract;
import tr.com.batuhanvural.types.YetkilerContract;

public class SifreIslemleriFE extends JDialog implements FeInterfaces{

	public SifreIslemleriFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Şifre belirleme işlemleri"));
		add(panel);
		
		setTitle("Şifre Belirleme İşlemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new GridLayout(5,2));
		JLabel personelLabel = new JLabel("Personel Seç:",JLabel.RIGHT);
		panel.add(personelLabel);
		JComboBox personelComboBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(personelComboBox);
		JLabel yetkiLabel = new JLabel("Yetki Seç:",JLabel.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		panel.add(yetkiBox);
		JLabel passwordLabel = new JLabel("Şifre Belirle:",JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passField = new JPasswordField(10);
		panel.add(passField);
		JLabel passTekrarLabel = new JLabel("Şifre Tekrar:",JLabel.RIGHT);
		panel.add(passTekrarLabel);
		JPasswordField passTekrar = new JPasswordField(10);
		panel.add(passTekrar);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AccountContact contract = new AccountContact();
				PersonelContract pContract = (PersonelContract) personelComboBox.getSelectedItem();
				YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();
				if (passField.getText().equals(passTekrar.getText())) {
					
					contract.setPersonelId(pContract.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(passField.getText());
					
					new AccountDAL().Insert(contract);
					JOptionPane.showMessageDialog(null, pContract.getAdiSoyadi()+" aldı kişiye "+yContract.getAdi()+" başarılı bir şekilde eklenmiştir.");
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Şifre tekrarı uyuşmuyor.");
				}
				
				
			}
		});

		return panel;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
