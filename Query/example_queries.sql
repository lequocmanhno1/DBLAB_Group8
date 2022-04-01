--3 Bang hoat dong khach san.
select * from "Booking";
select * from "Service";
select * from "Service_Use";
--Bang it chinh sua
select * from "Staff";
--Bang khong chinh sua.
select * from "Room";
select * from "Status";

--10 cau query cua Manh
-- Muc tieu
-- 3 cau bang don
-- 3 cau join
-- 2 view
-- 2 trigger function

-- Câu 1: Tính tổng số booking trong tháng trước
select count(id) as "Tổng số Booking"
from "Booking" bk
where extract('month' from check_in_date) = extract('month' from CURRENT_DATE) -1;

-- Câu 2: Tính số khách hàng có SDT Việt Nam đặt phòng
select count(id) as "Tổng"
from "Booking" bk 
where guest_phone like '+84%' or guest_phone like '0%';

-- Câu 3: Sắp xếp dịch vụ khách sạn từ được sử dụng nhiều nhất đến ít nhất
select sv.name, sum(su.quantity)
from "Service" sv
full outer join "Service_Use" su on sv.id = su.service_id
group by sv.id
order by sum(su.quantity) desc NULLS LAST;
--order by -sum(su.quantity) asc;
-- neu de sum(su.quantity) desc thi gia thi null len dau.


-- Câu 4: Tính tổng tiền dịch vụ cần phải trả của 1 booking
select sum(su.quantity * sv.price) as "Tiền dịch vụ"
from "Booking" bk
join "Service_Use" su on bk.id = su.booking_id
join "Service" sv on su.service_id = sv.id
where bk.id = 4;


-- Câu 5: Dịch vụ mà khách thuê phòng đôi sử dụng nhiều nhất.

with tmp as( 
select sv.name as service ,sum(su.quantity) as totalquan
from "Booking" bk
join "Service_Use" su on bk.id = su.booking_id
full outer join "Service" sv on su.service_id = sv.id
where bk.adult_amount = 2
group by sv.name
)
select service, totalquan
from tmp
where tmp.totalquan = (select max(totalquan) from tmp);

-- Câu 6: Hàm tính tiền phòng của 1 booking
create or replace function tienphong(in bkid integer) 
returns integer as
$$Declare result integer;
begin
SELECT into result (bk.check_out_date::date - bk.check_in_date::date) * r.price_per_night
FROM "Booking" bk join "Room" r ON bk.room_id = r.id 
WHERE bk.id = bkid;
return result;
END;
$$
LANGUAGE plpgsql
RETURNS Null ON NULL INPUT;

--select tienphong(4);


-- Câu 7: Hàm tính tiền tổng dịch vụ của 1 booking

--ham tinh tong tien dich vu 1 booking
create or replace function tiendichvu(in bkid integer) 
returns integer as
$$Declare dichvu integer;
begin
select into dichvu coalesce(sum(su.quantity * sv.price),0)
from "Booking" bk
join "Service_Use" su on bk.id = su.booking_id
join "Service" sv on su.service_id = sv.id
where bk.id = bkid;
return dichvu;
END;
$$
LANGUAGE plpgsql;

--select tiendichvu(2);

-- Câu 8: (View) Đưa ra danh sách booking, tên khách hàng, tổng tiền.
--ham tinh tong tien
create or replace function Total(in bkid integer) 
returns integer as
$$ Declare totalpay integer;
begin
    SELECT into totalpay Tienphong(bk.id) + Tiendichvu(bk.id)
    FROM "Booking" bk
	where bk.id =bkid;	  
return totalpay;
end;
$$
LANGUAGE plpgsql;

--select Total(5);
----
--drop view bill;

create or replace view bill as 
	select bk.id, bk.guest_lastname ||' '|| bk.guest_firstname as "Tên khách hàng", Total(bk.id) as "Tổng tiền"
	from "Booking" bk 
	order by bk.id;
--select * from bill;

-- Cau 8: Tong doanh thu khach san trong thang 2 nam 2022.	
	select sum(Total(bk.id)) as "Tong doanh thu"
	from "Booking" bk 
	where extract('month' from check_out_date) = 2 and extract('year' from check_out_date) = 2022;

-- Câu 9: (Trigger) Bắt buộc phải ít nhất passport hoặc Chứng minh thư khi insert, update booking
create or replace function tf_citizenid_passport()
returns trigger as
$$
begin
	if (New.guest_passport is NULL) and (New.guest_citizen_id is NULL) then
		RAISE EXCEPTION 'both passport and citizen_id cannot be null';
	else 
	return NEW;
	end if;
end;
$$
language plpgsql;


create trigger tg_citienid_passport
after insert or update on "Booking"
for each row
execute procedure tf_citizenid_passport();

set datestyle = dmy;
insert into "Booking"(guest_lastname, guest_firstname, guest_phone, guest_citizen_id, guest_passport, gender, check_in_date, check_out_date, adult_amount, children_amount, room_id, staff_id, status_id) 
		values('Le Tan','Tai', '01481491421',null,null,'Male','12/05/2022','15/05/2022','4','2','30','1','2');
		-- loi vi khong duoc trong va citienid va passport
insert into "Booking"(guest_lastname, guest_firstname, guest_phone, guest_citizen_id, guest_passport, gender, check_in_date, check_out_date, adult_amount, children_amount, room_id, staff_id, status_id) 
		values('Le Tan','Tai', '01481491421','6461661616',null,'Male','12/05/2022','15/05/2022','4','2','30','1','2');
		-- insert thanh cong
--insert into "Booking"(guest_lastname, guest_firstname, guest_phone, guest_citizen_id, guest_passport, gender, check_in_date, check_out_date, adult_amount, children_amount, room_id, staff_id, status_id) 
		--values('Le Van','Nam', '03431491421','64634341616',null,'Male','9/02/2022','11/02/2022','2','1','14','1','2');

-- Cau 10: (Trigger) 


