package main.read;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReadPrn {
    private Scanner scanner;
    private Pattern startsWithLetter;


    public ReadPrn(){
        scanner = new Scanner(System.in);
        startsWithLetter = Pattern.compile("[a-zA-Z].*");
    }

    public String[] readHeader(){
        String[] header = new String[6];
        for (int i=0; i<6; i++){
            header[i] = scanner.next();
            if (scanner.hasNext("Limit")){
                header[i] = header[i] + ' ' + scanner.next();
            }
        }
        return header;
    }

    public String[][] readClients(){
        String[][] clients = new String[100][6];

        int clientNum = 0;
        while (scanner.hasNext()){
            //scan for name, and place in clients array
            clients[clientNum][0] = scanner.next() + ' ' + scanner.next();

            clients[clientNum][1] = scanAddress();

            clients[clientNum][2] = scanPostCode();

            clients[clientNum][3] = scanPhone();

            //scan for credit limit
            clients[clientNum][4] = scanner.next();

            //scan for birthday
            clients[clientNum][5] = scanner.next();

            clientNum++;

            // Account for if array is not big enough
            if (clientNum == clients.length && scanner.hasNext()){
                String[][] clientsTemp = new String[clients.length*2][6];

            }
        }
        scanner.close();
        return clients;
    }

    private String scanAddress(){
        String address = scanner.next();
        // check if next scanned item is still part of the street name. If next item starts with a letter, then it is.
        while (scanner.hasNext(startsWithLetter)){
            address = address + ' ' + scanner.next();
        }
        // if the scanned item doesn't start with a letter, then it is the number of the street, and ends the address.
         return address + ' ' + scanner.next();
    }

    private String scanPostCode(){
        String postCode = scanner.next();
        // if next item starts with a letter, then it is the second part of the postal code.
        if (scanner.hasNext(startsWithLetter)){
            return postCode + ' ' + scanner.next();
        } else{
            return postCode;
        }
    }

    private String scanPhone(){
        String phone = scanner.next();
        String lastScanned = phone;
        // if last scanned item is LTE 3 characters, that means the phone number is not done being scanned yet.
        // sometimes an extension with the + symbol can be more than 3 characters, so add a check in there
        while (lastScanned.length() <= 3 || lastScanned.contains("+")) {
            lastScanned = scanner.next();
            phone = phone + ' ' + lastScanned;
        }
        return phone;
    }
}
