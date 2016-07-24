package server;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;

import world.Message;

public interface IServerOperations extends Remote {

	boolean add(Message message) throws RemoteException;

	void analyze() throws RemoteException, IOException;

	BufferedImage imageToBufferedImage(Image image) throws RemoteException;
	
	ImageIcon getGrayScale() throws RemoteException;
}
