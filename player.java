import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Player {
    private String mcIPString = null;
    private int mcPort = null;
    private String line = null;

    public Player(mcIPString, mcPort, line){   // Tenir en compte si passar tres parametres o un sol array
        this.mcIPString = mcIPString;
        this.mcPort = mcPort;
        this.line = line;
    }
    
    MulticastSocket mcSocket = new MulticastSocket(mcPort);
// 4: Inicialitzeu l’objecte mcSocket a un objecte de tipus MulticastSocket
// 5: Per aquesta inicialització podeu fer servir:
// mcSocket = new MulticastSocket(mcPort)

    InetAddress mcIPAddress = InetAddress.getByName(mcIPString);
// 6: Creeu un objecte InetAddress anomenat mcIPAddress
// 7: Inicialitzeu l’objecte mcIPAddress a
// InetAddress.getByName(mcIPString).

    mcSocket.joinGroup(mcIPAddress);
// 8: Uniu-vos al grup multicast mcIPAddress.
// 9: Per unir-se al grup podeu fer servir:
// mcSocket.joinGroup(mcIPAddress)
    while (true) do {
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        mcSocket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println("[Multicast Receiver] Received: " + msg);
        String player = "/usr/bin/mplayer";
        String noteFile = "notes/"+linia+"/"+msg+".mp3";
        String command = player+" "+noteFile;
        System.out.println("Command: "+command);
        String[] arrayOrdre = {player, noteFile};
    }
// 10: while true do
// 11: Creeu un objecte Datagrama d’usuari UDP (DatagramPacket) anomenat packet per a rebre ordres
// 12: Espereu per rebre un missatge multicast a mcSocket.
// 13: Per rebre el missatge Multicast podeu fer servir:
// mcSocket.receive(packet);
// 14: Extraieu el missatge del paquet rebut amb la nota musical
// 15: Aquesta extracció es pot fer fent servir:
// String msg = new String(packet.getData(), packet.getOffset(),
// packet.getLength());
// 16: Imprimiu el missatge rebut
// 17: Creeu una cadena amb el nom del programa per reproduir el so, i una
// amb l’argument (el path i nom del fitxer a reproduir), i poseu-les, respectivament, com a primer i segon elements d’un String[] (p. ex. arrayOrdre).

    Process process = Runtime.getRuntime().exec(arrayOrdre);
// 18: Executeu l’ordre desada en l’array String[] amb:
// Runtime.getRuntime().exec(arrayOrdre)
}