drop table if exists tblOrder_Item cascade;
drop table if exists tblUser_Query_Response cascade;
drop table if exists tblOrder cascade;
drop table if exists tblResponse cascade;
drop table if exists tblQuery cascade;
drop table if exists tblCustomer cascade;
drop table if exists tblAdministrator cascade;
drop table if exists tblProduct cascade;
drop table if exists tblCategory cascade;
drop table if exists tblUser cascade;
drop table if exists tblOrder_Status cascade;
drop table if exists tblShipping_Method cascade;
drop table if exists tblPayment_Method cascade;
drop table if exists tblOrder_Arrival_Details cascade;

DROP SEQUENCE IF EXISTS Order_Item_seq;
DROP SEQUENCE IF EXISTS Order_seq;
DROP SEQUENCE IF EXISTS Response_seq;
DROP SEQUENCE IF EXISTS Query_seq;
DROP SEQUENCE IF EXISTS Customer_seq;
DROP SEQUENCE IF EXISTS Administrator_seq;
DROP SEQUENCE IF EXISTS Product_seq;
DROP SEQUENCE IF EXISTS Category_seq;
DROP SEQUENCE IF EXISTS Order_Status_seq;
DROP SEQUENCE IF EXISTS Shipping_Method_seq;
DROP SEQUENCE IF EXISTS Payment_Method_seq;
DROP SEQUENCE IF EXISTS Order_Arrival_Details_seq;

create table tblUser (
	User_Email varchar(50),
	Username varchar(20) not null,
	password varchar(20) not null
);

create table tblCustomer (
	Customer_ID integer,
	_Name varchar(30) not null, 
	Surname varchar(30) not null,
	Phone_Number varchar(20) not null, 
	User_Email varchar(50) not null,
	Number_Of_Orders integer
);

create table tblAdministrator (
	Admin_ID integer,
	User_Email varchar(50) not null,
	Admin_Name varchar(20) not null
);

create table tblCategory (
	Category_ID integer,
	Category_Name varchar(20) not null
);

create table tblOrder_Arrival_Details(
	Arrival_Details_ID integer,
	Arrival_Date date not null,
	Arrival_Time time not null,
	Country varchar(30) not null,
	Postal_Code varchar(30) not null, 
	City varchar(30) not null, 
	Street_Name varchar(40) not null,
	Street_Number varchar(10) not null
);

create table tblPayment_Method (
	Payment_Method_ID integer,
	Payment_Method varchar(20) not null CHECK(Payment_Method = 'Pay With Card' or Payment_Method = 'Pay With Cash')
 );
 
create table tblShipping_Method (
   Shipping_ID integer,
   Shipping_Option varchar(20) not null CHECK(Shipping_Option = 'Delivery' or Shipping_Option = 'Takeaway'),
   Price numeric null
);

create table tblOrder_Status (
   Status_ID integer,
   Order_Status varchar(20) not null
);

create table tblProduct (
   Product_ID integer,
   Product_Name varchar(30) not null,
   Description varchar(120) not null,
   Product_Image varchar(200),
   Category_ID integer not null,
   Price_Per_Kilogram numeric not null
);



create table tblQuery (
   Query_ID integer,
   Customer_ID integer not null,
   _Date date, 
   _Query varchar(250) not null
);

create table tblResponse (
   Response_ID integer,
   Query_ID integer not null,
   _Date date, 
   Response varchar(250) not null
);

create table tblUser_Query_Response (
   Query_ID integer,
   Customer_ID integer,
   Response_ID integer
);

create table tblOrder (
   Order_ID integer,
   Customer_ID integer not null,
   Arrival_Details_ID integer not null, 
   Payment_Method_ID integer not null,
   Shipping_ID integer not null,
   Order_Date date,
   Total numeric,
   Status_ID integer
);

create table tblOrder_Item (
   Order_Item_ID integer,
   Order_ID integer not null,
   Product_ID integer not null,
   Quantity integer not null
);
 
 ALTER TABLE tblUser
	ADD CONSTRAINT pk_User PRIMARY KEY (User_Email);
ALTER TABLE tblCategory 
	ADD CONSTRAINT pk_Category PRIMARY KEY (Category_ID);
ALTER TABLE tblOrder_Arrival_Details 
	ADD CONSTRAINT pk_Order_Arrival_Details PRIMARY KEY (Arrival_Details_ID);
