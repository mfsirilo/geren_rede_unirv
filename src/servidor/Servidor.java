package servidor;

import com.sun.javafx.css.Rule;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) throws IOException {

        
       
new Thread(new Runnable() {
            @Override
            public void run() {
       ServerSocket server = null;

        try {
            server = new ServerSocket(3312);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                while(true){
                     try {
                         new trabalhaCliente(server.accept()).start();
                     } catch (IOException ex) {
                         Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                     }   
      } 
         
        
             }
        }).start();
     

     
    }
     
}

  class trabalhaCliente extends Thread {
     Socket sk=null;
      public trabalhaCliente(Socket sk){
          this.sk=sk;
          
      }
      
  @Override    
      public void run()
  {
       DataInputStream in = null;
        DataOutputStream ou = null;

        try {
            in = new DataInputStream(sk.getInputStream());
            ou = new DataOutputStream(sk.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            int n1 = in.readInt();
            int n2 = in.readInt();
            int oper = in.readInt();

            switch (oper) {
                case 1:
                    ou.writeUTF("Resultado da soma: " + (n1 + n2));
                    break;
                case 2:
                    ou.writeUTF("Resultado da subtracao: " + (n1 - n2));
                    break;
                case 3:
                    ou.writeUTF("Resultado da multiplicacao: " + (n1 * n2));
                    break;
                case 4:
                    ou.writeUTF("Resultado da divisao: " + (n1 / n2));
                    break;
                default:
                    System.out.println("A OPERAÇÃO INFORMADA NÃO CONTÉM EM NOSSOS DADOS!");
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
             sk.close();
         } catch (IOException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         }
        System.out.println("Terminou conexão");
    
}
  
  }
   