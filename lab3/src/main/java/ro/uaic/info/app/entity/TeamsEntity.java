package ro.uaic.info.app.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "teams", schema = "public", catalog = "postgres")
public class TeamsEntity {
    private Integer id;
    private String name;
    private Date foundingDate;
    private CitiesEntity citiesById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "founding_date", nullable = true)
    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamsEntity that = (TeamsEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (foundingDate != null ? !foundingDate.equals(that.foundingDate) : that.foundingDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (foundingDate != null ? foundingDate.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "teamsById")
    public CitiesEntity getCitiesById() {
        return citiesById;
    }

    public void setCitiesById(CitiesEntity citiesById) {
        this.citiesById = citiesById;
    }
}
