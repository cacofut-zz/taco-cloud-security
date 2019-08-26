
/**
 * Author:  cristianoca
 * Created: 09/08/2019
 */


create table ingredient (
    id varchar(255) not null, 
    name varchar(255), 
    type integer, 
    primary key (id)
)

create table taco (
    id bigint not null, 
    created_at timestamp, 
    name varchar(255) not null, 
    primary key (id)
)

create table taco_ingredients (
    taco_id bigint not null, 
    ingredients_id varchar(255) not null
)

create table taco_order (
    id bigint not null, 
    cccvv varchar(255),
    cc_expiration varchar(255), 
    cc_number varchar(255), 
    delivery_city varchar(255), 
    delivery_name varchar(255), 
    delivery_state varchar(255), 
    delivery_street varchar(255), 
    delivery_zip varchar(255), 
    place_at timestamp, 
    user_id bigint, primary key (id)
)

create table taco_order_tacos (
    order_id bigint not null, 
    tacos_id bigint not null
)

create table user (
    id bigint not null, 
    city varchar(255), 
    fullname varchar(255), 
    password varchar(255), 
    phone_number varchar(255), 
    state varchar(255), 
    street varchar(255), 
    username varchar(255), 
    zip varchar(255), 
    primary key (id)
)

create table users (
    id bigint not null, 
    enabled boolean not null, 
    password varchar(255), 
    username varchar(255), 
    primary key (id)
)

create table authority (
    id bigint not null, 
    type integer, 
    primary key (id)
)
