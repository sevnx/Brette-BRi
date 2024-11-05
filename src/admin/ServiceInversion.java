package admin;

import java.io.*;
import java.net.*;

import application.server.bri.BriService;

public class ServiceInversion implements BriService {
	
	private final Socket client;
	
	public ServiceInversion(Socket socket) {
		client = socket;
	}

	@Override
	public void run() {
		try {BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

			out.println("Tapez un texte à inverser");
		
			out.println(new StringBuilder(in.readLine()).reverse());
			client.close();
		}
		catch (IOException e) {
			//Fin du service d'inversion
		}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	public static String toStringue() {
		return "Inversion de texte";
	}
}
