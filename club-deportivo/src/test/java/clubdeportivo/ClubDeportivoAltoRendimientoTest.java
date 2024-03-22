package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

class ClubDeportivoAltoRendimientoTest {

    private ClubDeportivoAltoRendimiento club;

    @BeforeEach
    @DisplayName("Creamos un club")
    void setUp() throws ClubException {
        club = new ClubDeportivoAltoRendimiento("Club Prueba", 10, 20.0);
    }

    @Test
    @DisplayName("Verifica que el constructor funciona correctamente con valores válidos")
    void testConstructorConValoresValidos() {
        assertDoesNotThrow(() -> new ClubDeportivoAltoRendimiento("Club1", 10, 100, 1.5));
    }

    @Test
    @DisplayName("Verifica que el constructor lanza una excepción cuando se proporciona un valor máximo inválido")
    void testConstructorConMaximoInvalido() {
        Executable executable = () -> new ClubDeportivoAltoRendimiento("Club2", 10, -1, 1.5);
        assertThrows(ClubException.class, executable);
    }

    @Test
    @DisplayName("Verifica que el constructor lanza una excepción cuando se proporciona un valor de incremento inválido")
    void testConstructorConIncrementoInvalido() {
        Executable executable = () -> new ClubDeportivoAltoRendimiento("Club3", 10, 100, -1);
        assertThrows(ClubException.class, executable);
    }

    @Test
    @DisplayName("Verifica que el constructor lanza una excepción cuando se proporcionan valores máximo e incremento inválidos")
    void testConstructorConAmbosInvalidos() {
        Executable executable = () -> new ClubDeportivoAltoRendimiento("Club4", 10, 0, 0);
        assertThrows(ClubException.class, executable);
    }

    @Test
    @DisplayName("Verifica que el constructor crea un objeto Club válido cuando se proporcionan valores válidos")
    void pruebaConstructorValoresValidos(){
        String message = "El club no debería ser null";
        assertNotNull(club, message);
    }

    @Test
    @DisplayName("Verifica que el constructor lanza una excepción cuando se proporciona un valor máximo negativo")
    void pruebaConstructorValorMaximoNegativo() {
        Exception exception = assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Error", -5, 20.0));
        boolean obtainedValue = exception.getMessage().contains("valores 0 o negativos");
        assertTrue(obtainedValue);
    }

    @Test
    @DisplayName("Verifica que el constructor lanza una excepción cuando se proporciona un valor de incremento negativo")
    void pruebaConstructorValorIncrementoNegativo() {
        Exception exception = assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Error", 10, -20.0));
        boolean obtainedValue = exception.getMessage().contains("valores 0 o negativos");
        assertTrue(obtainedValue);
    }

    @Test
    @DisplayName("Verifica que el constructor lanza una excepción cuando se proporcionan valores máximo e incremento iguales a cero")
    void pruebaConstructorValoresCero() {
        Exception exception = assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Error", 0, 0));
        boolean obtainedValue = exception.getMessage().contains("valores 0 o negativos");
        assertTrue(obtainedValue);
    }

    @Test
    @DisplayName("Verifica que se lanza una excepción cuando se intenta añadir una actividad con datos insuficientes")
    void pruebaAnyadirActividadDatosInsuficientes() {
        String[] datos = {"Nombre", "Tipo", "5", "2"}; 
        ClubException exception = assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
        boolean expectedValue = exception.getMessage().contains("faltan datos");
        assertTrue(expectedValue);
    }

    @Test
    @DisplayName("Verifica que se puede añadir una actividad con plazas que exceden el máximo")
    void pruebaAnyadirActividadPlazasExcedenMaximo() throws ClubException {
        String[] datos = {"Actividad", "Tipo", "15", "2", "100.0"};
        club.anyadirActividad(datos);        
    }

    @Test
    @DisplayName("Verifica que se lanza una excepción cuando se intenta añadir una actividad con un formato de número incorrecto")
    void pruebaAnyadirActividadFormatoNumeroIncorrecto() {
        String[] datos = {"Actividad", "Tipo", "NaN", "2", "100.0"};
        ClubException exception = assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
        boolean obtainedValue = exception.getMessage().contains("formato de número incorrecto");
        assertTrue(obtainedValue);
    }

    @Test
    @DisplayName("Verifica que los ingresos se calculan correctamente cuando hay actividades")
    void pruebaIngresosConActividades() throws ClubException {
        String[] datos = {"Actividad", "Tipo", "5", "5", "100.0"};
        club.anyadirActividad(datos);
        double expectedValue = 500.0 + (500.0 * 0.2); 
        double obtainedValue = club.ingresos();
        assertEquals(expectedValue, obtainedValue, 0.01, "Los ingresos calculados no son correctos");
    }

    @Test
    @DisplayName("Verifica que los ingresos son cero cuando no hay actividades")
    void pruebaIngresosSinActividades() {
        double expectedValue = 0.0;
        double obtainedValue = club.ingresos();
        assertEquals(expectedValue, obtainedValue, 0.0, "Los ingresos deberían ser 0 sin actividades");
    }
}