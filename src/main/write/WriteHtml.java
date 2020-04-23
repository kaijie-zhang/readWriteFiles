package main.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteHtml {


    public WriteHtml(){

    }

    @Deprecated
    /**
     * Used to test that arrays created for different formats are equal
     */
    public void writeSimpleToText(String[][] clients, String fileName) throws IOException {
        String fileNameTxt = fileName + ".txt";
        File file = new File(fileName + ".txt");
        // Remove file just in case already created from previous run.
        file.delete();
        BufferedWriter writer = new BufferedWriter(new FileWriter("/output/fileName" + ".txt"));
        outerloop:
        for (int i=0; i < clients.length ; i++){
            for (int j=0; j< clients[i].length; j++){
                if ((clients[i][j]) == null){
                    break outerloop;
                }
                writer.write(clients[i][j]);
            }
        }
        writer.close();
    }


    public void write(String[] header, String[][] clients, String fileName) throws IOException{
        File file = new File( fileName + ".html.txt");
        // Remove file just in case already created from previous run.
        if (file.exists()){
            file.delete();
        }
        // Changed name because prn is a reserved word for Windows
        if (fileName.equals("prn")) {
            File printerFile = new File("printer.html.txt");
            if (printerFile.exists()) {
                printerFile.delete();
            }
        }

        System.out.println("<Clients>");
        int clientNum = 0;
        while (clients[clientNum][0] != null) {
            String[] client = clients[clientNum];
            System.out.println("\t<Client>");
            // generate HTML element for each column in the original document, for each client
            for (int col=0 ; col<client.length ; col++) {
                System.out.println("\t\t<" + header[col] + ">" + client[col] + "</" + header[col] + ">");
            }
            System.out.println("\t</Client>");
            clientNum++;
        }
        System.out.println("<Clients>");
    }
}
