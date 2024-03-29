CREATE TABLE EventExplorer.reset_account (
                                             `email` VARCHAR(100) NOT NULL,
                                             `token` VARCHAR(100) NOT NULL,
                                             `expiration_time` DATETIME NOT NULL,
                                             `created_at` DATETIME NOT NULL,
                                             PRIMARY KEY (`email`));
