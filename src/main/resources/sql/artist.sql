CREATE TABLE `EventExplorer`.artist (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
);

ALTER TABLE `EventExplorer`.`artist`
    ADD COLUMN `category_id` INT NOT NULL AFTER `name`;

-- Artists related to Sports Events
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (1, 'LeBron James',1);
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (2, 'Serena Williams',1);
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (3, 'Usain Bolt',1);

-- Artists related to Theater Shows
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (4, 'Lin-Manuel Miranda',2);
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (5, 'Meryl Streep',2);
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (6, 'Hugh Jackman',2);

-- Artists related to Music Events

INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (7, 'Ed Sheeran',3);
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (8, 'Beyoncé',3);
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (9, 'Coldplay',3);

-- Artists related to Concerts

INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (10, 'The Rolling Stones',4);
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (11, 'Justin Timberlake',4);
INSERT INTO EventExplorer.artist (id, name, category_id) VALUES (12, 'Taylor Swift',4);




