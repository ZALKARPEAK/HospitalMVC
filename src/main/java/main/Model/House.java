package main.Model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.Enum.HouseType;
import main.Model.id.Id;


@Setter
@Getter
@Entity
@AllArgsConstructor
public class House extends Id {
    @NotNull
    private HouseType houseType;
    @NotNull
    private String address;
    @NotNull
    private int price;
    @NotNull
    private int room;
    @NotNull
    private String country;
    @NotNull
    private String description;
    @NotNull
    private boolean isBooked;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private Agency agency;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Booking booking;

    public House() {
    }

    @Override
    public String toString() {
        return "House{" +
                "houseType=" + houseType +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", room=" + room +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", isBooked=" + isBooked +
                '}';
    }
}
