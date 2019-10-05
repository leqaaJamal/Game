package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Server extends JFrame implements ActionListener {

	static JPanel contentPane;
	static JDesktopPane desktopPane;
	static JTextField msgarea;
	static JButton send;
	static JTextArea msgtext;
	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	public void start(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String msgin = "";
		try {

			ss = new ServerSocket(5000);
			s = ss.accept();
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());

			while (!msgin.equals("exit")) {
				msgin = din.readUTF();
				msgarea.setText(msgarea.getText().trim() + "\n" + msgin);

			}
		} catch (Exception e) {

		}
	}
	public static void main(String[] args) throws IOException {
		Server s = new Server(null);
		s.start();
	}

	/**
	 * Create the frame.
	 */
	public Server(String s){
		
	}
	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);

		msgarea = new JTextField();
		msgarea.setEditable(false);
		msgarea.setBounds(10, 11, 305, 138);
		desktopPane.add(msgarea);
		msgarea.setColumns(10);
		send = new JButton("New button");

		send.setBounds(254, 197, 89, 23);
		send.addActionListener(this);
		send.setActionCommand("sendd");
		desktopPane.add(send);

		msgtext = new JTextArea();
		msgtext.setBackground(Color.RED);
		msgtext.setLineWrap(true);
		msgtext.setForeground(Color.DARK_GRAY);
		msgtext.setBounds(10, 196, 209, 23);
		desktopPane.add(msgtext);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msgout = "";
		if (e.getActionCommand().equals("sendd")) {
			try {
				msgout = msgtext.getText().trim();
				dout.writeUTF(msgout + "\n");
			} catch (Exception ex) {

			}
		}
	}

	// public void msgtextActionPerformed(java.awt.event.ActionEvent evt) {
	//
	// }
	//
	// public void sendActionPerformed(java.awt.event.ActionEvent evt) {
	// String msgout = "";
	// try {
	// msgout = msgtext.getText().trim();
	// dout.writeUTF(msgout);
	// } catch (Exception e) {
	//
	// }
	// }

}
