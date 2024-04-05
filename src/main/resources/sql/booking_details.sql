CREATE TABLE `EventExplorer`.`booking_details` (
                                                   `event_id` VARCHAR(100) NOT NULL,
                                                   `email_id` VARCHAR(255) NOT NULL,
                                                   `order_number` VARCHAR(50) NOT NULL,
                                                   `seat_number` VARCHAR(45) NOT NULL,
                                                   `attendees` INT NOT NULL,
                                                   `total_price` DECIMAL(10,2) NOT NULL,
                                                   `billing_uuid` VARCHAR(70) NOT NULL,
                                                   `confirmation_code` VARCHAR(50) NOT NULL,
                                                   `create_ts` DATETIME NULL,
                                                   `update_ts` DATETIME NULL,
                                                   PRIMARY KEY (`order_number`));
