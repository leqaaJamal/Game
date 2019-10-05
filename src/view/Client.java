package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class Client extends JFrame implements ActionListener {

	static JPanel contentPane;
	static JDesktopPane desktopPane;
	static JTextArea msgArea;
	static JTextArea msgtext;
	static JButton send;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	public void start() {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		try {
			s = new Socket("127.0.0.1", 5000);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgin = "";
			while (!msgin.equals("exit")) {
				msgin = din.readUTF();
				msgArea.setText(msgArea.getText().trim() + "\n" + msgin);

			}
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		Client cc= new Client(null);
		cc.start();
	}

	public Client(String s){
		
	}
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.DARK_GRAY);
		contentPane.add(desktopPane, BorderLayout.CENTER);

		msgArea = new JTextArea();
		msgArea.setEditable(false);
		msgArea.setBounds(10, 11, 316, 162);
		desktopPane.add(msgArea);

		msgtext = new JTextArea();
		msgtext.setBounds(20, 184, 235, 56);
		desktopPane.add(msgtext);

		send = new JButton("New button");
		send.setBounds(260, 199, 89, 23);
		send.addActionListener(this);
		send.setActionCommand("sendd");
		desktopPane.add(send);
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

}
