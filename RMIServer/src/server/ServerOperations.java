package server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import tools.Tools;
import world.Message;

public class ServerOperations extends UnicastRemoteObject implements IServerOperations {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage bufferedImage;

	private Message message;

	protected ServerOperations() throws RemoteException {
		super();
		this.bufferedImage = null;
	}

	@Override
	public boolean add(Message message) throws RemoteException {
		try {
			this.message = message;
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public void analyze() throws RemoteException, IOException {
		this.bufferedImage = this.imageToBufferedImage(this.message.getImageIcon().getImage());
		int mediaPixel, colorSRGB;
		Color colorAux;
		for (int i = 0; i < this.bufferedImage.getWidth(); i++) {
			for (int j = 0; j < this.bufferedImage.getHeight(); j++) {
				colorAux = new Color(this.bufferedImage.getRGB(i, j));
				mediaPixel = (int) ((colorAux.getRed() + colorAux.getGreen() + colorAux.getBlue()) / 3);
				colorSRGB = (mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
				this.bufferedImage.setRGB(i, j, colorSRGB);
			}
		}
		ImageIO.write(this.bufferedImage, Tools.extension(this.message.getName()),
				new File("src/persistence/" + this.message.getName()));
	}

	@Override
	public ImageIcon getGrayScale() throws RemoteException {
		try {
			this.bufferedImage = ImageIO.read(new File("src/persistence/" + this.message.getName()));
			return new ImageIcon(this.bufferedImage);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

	@Override
	public BufferedImage imageToBufferedImage(Image image) throws RemoteException {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		return bufferedImage;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}