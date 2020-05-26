drop database if exists consumermanagement;
create database if not exists consumerManagement;

use consumerManagement;

create table if not exists user(
                                   username varchar(20) primary key,
                                   password varchar(36) not null,
                                   first_name varchar(20),
                                   last_name varchar(20) not null,
                                   phone_number varchar(20) not null,
                                   gender varchar(10) not null,
                                   email varchar(50) not null
);

insert into user(username, password, first_name, last_name, phone_number, gender, email)
values ('tdnguyen.uet', '12345678', 'Triệu Đình', 'Nguyện', '0971219563', 'male', 'tdnguyen.uet@gmail.com'),
       ('user1', 'password1', 'firstName1', 'lastName1', '+65938129399', 'female', 'user1@email.com'),
       ('user2', 'password2', 'firstName2', 'lastName2', '+65938121112', 'male', 'user2@email.com'),
       ('user3', 'password3', 'firstName3', 'lastName3', '913873123', 'male', 'user3@email.com'),
       ('user4', 'password4', 'firstName4', 'lastName4', '91387321323', 'female', 'user4@email.com');

create table if not exists fund(
                                   fund_id int primary key auto_increment,
                                   name varchar(50) not null,
                                   owner varchar(20) not null,
                                   date_of_creation date not null,
                                   balance double not null
);

insert into fund(name, owner, date_of_creation, balance)
values ('p501', 'tdnguyen.uet', '2005-2-28', 1000000),
       ('abcd', 'tdnguyen.uet', '2004-3-1', -1),
       ('p501a', 'user1', '2005-2-7', 1232.31),
       ('p502', 'user2', '2004-12-31', 0);

create table if not exists transaction(
                                          transaction_id int primary key auto_increment,
                                          fund_id int not null,
                                          name varchar(20) not null,
                                          type varchar(10) not null,
                                          actor varchar(20) not null,
                                          amount_of_money double not null,
                                          note varchar(1000),
                                          date_of_creation date not null
);

insert into transaction(fund_id, name, type, actor, amount_of_money, note, date_of_creation)
values (1, 'buy something', 'reduce', 'tdnguyen.uet', 10, 'buy y * x', '2005-3-3'),
       (1, 'buy x', 'reduce', 'user3', 10, 'buy x * y', '2005-4-1'),
       (1, 'nộp tiền', 'increase', 'user3', 1000, 'nộp tiền hàng tháng', '2005-4-1'),
       (2, 'borrow', 'increase', 'user1', 200, 'borrow to travel', '2005-8-9'),
       (3, 'donate', 'reduce', 'user2', 15.2, null, '2020-12-9');

create table if not exists user_fund(
                                        username varchar(20) not null,
                                        fund_id int not null,
                                        date_of_participant date not null,
                                        primary key(username, fund_id)
);

insert into user_fund(username, fund_id, date_of_participant)
values ('tdnguyen.uet', 1, '2005-2-28'),
       ('tdnguyen.uet', 2, '2004-3-1'),
       ('user1', 1, '2005-3-31'),
       ('user1', 3, '2005-2-7'),
       ('user2', 4, '2004-12-31'),
       ('user3', 1, '2005-4-1'),
       ('user3', 2, '2004-3-2');