ALTER TABLE tblPayment_Method 
	ADD CONSTRAINT pk_Payment_Method PRIMARY KEY (Payment_Method_ID);
ALTER TABLE tblShipping_Method 
	ADD CONSTRAINT pk_Shipping_Method PRIMARY KEY (Shipping_ID);
ALTER TABLE tblOrder_Status 
	ADD CONSTRAINT pk_Order_Status PRIMARY KEY (Status_ID);
ALTER TABLE tblCustomer 
	ADD CONSTRAINT pk_Customer PRIMARY KEY (Customer_ID);
ALTER TABLE tblAdministrator
	ADD CONSTRAINT pk_Administrator PRIMARY KEY (Admin_ID);
ALTER TABLE tblProduct 
	ADD CONSTRAINT pk_Product PRIMARY KEY (Product_ID);
ALTER TABLE tblQuery 
	ADD CONSTRAINT pk_Query PRIMARY KEY (Query_ID);
ALTER TABLE tblResponse 
	ADD CONSTRAINT pk_Response PRIMARY KEY (Response_ID);
ALTER TABLE tblUser_Query_Response 
	ADD CONSTRAINT pk_User_Query_Response PRIMARY KEY (Query_ID, Customer_ID, Response_ID);
ALTER TABLE tblOrder 
	ADD CONSTRAINT pk_Order PRIMARY KEY (Order_ID);
ALTER TABLE tblOrder_Item 
	ADD CONSTRAINT pk_Order_Item PRIMARY KEY (Order_Item_ID);




ALTER TABLE tblCustomer 
	ADD CONSTRAINT FK_User_Customer FOREIGN KEY (User_Email)
    REFERENCES tblUser(User_Email);
   
ALTER TABLE tblAdministrator
	ADD CONSTRAINT fk_User_Administrator FOREIGN KEY (User_Email)
    REFERENCES tblUser(User_Email);
    
ALTER TABLE tblProduct
	ADD CONSTRAINT FK_Category_Product FOREIGN KEY (Category_ID)
    REFERENCES tblCategory(Category_ID); 

ALTER TABLE tblQuery
	ADD CONSTRAINT FK_Customer_Query FOREIGN KEY (Customer_ID)
    REFERENCES tblCustomer(Customer_ID);
	
ALTER TABLE tblResponse
	ADD CONSTRAINT FK_Query_Response FOREIGN KEY (Query_ID)
    REFERENCES tblQuery(Query_ID);
	
ALTER TABLE tblUser_Query_Response
	ADD CONSTRAINT FK_Customer_User_Query_Response FOREIGN KEY (Customer_ID)
    REFERENCES tblCustomer(Customer_ID),
	ADD CONSTRAINT FK_Query_User_Query_Response FOREIGN KEY (Query_ID)
	REFERENCES tblQuery(Query_ID),
	ADD CONSTRAINT FK_Response_User_Query_Response FOREIGN KEY (Response_ID)
	REFERENCES tblResponse (Response_ID);

ALTER TABLE tblOrder
	ADD CONSTRAINT FK_Customer_Order FOREIGN KEY (Customer_ID)
    REFERENCES tblCustomer(Customer_ID),
	ADD CONSTRAINT FK_Order_Arrival_Details_Order FOREIGN KEY (Arrival_Details_ID)
	REFERENCES tblOrder_Arrival_Details(Arrival_Details_ID),
	ADD CONSTRAINT FK_Payment_Method_Order FOREIGN KEY (Payment_Method_ID)
	REFERENCES tblPayment_Method (Payment_Method_ID),
	ADD CONSTRAINT FK_Shipping_Method_Order FOREIGN KEY (Shipping_ID)
	REFERENCES tblShipping_Method(Shipping_ID),
	ADD CONSTRAINT FK_Order_Status_Order FOREIGN KEY (Status_ID)
	REFERENCES tblOrder_Status(Status_ID);
	
ALTER TABLE tblOrder_Item
	ADD CONSTRAINT FK_Order_Order_Item FOREIGN KEY (Order_ID)
    REFERENCES tblOrder(Order_ID),
	ADD CONSTRAINT FK_Product_Order_Item FOREIGN KEY (Product_ID)
	REFERENCES tblProduct(Product_ID); 

    
