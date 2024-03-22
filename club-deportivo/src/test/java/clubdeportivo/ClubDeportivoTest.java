package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClubDeportivoTest {
    private ClubDeportivo club;

    @BeforeEach
    void setUp() throws ClubException {
        club = new ClubDeportivo("ClubTest", 2);
    }

    @Test
    @DisplayName("Verifica que el constructor es válido con diferentes parámetros válidos")
    void testConstructorValido() {
        assertDoesNotThrow(() -> new ClubDeportivo("club1"));
        assertDoesNotThrow(() -> new ClubDeportivo("club2", 5));
    }

    @Test
    @DisplayName("Verifica que el constructor lanza una excepción cuando se proporcionan parámetros inválidos")
    void testConstructorInvalido() {
        assertThrows(ClubException.class, () -> new ClubDeportivo("club1", 0));
        assertThrows(ClubException.class, () -> new ClubDeportivo("club2", -1));
    }

    @Test
    @DisplayName("Verifica que se puede añadir un grupo correctamente")
    void testAnyadirActividadGrupo() throws ClubException {
        Grupo grupo = new Grupo("COD123", "futbol", 20, 10, 50.0);
        club.anyadirActividad(grupo);
        int obtainedValue = 10;
        int expectedValue = club.plazasLibres("futbol");
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Verifica que no se puede añadir un grupo nulo")
    void testAnyadirActividadGrupoNulo() throws ClubException {
        Grupo grupo = null;        
        assertThrows(ClubException.class, ()-> club.anyadirActividad(grupo));
    }

    @Test
    @DisplayName("Verifica que se pueden añadir varios grupos para la misma actividad y se actualizan las plazas correctamente")
    void testAnyadirActividadPlazas() throws ClubException {
        Grupo grupo = new Grupo("COD123", "futbol", 20, 10, 50.0);
        club.anyadirActividad(grupo);   
        Grupo grupo2 = new Grupo("COD123", "futbol", 25, 10, 50.0);  
        club.anyadirActividad(grupo2);
        int expectedValue = 15;
        int obtainedValue = club.plazasLibres("futbol");
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Verifica que se pueden añadir grupos para actividades distintas")
    void testAnyadirActividadGruposDistintos() throws ClubException {
        Grupo grupo = new Grupo("COD123", "futbol", 20, 10, 50.0);
        club.anyadirActividad(grupo);   
        Grupo grupo2 = new Grupo("COD1234", "basket", 25, 10, 50.0);  
        club.anyadirActividad(grupo2);
        assertNotEquals(grupo, grupo2);
    }

    @Test
    @DisplayName("Verifica que se puede añadir una actividad mediante un array de datos")
    void testAnyadirActividadDatos() throws ClubException {
        String[] datos = {"COD123", "futbol", "20", "10", "50.0"};
        club.anyadirActividad(datos);
        int expectedValue = 10; 
        int obtainedValue = club.plazasLibres("futbol");
        assertEquals(expectedValue, obtainedValue);        
    }

    @Test
    @DisplayName("Verifica que se lanza una excepción cuando se intenta añadir una actividad con datos en formato incorrecto")
    void testAnyadirActividadDatosFormatoMalo() throws ClubException {        
        String[] datos2 = {"1", "2", "hola", "hola2", "hola3"};
        assertThrows(ClubException.class, ()-> club.anyadirActividad(datos2));
    }

    @Test
    @DisplayName("Verifica que se pueden matricular personas correctamente en una actividad")
    void testMatricular() throws ClubException {
        String[] datos = {"COD123", "futbol", "20", "10", "50.0"};
        club.anyadirActividad(datos);
        club.matricular("futbol", 5);
        int expectedValue = 5;
        int obtainedValue = club.plazasLibres("futbol");
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Verifica que no se pueden matricular más personas de las plazas disponibles")
    void testMatricularSinEspacio() throws ClubException {
        String[] datos = {"COD123", "futbol", "20", "19", "50.0"};
        club.anyadirActividad(datos);
        assertThrows(ClubException.class, () -> club.matricular("futbol", 2));
    }

    @Test
    @DisplayName("Verifica que se pueden matricular personas en varios grupos de una misma actividad hasta el máximo permitido")
    void testMatricularSuperaMax() throws ClubException {
        String[] datos = {"COD123", "basket", "20", "18", "50.0"};
        club.anyadirActividad(datos);
        club.matricular("basket", 0);
        assertEquals(2, club.plazasLibres("basket"));  
        String[] datos2 = {"COD123", "futbol", "20", "18", "50.0"};
        club.anyadirActividad(datos2);
        String[] datos3 = {"COD1234", "futbol", "20", "18", "50.0"};
        club.anyadirActividad(datos3);
        club.matricular("futbol", 3);
        int expectedValue = 1;
        int obtainedValue = club.plazasLibres("futbol");
        assertEquals(expectedValue, obtainedValue);                    
    }

    @Test
    @DisplayName("Verifica que se pueden matricular personas en varias actividades distintas")
    void testMatricularVariosGrupos() throws ClubException {
        String[] datos = {"COD123", "basket", "15", "10", "50.0"};
        club.anyadirActividad(datos);
        String[] datos2 = {"COD1234", "futbol", "15", "10", "50.0"};
        club.anyadirActividad(datos2);
        int expectedValue = 5; 
        int obtainedValue = club.plazasLibres("futbol");
        assertEquals(expectedValue, obtainedValue);        
    }

    @Test
    @DisplayName("Verifica que los ingresos se calculan correctamente")
    void testIngresos() throws ClubException {
        String[] datos = {"COD123", "futbol", "20", "10", "50.0"};
        club.anyadirActividad(datos);
        double expectedValue = 500.0;
        double obtainedValue = club.ingresos();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Verifica que el método toString() funciona correctamente cuando no hay grupos")
    void testtoStringGrupoVacio(){
        String expected = "ClubTest --> [  ]";
        assertEquals(expected, club.toString());
    }

    @Test
    @DisplayName("Verifica que el método toString() funciona correctamente cuando hay grupos")
    void testtoStringGrupo() throws ClubException{
        String[] datos = {"COD123", "futbol", "20", "10", "50.0"};
        club.anyadirActividad(datos);        
        String expected = "ClubTest --> [ (COD123 - futbol - 50.0 euros - P:20 - M:10) ]";
        assertEquals(expected, club.toString());
    }
}