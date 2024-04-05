CREATE TABLE `organizer` (
                                                                   `email` varchar(100) NOT NULL,
                                                                   `name` varchar(45) DEFAULT NULL,
                                                                   `phone_number` varchar(20) DEFAULT NULL,
                                                                   `state_id` varchar(45) DEFAULT NULL,
                                                                   `message` varchar(500) DEFAULT NULL,
                                                                   `current_ts` datetime DEFAULT NULL,
                                                                   `status` varchar(20) DEFAULT NULL,
                                                                   `update_ts` datetime DEFAULT NULL,
                                                                   PRIMARY KEY (`email`)
                                      )
