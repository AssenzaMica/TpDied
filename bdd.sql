CREATE SCHEMA died;

CREATE TABLE died.estacion(
    id_estacion SERIAL PRIMARY KEY,
    nombre varchar(20),
    horario_cierre varchar(5),
    horario_apertura varchar(5),
    estado varchar(20)
);

CREATE TABLE died.linea(
    id_linea SERIAL PRIMARY KEY,
    nombre varchar(20),
    color varchar(10),
    estado varchar(20)
);

CREATE TABLE died.ruta(
    id_ruta SERIAL PRIMARY KEY,
    distancia varchar(10),
    duracion varchar(10),
    capacidad varchar(10),
    estado varchar(20),
    costo decimal,
    id_linea integer REFERENCES died.linea(id_linea),
    id_origen integer REFERENCES died.estacion(id_estacion),
    id_destino integer REFERENCES died.estacion(id_estacion)
);

CREATE TABLE died.camino(
    id_camino SERIAL PRIMARY KEY,
    id_origen integer REFERENCES died.estacion(id_estacion),
    id_destino integer REFERENCES died.estacion(id_estacion)	
);

CREATE TABLE died.boleto(
    id_boleto SERIAL PRIMARY KEY,
    nombre varchar(20),
    email varchar(20),
    fecha date,
    costo decimal,
    id_origen integer REFERENCES died.estacion(id_estacion),
    id_destino integer REFERENCES died.estacion(id_estacion),
    id_camino integer REFERENCES died.camino(id_camino)

);

CREATE TABLE died.mantenimiento(
    id_mantenimiento SERIAL PRIMARY KEY,
    inicio varchar(10),
    fin varchar(10),
    observaciones varchar(50),
    id_estacion integer REFERENCES died.estacion(id_estacion)
);

CREATE TABLE died.camino_ruta(
    id_camino integer REFERENCES died.camino (id_camino),
    id_ruta integer REFERENCES died.ruta (id_ruta),
    CONSTRAINT PRIMARY KEY (id_camino,id_ruta)
);

INSERT INTO died.estacion (nombre, horario_cierre, horario_apertura, estado) VALUES ('Est1','6:50','23:30', 'OPERATIVA'), ('Est2','5:30','22:00', 'OPERATIVA'), ('Est3','7:00','23:40', 'OPERATIVA'), ('Est4','5:15','13:30', 'OPERATIVA'), ('Est5','6:20','18:15', 'OPERATIVA'), ('Est6','4:35','22:40', 'OPERATIVA');
INSERT INTO died.linea(nombre, color, estado) VALUES ('Uno', 'rosa', 'ACTIVA'), ('Dos', 'celeste', 'ACTIVA'), ('Tres', 'lila', 'ACTIVA'), ('Cuatro', 'verde', 'ACTIVA');
INSERT INTO died.ruta(id_linea, id_origen, id_destino, distancia, duracion, capacidad, estado, costo) VALUES (1, 1, 2, 30, 40, 5, 'ACTIVA', 300), (1, 2, 3, 50, 60, 15, 'ACTIVA', 600), (2, 1, 5, 20, 10, 3, 'ACTIVA', 300), (3, 1, 6, 10, 5, 6, 'ACTIVA', 100), (3, 6, 5, 15, 7, 12, 'ACTIVA', 150), (4, 5, 4, 40, 30, 4, 'ACTIVA', 200), (4, 4, 3, 60, 40, 4, 'ACTIVA', 300);