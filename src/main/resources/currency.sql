CREATE TABLE currency (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      code VARCHAR(10) NOT NULL,
      name VARCHAR(100) NOT NULL,
      updateTime TIMESTAMP(0)
);