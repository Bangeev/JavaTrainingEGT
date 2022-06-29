create
database if not exists recipe_api_jdbi;
use
recipe_api_jdbi;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`               bigint       NOT NULL AUTO_INCREMENT,
    `name`             varchar(255) NOT NULL,
    `username`         varchar(15)  NOT NULL,
    `password`         varchar(255) NOT NULL,
    `gender`           varchar(255) DEFAULT NULL,
    `role`             varchar(255) NOT NULL,
    `url`              varchar(255) DEFAULT NULL,
    `shortDescription` varchar(512) DEFAULT NULL,
    `status`           varchar(255) NOT NULL,
    `created`          DATETIME NULL DEFAULT NOW(),
    `modified`         DATETIME NULL DEFAULT NOW(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `UC_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `recipe`;

CREATE TABLE `recipe`
(
    `id`                 bigint        NOT NULL AUTO_INCREMENT,
    `userId`             bigint        NOT NULL,
    `name`               varchar(80)   NOT NULL,
    `shortDescription`   varchar(256)  NOT NULL,
    `cookingTimeMinutes` int           NOT NULL,
    `products`           varchar(255)  NOT NULL,
    `image`              varchar(255)  NOT NULL,
    `longDescription`    varchar(2048) NOT NULL,
    `tags`               varchar(255) DEFAULT NULL,
    `created`            DATETIME NULL DEFAULT NOW(),
    `modified`           DATETIME NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Georgi', 'georgi', 'Georgi123#', 'MALE', 'ADMIN', 'http://www.example.com/', 'this is short description',
        'ACTIVE', now(), now());

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Ivan', 'ivan', 'Ivan123#', 'MALE', 'ADMIN', 'http://www.example.com/', 'this is short description', 'ACTIVE',
        now(), now());

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Pesho', 'pesho', 'Pesho123#', 'MALE', 'USER', 'http://www.example.com/', 'this is short description', 'ACTIVE',
        now(), now());

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Stefi', 'stefi', 'Stefi123#', 'MALE', 'ADMIN', 'http://www.example.com/', 'this is short description',
        'ACTIVE', now(), now());

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Petko', 'petko', 'Petko123#', 'MALE', 'ADMIN', 'http://www.example.com/', 'this is short description',
        'ACTIVE', now(), now());

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Bradpit', 'bradpit', 'Bradpit123#', 'MALE', 'USER', 'http://www.example.com/', 'this is short description',
        'ACTIVE', now(), now());

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Kiril', 'kiril', 'Kiril123#', 'MALE', 'ADMIN', 'http://www.example.com/', 'this is short description',
        'ACTIVE', now(), now());

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Valio', 'valio', 'Valio123#', 'MALE', 'ADMIN', 'http://www.example.com/', 'this is short description',
        'ACTIVE', now(), now());

INSERT INTO user (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`,
                  `modified`)
VALUES ('Sasho', 'sasho', 'Sasho123#', 'MALE', 'USER', 'http://www.example.com/', 'this is short description', 'ACTIVE',
        now(), now());



INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('1', 'This is my recipe', 'this is short description', 30, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'tell,heart,develop', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('1', 'Creamy White Chili', 'this is short description', 30, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'blew,driver,liquid,hit,rocky,dry', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('2', 'Best Ever Banana Bread', 'this is short description', 40, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'science,draw,most,pile,start,religious', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('3', 'Cheeseburger Soup', 'this is short description', 30, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'if,dug,joined,mood,constantly,what', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('3', 'Amish Breakfast Casserole', 'this is short description', 30, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'if,dug,joined,mood,constantly,what', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('4', 'Favorite Chicken Potpie', 'this is short description', 30, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'chain,mainly,or,he,claws,sort', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('4', 'Flavorful Chicken Fajitas', 'this is short description', 30, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'chain,mainly,or,he,claws,sort', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('5', 'Apple Pie', 'this is short description', 30, 'krastavici, domati, chesun, luk', 'http://www.example.com/',
        'this is LONG description', 'breakfast,dinner,onlinetools,everybody,sing,social', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('6', 'Enchilada Casser-Ole!', 'this is short description', 30, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'sing,social', now(), now());

INSERT INTO recipe (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`, `products`, `image`, `longDescription`,
                    `tags`, `created`, `modified`)
VALUES ('7', 'Zucchini Pizza Casserole', 'this is short description', 30, 'krastavici, domati, chesun, luk',
        'http://www.example.com/', 'this is LONG description', 'blew,driver,liquid,hit,rocky,dry', now(), now());
