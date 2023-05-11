--TRIGGER 2: Calculate total price for order
CREATE OR REPLACE FUNCTION calculate_order_total()
 RETURNS TRIGGER AS 
$$
DECLARE order_total DECIMAL(10,2);
BEGIN
  SELECT SUM(Quantity * Price_Per_Kilogram)
  INTO order_total
  FROM tblorder_item
  INNER JOIN tblproduct ON tblorder_item.Product_ID = tblproduct.Product_ID
  WHERE tblorder_item.Order_ID = NEW.Order_ID;

  UPDATE tblorder
  SET Total = order_total
  WHERE Order_ID = NEW.Order_ID;

  RETURN NEW;
END;
$$ 
LANGUAGE 'plpgsql';

CREATE TRIGGER calculate_order_total
AFTER INSERT OR UPDATE OR DELETE ON tblorder_item
FOR EACH ROW
EXECUTE PROCEDURE calculate_order_total();