CREATE TABLE contract (
  contract_id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INT NOT NULL,
  file_id INT NOT NULL,
  customer_label VARCHAR(250) NOT NULL,
  contract_type ENUM ('A', 'B', 'C', 'D', 'E') NOT NULL,
  contract_status ENUM('NEW', 'CREATED', 'APPROVED', 'DENIED') NOT NULL,
  creation_date DATE NOT NULL,
  expiration_date DATE NOT NULL
);
