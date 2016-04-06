package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;

public class Simulator extends JFrame {

 private JPanel contentPane;
 private JTextArea mainDisp;
 private JTextField secDisp;
 private JButton button_0;
 private JButton button_1;
 private JButton button_2;
 private JButton button_3;
 private JButton button_4;
 private JButton button_5;
 private JButton button_6;
 private JButton button_7;
 private JButton btnClear;
 private JButton button_9;
 private JButton btnEnter;
 private JButton button_tara;
 private JButton button_8;
 private JTextArea taraDisp;
 private WeightDTO weight;

 /**
  * Launch the application.
  */
 

 /**
  * Create the frame.
  */
 public Simulator(WeightDTO weight) {
	this.weight = weight;
  setTitle("Weight Simulator 24");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 600, 300);
  contentPane = new JPanel();
  contentPane.setBackground(Color.LIGHT_GRAY);
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  contentPane.setLayout(null);
  
  mainDisp = new JTextArea();
  mainDisp.setText("0.0");
  mainDisp.setEditable(false);
  mainDisp.setBounds(6, 6, 303, 40);
  contentPane.add(mainDisp);
  mainDisp.setColumns(10);
  
  secDisp = new JTextField();
  secDisp.setText("...");
  secDisp.setEditable(false);
  secDisp.setColumns(10);
  secDisp.setBounds(6, 46, 303, 40);
  contentPane.add(secDisp);
  
  taraDisp = new JTextArea();
  taraDisp.setText("Current Tara: ");
  taraDisp.setBounds(6, 86, 303, 40);
  contentPane.add(taraDisp);
  
  button_1 = new JButton("1");
  button_1.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) 
   {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("1");
   }
  });
  button_1.setBounds(330, 110, 50, 50);
  contentPane.add(button_1);
  
  button_2 = new JButton("2");
  button_2.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("2");
   }
  });
  button_2.setBounds(382, 110, 50, 50);
  contentPane.add(button_2);
  
  button_3 = new JButton("3");
  button_3.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("3");
   }
  });
  button_3.setBounds(434, 110, 50, 50);
  contentPane.add(button_3);
  
  button_6 = new JButton("6");
  button_6.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("6");
   }
  });
  button_6.setBounds(434, 58, 50, 50);
  contentPane.add(button_6);
  
  button_4 = new JButton("4");
  button_4.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("4");
   }
  });
  button_4.setBounds(330, 58, 50, 50);
  contentPane.add(button_4);
  
  button_5 = new JButton("5");
  button_5.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("5");
   }
  });
  button_5.setBounds(382, 58, 50, 50);
  contentPane.add(button_5);
  
  button_9 = new JButton("9");
  button_9.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("9");
   }
  });
  button_9.setBounds(434, 6, 50, 50);
  contentPane.add(button_9);
  
  button_7 = new JButton("7");
  button_7.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) 
   {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("7");
   }
  });
  button_7.setBounds(330, 6, 50, 50);
  contentPane.add(button_7);
  
  button_8 = new JButton("8");
  button_8.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
    mainDisp.append("8");
   }
  });
  button_8.setBounds(382, 6, 50, 50);
  contentPane.add(button_8);
  
  btnClear = new JButton("clear");
  btnClear.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   mainDisp.setText("0.0");
   }
  });
  btnClear.setBounds(305, 162, 70, 50);
  contentPane.add(btnClear);
  
  button_0 = new JButton("0");
  button_0.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   if (mainDisp.getText().startsWith("kg")){
		   mainDisp.setText("");;
	   }
	   mainDisp.append("0");
   }
  });
  button_0.setBounds(382, 162, 50, 50);
  contentPane.add(button_0);
  
  //We need this?
  btnEnter = new JButton("enter");
  btnEnter.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   
	   try {
		   String current = mainDisp.getText();
			   weight.setBrutto(Double.parseDouble(current));
			   
	} catch (NumberFormatException e2) {
		weight.setMainDisp(mainDisp.getText());
	}
   }
  });
  btnEnter.setBounds(430, 162, 70, 50);
  contentPane.add(btnEnter);
  
  button_tara = new JButton("<T>");
  button_tara.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   
	  String newTara = mainDisp.getText();
	  taraDisp.setText("Current Tara: ");
	   taraDisp.append(newTara);
	   mainDisp.setText("0.0");
	   weight.setTara(Double.parseDouble(newTara));
	   
	  
   }
  });
  button_tara.setBounds(6, 162, 70, 50);
  contentPane.add(button_tara);
  
  JSpinner spinner = new JSpinner();
  spinner.setBounds(88, 162, 111, 50);
  spinner.addChangeListener(new ChangeListener() {
	  
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		mainDisp.setText(spinner.getValue().toString());
		
	}
});;
  contentPane.add(spinner);
  
  JLabel lblBruttoVgt = new JLabel("Brutto v�gt");
  lblBruttoVgt.setFont(new Font("Lucida Grande", Font.BOLD, 13));
  lblBruttoVgt.setBounds(203, 174, 90, 16);
  contentPane.add(lblBruttoVgt);
  
  
 }

public JTextArea getMainDisp() {
	return mainDisp;
}

public void setMainDisp(JTextArea txtKg) {
	this.mainDisp = txtKg;
}

public JTextField getSecDisp() {
	return secDisp;
}

public void setTara(WeightDTO weight, int i){
	weight.setTara(i);
	
}

public JTextArea getTaraDisp() {
	return taraDisp;
}

public JButton getBtnEnter() {
	return btnEnter;
}
}
