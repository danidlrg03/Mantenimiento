package org.mps.ronqi2;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mps.dispositivo.DispositivoSilver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ronQI2Silvertest{/*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser.
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos,
     * el método inicializar de ronQI2 o sus subclases,
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    @ParameterizedTest
    @CsvSource({
            "false, false, false, false, false",
            "true, false, false, false, false",
            "true, true, false, false, false",
            "true, true, true, true, true",
            "false, true, false, false, false",
            "false, true, true, false, false",
            "false, false, true, false, false",
            "false, false, true, true, false",
            "false, false, false, true, false",
            "true, false, true, true, false",
            "true, true, false, true, false",
            "true, false, false, true, false",
            "false, true, false, true, false",
            "false, true, true, true, false",
            "true, false, true, false, false",
            "true, true, true, false, false"
    })
    void testInicializar(boolean conectarPresion, boolean configurarPresion,
                         boolean conectarSonido, boolean configurarSonido,
                         boolean resultadoEsperado) {
        // Simular la dependencia disp
        DispositivoSilver dispMock = mock(DispositivoSilver.class);
        when(dispMock.conectarSensorPresion()).thenReturn(conectarPresion);
        when(dispMock.configurarSensorPresion()).thenReturn(configurarPresion);
        when(dispMock.conectarSensorSonido()).thenReturn(conectarSonido);
        when(dispMock.configurarSensorSonido()).thenReturn(configurarSonido);

        // Crear la instancia de la clase que contiene el método inicializar
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.disp = dispMock;

        boolean resultadoObtenido = ronq.inicializar();
        // Verificar el resultado
        assertEquals(resultadoEsperado,resultadoObtenido);
        if(resultadoObtenido){
            verify(dispMock, times(1)).configurarSensorSonido();
            verify(dispMock, times(1)).configurarSensorPresion();
        }
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
    @ParameterizedTest
    @CsvSource({
            "false, false, false, false",
            "false, true, false, false",
            "false, false, true, false",
            "false, true, true, true",

            "true, false, false, false",
            "true, true, false, false",
            "true, false, true, false",
            "true, true, true, false"
    })
    void testReconectar(boolean estaConectado, boolean conectarPresion, boolean conectarSonido, boolean resultadoEsperado){
        // Simular la dependencia disp
        DispositivoSilver dispMock = mock(DispositivoSilver.class);
        when(dispMock.estaConectado()).thenReturn(estaConectado);
        when(dispMock.conectarSensorPresion()).thenReturn(conectarPresion);
        when(dispMock.conectarSensorSonido()).thenReturn(conectarSonido);

        // Crear la instancia de la clase que contiene el método reconectar
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.disp = dispMock;
        assertEquals(resultadoEsperado,ronq.reconectar());
        if(!estaConectado){
            if(conectarPresion){
                verify(dispMock, times(1)).conectarSensorSonido();
            }
            verify(dispMock, times(1)).conectarSensorPresion();
        }
        if(estaConectado){
            verify(dispMock, times(0)).conectarSensorPresion();
            verify(dispMock, times(0)).conectarSensorSonido();
        }

    }

    @Test
    void testAnyadirDispositivo(){
        DispositivoSilver dispMock = mock(DispositivoSilver.class);
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.anyadirDispositivo(dispMock);
        assertEquals(dispMock,ronq.disp);
    }

    @Test
    void testEstaConectado(){
        DispositivoSilver dispMock = mock(DispositivoSilver.class);
        when(dispMock.estaConectado()).thenReturn(true);
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.disp = dispMock;
        assertTrue(ronq.estaConectado());
    }

    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     */

    @ParameterizedTest
    @CsvSource({
            "10.0,10.0,false",
            "20.0,10.0,false",
            "20.0,30.0,true",
            "10.0,30.0,false",
            "10.0,40.0,false",
            "40.0,10.0,false",
            "40.0,40.0,true"
    })
    void testEvaluarApneaSuenyo_con1Lectura(float avgP, float avgS, boolean resultadoEsperado) {
        DispositivoSilver dispMock = mock(DispositivoSilver.class);
        when(dispMock.leerSensorPresion()).thenReturn(avgP);
        when(dispMock.leerSensorSonido()).thenReturn(avgS);
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.disp = dispMock;
        ronq.obtenerNuevaLectura();
        assertEquals(resultadoEsperado, ronq.evaluarApneaSuenyo());
    }

    @Test
    void testEvaluarApneaSuenyo_conVariasLecturas() {
        DispositivoSilver dispMock = mock(DispositivoSilver.class);
        when(dispMock.leerSensorPresion()).thenReturn(30.0f);
        when(dispMock.leerSensorSonido()).thenReturn(40.0f);
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.disp = dispMock;
        for(int i=0; i<6; i++){
            ronq.obtenerNuevaLectura();
        }
        verify(dispMock, times(6)).leerSensorPresion();
        assertTrue(ronq.evaluarApneaSuenyo());
    }

     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}
