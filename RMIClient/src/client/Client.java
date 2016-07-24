package client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.ImageIcon;

import server.IServerOperations;
import tools.AllConstants;
import world.Message;

public class Client {

	private String ip;
	private int port;
	private Message message;

	private IServerOperations iServerOperations;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
		this.message = null;
		try {
			LocateRegistry.getRegistry(this.port);
			try {
				this.iServerOperations = (IServerOperations) Naming
						.lookup("rmi://" + this.ip + ":" + this.port + "/" + AllConstants.RMI_ID);
			} catch (MalformedURLException | NotBoundException e) {
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public ImageIcon send(Message message) throws IOException {
		boolean ok = this.iServerOperations.add(message);
		ImageIcon imageIcon = null;
		if (ok) {
			this.iServerOperations.analyze();
			imageIcon = this.iServerOperations.getGrayScale();
		}
		return imageIcon;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public IServerOperations getiServerOperations() {
		return iServerOperations;
	}

	public void setiServerOperations(IServerOperations iServerOperations) {
		this.iServerOperations = iServerOperations;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
