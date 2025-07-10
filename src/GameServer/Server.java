package GameServer;

import ApplyGraphics.GameMain;
import ApplyGraphics.MapController;
import ApplyGraphics.PauseGameController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    static Random random = new Random();
    static final int PORT = 9090;
    public static BufferedReader in;
    public static PrintWriter out;
    public static Socket socket;
    static ServerSocket serverSocket;
    public static void main(String[] args)throws IOException {
        GameMain.runner = "Server";
        serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting for client...");
        socket = serverSocket.accept();
        System.out.println("what happening?");
        System.out.println("Accepted connection ");

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        GameMain.main(args);
    }

    public static void sendEndMessage(int win) throws IOException {
        String line = "End" + win;
        if(GameMain.runner.equals("Server")) {
            Server.out.println(line);
        }
        if(GameMain.runner.equals("Client")) {
            Client.out.println(line);
        }
    }

    public static boolean winTheGame() throws IOException {
        if(GameMain.runner.equals("Server")) {
        if(in.ready()) {
            return true;
        }
        }
        return false;
    }

//    public static boolean readEndMessage() throws IOException {
//        if(GameMain.runner.equals("Server")) {
//
//        }
//    }

    public static int generateRandom(int range) throws IOException {
        if(GameMain.runner.equals("GameMain")){
            return random.nextInt(range);
        }
        else if(GameMain.runner.equals("Client")){
            String line = Client.in.readLine();
            if(line.startsWith("rand:")){
                return Integer.parseInt(line.substring(5));
            }
        }
        else{
            int rand = random.nextInt(range);
            String line = "rand:" + rand;
            out.println(line);
            return rand;
        }
        return 0;
    }
}
