CREATE TABLE EventExplorer.account_verification (
                                                        `email` VARCHAR(100) NOT NULL,
                                                        `otp_passcode` VARCHAR(6) NOT NULL,
                                                        `expiration_time` DATETIME NOT NULL,
                                                        `created_at` DATETIME NOT NULL,
                                                        PRIMARY KEY (`email`));
