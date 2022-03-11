package ChatApplication;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;

public class Client extends JFrame implements ActionListener {
	
	JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
	Client(){
		
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7 , 94 ,84));
		p1.setBounds(0 ,0 , 350 , 50);
		add(p1);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ChatApplication/Icons/3.png"));
		Image i2=i1.getImage().getScaledInstance(24 , 24 , Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
	    JLabel l1 = new JLabel(i3);
	    l1.setBounds(7,13,24,24);
	    p1.add(l1);
	    
	    l1.addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent ae)
	    		{
	    	       System.exit(0);
	    		}
	});
	    
	    ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("ChatApplication/Icons/saquib1.jpeg"));
		Image i5=i4.getImage().getScaledInstance(40 , 35 , Image.SCALE_DEFAULT);
		ImageIcon i6=new ImageIcon(i5);
	    JLabel l2 = new JLabel(i6);
	    l2.setBounds(40,8,40,35);
	    p1.add(l2);
	    
	    JLabel l3 = new JLabel("Saquib");
	    l3.setFont(new Font("SAN_SERIF",Font.BOLD,17));
	    l3.setForeground(Color.WHITE);
	    l3.setBounds(89,8,60,20);
	    p1.add(l3);
	    
	    JLabel l4 = new JLabel("Active Now");
	    l4.setFont(new Font("SAN_SERIF",Font.PLAIN,12));
	    l4.setForeground(Color.WHITE);
	    l4.setBounds(89,29,60,12);
	    p1.add(l4);
	    
	    a1=new JTextArea();
	    a1.setBounds(3,52,304,415);
	    a1.setFont(new Font("SAN_SERIF" , Font.PLAIN , 16));
	    a1.setBackground(Color.WHITE);
	    a1.setEditable(false);
	    a1.setLineWrap(true);
	    a1.setWrapStyleWord(true);
	    add(a1);
	    
	    t1 = new JTextField();
	    t1.setFont(new Font("SAN_SERIF" , Font.PLAIN , 15));
	    t1.setBounds(2,468,230,29);
	    add(t1);
	    
	    b1 = new JButton("SEND");
	    b1.setFont(new Font("SAN_SERIF" , Font.PLAIN , 12));
	    b1.setBounds(232,468,75,29);
	    b1.setBackground(new Color(7 , 94 ,84));
	    b1.setForeground(Color.WHITE);
	    b1.addActionListener(this);
	    add(b1);
	  
	    getContentPane().setBackground(Color.BLACK);
	    setLayout(null);
		setSize(310,500);
		setLocation(750,100);
		setUndecorated(true);
		setVisible(true);
		
	}
	 
	public void actionPerformed(ActionEvent ae){
		
		try {
			String out = "";
		    out = t1.getText();
			a1.setText(a1.getText()+"\n\t\t\t\t"+out);
			dout.writeUTF(out);
			t1.setText("");
		} catch(Exception e) {}
	}
	
	public static void main(String arguments[]){
		
		new Client().setVisible(true);
		
		try {
			s = new Socket("127.0.0.1",6002);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgInput="";
			while(!msgInput.equals("exit"))
			{
			msgInput = din.readUTF();
			a1.setText(a1.getText()+"\n"+msgInput);
			}
			
		} catch(Exception e) {}
		
	}
}
