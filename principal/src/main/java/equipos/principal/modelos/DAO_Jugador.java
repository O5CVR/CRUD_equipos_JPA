package equipos.principal.modelos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class DAO_Jugador {

    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;

    public DAO_Jugador(){
        this.emf = Persistence.createEntityManagerFactory("gestion_equipos");
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    //    INSERT
    public void insertEquipo(DTO_Jugador jugador){
        tx.begin();
        em.persist(jugador);
        tx.commit();
    }
    //    DELETE
    public void clear_ID(int id){
        em.createNativeQuery("ALTER TABLE jugador AUTO_INCREMENT=" + (id - 1)).executeUpdate();
    }

    public void deleteEquipo(int id){
        tx.begin();
        DTO_Jugador eq = new DTO_Jugador();
        eq.setId(id);
        em.remove(em.find(DTO_Jugador.class, eq.getId()));
        clear_ID(id);
        tx.commit();
    }

    //    UPDATE
    public void updateEquipo(DTO_Jugador jugador){
        tx.begin();
        DTO_Jugador jugadorExistente = em.find(DTO_Jugador.class, jugador.getId());

        if(jugadorExistente != null){
            jugadorExistente.setNombre(jugador.getNombre());
            jugadorExistente.setEstatura(jugador.getEstatura());
            jugadorExistente.setPeso(jugador.getPeso());
            jugadorExistente.setIdEquipo(jugador.getIdEquipo());

            em.merge(jugadorExistente);
            tx.commit();

        } else {
            System.out.println("No existe el jugador");
        }

    }

    //    READ
    public void readJugadores(){

        tx.begin();
        List<Object[]> jugadores = em.createNativeQuery("SELECT * FROM jugador").getResultList();

        for (Object[] eq: jugadores){
            DTO_Jugador e = new DTO_Jugador();
            e.setId((int) eq[0]);
            e.setNombre((String) eq[1]);
            e.setEstatura((float) eq[2]);
            e.setPeso((float) eq[3]);
//            e.setIdEquipo((DTO_Equipo) eq[4]); Como mostrar el setIdEquipo si me lo guarda como DTO?

            System.out.println("//" + e.getId() + " " + e.getNombre() + " " + e.getEstatura() + " " + e.getPeso() + "//");
        }
    }




}
