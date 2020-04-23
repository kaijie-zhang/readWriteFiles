package main.program;

import main.read.ReadCsv;
import main.read.ReadPrn;
import main.write.WriteHtml;
import main.write.WriteJson;

import java.io.IOException;

public class ReadWriteProgram {

    public static void main(String[] args) throws IOException {
        WriteHtml writeHtml = new WriteHtml();
        WriteJson writeJson = new WriteJson();


        if (args[0].equals("csv")){
            ReadCsv readCsv = new ReadCsv();

            String[] header = readCsv.readHeader();
            String[][] clients = readCsv.readClients();

            if (args[1].equals("html")){
                writeHtml.write(header, clients, "csv");
            } else if (args[1].equals("json")){
                writeJson.write(header, clients, "csv");
            } else{
                System.out.println("Output type not supported: " + args[1]);
            }


        } else if (args[0].equals("prn")) {
            ReadPrn readPrn = new ReadPrn();

            String[] header = readPrn.readHeader();
            String[][] clients = readPrn.readClients();

            if (args[1].equals("html")){
                writeHtml.write(header, clients, "prn");
            } else if (args[1].equals("json")){
                writeJson.write(header, clients, "prn");
            } else{
                System.out.println("Output type not supported: " + args[1]);
            }

        } else{
            System.out.println("Input type not supported: " + args[0]);
        }



    }
}
