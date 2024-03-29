package tr.com.batuhanvural.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import tr.com.batuhanvural.interfaces.FeInterfaces;
import tr.com.batuhanvural.types.PersonelContract;

public class LoginFE extends JDialog implements FeInterfaces{

	public static JComboBox emailBox;
	public LoginFE() {
		
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("Lütfen sisteme giriş yapmak için bilgilerinizi giriniz."));
		add(panel);
		setTitle("Lütfen Giriş Yapınız.");
		pack();
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
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel emaiLabel = new JLabel("Email:",JLabel.RIGHT);
		panel.add(emaiLabel);
		emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(emailBox);
		JLabel passwordLabel = new JLabel("Şifreniz:",JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(15);
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Giriş");
		panel.add(loginButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();
				
				if (new AccountDAL().GetPersonelIdveSifre(contract.getId(), passwordField.getText()).getId()!=0) {
					
					new AnaPencereFE();
					setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(null, "Giriş Başarısız.");

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
