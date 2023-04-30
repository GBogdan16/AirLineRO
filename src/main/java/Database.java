

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Database {
    String createUser(User user, boolean isadmin) {
        String message = null;
        int val = 0;

        //connect to DB with driver
        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //run SQL
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS (email, password, isadmin) VALUES (?,?,?)");

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, isadmin);

            val = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            String m = e.getMessage();
            if(m.contains("authentication failed for"))
                message="There must be a connection problem, our team is trying to fix it as soon as possible! Thanks for your patience!";
            else if(m.contains("ERROR: value too long for type character"))
                message="The username or password too long!! (max 40 characters) ";
            else message="General error, our team is trying to fix it as soon as possible. ";
        }

//        System.out.println(message);
        System.out.println(val + " user successfully created ");
        return message;
    }

    public List<Flights> readAllFlights() {
        List<Flights> listOfFlights = new ArrayList<>();

        //connect to DB
        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //run SQL
            Statement pSt = connection.createStatement();
            ResultSet rs = pSt.executeQuery("select * from flights ");


            while (rs.next()) {
                String flightname = rs.getString("flightname").trim();
                String date = rs.getString("date").trim();
                String hour = rs.getString("hour").trim();
                long id = rs.getLong("id");
                System.out.println("Flight number " + id + " " + "Flight course  " + flightname + "       " + "Date  " + date + "        " + "Hour  " + hour);

            }

            return listOfFlights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tickets> readTicketsOfUser(long id) throws SQLException {
        List<Tickets> listOfTickets = new ArrayList<>();

        //connect to DB
        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";


        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        //run SQL

        PreparedStatement pSt = connection.prepareStatement("select id,idflight from tickets where iduser=? ");
        pSt.setLong(1,id);
        ResultSet rs = pSt.executeQuery();
        while (rs.next()) {
            String idflight = rs.getString("idflight");
            String idF = rs.getString("id");
            Tickets uf = new Tickets(idflight);
            uf.setId(Integer.parseInt(idF));
            uf.setIduser((int)id);
            listOfTickets.add(uf);
        }
        return listOfTickets;

    }

    public String buyTickets(Tickets t, long id) {
        String message = null;
        int val = 0;

        //connect to DB

        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";


        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //run SQL

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tickets (idflight,iduser) VALUES (?,?)");
            preparedStatement.setInt(1, t.getIdflight());
            preparedStatement.setLong(2, id);
            val = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(val + " ticket successfully created");
        return message;
    }


    public String createFlights(Flights flights) {
        String message = null;
        int val = 0;

        //connect to DB

        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";


        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //run SQL

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Flights (flightname,date,hour) VALUES (?,?,?)");
            preparedStatement.setString(1, flights.getFlightname());
            preparedStatement.setString(2, flights.getDate());
            preparedStatement.setString(3, flights.getHour());
            val = preparedStatement.executeUpdate();

        } catch (SQLException | InputMismatchException e) {
            e.printStackTrace();
            String m = e.getMessage();
            if (m.contains(" authentication failed for"))
                message = "There must be a connection problem, our team is trying to fix it as soon as possible! Thanks for your patience! ";
            else if (m.contains("violates foreign key constraint"))
                message = "You cannot create this food, because the user for who you want to create the food, is not present in the table users";
            else if (m.contains("ERROR: value too long for type characte"))
                message = "The food name is too long. Please try again using maximum 80 characters. ";
            else message = "General error, our team is trying to fix it";
        }
        System.out.println(message);
        System.out.println(val + " flight successfully created");

        return message;
    }
        String updateFlight(Flights flights) {
        String message= null;
        int val=0;

        //connect to DB
        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //run SQL
            PreparedStatement preparedStatement = connection.prepareStatement("update flights set date=?,hour=? where flightname=?");
            preparedStatement.setString(1,flights.getDate());
            preparedStatement.setString(2,flights.getHour());
            preparedStatement.setString(3,flights.getFlightname());


            val = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            String m = e.getMessage();
            if(m.contains("authentication failed for"))
                message="There must be a connection problem. Our team is trying to fix it. Thanks for your patience. ";
            else if(m.contains("ERROR: value too long for type character"))
                message=" The flight name you want to update is too long. Please try again using maximum 40 characters";
            else if(m.contains("does not exist")&&val == 0)
                message="You cannot update this flight because the user for who you want to update the food, does not exist. ";
            else message="General error. Our team is trying to fix it.";

        }

        System.out.println(message);
        System.out.println(val + " flight successfully updated ");
        return message;
    }
    String deleteFlight(String flightname){
        String message = null;
        int val = 0;

        //connect to DB
        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";

        try {
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            //run SQL
            PreparedStatement preparedStatement = conn.prepareStatement("delete from flights where flightname = ? ");
            preparedStatement.setString(1,flightname);

            val = preparedStatement.executeUpdate();


            message=String.valueOf(val + " flight succesfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            String m = e.getMessage();
            if(m.contains(" authentication failed for"))
                message= "There must be a connection problem, our team is trying to fix it as soon as possible! Thanks for your patience! ";
            else if(m.contains("violates foreign key constraint"))
                message= "You cannot delete this flight ";
            else  message="General error, our team is trying to fix it.";
        }

        if(val<=0) System.out.println("The flight you want to delete does not exist. \nPlease try again using an existing flight");
        if(message!=null) System.out.println(message);
        return message;
    }
    String deleteTicket(Integer idflight,long id){
        String message = null;
        int val = 0;

        //connect to DB
        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";

        try {
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            //run SQL
            PreparedStatement preparedStatement = conn.prepareStatement("delete from tickets where idflight = ? and iduser=?");
            preparedStatement.setInt(1,idflight);
            preparedStatement.setLong(2,id);

            val = preparedStatement.executeUpdate();


            message=String.valueOf(val + " tickets/s succesfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            String m = e.getMessage();
            if(m.contains(" authentication failed for"))
                message= "There must be a connection problem, our team is trying to fix it as soon as possible! Thanks for your patience! ";
            else if(m.contains("violates foreign key constraint"))
                message= "You cannot delete this food because there is an user registered for it";
            else  message="General error, our team is trying to fix it.";
        }

        if(val<=0) System.out.println("The food you want to delete does not exist or is not alocated for the presented iduser. \nPlease try again using an existing food or make sure the event is alocated for the iduser you presented.");
        if(message!=null) System.out.println(message);
        return message;
    }

    long login(User user) {

        // -1 daca nu exista , si id-ul usaerului daca exista
        long id = -1;

        //connect to DB
        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //run SQL
            PreparedStatement preparedStatement = connection.prepareStatement("select id from users where email=? and password=? ");

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                id = resultSet.getLong("id");
                return id;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(id);
        return id;
    }

    boolean isAdmin(User user) {

        // -1 daca nu exista , si id-ul usaerului daca exista


        boolean isAdmin = false;

        //connect to DB
        String URL = "jdbc:postgresql://localhost:5432/AirRO";
        String USERNAME = "postgres";
        String PASSWORD = "postgres1245!";

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //run SQL
            PreparedStatement preparedStatement = connection.prepareStatement("select isadmin from users where email=? and password=? ");

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                isAdmin = resultSet.getBoolean("isadmin");
                return isAdmin;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdmin;
    }
}
