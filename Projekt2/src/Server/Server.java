package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.util.Scanner;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;

public class Server implements Runnable {
	private ServerMonitor monitor;
	private static String trustStorePw = "e34+C75N3XLiwT";
	private static String keyStorePw = "e34+C75N3XLiwT";
	private static int port;
	
	private ServerSocket serverSocket;
	
	public Server(ServerSocket ss,ServerMonitor mon){
		this.serverSocket = ss;
		this.monitor = mon;
		newConnection();
	}
	
	public static void main(String[] args) {
		try{
			port = Integer.parseInt(args[0]);
		}catch(Exception e){
			System.out.println("Invalid arguments, enter port");
			System.exit(1);
		}
		startServer();
		
		
	}
	
	public void run(){
        try {
            SSLSocket socket=(SSLSocket)serverSocket.accept();
            newConnection();
            
            SSLSession session = socket.getSession();
            X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];
            String subject = cert.getSubjectDN().getName();
            
           
            User user = UserFactory.createUser(subject,monitor);
            if(user == null){
            	System.out.println("Client invalid cert");
            	socket.close();
            	return;
            }
            System.out.println("Client "+user.getUserName() + " connected");
            

            PrintWriter out = null;
            BufferedReader in = null;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String clientMsg = null;
            while ((clientMsg = in.readLine()) != null) {		   
                Command c = CommandFactory.createCommand(clientMsg);
            	String s = monitor.execCommand(user,c);
            	out.println(s);
				out.flush();
			}
            
			in.close();
			out.close();
			socket.close();
			System.out.println("Client "+user.getUserName() + " Disconnected");
		} catch (IOException e) {
            System.out.println("Client died: " + e.getMessage());
            e.printStackTrace();
            return;
        }
		
	}
	
	private void newConnection() { (new Thread(this)).start(); }

	private static void startServer() {
		try {
			ServerSocketFactory ssf = getServerSocketFactory();
			ServerSocket ss = ssf.createServerSocket(port);
			((SSLServerSocket)ss).setNeedClientAuth(true); // enables client authentication
			new Server(ss,new ServerMonitor());
		} catch (IOException e) {
			System.out.println("Unable to start Server: " + e.getMessage());
			e.printStackTrace();
		}	
	}

	private static ServerSocketFactory getServerSocketFactory() {
        SSLServerSocketFactory ssf = null;
        try { // set up key manager to perform server authentication
            SSLContext ctx = SSLContext.getInstance("TLS");
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            KeyStore ks = KeyStore.getInstance("JKS");
			KeyStore ts = KeyStore.getInstance("JKS");
           

            ks.load(new FileInputStream("../Projekt2/Server/keystore"), keyStorePw.toCharArray());  // keystore password (storepass)
            ts.load(new FileInputStream("../Projekt2/Server/truststore"), trustStorePw.toCharArray()); // truststore password (storepass)
            kmf.init(ks, keyStorePw.toCharArray()); // certificate password (keypass)
            tmf.init(ts);  // possible to use keystore as truststore here
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            ssf = ctx.getServerSocketFactory();
            return ssf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

	
	

}
