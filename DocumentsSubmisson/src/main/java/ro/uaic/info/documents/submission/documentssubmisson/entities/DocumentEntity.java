package ro.uaic.info.documents.submission.documentssubmisson.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "documents", schema = "public", catalog = "documents")
@NamedQuery(name = "DocumentEntity.findAll", query = "SELECT d FROM DocumentEntity d where d.author = :author")
public class DocumentEntity {
    private String uniqueid;
    private String author;
    private Date date;

    @Id
    @Column(name = "uniqueid", nullable = false, length = -1)
    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    @Basic
    @Column(name = "author", nullable = false, length = -1)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentEntity that = (DocumentEntity) o;

        if (uniqueid != null ? !uniqueid.equals(that.uniqueid) : that.uniqueid != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uniqueid != null ? uniqueid.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
