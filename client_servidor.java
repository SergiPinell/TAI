import java.net.*;
import java.io.*;

class Server {
    public void start(){
        try {
            DatagramSocket socket = new DatagramSocket(9400);
            byte[] buffer = new byte[1024];
    
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
    
            String msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Missatge rebut: " + msg);
    
            socket.close();
        }catch (SocketException e) {
            System.out.println("Error de socket: " + e.getMessage());
            e.printStackTrace();
       
        }catch (IOException e) {
            System.out.println("Error de I/O: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}

class Client {
    public void start() {
        try {
            DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
    
        String msg = "Hello, world!";
        byte[] buffer = msg.getBytes();
    
        DatagramPacket paquet = new DatagramPacket(buffer, buffer.length, address, 9400);
        socket.send(paquet);
    
        System.out.println("Missatge enviat: " + msg);
        socket.close();
        }catch (SocketException e) {
            System.out.println("Error de Socket: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error de I/O: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

}

public class client_servidor {
    public static void main(String[] args) {
        if (args[0].equals("server")) {
            new Server().start();
        } else {
            new Client().start();
        }
    }
}

