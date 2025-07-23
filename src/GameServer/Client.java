package GameServer;

import ApplyGraphics.GameMain;
import ApplyGraphics.MapController;
import ApplyGraphics.PauseGameController;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class Client {
    public static BufferedReader in;
    public static PrintWriter out;
    public static ArrayList<String> info = new ArrayList<>();
    public static int clientCounter = 0;
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
