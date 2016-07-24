package clientView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Client;

public class DialogClient extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FrameClient frameClient;

	private JTextField jTextFieldIp, jTextFieldPort;
	private JButton jButtonStart;

	public DialogClient(FrameClient frameClient) {

		this.frameClient = frameClient;
		this.setTitle("Client settings");
		this.setSize(300, 130);
		this.setLocationRelativeTo(this.frameClient);
		this.setLayout(new BorderLayout());
		this.setModal(true);

		JPanel jPanel1 = new JPanel(new GridLayout(2, 2));
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.add(new JLabel("IP", JLabel.CENTER));
		jPanel1.add(this.jTextFieldIp = new JTextField());
		jPanel1.add(new JLabel("Port", JLabel.CENTER));
		jPanel1.add(this.jTextFieldPort = new JTextField());
		this.add(jPanel1, BorderLayout.CENTER);
		this.add(this.jButtonStart = new JButton("Start"), BorderLayout.SOUTH);
		this.jButtonStart.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.jButtonStart)) {
			try {
				this.frameClient.setClient(
						new Client(this.jTextFieldIp.getText(), Integer.parseInt(this.jTextFieldPort.getText())));
				this.dispose();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}
}
