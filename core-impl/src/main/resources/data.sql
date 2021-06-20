DROP TABLE IF EXISTS shoe;

CREATE TABLE stock (
  stock_id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE shoe (
  shoe_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  size INT NOT NULL,
  color VARCHAR(100) DEFAULT NULL
);

INSERT INTO shoe(shoe_id, name, size, color) VALUES(1, 'Nike Running', 42, 'BLACK');
