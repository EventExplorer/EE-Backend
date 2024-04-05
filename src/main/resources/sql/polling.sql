CREATE TABLE `EventExplorer`.`polling` (
                                           `id` INT NOT NULL AUTO_INCREMENT ,
                                           `category_id` INT NOT NULL,
                                           `artist_name` VARCHAR(255) NOT NULL,
                                           `votes` INT NOT NULL,
                                           `email` VARCHAR(45) NOT NULL,
                                           `current_ts` DATETIME NOT NULL,
                                           `update_ts` DATETIME NOT NULL,
                                           PRIMARY KEY (`id`));
