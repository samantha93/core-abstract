DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS shoe;

CREATE TABLE stock (
  stock_id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE shoe (
  shoe_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  size INT NOT NULL,
  color VARCHAR(100) DEFAULT NULL,
  stock_id INT NOT NULL,
  FOREIGN KEY (stock_id) REFERENCES stock(stock_id)
);

INSERT INTO stock(stock_id) VALUES(123);
INSERT INTO shoe(shoe_id, name, size, color, stock_id) VALUES(1, 'Nike Running', 42, 'BLACK', 123);
