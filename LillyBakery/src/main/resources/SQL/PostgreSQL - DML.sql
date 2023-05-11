INSERT INTO tblUser (User_Email, Username, Password)
VALUES ('marinacvijetic2@gmail.com', 'marinacvijetic', 'marina123'),
	   ('ana.radovanovic23@hotmail.com', 'Ana_Rad', 'Gh2kHoy'),
	   ('vlad_sav88@gmail.com', 'VladaSavic', 'mnK9Iz2'),
       ('jokicsanja@yahoo.com', 'Sanja_Jokic', '22uUr997p'),
       ('slav.gajic77@gmail.com', 'Slavicaa', '9lk6cSE3'),
       ('markosajic22@hotmail.com', 'markosajic', '74pppk8'),
       ('mici.micc55@gmail.com', 'MilicaKrstic', 'wQp478o'),
       ('anja.krajsic92@gmail.com', 'Anja_K', 'kKA82L6'),
	   ('test.user@gmail.com', 'testUser', 'test');

INSERT INTO tblAdministrator (User_Email, Admin_Name)
VALUES ('marinacvijetic2@gmail.com', 'Marina');
       
INSERT INTO tblCustomer (_Name, Surname, Phone_Number, User_Email, Number_Of_Orders)
VALUES ('Ana', 'Radovanović', '064-555-48-22', 'ana.radovanovic23@hotmail.com', 1),
	   ('Vladimir', 'Savić', '062-458-987', 'vlad_sav88@gmail.com', 2),
       ('Sanja', 'Jokić', '065-124-99-88', 'jokicsanja@yahoo.com', 2),
       ('Slavica', 'Gajić', '061-555-47-77', 'slav.gajic77@gmail.com', 1),
       ('Marko', 'šajić', '063-693-66-24', 'markosajic22@hotmail.com', 1),
       ('Milica', 'Krstić', '064-545-23-21', 'mici.micc55@gmail.com', 0);
       
INSERT INTO tblCategory (Category_Name)
VALUES ('Slana Predjela'),
	   ('Torte'),
       ('Kolači'),
       ('Slane Torte');

INSERT INTO tblOrder_Status (Order_Status)
VALUES ('Accepted'),
	   ('Declined'),
       ('In Process'),
       ('Delivered');

INSERT INTO tblShipping_Method (Shipping_Option, Price)
VALUES ('Delivery', 250.00),
	   ('Takeaway', null);

INSERT INTO tblPayment_Method (Payment_Method)
VALUES ('Pay With Card'),
	   ('Pay With Cash');
       
INSERT INTO tblOrder_Arrival_Details (Arrival_Date, Arrival_Time, Country, Postal_Code, City, Street_Name, Street_Number)
VALUES ('2023-06-23', '13:00:00', 'Srbija', '22000', 'Sremska Mitrovica', 'Kuzminska', '24A'),
	   ('2023-08-12', '12:30:00', 'Srbija', '322482', 'Lacarak', 'Ulica 1. Novembra', '277'),
	   ('2023-05-03', '14:30:00', 'Srbija', '22202', 'Macvanska Mitrovica', 'Stojana Cupica', 'BB'),
       ('2023-07-29', '18:00:00', 'Srbija', '321096', 'Sremska Mitrovica', 'Stevana Sremca', '89A'),
       ('2023-07-13', '19:00:00', 'Srbija', '22000', 'Sremska Mitrovica', 'Trg Cire Milekica', '12');
       

INSERT INTO tblProduct (Product_Name, Description, Product_Image, Category_ID, Price_Per_Kilogram)
VALUES ('Žuti Rolat', 'Rolat od jaja, punjen fetom, šunkom i krastavčićima.', null, 1, 900),
	   ('Doboš Torta', 'Doboš torta.', null, 2, 1200),
       ('Kinder torta', 'Kinder torta sa eurokremom i lešnicima.', null, 2, 1500),
       ('Lešnik Štangle', 'Sitni kolači od lešnika i eurokrema sečeni u štanglice.', null, 3, 1500),
       ('Kikiriki Štangle', 'Sitni kolači od kikirikija i keksa sečeni u štanglice.', null, 3, 1200),
       ('Rafaelo Kuglice', 'Rafaelo kuglice od kokosa i mleka sa lešnikom.', null, 3, 1400);     

       


--Vrednost za polje 'Total' je null, jer se nakon sledeceg inserta u tabelu tblOrderItem
--aktivira trigger 2 koji ce azurirati polje 'Total' u tblOrder nakon sto izracuna vrednost porudzbine 
INSERT INTO tblOrder (Customer_ID, Arrival_Details_ID, Payment_Method_ID, Shipping_ID, Order_Date, Total, Status_ID)
VALUES (1, 3, 2, 1, '2023-04-03', null, 1),
	   (2, 1, 1, 2, '2023-05-31', null, 3),
	   (3, 2, 1, 2, '2023-07-29', null, 4),
	   (4, 5, 2, 1, '2023-07-01', null, 1),
	   (5, 4, 2, 2, '2023-07-01', null, 3);


INSERT INTO tblOrder_Item (Order_ID, Product_ID, Quantity)
VALUES  (4, 1, 3),
		(4, 6, 2),
		(4, 2, 1),
		(1, 3, 2),
		(1, 1, 3),
		(1, 5, 2),
		(2, 2, 3),
		(2, 1, 2),
		(3, 4, 2),
		(3, 3, 2),
		(5, 2, 3);


insert into tblquery(customer_id, _date, _query)
values (3, '2023-04-08', 'query test'),
	   (2, '2023-05-05', 'blablabla query');

insert into tblresponse(query_id, _date, response)
values(1, '2023-05-05', 'bla bla response'),
(2,'2023-04-08', 'response test')

insert into tbluser_query_response
values(1, 3, 2),
	  (2, 2, 2);


--Provera za trigger 1
Insert into tblOrder (Customer_ID, Arrival_Details_ID, Payment_Method_ID, Shipping_ID, Order_Date, Total, Status_ID)
VALUES (6, 1, 1, 2, '2023-06-05', null, 3);

select * from tblcustomer

update tblorder
set Status_ID = 4
where Customer_ID = 6

