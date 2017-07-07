import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by elliot on 05/07/2017.
 */
public class connection extends Thread{

    private ServerSocket main_init;

    public connection() throws IOException{
        main_init = new ServerSocket(1111);
        main_init.setSoTimeout(10000);

    }

    public void run(){
        while(true){
            try{
                System.out.println("Waiting for connection to "+
                main_init.getLocalPort());
                Socket server = main_init.accept();

                System.out.println("Connection received from: " +
                server.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thanks for connecting to Office_Space" +
                server.getLocalSocketAddress() + "\nSee ya!");
                server.close();

            }catch(SocketTimeoutException s){
                System.out.println("Timeout!");
                break;
            } catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread t = new connection();
            t.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
