package Client;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.security.KeyStore;
import java.util.Scanner;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;

public class Client {
	private static Scanner consoleScanner;
	
	public static void main(String[] args) {
		consoleScanner = new Scanner(System.in);
		connectToHost(args);
		consoleScanner.close();
	   
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
	            boolean success = false;
	            while(!success){
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
	            		success = true;
	            	} catch (Exception e) {
	            		System.out.println("Invalid username or password");
	            	}
	            	
	            }
	            SSLSocket socket = null;
	            try{
	            	socket = (SSLSocket)factory.createSocket(host, port);
	            }catch(ConnectException e){
	            	System.out.println("Server offline, try again later");
	            	System.exit(1);
	            }

	            /*
	             * send http request
	             *
	             * See SSLSocketClient.java for more information about why
	             * there is a forced handshake here when using PrintWriters.
	             */
	            socket.startHandshake();

	            SSLSession session = socket.getSession();
	            X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];
	            

	            communicate(socket);
            
	        	
	    		
	            socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
	}

	public static char[] getPassword(){
		
		Console cons = System.console();
		char[] pw;
		System.out.print("Password: ");
	    if (cons != null){
			pw = cons.readPassword();
	    }else{
	    	String s =consoleScanner.nextLine();
	    	pw = s.toCharArray();
	    }
		return pw;
		
	}
	
	public static String getUser(){
		System.out.print("Username: ");
		String user = consoleScanner.nextLine();
		return user;
		
	}

	public static void communicate(SSLSocket socket) throws IOException{
	
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
        String msg;
        boolean finished = false;
        System.out.println("Enter a command, type 'help' for a commandlist, type 'quit' to logout:");
		while(!finished) {
			
            System.out.print(">");
            msg = read.readLine();
            if (msg.equalsIgnoreCase("quit")) {
			    finished=true;
			}else if(msg.equalsIgnoreCase("help")){
				writeHelp();
			}
            else{
            	out.println(msg);
            	out.flush();
            	
            	System.out.println(in.readLine());
			}

        }
		
		in.close();
		out.close();
		read.close();
		
	
		
	}

	private static void writeHelp() {
		System.out.println("\t\t\t /* HELP */");
		System.out.println("-create [filename] [header] \t\t\t to create a medical record, HeaderSyntax 'Patient/Nurse/Doctor/Div'");
		System.out.println("-remove [filename] \t\t\t\t to remove a medical record");
		System.out.println("-read [filename] \t\t\t\t to read a medical record");
		System.out.println("-write [filename] [content] \t\t\t to write content to a medical record");
		System.out.println();
		System.out.println("'Invalid command or missing arguments' \t\t if invalid command was entered");
		System.out.println("'Permission denied' \t\t\t\t if tried to access file withour permission");
	}
}
