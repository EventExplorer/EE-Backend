CREATE TABLE `EventExplorer`.`transaction_details` (
                                                       `order_number` VARCHAR(50) NOT NULL,
                                                       `transaction_id` VARCHAR(50) NOT NULL,
                                                       `transaction_ts` DATETIME NOT NULL,
                                                       `bank_name` VARCHAR(50) NOT NULL,
                                                       `billing_uuid` VARCHAR(70) NOT NULL,
                                                       `total_price` DECIMAL(10,2) NOT NULL,
                                                       `status` VARCHAR(50) NULL,
                                                       PRIMARY KEY (`order_number`));

