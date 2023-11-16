package main.Api;

public class BookingApi {

    private boolean isBooked;

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        this.isBooked = true;
    }

    public void unbook() {
        this.isBooked = false;
    }
}