CREATE INDEX idx_pk_Order_Item ON tblOrder_Item (Order_Item_ID);  
CREATE INDEX idx_pk_Order ON tblOrder(Order_Id);
CREATE INDEX idx_pk_Response ON tblResponse(Response_ID);
CREATE INDEX idx_pk_Query ON tblQuery(Query_ID);
CREATE INDEX idx_pk_Customer ON tblCustomer(Customer_ID);
CREATE INDEX idx_pk_Administrator ON tblAdministrator(Admin_ID);  
CREATE INDEX idx_pk_Product ON tblProduct(Product_ID);
CREATE INDEX idx_pk_Category ON tblCategory(Category_ID);  
CREATE INDEX idx_pk_Order_Status ON tblOrder_Status(Status_ID);
CREATE INDEX idx_pk_Shipping_Method ON tblShipping_Method(Shipping_ID);
CREATE INDEX idx_pk_Payment_Method ON tblPayment_Method(Payment_Method_ID);  
CREATE INDEX idx_pk_Order_Arrival_Details ON tblOrder_Arrival_Details(Arrival_Details_ID);

CREATE INDEX idx_fk_User_Administrator ON tblAdministrator(User_Email);
CREATE INDEX idx_fk_User_Customer ON tblCustomer(User_Email); 	
CREATE INDEX idx_fk_Category_Product ON tblProduct(Category_ID);
CREATE INDEX idx_fk_Customer_Query ON tblQuery(Customer_ID); 
CREATE INDEX idx_fk_Query_Response ON tblResponse(Query_ID);
CREATE INDEX idx_fk_User_Query_Response ON tblUser_Query_Response(Customer_ID, Query_ID, Response_ID); 
CREATE INDEX idx_fk_Customer_Order ON tblOrder(Customer_ID);
CREATE INDEX idx_fk_Order_Arrival_Details_Order ON tblOrder(Arrival_Details_ID);
CREATE INDEX idx_fk_Payment_Method_Order ON tblOrder(Payment_Method_ID);
CREATE INDEX idx_fk_Shipping_Method_Order ON tblOrder(Shipping_ID);
CREATE INDEX idx_fk_Order_Status_Order ON tblOrder(Status_ID);
CREATE INDEX idx_fk_Order_Order_Item ON tblOrder_Item(Order_ID);
CREATE INDEX idx_fk_Product_Order_Item ON tblOrder_Item(Product_ID);

    
CREATE SEQUENCE IF NOT EXISTS Order_Item_seq INCREMENT 1 START 1;  
CREATE SEQUENCE IF NOT EXISTS Order_seq INCREMENT 1 START 1;     
CREATE SEQUENCE IF NOT EXISTS Response_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Query_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Customer_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Administrator_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Product_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Category_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Order_Status_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Shipping_Method_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Payment_Method_seq INCREMENT 1 START 1;
CREATE SEQUENCE IF NOT EXISTS Order_Arrival_Details_seq INCREMENT 1 START 1;


ALTER TABLE tblOrder_Item ALTER COLUMN Order_Item_ID SET DEFAULT nextval('Order_Item_seq');
ALTER TABLE tblOrder ALTER COLUMN Order_ID SET DEFAULT nextval('Order_seq');
ALTER TABLE tblResponse ALTER COLUMN Response_ID SET DEFAULT nextval('Response_seq');
ALTER TABLE tblQuery ALTER COLUMN Query_ID SET DEFAULT nextval('Query_seq');
ALTER TABLE tblCustomer ALTER COLUMN Customer_ID SET DEFAULT nextval('Customer_seq');
ALTER TABLE tblAdministrator ALTER COLUMN Admin_ID SET DEFAULT nextval('Administrator_seq');
ALTER TABLE tblProduct ALTER COLUMN Product_ID SET DEFAULT nextval('Product_seq');
ALTER TABLE tblCategory ALTER COLUMN Category_ID SET DEFAULT nextval('Category_seq');
ALTER TABLE tblOrder_Status ALTER COLUMN Status_ID SET DEFAULT nextval('Order_Status_seq');
ALTER TABLE tblShipping_Method ALTER COLUMN Shipping_ID SET DEFAULT nextval('Shipping_Method_seq');
ALTER TABLE tblPayment_Method ALTER COLUMN Payment_Method_ID SET DEFAULT nextval('Payment_Method_seq');
ALTER TABLE tblOrder_Arrival_Details ALTER COLUMN Arrival_Details_ID SET DEFAULT nextval('Order_Arrival_Details_seq');
