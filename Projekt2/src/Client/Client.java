package Client;
import java.net.*;
import java.io.*;

import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

import Security.Hash;

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
	            	String user = getUser();
	                char[] password = getPassword();
	                KeyStore ks = KeyStore.getInstance("JKS");
	                KeyStore ts = KeyStore.getInstance("JKS");
	                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	                TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
	                SSLContext ctx = SSLContext.getInstance("TLS");
	                
	                ks.load(new FileInputStream("../Projekt2/Client/" +user+"/keystore"), password);  // keystore password (storepass)
					ts.load(new FileInputStream("../Projekt2/Client/"+user+"/truststore"), password); // truststore password (storepass);
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

	            askForMedRecords(socket);
            
	        	
	    		
	            socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
	}

	public static char[] getPassword(){
		Scanner scan = new Scanner(System.in);
		
		 Console cons = System.console();
		    if (cons != null){
		    	System.out.print("Password: ");
				return cons.readPassword();
		    }else{
		    	System.out.print("Password: ");
		    	return  scan.nextLine().toCharArray();
		    }
		
		
	}
	
	public static String getUser(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Username: ");
		return scan.nextLine();
		
	}

	public static void askForMedRecords(SSLSocket socket) throws IOException{
	
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
        String msg;
        boolean finished = false;
        System.out.println("Enter a command, type 'quit' to logout:");
		while(!finished) {
			
            System.out.print(">");
            msg = read.readLine();
            if (msg.equalsIgnoreCase("quit")) {
			    finished=true;
			}else{
            	
            	out.println(msg);
            	out.flush();
            	
            	System.out.println(in.readLine());
			}

        }
		
		in.close();
		out.close();
		read.close();
		
	
		
	}
}
