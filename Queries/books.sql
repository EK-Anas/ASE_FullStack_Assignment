create table books(
  id bigint AUTO_INCREMENT PRIMARY KEY,
  author VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT null,
  isbn VARCHAR(255) NOT null unique,
  category ENUM("FICTION", "NON_FICTION", "RESEARCH") NOT NULL
)

create table ebooks(
id bigint,
  file_url text NOT NULL,
  file_size_mb bigint NOT null,
  FOREIGN KEY (id) REFERENCES books(id)
);


create table printedbooks(
	id bigint,
  shelf_location text NOT NULL,
  copies_available int NOT null,
  FOREIGN KEY (id) REFERENCES books(id)
)