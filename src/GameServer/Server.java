package GameServer;

import ApplyGraphics.GameMain;

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

    public static void main(String[] args)throws IOException {
        GameMain.runner = "Server";
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting for client...");
        socket = serverSocket.accept();
        System.out.println("what happening?");
        System.out.println("Accepted connection ");

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        GameMain.main(args);
    }

    public static int generateRandom(int range) throws IOException {
        if(GameMain.runner == "GameMain"){
            return random.nextInt(range);
        }
        else if(GameMain.runner.equals("Client")){
            return Integer.parseInt(Client.in.readLine());
        }
        else{
            int rand = random.nextInt(range);
            out.println(rand);
            return rand;
        }
    }
}
