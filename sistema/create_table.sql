

CREATE TABLE pessoa
(
  id serial primary key,
  nome varchar not null,
  email varchar not null,
  telefone varchar not null
);
CREATE TABLE anuncio
(
id serial primary key,
descricao varchar not null,
valor varchar not null,
id_pessoa integer references pessoa(id) on delete cascade
);
