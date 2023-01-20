import java.io.*;
import java.util.*;

public class BankM
{
    public static void main(String[] args)
    {
        boolean ProgramActive = true;
        while(ProgramActive)
        {
            Scanner sc =new Scanner(System.in);
            System.out.println("|----------------------------------------------|");
            System.out.println("|------------Welcome to Times Bank-------------|");
            System.out.println("|----------------------------------------------|");
            System.out.println("|<<=      Create Bank Accounts             =1>>|");
            System.out.println("|<<=      Display All Bank Accounts        =2>>|");
            System.out.println("|<<=      Deposit Money to a Account       =3>>|");
            System.out.println("|<<=      WithDraw Money From a Account    =4>>|");
            System.out.println("|<<=      Search From the DataBase         =5>>|");
            System.out.println("|<<=      Delete Particular Record         =6>>|");
            System.out.println("|<<=      Del All the Records in DataBase  =7>>|");
            System.out.println("|<<=      Exit                             =8>>|");
            System.out.println("|---------------------------------------------|");
            System.out.print("Enter Your Choice : ");
            int k=sc.nextInt();
            switch (k) {
                case 1 -> add();
                case 2 -> read();
                case 3 -> Deposit();
                case 4 -> WithDraw();
                case 5 -> search();
                case 6 -> delete();
                case 7 -> deleteRecords();
                case 8 -> ProgramActive = false;
                default -> {System.out.println("Please Enter the Correct choice");main(null);}
            }
            if (ProgramActive){
                System.out.print("\nWant to Continue (Y/N) : ");
                String option = sc.next();
                if (option.equals("Y") || option.equals("y") || option.equals("YES") || option.equals("yes") || option.equals("Yes")){
                    System.out.println("Here we go Again");
                }
                else if(option.equals("N") || option.equals("n") || option.equals("NO") || option.equals("no") || option.equals("No")){
                    ProgramActive = false;
                }
                else System.out.println("Please Enter the Correct Option from Y/N");}
        }
    }

