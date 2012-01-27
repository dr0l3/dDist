package week2;

import java.net.*;
import java.io.*;

/**
 *
 * A very simple server which will way for a connection from a client and print 
 * what the client sends. When the client closes the connection, the server is
 * ready for the next client.
 */

public class DDistDemoServer1 {

    /*
     * Your group should use port number 40HGG, where H is your "hold nummer (1,2 or 3) 
     * and GG is gruppe nummer 00, 01, 02, ... So, if you are in group 3 on hold 1 you
     * use the port number 40103. This will avoid the unfortunate situation that you
     * connect to each others servers.
     */
    protected int portNumber = 40407;  
    protected ServerSocket serverSocket;

    /**
     *
     * Will print out the IP address of the local host and the port on which this
     * server is accepting connections. 
     */
    protected void printLocalHostAddress() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            String localhostAddress = localhost.getHostAddress();
            System.out.println("Contact this server on the IP address " + localhostAddress);
        } catch (UnknownHostException e) {
            System.err.println("Cannot resolve the Internet address of the local host.");
            System.err.println(e);
            System.exit(-1);            
        }
    }

    /**
     *
     * Will register this server on the port number portNumber. Will not start waiting
     * for connections. For this you should call waitForConnectionFromClient().
     */
    protected void registerOnPort() {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            serverSocket = null;
            System.err.println("Cannot open server socket on port number" + portNumber);
            System.err.println(e);
            System.exit(-1);            
        }
    }

    public void deregisterOnPort() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                serverSocket = null;
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     *
     * Waits for the next client to connect on port number portNumber or takes the 
     * next one in line in case a client is already trying to connect. Returns the
     * socket of the connection, null if there were any failures.
     */
    protected Socket waitForConnectionFromClient() {
        Socket res = null;
        try {
            res = serverSocket.accept();
        } catch (IOException e) {
            // We return null on IOExceptions
        }
        return res;
    }

    public void run() {
        System.out.println("Hello world! demo");

        printLocalHostAddress();

        registerOnPort();

        while (true) {
            Socket socket = waitForConnectionFromClient();

            if (socket != null) {
                System.out.println("Connection from " + socket);
                try {
                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String s;
                    // Read and print what the client is sending
                    while (!(s = fromClient.readLine()).equals("")) { // Ctrl-D terminates the connection
                        processInput(socket, s);
                    }
                    socket.close();
                } catch (IOException e) {
                    // We report but otherwise ignore IOExceptions
                    System.err.println(e);
                }
                System.out.println("Connection closed by client.");
            } else {
                // We rather agressively terminate the server on the first connection exception
                break;
            }
        }

        deregisterOnPort();

        System.out.println("Goodbuy world!");
    }

    private void processInput(Socket socket, String input) throws IOException {
        String[] request = input.split(" ");
        if(request[0].equals("GET")) {
            File file = new File("FilesToBeServed" + request[1]);

            // start en outputstream
            PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);

            if(file.exists()) {
                // send responseheader
                toClient.println("HTTP/1.0 200 OK");
                toClient.println();

                BufferedReader br = new BufferedReader(new FileReader(file));

                String line;
                while((line = br.readLine()) != null) {
                    toClient.println(line);
                }

                br.close();

                System.out.println("Filen blev returneret :D");
            } else {
                // send responseheader
                toClient.println("HTTP/1.0 404 FileNotFound");
                toClient.println();

                System.out.println("Filen findes ikke !!");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DDistDemoServer1 server = new DDistDemoServer1();
        server.run();
    }

}