package ChatApplication;

import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.awt.*;
import java.io.*;

public class Server extends JFrame implements ActionListener {
	
	JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
	Server(){
		
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
	    
	    ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("ChatApplication/Icons/gourav1.jpeg"));
		Image i5=i4.getImage().getScaledInstance(40 , 35 , Image.SCALE_DEFAULT);
		ImageIcon i6=new ImageIcon(i5);
	    JLabel l2 = new JLabel(i6);
	    l2.setBounds(40,8,40,35);
	    p1.add(l2);
	    
	    JLabel l3 = new JLabel("Gourav");
	    l3.setFont(new Font("SAN_SERIF",Font.BOLD,14));
	    l3.setForeground(Color.WHITE);
	    l3.setBounds(89,11,60,14);
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
		setLocation(200,100);
		setUndecorated(true);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		try {
			String out = "";
			out = t1.getText();
			a1.setText(a1.getText()+"\n\t\t\t\t"+out);
			dout.writeUTF(out);
			t1.setText("");
		} catch(Exception e) {}
		
	}
	
	public static void main(String arguments[]){
		
		new Server().setVisible(true);
		
		String msgInput="";
		try {
			skt= new ServerSocket(6002);
			s = skt.accept();
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			while(!msgInput.equals("exit"))
			{
			msgInput = din.readUTF();
			a1.setText(a1.getText()+"\n"+msgInput);
			}
            skt.close();
            s.close();
			
		}catch(Exception e) {}
		
	}
	
}
