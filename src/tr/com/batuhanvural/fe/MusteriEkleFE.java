package tr.com.batuhanvural.fe;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.com.batuhanvural.dal.MusteriDAL;
import tr.com.batuhanvural.interfaces.FeInterfaces;
import tr.com.batuhanvural.types.MusteriContract;

public class MusteriEkleFE extends JDialog implements FeInterfaces {

	public MusteriEkleFE() {

		initPencere();

	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekle"));

		add(panel);
		setTitle("Müşteri Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
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

		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldpanel = new JPanel(new GridLayout(5, 2));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		JLabel adiSoyadiLabel = new JLabel("Adı Soyadı:", JLabel.RIGHT);
		fieldpanel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(15);
		fieldpanel.add(adiSoyadiField);
		JLabel telefonLabel = new JLabel("Telefon:", JLabel.RIGHT);
		fieldpanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldpanel.add(telefonField);
		JLabel sehirSecLabel = new JLabel("Şehir Seç:", JLabel.RIGHT);
		fieldpanel.add(sehirSecLabel);
		JComboBox sehirlerBox = new JComboBox();
		fieldpanel.add(sehirlerBox);
		JLabel adresLabel = new JLabel("Adres:");
		fieldpanel.add(adresLabel);

		JTextArea adresArea = new JTextArea(7, 1);
		JScrollPane pane = new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));

		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		buttonPanel.add(iptalButton);

		panel.add(fieldpanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		

		return panel;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
