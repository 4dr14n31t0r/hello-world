Unas plantillas de codigo para programar graficos en canvas que podrian servir en cualquier lugar que programe gráficos.
Estos 2 archivos muestran como dibujar en perspectiva conica usando solamente canvas con el contexto de 2D.

1 - Creo un bufer donde realmente dibujo.

2 - La función 'actualizar' dibuja lo que esta en el buffer a la pantalla.

3 - La función 'redibujar' ejecuta todas las funciones de un array para dibujar en el búffer pasandole a cada funcion por parametro el contexto. De esta manera, si quiero dibujar un elemento más solo tiengo que añadir la función que lo dibuja al final del array y llamar al metodo redibujar. Asi solo tengo que preocuparme de dibujar lo que quiero sin tener que preocuparme de dibujar otra vez todo lo demas.

4 - Cada función de las que se llaman desde el array solo obtienen los parametros y se los pasan a otro metodo con el mismo nombre. Los parametros que se les pasan a los nuevos metodos se obtienen llamando a los metodos getters, los cuales a su vez son los que recogen los datos de los campos de los formularios. De esta manera, se pueden testear esas funciones mas facilmente y se pueden usar tambien para otras cosas.

5 - La función 'limpiarBuffer' usa fillRect en vez de clearRect para pintar todo de blanco para que cuando actualize la pantalla no se vean las cosas que no se han redibujado.

6 - Cada vez que modifico el estado del contexto para dibujar, primero lo guardo y cuando termino lo restauro.
