package equipos.principal.main;

import equipos.principal.modelos.DAO_Equipo;
import equipos.principal.modelos.DAO_Jugador;
import equipos.principal.modelos.DTO_Equipo;

public class Main {
    public static void main(String[] args) {

        DAO_Equipo de = new DAO_Equipo();
        DAO_Jugador dj = new DAO_Jugador();

//        DTO_Equipo equipo = new DTO_Equipo();
//        equipo.setId(7);
//        equipo.setNombre("Atleti");
//        equipo.setEstadio("Calderon");
//        de.insertEquipo(equipo);
//        DTO_Equipo equipo2 = new DTO_Equipo();
//        equipo2.setNombre("Madrid");
//        equipo2.setEstadio("Bernabeu");
//        de.insertEquipo(equipo2);

//        de.deleteEquipo(7);
//        de.deleteEquipo(8);

//        de.updateEquipo(equipo);

//        de.readTeamsOrderAsc();

        dj.readJugadores();
    }
}
