package ro.info.uaic.app;

import org.junit.Test;
import ro.uaic.info.app.model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestConnection {
    @Test
    public void testConnection() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamplePU_Local");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Team team = new Team();
        team.setName("Foo");
        em.persist(team);

        team = (Team) em.createQuery("select t from TeamsEntity t where t.name='Juventus'").getSingleResult();
        team.setName("Juventus");

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
