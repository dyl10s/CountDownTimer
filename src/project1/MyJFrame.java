package project1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyJFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	CountDownTimer cdt = new CountDownTimer(1, 0, 0);
	
	Timer timer = new Timer("Tick");
	
	JPanel Top = new JPanel();
	JPanel Middle = new JPanel();
	JPanel Bottom = new JPanel();
	
	JLabel lblTime = new JLabel(cdt.toString());
	JButton btnInc = new JButton("Increment");
	JButton btnDec = new JButton("Decrease");
	JButton btnSuspend = new JButton("Suspend");
	JButton btnNewTimer = new JButton("New Timer");
	JButton btnAddTime = new JButton("Add Time");
	
	JMenuBar mainMenuBar = new JMenuBar();
	JMenu fileMenu = new  JMenu("File");
	JMenuItem saveItem = new JMenuItem("Save");
	JMenuItem loadItem = new JMenuItem("Load");
	
	public MyJFrame(int hours, int minutes, int seconds) {
		
		cdt = new CountDownTimer(hours, minutes, seconds);
		formatFrame();
		
	}
	
	public MyJFrame() {

		formatFrame();

	}
	
	public void formatFrame() {
		
		Font timerFont = new Font("Times New Roman", 0, 100);
		lblTime.setFont(timerFont);
			
		FlowLayout layout = new FlowLayout();
		this.setTitle("CountDownTimer");
		this.setSize(500, 275);
		this.setLayout(layout);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.setResizable(false);
		
		this.setJMenuBar(mainMenuBar);
		mainMenuBar.add(fileMenu);
		fileMenu.add(saveItem);
		saveItem.addActionListener(this);
		
		fileMenu.add(loadItem);
		loadItem.addActionListener(this);	
		
		this.add(Top);
		this.add(Middle);
		this.add(Bottom);
			
		Top.add(btnInc);
		btnInc.addActionListener(this);
		
		Top.add(btnDec);
		btnDec.addActionListener(this);
		
		Middle.add(lblTime);
		
		Bottom.add(btnAddTime);
		btnAddTime.addActionListener(this);
		
		Bottom.add(btnSuspend);
		btnSuspend.addActionListener(this);
		
		Bottom.add(btnNewTimer);
		btnNewTimer.addActionListener(this);
		
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
		
		if (comp == btnSuspend) {
			
			if (CountDownTimer.getSuspend() == false) {
				CountDownTimer.Suspend(true);
				btnSuspend.setText("Resume");
			}else {
				CountDownTimer.Suspend(false);
				btnSuspend.setText("Suspend");
			}

		}
		
		if (comp == btnNewTimer) {
			
			int hours = Integer.parseInt(JOptionPane.showInputDialog(this, "How many hours would you like the timer to have?", "0"));
			int mins = Integer.parseInt(JOptionPane.showInputDialog(this, "How many minutes would you like the timer to have?", "0"));
			int seconds = Integer.parseInt(JOptionPane.showInputDialog(this, "How many seconds would you like the timer to have?", "0"));
			
			@SuppressWarnings("unused")
			MyJFrame newFrame = new MyJFrame(hours, mins, seconds);
			
		}
		
		if (comp == loadItem) {
			
			JFileChooser fc = new JFileChooser();
			
			if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				cdt.load(fc.getSelectedFile().getPath());
			}
			
		}
		
		if (comp == saveItem) {
			
			JFileChooser fc = new JFileChooser();
			
			if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				cdt.save(fc.getSelectedFile().getPath() + ".timer");
			}
			
		}
		
		if (comp == btnAddTime) {
			
			int hours = Integer.parseInt(JOptionPane.showInputDialog(this, "How many hours would you like to add?", "0"));
			int mins = Integer.parseInt(JOptionPane.showInputDialog(this, "How many minutes would you like to add?", "0"));
			int seconds = Integer.parseInt(JOptionPane.showInputDialog(this, "How many seconds would you like to add?", "0"));
			
			cdt.add(new CountDownTimer(hours, mins, seconds));
			
		}
		
		if (comp == btnInc) {
			cdt.inc();
		}
		
		updateTimer();
	}
	
	public void Tick() {
		
		cdt.dec();
		lblTime.setText(cdt.toString());
		
		if (CountDownTimer.getSuspend() == true) {
			btnSuspend.setText("Resume");
		}else {
			btnSuspend.setText("Suspend");
		}
		
	}
	
	public void updateTimer() {
		
		lblTime.setText(cdt.toString());
		
	}

}
