create database evara_shop;

use evara_shop;

-- Tạo bảng Roles (Vai trò)
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role ENUM('customer', 'admin', 'staff') DEFAULT 'customer',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 2. EMPLOYEES
CREATE TABLE employees (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  position VARCHAR(50),
  salary DECIMAL(10, 2),
  hire_date DATE,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 3. SUPPLIERS
CREATE TABLE suppliers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  phone VARCHAR(20),
  address TEXT
);

-- 4. PRODUCTS
CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  description TEXT,
  price DECIMAL(10, 2) NOT NULL,
  old_price DECIMAL(10, 2),
  stock INT DEFAULT 0,
  image VARCHAR(255),
  image_hover VARCHAR(255),
  supplier_id INT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

-- 5. PRODUCT REVIEWS
CREATE TABLE product_reviews (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT NOT NULL,
  user_id INT NOT NULL,
  rating INT CHECK (rating BETWEEN 1 AND 5),
  comment TEXT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (product_id) REFERENCES products(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 6. CARTS
CREATE TABLE carts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 7. CART ITEMS
CREATE TABLE cart_items (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cart_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT DEFAULT 1,
  FOREIGN KEY (cart_id) REFERENCES carts(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 8. ORDERS
CREATE TABLE orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  total_amount DECIMAL(10,2),
  status ENUM('pending', 'processing', 'completed', 'cancelled') DEFAULT 'pending',
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 9. ORDER ITEMS
CREATE TABLE order_items (
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 10. WISHLISTS
CREATE TABLE wishlists (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  product_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 11. COMPARE LISTS
CREATE TABLE compares (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  product_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO users (name, email, password, role) VALUES
('John Doe', 'john.doe@email.com', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'customer'),
('Jane Smith', 'jane.smith@email.com', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'admin'),
('Mike Brown', 'mike.brown@email.com', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'staff'),
('Sarah Wilson', 'sarah.wilson@email.com', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'customer'),
('Emma Davis', 'emma.davis@email.com', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'customer');

update users set password = "$2a$12$gFpukJgS87eYXAVr3UuUju/JN5CLlP/CY2kuzlyHMdBk.uuG3/e.S" where id = 2;
select * from users;
-- Insert data into employees table
INSERT INTO employees (user_id, position, salary, hire_date) VALUES
(2, 'Manager', 5000.00, '2024-01-15'),
(3, 'Sales Associate', 2500.00, '2024-03-01');

-- Insert data into suppliers table
INSERT INTO suppliers (name, email, phone, address) VALUES
('Trendy Threads', 'contact@trendythreads.com', '555-0101', '123 Fashion St, New York, NY'),
('Style Source', 'info@stylesource.com', '555-0102', '456 Vogue Ave, Los Angeles, CA'),
('Chic Supplies', 'sales@chicsupplies.com', '555-0103', '789 Glam Rd, Miami, FL');

-- Insert data into products table
INSERT INTO products (name, description, price, old_price, stock, image, image_hover, supplier_id) VALUES
('Slim Fit Jeans', 'Comfortable denim jeans with a modern slim fit.', 59.99, 79.99, 100, '/images/jeans1.jpg', '/images/jeans1-hover.jpg', 1),
('Floral Dress', 'Elegant floral dress perfect for summer outings.', 79.99, NULL, 50, '/images/dress1.jpg', '/images/dress1-hover.jpg', 2),
('Leather Jacket', 'Premium leather jacket with a classic design.', 199.99, 249.99, 30, '/images/jacket1.jpg', '/images/jacket1-hover.jpg', 3),
('Casual T-Shirt', 'Soft cotton t-shirt in various colors.', 19.99, NULL, 200, '/images/tshirt1.jpg', '/images/tshirt1-hover.jpg', 1),
('Sneakers', 'Stylish and comfortable sneakers for everyday wear.', 89.99, 99.99, 80, '/images/sneakers1.jpg', '/images/sneakers1-hover.jpg', 2);

select * from products;
-- Insert data into product_reviews table
INSERT INTO product_reviews (product_id, user_id, rating, comment) VALUES
(1, 1, 4, 'Great jeans, very comfortable but slightly tight.'),
(2, 4, 5, 'Love this dress! Perfect for summer.'),
(3, 5, 3, 'Nice jacket but a bit expensive.'),
(4, 1, 5, 'Super comfy t-shirt, bought multiple colors!');

-- Insert data into carts table
INSERT INTO carts (user_id) VALUES
(1),
(4),
(5);

-- Insert data into cart_items table
INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 4, 3),
(2, 2, 1),
(3, 5, 1);

-- Insert data into orders table
INSERT INTO orders (user_id, total_amount, status) VALUES
(1, 139.97, 'completed'),
(4, 79.99, 'processing'),
(5, 199.99, 'pending');

select * from orders;
delete from orders where id = 5;
-- Insert data into order_items table
INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 59.99),
(1, 4, 1, 19.99),
(2, 2, 1, 79.99),
(3, 3, 1, 199.99);

-- Insert data into wishlists table
INSERT INTO wishlists (user_id, product_id) VALUES
(1, 3),
(4, 5),
(5, 1);

-- Insert data into compares table
INSERT INTO compares (user_id, product_id) VALUES
(1, 1),
(1, 5),
(4, 2),
(4, 3);
    