package GameServer;

import ApplyGraphics.GameMain;
import ApplyGraphics.MapController;
import ApplyGraphics.PauseGameController;

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
            BufferedReader in2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(in2.equals(in));

            GameMain.main(args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void sendEndMessage() throws IOException {
        out.println(PauseGameController.isWin);
        int number = Integer.parseInt(in.readLine());
        if(number == -1){
            PauseGameController.isWin = 1;
            MapController.pause();
        }else if(number == 1){
            PauseGameController.isWin = -1;
            MapController.pause();
        }
    }
}
