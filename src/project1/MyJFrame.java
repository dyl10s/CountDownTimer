package project1;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyJFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	CountDownTimer cdt = new CountDownTimer(1, 0, 0);
	
	Timer timer = new Timer("Tick");
	
	JLabel lblTime = new JLabel(cdt.toString());
	JButton btnInc = new JButton("Increment");
	JButton btnDec = new JButton("Decrease");
	
	public MyJFrame() {
		
		Font timerFont = new Font("Times New Roman", 0, 100);
		lblTime.setFont(timerFont);
		
		FlowLayout layout = new FlowLayout();
		this.setTitle("CountDownTimer");
		this.setSize(500, 500);
		this.setLayout(layout);
		
		this.add(btnInc);
		btnInc.addActionListener(this);
		
		this.add(btnDec);
		btnDec.addActionListener(this);
		
		this.add(lblTime);
		
		this.setVisible(true);
		
		TimerTask task = new TimerTask() {
			public void run() {
				Tick();
			}
		};
		
		timer.scheduleAtFixedRate(task, 0, 1000);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		JComponent comp = (JComponent) e.getSource();
		
		if (comp == btnDec) {
			cdt.dec();
		}
		
		if (comp == btnInc) {
			cdt.inc();
		}
		
		updateTimer();
	}
	
	public void Tick() {
		
		cdt.dec();
		lblTime.setText(cdt.toString());
		
	}
	
	public void updateTimer() {
		
		lblTime.setText(cdt.toString());
		
	}

}
