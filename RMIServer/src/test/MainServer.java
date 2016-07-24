package test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import server.Server;


public class MainServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("RMI Server");
		System.out.print("Puerto");
		 Scanner scanner = new Scanner(System.in);
		 int port = scanner.nextInt();
		Server server = new Server(port);
		try {
			server.runServer();
			System.out.println("Server running");
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			System.err.println(e.getMessage());
		}
	}
}
