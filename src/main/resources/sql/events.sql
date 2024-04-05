
CREATE TABLE EventExplorer.events (
                                      `event_id` varchar(40) NOT NULL,
                                      `event_name` varchar(400) NOT NULL,
                                      `event_type` varchar(50) NOT NULL,
                                      `event_date` date NOT NULL,
                                      `event_time` time NOT NULL,
                                      `venue_name` varchar(200) NOT NULL,
                                      `capacity` int NOT NULL,
                                      `create_ts` datetime NOT NULL,
                                      `update_ts` datetime NOT NULL,
                                      `regular_price` decimal(10,2) NOT NULL,
                                      `city` varchar(50) NOT NULL,
                                      `state` varchar(50) NOT NULL,
                                      `contact_number` char(10) NOT NULL,
                                      `venue_type` varchar(50) NOT NULL,
                                      `description` varchar(10000) NOT NULL,
                                      `event_email_address` varchar(45) NOT NULL,
                                      `customer_id` varchar(40) NOT NULL,
                                      `user_email` varchar(45) NOT NULL,
                                      PRIMARY KEY (`event_id`),
                                      UNIQUE KEY `customer_id_UNIQUE` (`customer_id`)
);



ALTER TABLE `EventExplorer`.`events`
    ADD COLUMN `vip_price` DECIMAL(10,2) NOT NULL ,
    ADD COLUMN `disabled_price`  DECIMAL(10,2) NOT NULL ;
