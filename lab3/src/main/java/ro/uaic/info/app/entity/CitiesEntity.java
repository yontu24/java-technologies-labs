package ro.uaic.info.app.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "cities", schema = "public", catalog = "postgres")
public class CitiesEntity {
    private Integer id;
    private BigInteger teamId;
    private String name;
    private TeamsEntity teamsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "team_id", nullable = false, precision = 0)
    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitiesEntity that = (CitiesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    public TeamsEntity getTeamsById() {
        return teamsById;
    }

    public void setTeamsById(TeamsEntity teamsById) {
        this.teamsById = teamsById;
    }
}
