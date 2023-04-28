import java.sql.Date;
import java.sql.Time;

public class Flights {
    String flightname;
    String hour;
    String date;
   int id;

    public Flights(String flightname, String date, String hour) {
        this.flightname = flightname;
        this.hour = hour;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "flightname='" + flightname + '\'' +
                ", hour=" + hour +
                ", date=" + date +
                ", id=" + id +
                '}';
    }

    public String getFlightname() {
        return flightname;
    }

    public void setFlightname(String flightname) {
        this.flightname = flightname;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setid(long id) {
    }
}
