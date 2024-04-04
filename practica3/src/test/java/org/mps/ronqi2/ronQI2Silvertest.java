package org.mps.ronqi2;


import org.junit.jupiter.api.Test;
import org.mps.dispositivo.DispositivoSilver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ronQI2Silvertest{/*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser.
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos,
     * el método inicializar de ronQI2 o sus subclases,
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */

    @Test
    void testInicializarConExito() {
        RonQI2Silver ronq = new RonQI2Silver();

        DispositivoSilver mockDisp = mock(DispositivoSilver.class);
        when(mockDisp.conectarSensorPresion()).thenReturn(true);
        when(mockDisp.conectarSensorSonido()).thenReturn(true);
        when(mockDisp.configurarSensorPresion()).thenReturn(true);
        when(mockDisp.configurarSensorSonido()).thenReturn(true);
        ronq.disp = mockDisp;

        boolean obtainedValue = ronq.inicializar();

        assertTrue(obtainedValue);
    }

    @Test
    void inicializar_unSensorNoConectado() {
        RonQI2Silver ronq = new RonQI2Silver();

        DispositivoSilver mockDisp = mock(DispositivoSilver.class);
        when(mockDisp.conectarSensorPresion()).thenReturn(true);
        when(mockDisp.conectarSensorSonido()).thenReturn(false);
        when(mockDisp.configurarSensorPresion()).thenReturn(true);
        when(mockDisp.configurarSensorSonido()).thenReturn(true);
        ronq.disp = mockDisp;

        boolean obtainedValue = ronq.inicializar();

        assertFalse(obtainedValue);
    }

    @Test
    void inicializar_unSensorNoConfigurado() {
        RonQI2Silver ronq = new RonQI2Silver();

        DispositivoSilver mockDisp = mock(DispositivoSilver.class);
        when(mockDisp.conectarSensorPresion()).thenReturn(true);
        when(mockDisp.conectarSensorSonido()).thenReturn(true);
        when(mockDisp.configurarSensorPresion()).thenReturn(false);
        when(mockDisp.configurarSensorSonido()).thenReturn(true);
        ronq.disp = mockDisp;

        boolean obtainedValue = ronq.inicializar();

        assertFalse(obtainedValue);
    }


    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */
    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}
