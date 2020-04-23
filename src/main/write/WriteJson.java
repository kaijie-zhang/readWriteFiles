package main.write;

import java.io.File;

/**
 * Class to output information that was read in to JSON format
 */
public class WriteJson {

    public WriteJson(){
        // default constructor
    }

    public void write(String[] header, String[][] clients, String fileName) {
        File file = new File( fileName + ".json.txt");
        if (file.exists()){
            file.delete();
        }
        // Changed name because prn is a reserved word for Windows
        if (fileName.equals("prn")) {
            File printerFile = new File("printer.json.txt");
            if (printerFile.exists()) {
                printerFile.delete();
            }
        }

        // JSON is written with a list of clients
        System.out.println("{\"Clients\": [");
        int clientNum = 0;
        while (clients[clientNum][0] != null) {
            String[] client = clients[clientNum];
            System.out.println("\t{");
            // generate element for each column in the original document, for each client
            for (int col=0 ; col<client.length ; col++) {
                System.out.println("\t\t\"" + header[col] + "\": \"" + client[col] + "\",");
            }
            System.out.print("\t}");
            clientNum++;
            // if this is the last client, enclose the list with a closing square bracket
            if (clients[clientNum][0] == null){
                System.out.println("]");
            } else{ // otherwise continue with a comma
                System.out.println(",");
            }
        }
        System.out.println("}}");
    }
}
