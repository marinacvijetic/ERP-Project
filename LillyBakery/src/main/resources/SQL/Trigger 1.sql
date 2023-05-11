--TRIGGER 1: Track number of orders for every Customer
CREATE OR REPLACE FUNCTION update_customer_orders()
 RETURNS TRIGGER AS 
$$
BEGIN
  IF NEW.Status_ID = 4 AND OLD.Status_ID <> NEW.Status_ID 
  THEN
    UPDATE tblcustomer
    SET Number_Of_Orders = Number_Of_Orders + 1
    WHERE Customer_ID = NEW.Customer_ID;
  END IF;
  RETURN NEW;
END;
$$ 
LANGUAGE 'plpgsql';

CREATE TRIGGER update_customer_orders
AFTER UPDATE 
ON tblorder
FOR EACH ROW
EXECUTE PROCEDURE update_customer_orders();



