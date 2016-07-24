package clientView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import client.Client;
import tools.AllConstants;
import world.Message;

public class FrameClient extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar jMenuBar;
	private JMenu jMenuOptions;
	private JMenuItem jMenuItemSettings, jMenuItemExit;

	private DialogClient dialogClient;

	private JTabbedPane jTabbedPane;

	private JPanel jPanelImage;
	private JLabel jLabelNameImage, jLabelImage;
	private JButton jButtonSend, jButtonAdd;
	private JFileChooser jFileChooser;

	private BufferedImage bufferedImage;

	private JPanel jPanelConverted;
	private JLabel jLabelNameConverted, jLabelImageConverted;

	private File file;

	private Client client;

	private Message message;

	public FrameClient() {
		this.client = null;
		this.file = null;
		// ******************************************************
		this.setTitle("RMI Client");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setJMenuBar(this.jMenuBar = new JMenuBar());
		this.jMenuBar.add(this.jMenuOptions = new JMenu("Options"));
		this.jMenuOptions.add(this.jMenuItemSettings = new JMenuItem("Settings"));
		this.jMenuItemSettings.addActionListener(this);
		this.jMenuOptions.add(new JSeparator());
		this.jMenuOptions.add(this.jMenuItemExit = new JMenuItem("Exit"));
		this.jMenuItemExit.addActionListener(this);
		// ******************************************************
		this.jFileChooser = new JFileChooser();
		this.jFileChooser.setFileFilter(AllConstants.FILE_NAME_EXTENSION_FILTER);
		// ******************************************************
		this.jTabbedPane = new JTabbedPane();
		// ******************************************************
		this.jTabbedPane.addTab("Image", this.jPanelImage = new JPanel(new BorderLayout()));
		this.jTabbedPane.addTab("Converted", this.jPanelConverted = new JPanel(new BorderLayout()));
		// ******************************************************
		this.jPanelImage.add(this.jLabelNameImage = new JLabel("", JLabel.CENTER), BorderLayout.NORTH);
		// ******************************************************
		JScrollPane jScrollPane = new JScrollPane(this.jLabelImage = new JLabel());
		this.jPanelImage.add(jScrollPane, BorderLayout.CENTER);
		// ******************************************************
		JPanel jPanelControls = new JPanel(new GridLayout(1, 2));
		jPanelControls.setBorder(BorderFactory.createEmptyBorder());
		jPanelControls.add(this.jButtonAdd = new JButton("Add"));
		jPanelControls.add(this.jButtonSend = new JButton("Send"));
		this.jButtonAdd.addActionListener(this);
		this.jButtonSend.addActionListener(this);
		this.jPanelImage.add(jPanelControls, BorderLayout.SOUTH);
		// ******************************************************
		this.jPanelConverted.add(this.jLabelNameConverted = new JLabel("", JLabel.CENTER), BorderLayout.NORTH);
		// ******************************************************
		JScrollPane jScrollPane2 = new JScrollPane(this.jLabelImageConverted = new JLabel());
		this.jPanelConverted.add(jScrollPane2, BorderLayout.CENTER);

		this.add(this.jTabbedPane, BorderLayout.CENTER);
	}

	private void refreshImage() {
		try {
			this.bufferedImage = null;
			this.file = this.jFileChooser.getSelectedFile();
			this.bufferedImage = ImageIO.read(this.file);
			this.jLabelNameImage.setText(this.file.getName());
			this.jLabelImage.setIcon(new ImageIcon(this.bufferedImage));
			this.bufferedImage = null;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void send() {
		if (this.file != null) {
			try {
				this.bufferedImage = ImageIO.read(this.file);
				this.message = new Message(new ImageIcon(this.bufferedImage), this.file.getName());
				this.jLabelImageConverted.setIcon(this.client.send(this.message));
				this.repaint();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.jMenuItemSettings)) {
			this.dialogClient = new DialogClient(this);
			this.dialogClient.setVisible(true);
		}
		if (e.getSource().equals(this.jMenuItemExit)) {
			System.exit(0);
		}
		if (e.getSource().equals(this.jButtonAdd)) {
			int returnValue = this.jFileChooser.showOpenDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				this.refreshImage();
			}
		}
		if (e.getSource().equals(this.jButtonSend)) {
			this.send();
		}
	}

	public JMenuBar getjMenuBar() {
		return jMenuBar;
	}

	public void setjMenuBar(JMenuBar jMenuBar) {
		this.jMenuBar = jMenuBar;
	}

	public JMenu getjMenuOptions() {
		return jMenuOptions;
	}

	public void setjMenuOptions(JMenu jMenuOptions) {
		this.jMenuOptions = jMenuOptions;
	}

	public JMenuItem getjMenuItemSettings() {
		return jMenuItemSettings;
	}

	public void setjMenuItemSettings(JMenuItem jMenuItemSettings) {
		this.jMenuItemSettings = jMenuItemSettings;
	}

	public JMenuItem getjMenuItemExit() {
		return jMenuItemExit;
	}

	public void setjMenuItemExit(JMenuItem jMenuItemExit) {
		this.jMenuItemExit = jMenuItemExit;
	}

	public DialogClient getDialogClient() {
		return dialogClient;
	}

	public void setDialogClient(DialogClient dialogClient) {
		this.dialogClient = dialogClient;
	}

	public JTabbedPane getjTabbedPane() {
		return jTabbedPane;
	}

	public void setjTabbedPane(JTabbedPane jTabbedPane) {
		this.jTabbedPane = jTabbedPane;
	}

	public JPanel getjPanelImage() {
		return jPanelImage;
	}

	public void setjPanelImage(JPanel jPanelImage) {
		this.jPanelImage = jPanelImage;
	}

	public JLabel getjLabelNameImage() {
		return jLabelNameImage;
	}

	public void setjLabelNameImage(JLabel jLabelNameImage) {
		this.jLabelNameImage = jLabelNameImage;
	}

	public JLabel getjLabelImage() {
		return jLabelImage;
	}

	public void setjLabelImage(JLabel jLabelImage) {
		this.jLabelImage = jLabelImage;
	}

	public JButton getjButtonSend() {
		return jButtonSend;
	}

	public void setjButtonSend(JButton jButtonSend) {
		this.jButtonSend = jButtonSend;
	}

	public JButton getjButtonAdd() {
		return jButtonAdd;
	}

	public void setjButtonAdd(JButton jButtonAdd) {
		this.jButtonAdd = jButtonAdd;
	}

	public JFileChooser getjFileChooser() {
		return jFileChooser;
	}

	public void setjFileChooser(JFileChooser jFileChooser) {
		this.jFileChooser = jFileChooser;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public JPanel getjPanelConverted() {
		return jPanelConverted;
	}

	public void setjPanelConverted(JPanel jPanelConverted) {
		this.jPanelConverted = jPanelConverted;
	}

	public JLabel getjLabelNameConverted() {
		return jLabelNameConverted;
	}

	public void setjLabelNameConverted(JLabel jLabelNameConverted) {
		this.jLabelNameConverted = jLabelNameConverted;
	}

	public JLabel getjLabelImageConverted() {
		return jLabelImageConverted;
	}

	public void setjLabelImageConverted(JLabel jLabelImageConverted) {
		this.jLabelImageConverted = jLabelImageConverted;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}