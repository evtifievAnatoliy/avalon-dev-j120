CREATE TABLE ITEMS (
    article varchar(100) not null primary key,
    name varchar(150) not null,
    color varchar (50),
    price int not null,
    stock_balance int not null
);

CREATE TABLE ORDERS(
    order_number varchar(100) not null primary key,
    date_the_order_was_greated date not null,
    contact_person int not null references PERSONS (person_id),
    disconte int not null,
    status_of_order varchar(25) not null
);

CREATE TABLE ORDER_POSITIONS(
    order_positions_id int not null primary key,
    order_id varchar(100) not null references ORDERS (order_number),
    item_id varchar(100) not null references ITEMS (article),
    number_of_items int not null,
    amount_OF_items int not null,
    disconte int not null
);

CREATE TABLE ADDRESSES(
    address_id int not null primary key,
    contry varchar(100) not null,
    region varchar(150) not null,
    street varchar(100),
    house varchar(20) not null,
    flat varchar(20)
);

CREATE TABLE PERSONS(
    person_id int not null primary key,
    person_name varchar(100) not null,
    address_to_delivery int not null references ADDRESSES (address_id)
);

insert INTO ITEMS (ARTICLE, NAME, COLOR, PRICE, STOCK_BALANCE) VALUES ('1', '1', '1', 1800, 1);

SELECT * FROM ITEMS;  

DELETE FROM ITEMS i
    WHERE i.ARTICLE = '1';

UPDATE ITEMS SET NAME = 'Стол обеденный', COLOR = 'орех', PRICE = 15000, STOCK_BALANCE = 1  WHERE ARTICLE = '32516028337712';

ALTER TABLE ORDERS ADD COLUMN contact_person int references PERSONS (person_id);

