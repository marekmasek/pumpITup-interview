INSERT INTO categories (id, name)
VALUES
    (1, 'T-shirts'),
    (2, 'Pants');

INSERT INTO items (id, name, category_id)
VALUES
    (1, 'Basic T-shirt', 1),
    (2, 'Long sleeve T-shirt', 1),
    (3, 'Pants', 2),
    (4, 'Joggers', 2);

INSERT INTO item_types (id, item_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 2),
    (6, 2),
    (7, 3),
    (8, 3),
    (9, 3),
    (10, 4);

INSERT INTO configurations (item_type_id, name, config_value)
VALUES
    (1, 'color', 'Black'),
    (1, 'size', 'M'),
    (2, 'color', 'Red'),
    (2, 'size', 'L'),
    (3, 'color', 'Red'),
    (3, 'size', 'XL'),
    (4, 'color', 'Blue'),
    (4, 'size', 'XL'),
    (5, 'size', 'M'),
    (6, 'size', 'L'),
    (7, 'type', 'Straight'),
    (7, 'size', '30'),
    (8, 'type', 'Straight'),
    (8, 'size', '32'),
    (9, 'type', 'Slim'),
    (9, 'size', '32'),
    (10, 'color', 'grey'),
    (10, 'size', 'S');