CREATE TABLE `EventExplorer`.Region (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
);

INSERT INTO EventExplorer.Region (id, name) VALUES (1, 'New York');
INSERT INTO EventExplorer.Region (id, name) VALUES (2, 'New Jersey');
INSERT INTO EventExplorer.Region (id, name) VALUES (3, 'Connecticut');
INSERT INTO EventExplorer.Region (id, name) VALUES (4, 'Pennsylvania');
INSERT INTO EventExplorer.Region (id, name) VALUES (5, 'Massachusetts');