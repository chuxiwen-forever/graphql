DROP TABLE IF EXISTS event;
CREATE TABLE event(
    id SERIAL,
    title varchar(100) NOT NULL ,
    description varchar(255) NOT NULL ,
    price float NOT NULL ,
    date timestamp NOT NULL ,
    primary key (id)
)