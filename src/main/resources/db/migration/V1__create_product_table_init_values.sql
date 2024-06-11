CREATE SEQUENCE products_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE products (
                          id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('products_seq'),
                          name VARCHAR(255) NOT NULL,
                          price NUMERIC(10,2) NOT NULL,
                          category VARCHAR(255) NOT NULL,
                          description TEXT NOT NULL,
                          deleted TIMESTAMP(6),
                          created_at TIMESTAMP WITHOUT TIME ZONE,
                          updated_at TIMESTAMP WITHOUT TIME ZONE,
                          CONSTRAINT category_check CHECK (category IN ('ELECTRONICS', 'FASHION', 'HOME', 'TOYS_AND_GAMES', 'BOOKS', 'BEAUTY', 'SPORT'))
);


INSERT INTO products (name, price, category, description) VALUES
                                                              ('Smartphone', 699.99, 'ELECTRONICS', 'Latest model smartphone with advanced features.'),
                                                              ('Jeans', 49.99, 'FASHION', 'Comfortable and stylish jeans.'),
                                                              ('Blender', 29.99, 'HOME', 'High-performance blender for smoothies and more.'),
                                                              ('Board Game', 19.99, 'TOYS_AND_GAMES', 'Fun board game for all ages.'),
                                                              ('Novel', 14.99, 'BOOKS', 'Best-selling novel by a famous author.'),
                                                              ('Lipstick', 9.99, 'BEAUTY', 'Long-lasting and vibrant lipstick.'),
                                                              ('Soccer Ball', 24.99, 'SPORT', 'Durable soccer ball for professional games.'),
                                                              ('Laptop', 999.99, 'ELECTRONICS', 'High-performance laptop with great battery life.'),
                                                              ('T-shirt', 19.99, 'FASHION', 'Casual and comfortable t-shirt.'),
                                                              ('Vacuum Cleaner', 89.99, 'HOME', 'Powerful vacuum cleaner with multiple attachments.');
