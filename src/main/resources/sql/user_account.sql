CREATE TABLE EventExplorer.user_account (
                                            user_id VARCHAR(50) PRIMARY KEY,
                                            email VARCHAR(255) NOT NULL UNIQUE,
                                            password VARCHAR(255) NOT NULL,
                                            first_name VARCHAR(50),
                                            last_name VARCHAR(50),
                                            date_of_birth DATE NOT NULL,
                                            gender ENUM('MALE', 'FEMALE', 'OTHER'),
                                            address VARCHAR(255),
                                            city VARCHAR(50),
                                            state VARCHAR(50),
                                            zipcode VARCHAR(5),
                                            phone_number VARCHAR(20),
                                            registration_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                            last_login_date DATETIME,
                                            update_ts DATETIME,
                                            account_status ENUM('ACTIVE', 'INACTIVE', 'PENDING_VERIFICATION') DEFAULT 'INACTIVE'
);
