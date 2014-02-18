import java.net.*;
import java.io.*;

import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

import Server.Hash;

import java.security.KeyStore;
import java.security.cert.*;
import java.io.Console;
import java.util.Scanner;

public class Client {

	
	public static void main(String[] args) {
		
		connectToHost(args);
	   
	}
	
	
	
	public static void connectToHost(String[] args){
		
		 String host = null;
	        int port = -1;
	       
	        if (args.length < 2) {
	            System.out.println("ILLEGAL ARGUMENT; USAGE: java client host port");
	            System.exit(-1);
	        }
	        try { // get input parameters 
	            host = args[0];
	            port = Integer.parseInt(args[1]);
	        } catch (IllegalArgumentException e) {
	            System.out.println("ILLEGAL ARGUMENT; USAGE: java client host port");
	            System.exit(-1);
	        }

	        try { // set up a key manager for client authentication 
	            SSLSocketFactory factory = null;
	            try {
	            	
	                char[] password = "password".toCharArray();
	                KeyStore ks = KeyStore.getInstance("JKS");
	                KeyStore ts = KeyStore.getInstance("JKS");
	                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	                TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
	                SSLContext ctx = SSLContext.getInstance("TLS");
	                ks.load(new FileInputStream("clientkeystore"), password);  // keystore password (storepass)
					ts.load(new FileInputStream("clienttruststore"), password); // truststore password (storepass);
					kmf.init(ks, password); // user password (keypass)
					tmf.init(ts); // keystore can be used as truststore here
					ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
	                factory = ctx.getSocketFactory();
	            } catch (Exception e) {
	                throw new IOException(e.getMessage());
	            }
	            SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
	            System.out.println("\nsocket before handshake:\n" + socket + "\n");

	            /*
	             * send http request
	             *
	             * See SSLSocketClient.java for more information about why
	             * there is a forced handshake here when using PrintWriters.
	             */
	            socket.startHandshake();

	            SSLSession session = socket.getSession();
	            X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];
	            String subject = cert.getSubjectDN().getName();
	            
	            System.out.println("secure connection established\n\n");

	            
	            login();
	            askForMedRecords(socket);
            
	        	
	    		
	            socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
	}
	
	public static void login(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Username: ");
		String usrname = scan.nextLine();
		
		System.out.print("Password: ");
		
		/*
		 Console cons = System.console();
		    if (cons != null){
		    	char[] password = cons.readPassword();
			   // System.out.println(password);
		    }else{
		    	System.out.println("There is no console.");
		    }
		*/
		
		String passwd = scan.nextLine();
		Hash.Crypt(passwd, "SALT");
		
	}

	public static void askForMedRecords(SSLSocket socket) throws IOException{
	
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
        String msg;
		for (;;) {
            System.out.print(">");
            msg = read.readLine();
            if (msg.equalsIgnoreCase("quit")) {
			    break;
			}
            System.out.print("sending '" + msg + "' to server...");
            out.println(msg);
            out.flush();

        }
		
		in.close();
		out.close();
		read.close();
		
	
		
	}
}
