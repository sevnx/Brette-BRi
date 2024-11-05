package admin;

import application.server.bri.BriService;

import java.io.*;
import java.net.*;

public class ServicePalindrome implements BriService {

    private final Socket client;

    public ServicePalindrome(Socket socket) {
        client = socket;
    }
@Override
    public void run() {
        try {BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
            PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

            out.println("Tapez un texte à vérifier");

            String line = in.readLine();

            if (isPalindrome(line)) {
                out.println("Le texte est un palindrome");
            } else {
                out.println("Le texte n'est pas un palindrome");
            }

            client.close();
        }
        catch (IOException e) {
            //Fin du service d'inversion
        }
    }

    private boolean isPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    protected void finalize() throws Throwable {
         client.close(); 
    }

    public static String toStringue() {
        return "Palindrome";
    }
}