package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GrupoTest {
    private Grupo grupo;

    @BeforeEach
    @DisplayName("Creamos un grupo")
    void setUp() throws ClubException {
        grupo = new Grupo("COD123", "futbol", 20, 10, 50.0);
    }

    @Test
    @DisplayName("Probamos que el constructor se crea correctamente")
    void testConstructorValido() {
        assertDoesNotThrow(() -> new Grupo("COD123", "futbol", 20, 10, 50.0));
    }

    @Test
    @DisplayName("Probamos que salten las excepciones cuando el constructor no se crea correctamente")
    void testConstructorDatosInvalidos() {
        assertThrows(ClubException.class, () -> new Grupo("COD123", "futbol", -1, 10, 50.0));
        assertThrows(ClubException.class, () -> new Grupo("COD123", "futbol", 20, -1, 50.0));
        assertThrows(ClubException.class, () -> new Grupo("COD123", "futbol", 20, 21, 50.0));
        assertThrows(ClubException.class, () -> new Grupo("COD123", "futbol", 20, 10, -50.0));
    }

    @Test
    @DisplayName("Probamos que salten las excepciones cuando el constructor no se crea correctamente")
    void testActualizarPlazasValido() throws ClubException {
        grupo.actualizarPlazas(25);
        int expectedValue = 25;
        int obtainedValue = grupo.getPlazas();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Probamos que salten las excepciones al actualizar las plazas a un valor inválido")
    void testActualizarPlazasInvalido() {
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(0));
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(9)); // Menor que matriculados
    }

    @Test
    @DisplayName("Verificamos la matriculación válida de nuevos miembros")
    void testMatricularValido() throws ClubException {
        grupo.matricular(5);
        int expectedValue = 15;
        int obtainedValue = grupo.getMatriculados();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Probamos que salten excepciones al matricular un número inválido de miembros")
    void testMatricularInvalido() {
        assertThrows(ClubException.class, () -> grupo.matricular(11)); // Más de las plazas libres
        assertThrows(ClubException.class, () -> grupo.matricular(-1)); // Número negativo
    }

    @Test
    @DisplayName("Comprobamos el cálculo de plazas libres")
    void testPlazasLibres() {
        int expectedValue = 10;
        int obtainedValue = grupo.plazasLibres();
        assertEquals(obtainedValue, expectedValue);
    }

    @Test
    @DisplayName("Verificamos el funcionamiento correcto de equals para un caso de igualdad")
    void testEquals() throws ClubException {
        Grupo otroGrupo = new Grupo("COD123", "futbol", 20, 10, 50.0);
        assertEquals(grupo, otroGrupo);
    }

    @Test
    @DisplayName("Verificamos el funcionamiento correcto de equals para casos de desigualdad")
    void testNotEquals() throws ClubException {
        Grupo otroGrupo = new Grupo("COD124", "Baloncesto", 20, 10, 50.0);
        Grupo otroGrupo2 = new Grupo("cod123", "FUTBOL", 20, 10, 50.0);
        assertNotEquals(grupo, otroGrupo);
        assertNotEquals(grupo, null);
        assertEquals(grupo, otroGrupo2);
    }

    @Test
    @DisplayName("Comprobamos el funcionamiento del método getCodigo")
    void getCodigoWorks() {
        String expectedValue = "COD123";
        String obtainedValue = grupo.getCodigo();
         assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Comprobamos el funcionamiento del método getActividad")
    void getActividadWorks() {
        String expectedValue = "futbol";
        String obtainedValue = grupo.getActividad();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
@DisplayName("Verificamos que la tarifa se obtiene correctamente")
void getTarifa() {
    double expectedValue = 50.0;
    double obtainedValue = grupo.getTarifa();
    assertEquals(expectedValue, obtainedValue);
}

@Test
@DisplayName("Probamos que la representación toString del grupo es correcta")
void testToString() {
    String expectedValue = "(COD123 - futbol - 50.0 euros - P:20 - M:10)";
    String obtainedValue = grupo.toString();
    assertEquals(expectedValue, obtainedValue);
}

@Test
@DisplayName("Comprobamos que el código hash se genera correctamente")
void testHashCode() {
    int expectedValue = (grupo.getCodigo().toUpperCase().hashCode() + grupo.getActividad().toUpperCase().hashCode());
    int obtainedValue = grupo.hashCode();
    assertEquals(expectedValue, obtainedValue);
}

}