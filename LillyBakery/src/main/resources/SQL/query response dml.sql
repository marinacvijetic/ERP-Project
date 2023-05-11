select * from tblquery;

select * from tblresponse;

select * from tbluser_query_response


insert into tblquery(customer_id, _date, _query)
values (3, '2023-04-08', 'query test');

insert into tblresponse(query_id, _date, response)
values(1, '2023-05-05', 'bla bla response'),
(2,'2023-04-08', 'response test')

insert into tbluser_query_response(1, 2, 1, )