## Pactica 3.
##ANDROID-GYMKANA
### Aplicacion Punto Movimiento Sonido.
### Autores
* Samuel Peregrina Morillas
* Nieves Victoria Velásquez Díaz

### Duración de la práctica.
Desde 15-Ene-2016 hasta 15-Feb-2016

### Breve descripción de la practica.
Para la realizacion de esta práctica, se programarán cinco aplicaciones android diferentes de forma que, cada una hace uso de los distintos sensores que posee el dispositivo android.
### Descripción del problema.
Esta aplicación consiste en, una vez se ha realizado un patrón específico, el dispositivo lo reconocerá y se reproducirá un sonido determinado. En este caso el patrón de movimiento consiste en mover el dispositivo rápidamente en el eje X, como si de un látigo se tratase.
###Clases.
En este caso, solo ha sido necesario usar una clase **PtoMovSonido**.
En esta clase, se crea la actividad en su totalidad, desde esperar los cambios del acelerometro hasta inicializar el reproductor para que se escuche el sonido. Tambien se implementa un **Toast** que aparece en caso de que el volumen del dispositivo esté demasiado bajo. Esta clase se extiende de **AppCompatActivity** para poder crear la actividad e implementa metodos de **SensorEventListener** con los que poder usar los sensores, en este caso, el acelerómetro.
###Métodos.
Los métodos implementados en la clase **PtoMovSonido** han sido:
* **protected void onCreate(Bundle savedInstanceState)**: este es el método principal de la clase, y es llamado al inicializarse la aplicación. En este método todo lo que hacemos es establcer la vista e inicializamos las variables que vamos a usar, en estes caso son **miSensor** de la clase "Sensor" y **player** de la clase player.

* **public void onSensorChanged(SensorEvent event):** este metodo es llamado cada vez que un sensor manda información, es decir cada vez que el acelerometro detecta un movimiento del dispositivo. En este método se usan las variables **valX,** **valY** y **valZ**, en las que almacenaremos los valores de los respectivos ejes  segun la posicion en la que se encuentra el dispositivo. Tras esto compobamos si el valor del eje X, **valX** se ha modificado, de forma que si el telefono se mueve en el eje X, pero no lo suficiente, entrará en el if. Como solo nos vamos a fijar en el movimiento en X, los valores de **valY** y **valZ** los comparamos con valores my grandes para que, si se mueven en los dos ejes restantes, el movimiento pase desapercibido. Si se cumple la condicion, como el valor de X no es lo suficientemente grande, se toma como "ruido" y se resetean los valores de las tres variables, y si se ha movido más que el valor que establece la condición, entonces llama al metodo **reproducirSonido()** y guardamos los valores de **valX, valY y valZ.**

* **reproducirSonido():**  este metodo se encarga de, en el caso de que se detecte que el volumen del dispositivo esta demasiado bajo, ejecuta un toast en la parte baja de la pantalla indicandolo, y reproduce el sonido mediante la llamada **player.start()** donde player es el objeto de la clase **MediaPlayer** y con start le indicamos que queremos que comience a reproducir el sonido ya establecido. Para que el programa sepa como trabajar con el audio, se han creado dos carpetas: **raw y assets** donde almacenamos el archivo de audio. 

* **protected void onResume():** con este método nos aseguramos que la aplicacion prosiga con su ejecucion al volver del segundo plano o en caso de que se haya suspendido.

* **protected void onPause():**  metodo con el que suspendemos la aplicacion.
### Bibliografía.
* [Introduccion al uso del acelerometro](http://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125)
* [Ejemplo de programa del acelerometro](http://examples.javacodegeeks.com/android/core/hardware/sensor/android-accelerometer-example/)
* [Sonidos gratis](http://soundbible.com/free-sound-effects-1.html)
* [Guia para reproducir sonidos con Android](http://www.jc-mouse.net/android/reproduce-archivos-mp3-desde-android)
