import java.net.*;
import java.io.*;

public class client_servidor2 {
    public static void main(String[] args) {
        if (args[0].equals("server")) {
            new Server().start();
        } else {
            new Client().start();
        }
    }
}

class Server {
    public void start(){
        try {
            DatagramSocket socket = new DatagramSocket(9400);
            byte[] buffer = new byte[1024];
    
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
    
            String directory = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Directori rebut: " + directory);

            String command = "cmd /c dir " + directory;
            Process process = Runtime.getRuntime().exec(command);

            // captura de la sortida del comandament

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }

            // Envia la sortida del comandament
            byte[] response = result.toString().getBytes();
            DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);

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
        
            String directory = "C:\\Python313";
            byte[] buffer = directory.getBytes();
        
            DatagramPacket paquet = new DatagramPacket(buffer, buffer.length, address, 9400);
            socket.send(paquet);
        
            System.out.println("Directori enviat: " + directory);
            // resposta del servidor
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Contingut del directori:\n" + response);


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
