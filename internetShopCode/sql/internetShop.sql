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
    contact_person varchar(150) not null,
    disconte int not null,
    status_of_order varchar(25) not null
);

CREATE TABLE ORDER_POSITIONS(
    id int not null primary key,
    order_id varchar(100) not null references ORDERS,
    item_id varchar(100) not null references ITEMS,
    number_of_items int not null,
    amount_OF_items int not null,
    disconte int not null
)