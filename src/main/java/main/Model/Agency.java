package main.Model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.Model.id.Id;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
public class Agency extends Id {
    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String phoneNumber;
    @Column(length = 20000)
    @NotNull
    private String imageLink;
    @NotNull
    private String email;

    @OneToMany(mappedBy = "agency",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<House> houses;

    @ManyToMany(mappedBy = "agency",cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Customer> customers;

    public Agency() {
    }

    public void addCustomer(Customer c){
        if (this.customers == null) {
            customers = new ArrayList<>();
        }
        customers.add(c);
    }

    @Override
    public String toString() {
        return "Agency{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
