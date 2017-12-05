Lo mas interesante de aquel proyecto fue como solucioné el problema de dibujar una recta que pasa por delante y por detras del observador y como hacer para romper la tercera pared para poder dibujar objetos en el infinito en perspectiva conica.

Para dibujar una recta que pasa por delante y por detras del observador en perspectiva conica solo tuve que multiplicar la coordenada X y la coordenada Y en los shaders por un numero muy grande, sin llegar a ser infinito, ya que de ser infinito se dibujaria una recta a 45 grados. Es decir, no se mantendria la proporcion que hay entre la X y la Y.

Para romper la tercera pared y poder dibujar objetos con una coordenada Z con cualquier valor, en vez de usar la Z usé la arcotangente de Z dividido entre 2*PI, ya que arctan(Z)/(2*PI) es una funcion que crece desde menos infinito hasta infinito sin alcanzar nunca valores inferiores a -1 o superiores a 1.
