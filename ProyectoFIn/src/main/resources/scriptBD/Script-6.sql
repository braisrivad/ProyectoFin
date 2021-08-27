CREATE database  juegos;
use juegos;
DROP TABLE IF EXISTS `categorias`;
CREATE TABLE `Categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `perfiles`;
CREATE TABLE `Perfiles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `perfil` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `Usuarios`;
CREATE TABLE `Usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `estatus` int NOT NULL DEFAULT '1',
  `fechaRegistro` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `videojuegos`;
CREATE TABLE `videojuegos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha` date NOT NULL,
  `precio` double NOT NULL,
  `estatus` enum('Creada','Aprobada','Eliminada') NOT NULL,
  `destacado` int NOT NULL,
  `imagen` varchar(250) NOT NULL,
  `detalles` text,
  `idCategoria` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_videojuegos_categorias1_idx` (`idCategoria`),
  CONSTRAINT `fk_videojuegos_categorias1` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `solicitudes`;
CREATE TABLE `solicitudes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `archivo` varchar(250) NOT NULL,
  `comentarios` text,
  `idVideojuegos` int NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Videojuegos_Usuario_UNIQUE` (`idVideojuegos`,`idUsuario`),
  KEY `fk_Solicitudes_Videojuegos1_idx` (`idVideojuegos`),
  KEY `fk_Solicitudes_Usuarios1_idx` (`idUsuario`),
  CONSTRAINT `fk_Solicitudes_Usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_Solicitudes_Videojuegos1` FOREIGN KEY (`idVideojuegos`) REFERENCES `videojuegos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `usuarioPerfil`;
CREATE TABLE `usuarioPerfil` (
  `idUsuario` int NOT NULL,
  `idPerfil` int NOT NULL,
  UNIQUE KEY `Usuario_Perfil_UNIQUE` (`idUsuario`,`idPerfil`),
  KEY `fk_Usuarios1_idx` (`idUsuario`),
  KEY `fk_Perfiles1_idx` (`idPerfil`),
  CONSTRAINT `fk_Usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_Perfiles1` FOREIGN KEY (`idPerfil`) REFERENCES `perfiles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla de usuarios
CREATE TABLE `users` (
	`username` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	`enabled` tinyint(1) NOT NULL,
	PRIMARY KEY (`username`)
) ENGINE=InnoDB;

-- Crear tabla de roles
CREATE TABLE `authorities` (
	`username` varchar(50) NOT NULL,
	`authority` varchar(50) NOT NULL,
	UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
	CONSTRAINT `authorities_ibfk_1`
	FOREIGN KEY (`username`)
	REFERENCES `users` (`username`)
) ENGINE=InnoDB;

CREATE TABLE `comentarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `nombreVideojuegos` varchar(200),
  `descripcion` text NOT NULL,
  `idVideojuegos` int NOT null,
  `idUsuario` int NOT null,
  `fechaCreacion` varchar(45),

  PRIMARY KEY (`id`),
  
  CONSTRAINT `fk_Comentarios_Usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`) on delete cascade,
  CONSTRAINT `fk_Comentarios_Videojuegos1` FOREIGN KEY (`idVideojuegos`) REFERENCES `videojuegos` (`id`) on delete cascade 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `respuesta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `respuestaDescripcion` text NOT NULL,
  `idComentarios` int NOT null,
  `idVideojuegos` int NOT null,
  `idUsuario` int NOT null,
  `fechaCreacion` varchar(45) ,

  PRIMARY KEY (`id`),
  
  CONSTRAINT `fk_Respuesta_Usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`) on delete cascade,
  CONSTRAINT `fk_Respuesta_Comentarios2` FOREIGN KEY (`idComentarios`) REFERENCES `comentarios` (`id`) on delete cascade ,
  CONSTRAINT `fk_Respuesta_Videojuegos1` FOREIGN KEY (`idVideojuegos`) REFERENCES `videojuegos` (`id`) on delete cascade 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- Insertamos nuestros usuarios
INSERT INTO `users` VALUES ('supervisor','$2a$10$7sn5rckjcGoaA7juu00UOOGK6NRbEtT.CInhDoxgLyP1JG5640kRa',1);
INSERT INTO `users` VALUES ('admin','$2a$10$uADwB6I6K1UJ1N139tZJo.x0Fi.iSRMkPIEz67LtZ7Jnj1ikv.7De',1);

-- Insertamos (asignamos roles) a nuestros usuarios.
INSERT INTO `authorities` VALUES ('supervisor','SUPERVISOR');
INSERT INTO `authorities` VALUES ('admin','ADMINISTRADOR');

INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (1,'Accion','Un videojuego de acción es un videojuego en el que el jugador debe usar su velocidad, destreza y tiempo de reacción.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (2,'Indie','Los videojuegos independientes, popularmente clasificados como Indies, son videojuegos desarrollados por grupos reducidos de individuos o pequeñas empresas. No suelen contar con el apoyo financiero de distribuidores y publicistas, sino que su difusión depende principalmente de la voluntad de los jugadores para compartirlos con otros.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (3,'Aventuras','Los videojuegos de aventura son un género de videojuegos, caracterizados por la investigación, exploración, la solución de rompecabezas, la interacción con personajes del videojuego, y un enfoque en el relato en vez de desafíos basados en reflejos.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (4,'RPG','Los RPG son un género de videojuegos denominados juegos de rol, se caracterizan porque un jugador controla un personaje en un mundo totalmente recreado donde transcurre una historia y hay que ir completando misiones hasta alcanzar el objetivo final.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (5,'Estrategia','Los videojuegos de estrategia son videojuegos que requieren que el jugador ponga en práctica sus habilidades de planeamiento y pensamiento para maniobrar, gestionando recursos de diverso tipo (materiales, humanos, militares...) para conseguir la victoria. En la mayoría de los videojuegos de estrategia "al jugador se le concede una vista del mundo absoluta, controlando indirectamente las unidades bajo su poder".');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (6,'Simulacion','Los videojuegos de simulación reproducen sensaciones que en realidad no están sucediendo. Pretenden reproducir tanto las sensaciones físicas (velocidad, aceleración, percepción del entorno) y una de sus funciones es dar una experiencia real de algo que no está sucediendo para de esta forma no poner en riesgo la vida de alguien.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (7,'Shooters','Los videojuegos de disparos, tiros o shooters conforman un género que engloba un amplio número de subgéneros que tienen la característica común de permitir controlar un personaje que, por norma general, dispone de un arma (mayoritariamente de fuego) que puede ser disparada a voluntad.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (8,'Carreras','Un videojuego de carreras es un videojuego en el que se imitan competencias entre vehículos. Usualmente el objetivo es recorrer cierta distancia o ir de un sitio hacia otro en el menor tiempo posible, como en el automovilismo y el motociclismo (los que generalmente son imitados).');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (9,'Deporte','Un videojuego de deportes es un videojuego de consola o de computadora que simula el campo de deportes tradicionales. Estos videojuegos son sumamente populares, el género incluye algunos de los videojuegos con más éxito de venta. Casi todos los deportes conocidos han sido recreados con un videojuego, incluyendo béisbol, fútbol, fútbol americano, boxeo, lucha libre, críquet, golf, básquetbol, hockey sobre hielo, bolos, rugby, caza, pesca, skate, artes marciales mixtas, tabla sobre nieve, etc.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (10,'Rompecabezas','Los videojuegos de lógica, también conocidos como videojuegos de inteligencia o videojuegos de puzle, son un género de videojuegos que se caracteriza por exigir agilidad mental al jugador. Pueden involucrar problemas de lógica, matemáticas, estrategia, reconocimiento de patrones, completar palabras o hasta simple azar.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (11,'Plataforma','Los videojuegos de plataformas, o simplemente plataformas, son un género de videojuegos que se caracterizan por tener que caminar, correr, saltar o escalar sobre una serie de plataformas y acantilados, con enemigos, mientras se recogen objetos para poder completar el juego.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (12,'Point & Click','Un género que nunca ha dejado de sorprendernos ya sea por sus ingeniosos giros de guion, sus grandes dosis de humor o sus extravagantes y carismáticos personajes.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (13,'Lucha','Un videojuego de lucha, pelea o combate, es un videojuego que se basa en manejar un luchador o un grupo de luchadores, ya sea dando golpes, usando poderes mágicos o armas (incluyendo las de fuego), arrojando objetos o aplicando llaves.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (14,'Arcade','Arcade es el término genérico de las máquinas recreativas de videojuegos disponibles en lugares públicos de diversión, centros comerciales, restaurantes, bares, o salones recreativos especializados. Son similares a los pinballs y a las tragamonedas de los casinos, pero debido a que no son juegos de azar ni de apuestas —ya que se basan en la destreza del jugador— por lo general no tienen las limitaciones legales de estas.');
INSERT INTO `categorias` (`id`,`nombre`,`descripcion`) VALUES (15,'MMO','	Un videojuego multijugador masivo en línea o MMOG (siglas en inglés de massively multiplayer online game) es un videojuego en donde pueden participar, e interactuar en un mundo virtual, un gran número de jugadores del orden de cientos o miles simultáneamente (multijugador) conectados a través de la red (en línea), normalmente Internet dado el grado de concurrencia que pueden llegar a alcanzar y las características técnicas de los servidores que han de gestionar ese volumen de conexiones.');


INSERT INTO `videojuegos` (`id`,`nombre`,`descripcion`,`fecha`,`precio`,`estatus`,`destacado`,`imagen`,`detalles`,`idCategoria`) VALUES (1,'Grand Theft Auto V','Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games.','2013-09-17',10,'Released',1,'GTAV-PC.jpg',' <h1>Grand Theft Auto V</h1>
    <p>Grand Theft Auto V, comúnmente abreviado como GTA V, es la decimoquinta entrega de la saga Grand Theft Auto que fue lanzado el 17 de septiembre de 2013 para las consolas Xbox 360 y PlayStation 3. Su juego predecesor es Grand Theft Auto: The Ballad of Gay Tony, lanzado para las mismas plataformas. Es la segunda entrega de la saga para consolas de "última generación", con mejoras gráficas muy notables, un mundo Sandbox más grande y realista, motor físico anteriormente utilizado para GTA IV. Superó en una semana y media los mil millones de dólares acumulados, también nombrado el juego del año en primer lugar.</p><br>
    <p>Tras debutar en la séptima generación de consolas en 2013, se re-estrenó un año después en la siguiente generación con algunas mejoras. Además, el 11 de junio de 2020 Sony confirmó que también formará parte del catálogo de PlayStation 5 en 2021.</p><br>
    <p>La historia transcurre en la remodelada ciudad de Los Santos, donde Michael, Trevor y Franklin trabajan individualmente hasta que se cruzan sus vidas y empiezan a trabajar en equipo robando bancos o realizando trabajos para diversos personajes.</p><br>',1);

   
