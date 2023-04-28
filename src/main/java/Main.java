import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Datebase obiect = new Datebase();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {


        long id = -1;
        User user =new User();
//User user=null;
        while (true) {
            int choice = 0;
            printLogin();
            System.out.println("enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
            switch (choice) {
//                case 0 -> printInstructionsUser();
                case 1: {
                    while (true) {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("username: ");
                        String username = sc.nextLine();
                        System.out.println("password: ");
                        String password = sc.nextLine();

                        user = new User(username, password);

                        id = obiect.login(user);

                        user.setId(id);

                        if (id != -1)
                            break;
                    }
                    break;
                }
                case 2: {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("username: ");
                    String email = sc.nextLine();
                    System.out.println("password: ");
                    String password = sc.nextLine();
                    User u = new User(email, password);
                    obiect.createUser(u, false);
                break;
                }

            }
            break;
        }


        // ura sunt logat

            while (true) {


//                assert user != null;
                boolean isAdmin = obiect.isAdmin(user);
                if (!isAdmin) {
                    boolean exit = false;
                    int choice = 0;
                    printInstructionsUser();
                    while (!exit) {
                        System.out.println("enter your choice: ");
                        try {
                            choice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                        }

                        switch (choice) {
                        case 0 -> printInstructionsUser();
                        case 1 -> obiect.readAllFlights();
                        case 2 -> {obiect.readAllFlights();
                            Scanner sc = new Scanner(System.in);
                            System.out.println("Choose the flight number to buy a ticket ");
                            String idflight = sc.nextLine();
                            Tickets t=new Tickets(idflight);
                            obiect.buyTickets(t,id);}
                        case 3 ->{
                            List<Tickets> t= obiect.readTicketsOfUser(id);
                            System.out.println(t);
                        }
                        case 4 -> exit = true;
                        }

                    } break;


                } else {

                    boolean exit = false;
                    int choice = 0;
                    printInstructionAdmin();
                    while (!exit) {
                        System.out.println("enter your choice: ");
                        try {
                            choice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                        }

                        switch (choice) {
                            case 0 -> printInstructionAdmin();
                            case 1 -> {
                                Scanner sc = new Scanner(System.in);
                                System.out.println("username: ");
                                String email = sc.nextLine();
                                System.out.println("password: ");
                                String password = sc.nextLine();
                                User u = new User(email, password);
                                obiect.createUser(u, true);
                            }
                        case 2 -> obiect.readAllFlights();
                        case 3-> {
                                Scanner sc = new Scanner(System.in);
                            System.out.println("FlightName: ");
                            String flightname = sc.nextLine();
                            System.out.println("Date: ");
                            String date = sc.nextLine();
                            System.out.println("Hour: ");
                            String time = sc.nextLine();
                            Flights f = new Flights(flightname,date,time);
                            obiect.createFlights(f);
                            }
                        case 4 ->{
                            Scanner sc = new Scanner(System.in);
                            System.out.println("FlightName: ");
                            String flightname = sc.nextLine();
                            System.out.println("Update date: ");
                            String date = sc.nextLine();
                            System.out.println("Update hour: ");
                            String time = sc.nextLine();
                            Flights f = new Flights(flightname,date,time);
                            obiect.updateFlight(f);
                        }
                        case 5->{
                            Scanner sc = new Scanner(System.in);
                            System.out.println("FlightName to delete: ");
                            String flightname = sc.nextLine();
                            obiect.deleteFlight(flightname);
                        }
                            case 6 -> exit = true;
                        }

                        }
                    }
                    break;
                }
            }




        public static void printInstructionsUser () {
            System.out.println("\nPress");
            System.out.println("\t 0 - to print choice options");
            System.out.println("\t 1 - Show flights");
            System.out.println("\t 2 - Buy ticket");
            System.out.println("\t 3 - Show ticket");
            System.out.println("\t 4 - to exit");
        }

        public static void printInstructionAdmin () {
            System.out.println("\nPress");
            System.out.println("\t 0 - to print choice options");
            System.out.println("\t 1 - Create admin user");
            System.out.println("\t 2 - Show all flights");
            System.out.println("\t 3 - Create flights");
            System.out.println("\t 4 - Update flights");
            System.out.println("\t 5 - Delete flight");
            System.out.println("\t 6 - to exit");

        }
        public static  void printLogin(){ System.out.println("\nPress");
            System.out.println("\t 1 - Login");
            System.out.println("\t 2 - Register");
            System.out.println("\t 3 - to exit");

        }
    }

