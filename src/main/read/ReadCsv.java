package main.read;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReadCsv {

    private Scanner scanner;

    public ReadCsv(){
        scanner = new Scanner(System.in);
    }

    public String[] readHeader(){
        scanner.useDelimiter("\n");
        String headerRaw = scanner.nextLine();
        return headerRaw.split(",");
    }

    public String[][] readClients(){
        String[][] clients = new String[100][6];
        int i = 0;
        while (scanner.hasNext()){
            String clientRawScan = scanner.next();
            String[] client = new String[6];

            // Split on ",", because it is a CSV file, but it will split the name column as well.
            String[] clientRaw = clientRawScan.split(",");

            client[0] = formatName(clientRaw);

            // First two elements scanned were last and first name.
            // The rest of the elements shouldn't have commas, so can juts copy over as is.
            // Note that the final client array is one element behind the rawClient array
            for (int j=2; j<clientRaw.length; j++){
                client[j-1] = clientRaw[j];
            }

            client[4] = formatCreditLimit(client[4]);
            try {
                client[5] = formatDate(client[5]);
            } catch (ParseException e) {
                System.out.println("Error parsing date: " + client[5]);
                e.printStackTrace();
            }

            // add the client to the total clients array
            clients[i] = client;
            i++;
        }


        scanner.close();
        return clients;
    }

    private String formatName(String[] clientRaw){
        // Parse first and second element to concatenate them into one full name
        String lastName = clientRaw[0].substring(1);
        String firstNameRaw = clientRaw[1];
        String firstName = firstNameRaw.substring(0, firstNameRaw.length() - 1);
        return lastName + "," + firstName;
    }

    private String formatCreditLimit(String creditLimit){
        // parse cred limit
        if (creditLimit.contains(".")){
            String[] splitNumber = creditLimit.split("\\.",2);
            if (splitNumber[1].length() == 2){
                creditLimit = creditLimit.replaceAll("\\.", "");
            } else if (splitNumber[1].length() == 1){
                creditLimit = creditLimit.replaceAll("\\.", "");
                creditLimit += "0";
            }

        } else{
            creditLimit += "00";
        }
        return creditLimit;
    }

    private String formatDate(String oldDate) throws ParseException {
        // change date format to be that of the prn file
        DateFormat oldFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat newFormat = new SimpleDateFormat("yyyyMMdd");


        Date date = oldFormat.parse(oldDate);
        return newFormat.format(date);
    }
}
