import java.util.Scanner;

public class ShrekAndDonkey {
    public static void main(String[] args) {
        String divider = "____________________________________________________________";
        //Created using ChatGPT
        String banner = " ____  _              _        _              _ ____              _\n"
                + "/ ___|| |__  _ __ ___| | __   / \\   _ __   __| |  _ \\  ___  _ __ | | _____ _   _\n"
                + "\\___ \\| '_ \\| '__/ _ \\ |/ /  / _ \\ | '_ \\ / _` | | | |/ _ \\| '_ \\| |/ / _ \\ | | |\n"
                + " ___) | | | | | |  __/   <  / ___ \\| | | | (_| | |_| | (_) | | | |   <  __/ |_| |\n"
                + "|____/|_| |_|_|  \\___|_|\\_\\/_/   \\_\\_| |_|\\__,_|____/ \\___/|_| |_|_|\\_\\___|\\__, |\n"
                + "                                                                            |___/\n";

        System.out.println(divider);
        System.out.print(banner);
        System.out.println("Grrr GET OUT OF MY SWAMP! Ohh, I didn't see you " +
                "there stranger! I'm ShrekAndDonkey.");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        //Array of fixed size 100
        String[] array = new String[100];

        //Added Pointer for current Count
        int pointer = 0;


        //Scanning for input by user
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //Scans till bye is input
        while (!input.equals("bye")) { 
            System.out.println(divider);
            
            //If list is input 
            if (input.equals("list")) {

                //Iterate through array to get list
                for (int i = 0; i < pointer; i++) {
                    //+1 due to 0 index
                    System.out.println(i+1+". " + array[i]); 
                }
                System.out.println(divider);
                
            }
            else{ 
                //Added formatting
                System.out.println("Added: " + input);
                array[pointer] = input;
                pointer++;
                System.out.println(divider);
                
            } 
            input = scanner.nextLine();
        }

        System.out.println(divider);
        System.out.println("FEE FIE FOE FUMP. GET OUT OF MY SWAMP!");
        System.out.println(divider);
    }
}
