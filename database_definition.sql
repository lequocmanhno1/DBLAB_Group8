--psql -U postgres -f "C:\Users\leemain\Downloads\Documents\20211\DB LAB\Project0\def.sql"
create database "BookingAssignment";
\c BookingAssignment;
CREATE TABLE IF NOT EXISTS public."Staff"
(
    staff_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    password text  NOT NULL,
    staffname text  NOT NULL,
    national_id text  NOT NULL,
    phone text ,
    address text ,
    permission integer NOT NULL DEFAULT 3,
    CONSTRAINT "Staff_pkey" PRIMARY KEY (staff_id)
);

--sample data
insert into public."Staff"(
    password,staffname,national_id,phone,address)
    values('123456','nhan vien','HN','0963636189','HN');


CREATE TABLE IF NOT EXISTS public."Room"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    room_number integer NOT NULL,
    adult_capacity integer NOT NULL,
    children_capacity integer NOT NULL,
    price_per_night decimal NOT NULL,
    CONSTRAINT "Room_pkey" PRIMARY KEY (id)
);

--sample data
INSERT INTO public."Room"(room_number, adult_capacity, children_capacity, price_per_night) VALUES 
    (101,1,0,34),
    (102,1,0,34),
    (103,1,0,34),
    (104,2,1,52),
    (105,2,1,52),
    (106,2,1,52),
    (107,2,2,64),
    (108,2,2,64),
    (201,1,0,34),
    (202,1,0,34),
    (203,1,0,34),
    (204,2,1,52),
    (205,2,1,52),
    (206,2,1,52),
    (207,2,2,64),
    (208,2,2,64),
    (301,1,0,34),
    (302,1,0,34),
    (303,1,0,34),
    (304,2,1,52),
    (305,2,1,52),
    (306,2,1,52),
    (307,2,2,64),
    (308,2,2,64),
    (401,2,2,64),
    (402,2,2,64),
    (403,2,2,64),
    (404,4,2,107),
    (405,4,2,107),
    (406,4,2,107);



CREATE TABLE IF NOT EXISTS public."Status"
(
    id integer NOT NULL,
    label text  NOT NULL,
    CONSTRAINT "Status_pkey" PRIMARY KEY (id)
);

-- SAMPLE DATA --
INSERT INTO public."Status"(id,label) VALUES 
    ('1','Booked'), 
    ('2','Checked In'), 
    ('3','Checked Out'), 
    ('4','Canceled');


CREATE TABLE IF NOT EXISTS public."Booking"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    guest_lastname text  NOT NULL,
    guest_firstname text  NOT NULL,
    guest_phone text  NOT NULL,
    guest_citizen_id text,
    guest_passport text,
    gender text  NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    adult_amount integer NOT NULL,
    children_amount integer DEFAULT 0,
    room_id integer NOT NULL,
    staff_id integer NOT NULL,
    status_id integer NOT NULL,
    CONSTRAINT "Booking_pkey" PRIMARY KEY (id),
    CONSTRAINT booking_fk_room_id FOREIGN KEY (room_id)
        REFERENCES public."Room" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT booking_fk_staff_id FOREIGN KEY (staff_id)
        REFERENCES public."Staff" (staff_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT booking_fk_status_id FOREIGN KEY (status_id)
        REFERENCES public."Status" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);


CREATE TABLE IF NOT EXISTS public."Service"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name text  NOT NULL,
    description text  NOT NULL,
    price decimal NOT NULL,
    CONSTRAINT "Service_pkey" PRIMARY KEY (id)
);



-- SAMPLE DATA --
INSERT INTO public."Service"(name, description, price) VALUES 
    ('Laundry', 'Laundry for clothes', '10'),
    ('Party', 'Party organizing', '120'),
    ('Wedding', 'Wedding organizing', '200'),
    ('Spa', 'Skin care & Massage', '60'),
    ('Wine', 'Special wine', '60'),
    ('Extra bed','1 Extra bed', '20'),
    ('Sauna','per person','20'),
    ('Local Perks','Provide Local Area Perks, per day','50'),
    ('Safety Box','A safety deposit box','300');



CREATE TABLE IF NOT EXISTS public."Service_Use"
(
    booking_id integer NOT NULL,
    service_id integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT "Service_Use_pkey" PRIMARY KEY (booking_id, service_id),
    CONSTRAINT service_use_booking_id FOREIGN KEY (booking_id)
        REFERENCES public."Booking" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT service_use_service_id FOREIGN KEY (service_id)
        REFERENCES public."Service" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

