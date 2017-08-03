package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Socket sk = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        try {
            sk = new Socket("localhost", 3312);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out = new DataOutputStream (sk.getOutputStream());
            in = new DataInputStream (sk.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("Informe o primeiro numero");
            int n1 = reader.nextInt();
            System.out.println("Informe o segundo numero");
            int n2 = reader.nextInt();
            System.out.println("Informe a operação: \n"
                    + "1 - soma \n"
                    + "2 - subtracao \n"
                    + "3 - multiplicacao \n"
                    + "4 - divisao \n");
            int oper = reader.nextInt();
            out.writeInt(n1);
            out.writeInt(n2);
            out.writeInt(oper);
            String res = in.readUTF();
            System.out.println(res);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
