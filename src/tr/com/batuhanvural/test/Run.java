package tr.com.batuhanvural.test;

import javax.swing.SwingUtilities;

import tr.com.batuhanvural.dal.UrunlerDAL;
import tr.com.batuhanvural.fe.AnaPencereFE;
import tr.com.batuhanvural.fe.LoginFE;

public class Run {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new LoginFE();
				
				

				
			}
		});

	}

}
