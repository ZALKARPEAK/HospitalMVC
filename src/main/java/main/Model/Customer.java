package main.Model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.Enum.Gender;
import main.Model.id.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
public class Customer extends Id {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private String phoneNumber;
    @NotNull
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Booking> booking;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Agency> agency;

    public Customer() {
    }
    public void addAgency(Agency c){
        if (this.agency == null) {
            agency = new ArrayList<>();
        }
        agency.add(c);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
