DELETE
FROM order_item;
DELETE
FROM `order`;
DELETE
FROM order_items;

INSERT INTO `order`
VALUES (1, 'address1', 'one@gmail.com'),
       (2, 'address2', 'two@gmail.com'),
       (3, 'address3', 'three@gmail.com');

INSERT INTO `order_item`
VALUES (1, 1, 25.5, 5),
       (2, 1, 70.0, 2),
       (3, 3, 15, 1),
       (4, 3, 15, 1);

INSERT INTO `order_items`
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (3, 4);