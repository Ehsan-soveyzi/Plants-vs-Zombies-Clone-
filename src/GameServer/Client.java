package GameServer;

import ApplyGraphics.GameMain;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static BufferedReader in;
    public static PrintWriter out;
    public static void main(String[] args)throws IOException {
        GameMain.runner = "Client";
        try {
            InetAddress address = InetAddress.getByName(null);
            Socket socket = new Socket(address, Server.PORT);


            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            GameMain.main(args);
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
