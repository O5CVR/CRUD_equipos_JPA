package equipos.principal.modelos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class DAO_Equipo {

    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;

    public DAO_Equipo(){
        this.emf = Persistence.createEntityManagerFactory("gestion_equipos");
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

//    INSERT
    public void insertEquipo(DTO_Equipo equipo){
        tx.begin();
        em.persist(equipo);
        tx.commit();
    }
//    DELETE
    public void clear_ID(int id){
        em.createNativeQuery("ALTER TABLE equipo AUTO_INCREMENT=" + (id - 1)).executeUpdate();
    }

    public void deleteEquipo(int id){
        tx.begin();
        DTO_Equipo eq = new DTO_Equipo();
        eq.setId(id);
        em.remove(em.find(DTO_Equipo.class, eq.getId()));
        clear_ID(id);
        tx.commit();
    }

//    UPDATE
    public void updateEquipo(DTO_Equipo equipo){
        tx.begin();
        DTO_Equipo equipoExistente = em.find(DTO_Equipo.class, equipo.getId());

        if(equipoExistente != null){
            equipoExistente.setNombre(equipo.getNombre());
            equipoExistente.setEstadio(equipo.getEstadio());

            em.merge(equipoExistente);
            tx.commit();

        } else {
           System.out.println("No existe el equipo");
        }

    }

//    READ
    public void readTeams(){

        tx.begin();
        List<Object[]>  equipos = em.createNativeQuery("SELECT * FROM equipo").getResultList();

        for (Object[] eq: equipos){
            DTO_Equipo e = new DTO_Equipo();
            e.setId((int) eq[0]);
            e.setNombre((String) eq[1]);
            e.setEstadio((String) eq[2]);

            System.out.println("//" + e.getId() + " " + e.getNombre() + " " + e.getEstadio() + "//");
        }
    }

    public void readTeamsOrderAsc(){

        tx.begin();
        List<Object[]>  equipos = em.createNativeQuery("SELECT * FROM equipo order by id asc ").getResultList();

        for (Object[] eq: equipos){
            DTO_Equipo e = new DTO_Equipo();
            e.setId((int) eq[0]);
            e.setNombre((String) eq[1]);
            e.setEstadio((String) eq[2]);

            System.out.println("//" + e.getId() + " " + e.getNombre() + " " + e.getEstadio() + "//");
        }
    }

    public void readTeamsOrderDesc(){

        tx.begin();
        List<Object[]>  equipos = em.createNativeQuery("SELECT * FROM equipo order by id desc").getResultList();

        for (Object[] eq: equipos){
            DTO_Equipo e = new DTO_Equipo();
            e.setId((int) eq[0]);
            e.setNombre((String) eq[1]);
            e.setEstadio((String) eq[2]);

            System.out.println("//" + e.getId() + " " + e.getNombre() + " " + e.getEstadio() + "//");
        }
    }

}
