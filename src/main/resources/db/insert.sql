SET FOREIGN_KEY_CHECKS = 0;

truncate table pet;
truncate table store;

INSERT into store(`id`, `name`, `location`, `contact_no`)
VALUES (21, 'super store', 'jos', '07034515037'),
(22, 'super store2', 'Abuja', '08157646321'),
(23, 'super store3', 'Lagos', '07034500212'),
(24, 'super store4', 'Enugu', '07098730926');


INSERT into pet(`id`, `name`, `breed`, `color`, `age`, `pet_sex`, `store_id`)
VALUES (21, 'jill', 'parrot', 'green', 2, 'MALE', 21),
(22, 'james', 'hedgehog', 'brown', 4, 'MALE', 21),
(24, 'prissy', 'billygoat', 'black', 1, 'FEMALE', 21),
(25, 'jafar', 'tiger', 'brown', 6, 'MALE', 21),
(27, 'salazar', 'python', 'white', 5, 'FEMALE', 21),
(28, 'sheba', 'cat', 'silver', 3, 'FEMALE', 21);

SET FOREIGN_KEY_CHECKS = 1;