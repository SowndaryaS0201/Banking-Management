USE banking_system;

-- Insert sample users
INSERT INTO users (name, email, phone, password, status)
VALUES
('Sowndarya', 'sow@gmail.com', '9876543210', 'pass123', 'ACTIVE'),
('Rahul', 'rahul@gmail.com', '9123456780', 'pass456', 'ACTIVE');

-- Insert sample accounts
INSERT INTO accounts (user_id, account_type, balance)
VALUES
(1, 'SAVINGS', 5000),
(1, 'CHECKING', 3000),
(2, 'INVESTMENT', 10000);

ALTER TABLE users ADD pin VARCHAR(10);
ALTER TABLE users ADD role VARCHAR(10);

