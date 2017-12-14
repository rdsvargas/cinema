CREATE DATABASE lp2_crudcinema
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;

CREATE TABLE filme
(
  filme_id serial NOT NULL,
  filme_nome character varying(50),
  filme_genero character varying(70),
  filme_sinopsia character varying(200),
  primary key (filme_id)
);
 
CREATE TABLE sala
(
  sala_id serial NOT NULL,
  sala_codigo character varying(10) NOT NULL,
  sala_qtd_assentos integer NOT NULL,
  primary key (sala_id)
);

CREATE TABLE sessao
(
  sessao_id serial NOT NULL,
  sessao_hora character varying(5) NOT NULL,
  filme_id integer NOT NULL,
  sala_id integer NOT NULL,
  primary key (sessao_id ),
  foreign key (filme_id)
    references filme (filme_id) match SIMPLE
    on update no ACTION on delete no ACTION,
  foreign key (sala_id)
    references sala (sala_id) match SIMPLE
    on update no ACTION on delete no ACTION
)

CREATE TABLE ingresso
(
  ingresso_id serial NOT NULL,
  ingresso_qtd integer not null,
  sessao_id integer NOT NULL,
  sala_id integer NOT NULL,
  primary key (ingresso_id),
  foreign key (sessao_id)
    references sessao (sessao_id) match SIMPLE
    on update no ACTION on delete no ACTION,
  foreign key (sala_id)
    references sala (sala_id) match SIMPLE
    on update no ACTION on delete no ACTION
);