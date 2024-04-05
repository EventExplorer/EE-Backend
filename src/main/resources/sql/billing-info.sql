CREATE TABLE `EventExplorer`.`billing-info` (
                                `email` varchar(50) NOT NULL,
                                `card_holder_name` varchar(50) NOT NULL,
                                `card_number` varchar(12) NOT NULL,
                                `cvv` int NOT NULL,
                                `expiry_date` int NOT NULL,
                                `address` varchar(50) NOT NULL,
                                `city` varchar(50) NOT NULL,
                                `state` varchar(50) NOT NULL,
                                `phone_number` varchar(10) NOT NULL,
                                `zipcode` int NOT NULL,
                                `bank_name` varchar(50) NOT NULL,
                                `billing_uuid` varchar(70) NOT NULL,
                                PRIMARY KEY (`billing_uuid`)
)