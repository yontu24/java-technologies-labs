package ro.uaic.info.app.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Team implements Serializable {
    private static final long serialVersionUID = -2730911646817994361L;

    private String name;
    private LocalDate foundingDate;
    private String city;

    public void setName(String name) {
        this.name = name;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Team))
            return false;

        Team team = (Team) obj;
        return team.getCity().equals(this.city) && team.getFoundingDate().toString().equals(this.foundingDate.toString())
                && team.getName().equals(this.name);
    }

    @Override
    public String toString() {
        return name + "[" + city + "]";
    }
}