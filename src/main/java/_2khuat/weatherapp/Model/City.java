package _2khuat.weatherapp.Model;

public class City {
    public String name;
    public String country;
    public String state;
    public int lat;
    public int lon;
    public LocalNames localNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public LocalNames getLocalNames() {
        return localNames;
    }

    public void setLocalNames(LocalNames localNames) {
        this.localNames = localNames;
    }
}
