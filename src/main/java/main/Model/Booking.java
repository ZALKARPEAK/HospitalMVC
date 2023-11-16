package main.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.Model.id.Id;


@Setter
@Getter
@Entity
@AllArgsConstructor
public class Booking extends Id {
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private Customer customer;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private House house;

    public Booking() {
    }


}
