Unas plantillas de codigo para programar graficos en canvas que podrian servir en cualquier lugar que programe gráficos.

1 - Creo un bufer donde realmente dibujo

2 - La función 'actualizar' dibuja lo que esta en el buffer a la pantalla

3 - La función 'redibujar' ejecuta todas las funciones de un array para dibujar en el búffer pasandole a cada funcion por parametro el contexto. De esta manera, si quiero dibujar un elemento más solo tiengo que añadir la función que lo dibuja al final del array y llamar al metodo redibujar. Asi solo tengo que preocuparme de dibujar lo que quiero sin tener que preocuparme de dibujar otra vez todo lo demas.

4 - La función 'limpiarBuffer' usa fillRect en vez de clearRect para pintar todo de blanco para que cuando actualize la pantalla no se vean las cosas que no se han redibujado.

5 - Cada vez que modifico el estado del contexto para dibujar, primero lo guardo y cuando termino lo restauro.
