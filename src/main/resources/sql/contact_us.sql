CREATE TABLE `EventExplorer`.`contact_us` (
                                              `id` INT AUTO_INCREMENT,
                                              `name` VARCHAR(50) NOT NULL,
                                              `phone_number` CHAR(10) NOT NULL,
                                              `message` VARCHAR(500) NOT NULL,
                                              `email` VARCHAR(45) NOT NULL,
                                              PRIMARY KEY (`id`));