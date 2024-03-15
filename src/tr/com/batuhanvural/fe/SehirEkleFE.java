package tr.com.batuhanvural.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.batuhanvural.interfaces.FeInterfaces;

public class SehirEkleFE extends JDialog implements FeInterfaces{

	public SehirEkleFE() {
		
		initPencere();

	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Şehir Ekle"));		
		add(panel);
		
		setTitle("Şehir Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JMenuBar initBar() {
		
	
		
		return null;
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());		
		JPanel fieldPanel = new JPanel(new GridLayout(2,2));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		
		JLabel sehirLabel = new JLabel("Şehir Adı:",JLabel.RIGHT);
		fieldPanel.add(sehirLabel);
		JTextField sehirTextField = new JTextField(15);
		fieldPanel.add(sehirTextField);
		
		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		buttonPanel.add(iptalButton);
		
		
		
		panel.add(fieldPanel,BorderLayout.NORTH);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		
		
		
		
		return panel;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