    public static void deleteRecords(){
        try{
            FileWriter fileWrite = new FileWriter("BankAccounts.txt");
            fileWrite.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Deposit(){
        Scanner input = new Scanner(System.in);
        System.out.print("Do you Prefer to Continue by Name or Account Number ??\n1. Account Number\n2. Name\nEnter your Choice : ");
        String opt = input.next();
        int optFinal = Integer.parseInt(opt);
        switch (optFinal){
            case 1 -> byNumber("Deposit");
            case 2 -> byName("Deposit");
            default -> {
                System.out.println("Please Enter the Correct Choice");
                Deposit();
            }
        }
    }

    public static void WithDraw(){
        Scanner input = new Scanner(System.in);
        System.out.print("Do you Prefer to Continue by Name or Account Number ??\n1. Account Number\n2. Name\nEnter your Choice : ");
        String opt = input.next();
        int optFinal = Integer.parseInt(opt);
        switch (optFinal){
            case 1 -> byNumber("WithDraw");
            case 2 -> byName("WithDraw");
            default -> {
                System.out.println("Please Enter the Correct Choice");
                WithDraw();
            }
        }
    }

    public static void add()
    {
        File base = new File("BankAccounts.txt");
        boolean exists = base.exists();
        if (!exists){
            createFile();
        }
        Scanner input = new Scanner(System.in);
        try
        {
//            Reading the Inputs from the User

            System.out.print("Enter the First Name : ");
            String firstName = input.next();
            System.out.print("Enter the Last Name : ");
            String lastName = input.next();
            System.out.print("Enter the PIN for this Account : ");
            String pin = input.next();
            System.out.print("Enter the Amount to Deposit : ");
            String tempAmount = input.next();

//            Read the Inputs from the User

//            Got the Inputs from the User
//            Now start the Operation to perform
            
            String name = firstName + " " + lastName;
            int PIN = Integer.parseInt(pin);
            int amount = Integer.parseInt(tempAmount);
            Random rand = new Random();
            int upperBound = 99;
            int id = rand.nextInt(upperBound);

            String afterName = space(name, 32);
            String afterAccount = space(String.valueOf(id),16);
            String afterPIN = space(pin,5);
            afterName = afterName+"      ";

//            Got the Spaces and now writing the data in the DataBase with the spaces acquired

            FileWriter Obj = new FileWriter("BankAccounts.txt",true);

            String data = name+afterName+id+afterAccount+PIN+afterPIN+amount;
            Obj.write(data);
            Obj.flush();
            Obj.close();
        }
        catch(IOException e)
        {
            System.out.println("DataBase not found Try Restarting the Program");
            e.printStackTrace();
        }
    }

    public static String space(String label, int noOfSpace){
        int len = label.length();
        int space = noOfSpace - len;
        return " ".repeat(Math.max(0, space));
    }

    public static void createFile(){
        try{
            FileWriter obj = new FileWriter("BankAccounts.txt",true);
            obj.close();
        } catch (IOException e) {
            System.out.println("Error Creating DataBase File Please Restart the Program");
        }
    }


    public static void read()
    {
        File fileObj=new File("BankAccounts.txt");
        try
        {
            Scanner s2=new Scanner(fileObj);
            System.out.println("Name Account Number  PIN  Amount \n");
            while(s2.hasNextLine())
            {
                String str=s2.nextLine();
                System.out.println(str);
            }
        }
        catch(IOException e )
        {
            System.out.println("FILE NOT FOUND ");
        }
    }


    public static void search()
    {
        Scanner sc =new Scanner(System.in);
        System.out.print("Want to Search by Name or Account Number??\n" +
                "1. Account Number\n" +
                "2. Name\n" +
                "Enter Your Choice : ");
        int opt=sc.nextInt();
        if(opt==1)
        {
            File f=new File("BankAccounts.txt");
            System.out.print("Enter the Account Number : ");
            int accountNumber=sc.nextInt();
            try
            {
                Scanner scc=new Scanner(f);
                while(scc.hasNext())
                {
                    String data = scc.nextLine();
                    for (int i = 0; i<1;i++){
                        String[] arr = data.split(" ",26);
                        if(accountNumber == Integer.parseInt(arr[24])){
                            System.out.print("Name                            Account Number  PIN  Amount\n"+data);
                        }
                    }
                }
                scc.close();
            }
            catch(IOException e)
            {
                System.out.println("SORRY NOT FOUND ");
            }
        }

        else if(opt==2)
        {
            File f=new File("BankAccounts.txt");
            System.out.print("Enter the First Name of the Customer : ");
            String firstName=sc.next();
            System.out.print("Enter the Last Name of the Customer : ");
            String SecondName=sc.next();
            String nameOfStudent = firstName + " " + SecondName;
            try
            {
                boolean toDisplay = true;
                Scanner st=new Scanner(f);
                while(st.hasNext())
                {
                    String data = st.nextLine();
                    String[] arr = data.split(" ",26);
                    for (int i = 0;i<15;i++){
                        String name = arr[0]+" "+arr[1];
//                        System.out.println(arr[0] + arr[1]);
                        if (nameOfStudent.equals(name) && toDisplay){
                            System.out.print("Name                            Account Number  PIN  Amount\n"+data);
                            toDisplay = false;
                        }
                    }
                }
                st.close();
            }
            catch(IOException e)
            {
                System.out.println("SORRY NOT FOUND ");
            }
        }
        else
        {
            System.out.println("RECORD NOT FOUND ");
        }
    }

    public static void byName(String choice){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the First Name of the Customer : ");
        String firstName = input.next();
        System.out.println("Enter the Last Name of the Customer : ");
        String lastName = input.next();
        String mainName = firstName + " " + lastName;
        File database = new File("DataBase.txt");
        String main_data = "";
        try {
            Scanner scc = new Scanner(database);
            while (scc.hasNext()) {
                String data = scc.nextLine();
                String[] arr = data.split(" ",26);
                for (int i = 0;i<15;i++){
                    String name = arr[0]+" "+arr[1];
                    if (mainName.equals(name)) {
                        main_data = data;
                        break;
                    }
                }
            }
            scc.close();
        } catch (IOException e) {
            System.out.println("catch block");
        }

//        Now Get the New Input from the User
        String finalMSG = "";
        if (choice.equals("Deposit")){
            System.out.print("Enter the Amount You Want to Deposit : ");
            String tempAmount = input.next();
            int OldAmount;
            String var = "0";
            int pinNumber = 0;
            try{
                Scanner scc = new Scanner(database);
                while(scc.hasNext()){
                    String temp = scc.next();
                    int len = temp.length();
                    if(len == 4){
                        try{
                            pinNumber = Integer.parseInt(temp);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
//                    var = scc.next();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            OldAmount = Integer.parseInt(var);
            int newAmount = OldAmount + Integer.parseInt(tempAmount);
            if(newAmount > 100000000){
                System.out.println("Entered Amount is Greater than 100 Million So your Account wont be getting Affected while You will be Reverted back to the Main Menu");

            }

            if(newAmount <= 100000000){
                String regex = String.valueOf(pinNumber);
//                52 will be on left side
                String[] arr = main_data.split(regex,52);
                finalMSG = arr[0] + newAmount;
            }



        }

//        Now Taken and Now Write all the Data to the File

        try {
            File inputFile = new File("BankAccounts.txt");
            File tempFile = new File("DataBase_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(main_data)) currentLine = finalMSG;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);
            System.out.println(successful);
        } catch (IOException e) {
            System.out.println("Hello");
        }



    }

    public static void byNumber(String choice){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the First Name of the Customer : ");
        String firstName = input.next();
        System.out.println("Enter the Last Name of the Customer : ");
        String lastName = input.next();
        String mainName = firstName + " " + lastName;
        File database = new File("DataBase.txt");
        String main_data = "";
        try {
            Scanner scc = new Scanner(database);
            while (scc.hasNext()) {
                String data = scc.nextLine();
                String[] arr = data.split(" ",26);
                for (int i = 0;i<15;i++){
                    String name = arr[0]+" "+arr[1];
                    if (mainName.equals(name)) {
                        main_data = data;
                        break;
                    }
                }
            }
            scc.close();
        } catch (IOException e) {
            System.out.println("catch block");
        }

//        Now Get the New Input from the User
        String finalMSG = "";
        if (choice.equals("Deposit")){
            System.out.print("Enter the Amount You Want to Deposit : ");
            String tempAmount = input.next();
            int OldAmount;
            String var = "0";
            int pinNumber = 0;
            try{
                Scanner scc = new Scanner(database);
                while(scc.hasNext()){
                    String temp = scc.next();
                    int len = temp.length();
                    if(len == 4){
                        try{
                            pinNumber = Integer.parseInt(temp);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
//                    var = scc.next();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            OldAmount = Integer.parseInt(var);
            int newAmount = OldAmount + Integer.parseInt(tempAmount);
            if(newAmount > 100000000){
                System.out.println("Entered Amount is Greater than 100 Million So your Account wont be getting Affected while You will be Reverted back to the Main Menu");

            }

            if(newAmount <= 100000000){
                String regex = String.valueOf(pinNumber);
//                52 will be on left side
                String[] arr = main_data.split(regex,52);
                finalMSG = arr[0] + newAmount;
            }



        }

//        Now Taken and Now Write all the Data to the File

        try {
            File inputFile = new File("BankAccounts.txt");
            File tempFile = new File("DataBase_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(main_data)) currentLine = finalMSG;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);
            System.out.println(successful);
        } catch (IOException e) {
            System.out.println("Hello");
        }



    }

    public static void delete() {
        Scanner sc =new Scanner(System.in);
        System.out.print("Want to Delete by Name or Account Number??\n1. Account Number\n2. Name\nEnter Your Choice : ");
        int opt=sc.nextInt();
        if(opt==1)
        {
            System.out.println("Enter the Account Number : ");
            int rollNumber = sc.nextInt();
            String main_data = "";
            File main = new File("BankAccounts.txt");
            try
            {
                Scanner scc=new Scanner(main);
                while(scc.hasNext())
                {
                    String data = scc.nextLine();
                    for (int i = 0; i<1;i++){
                        String[] arr = data.split(" ",26);
                        if(rollNumber == Integer.parseInt(arr[24])){
                            main_data = data;
                        }
                    }
                }
                scc.close();
            }
            catch(IOException e)
            {
                System.out.println("catch block");
            }

            try{
                File inputFile = new File("BankAccounts.txt");
                File tempFile = new File("DataBase_temp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String currentLine;

                while((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if(trimmedLine.equals(main_data)) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                boolean successful = tempFile.renameTo(inputFile);
                System.out.println(successful);
            } catch (IOException e){
                System.out.println("Hello");
            }
        }

        else if(opt==2) {
            System.out.print("Enter the First Name : ");
            String firstName = sc.next();
            System.out.print("Enter the Last Name : ");
            String lastName = sc.next();
            String mainName = firstName + " " + lastName;
            String main_data = "";
            File main = new File("BankAccounts.txt");
            try {
                Scanner scc = new Scanner(main);
                while (scc.hasNext()) {
                    String data = scc.nextLine();
                    String[] arr = data.split(" ",26);
                    for (int i = 0;i<15;i++){
                        String name = arr[0]+" "+arr[1];
                        if (mainName.equals(name)) {
                            main_data = data;
                            break;
                        }
                    }
                }
                scc.close();
            } catch (IOException e) {
                System.out.println("catch block");
            }

            try {
                File inputFile = new File("BankAccounts.txt");
                File tempFile = new File("DataBase_temp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(main_data)) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                boolean successful = tempFile.renameTo(inputFile);
                System.out.println(successful);
            } catch (IOException e) {
                System.out.println("Hello");
            }
        }
    }
}