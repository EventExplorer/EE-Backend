
CREATE TABLE `EventExplorer`.category (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL
);

INSERT INTO EventExplorer.category (id, name) VALUES (1, 'Sports');
INSERT INTO EventExplorer.category (id, name) VALUES (2, 'Arts');
INSERT INTO EventExplorer.category (id, name) VALUES (3, 'Music');
INSERT INTO EventExplorer.category (id, name) VALUES (4, 'Concerts');
