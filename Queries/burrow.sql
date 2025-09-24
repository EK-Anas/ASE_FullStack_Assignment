create table borrowrecord(
  id bigint AUTO_INCREMENT PRIMARY KEY,
  borrow_date date NOT NULL,
  return_date date null,
  user_id bigint,
  book_id bigint,  
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (book_id) REFERENCES books(id)  
)