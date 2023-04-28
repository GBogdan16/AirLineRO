public class Tickets {
    int id;
    int idflight;
    int iduser;


    public int getIdflight() {
        return idflight;
    }

    public void setIdflight(int idflight) {
        this.idflight = idflight;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", flightname='" + idflight + '\'' +
                '}';
    }

    public Tickets(String idflight) {
        this.idflight = Integer.parseInt(idflight);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
