INSERT INTO employee (name, access_level) VALUES ('João Silva', 1);
INSERT INTO employee (name, access_level) VALUES ('Maria Oliveira', 2);
INSERT INTO employee (name, access_level) VALUES ('Carlos Souza', 3);
INSERT INTO employee (name, access_level) VALUES ('Ana Costa', 1);
INSERT INTO employee (name, access_level) VALUES ('Pedro Lima', 2);

INSERT INTO supply (name, lot_number, quantity) VALUES ('Seringa 5ml', 1001, 500);
INSERT INTO supply (name, lot_number, quantity) VALUES ('Luvas cirúrgicas M', 1002, 300);
INSERT INTO supply (name, lot_number, quantity) VALUES ('Máscaras descartáveis', 1003, 1000);
INSERT INTO supply (name, lot_number, quantity) VALUES ('Ampolas de soro 250ml', 1004, 200);
INSERT INTO supply (name, lot_number, quantity) VALUES ('Algodão estéril', 1005, 150);

INSERT INTO unit (CEP, "number") VALUES ('01001000', 100);
INSERT INTO unit (CEP, "number") VALUES ('02020000', 200);
INSERT INTO unit (CEP, "number") VALUES ('03030000', 300);
INSERT INTO unit (CEP, "number") VALUES ('04040000', 400);
INSERT INTO unit (CEP, "number") VALUES ('05050000', 500);

INSERT INTO supply_warehouse (unit_id) VALUES (1);
INSERT INTO supply_warehouse (unit_id) VALUES (2);
INSERT INTO supply_warehouse (unit_id) VALUES (3);
INSERT INTO supply_warehouse (unit_id) VALUES (4);
INSERT INTO supply_warehouse (unit_id) VALUES (5);

INSERT INTO totem (supply_warehouse_id) VALUES (1);
INSERT INTO totem (supply_warehouse_id) VALUES (2);
INSERT INTO totem (supply_warehouse_id) VALUES (3);
INSERT INTO totem (supply_warehouse_id) VALUES (4);
INSERT INTO totem (supply_warehouse_id) VALUES (5);

INSERT INTO unit_employee (unit_id, employee_id) VALUES (1, 1);
INSERT INTO unit_employee (unit_id, employee_id) VALUES (2, 2);
INSERT INTO unit_employee (unit_id, employee_id) VALUES (3, 3);
INSERT INTO unit_employee (unit_id, employee_id) VALUES (4, 4);
INSERT INTO unit_employee (unit_id, employee_id) VALUES (5, 5);

INSERT INTO supply_warehouse__supply (supply_warehouse_id, supply_id) VALUES (1, 1);
INSERT INTO supply_warehouse__supply (supply_warehouse_id, supply_id) VALUES (2, 2);
INSERT INTO supply_warehouse__supply (supply_warehouse_id, supply_id) VALUES (3, 3);
INSERT INTO supply_warehouse__supply (supply_warehouse_id, supply_id) VALUES (4, 4);
INSERT INTO supply_warehouse__supply (supply_warehouse_id, supply_id) VALUES (5, 5);

INSERT INTO supply_movement ("date", totem_id, employee_id, supply_id, quantity) VALUES (CURRENT_TIMESTAMP, 1, 1, 1, 10);
INSERT INTO supply_movement ("date", totem_id, employee_id, supply_id, quantity) VALUES (CURRENT_TIMESTAMP, 2, 2, 2, -5);
INSERT INTO supply_movement ("date", totem_id, employee_id, supply_id, quantity) VALUES (CURRENT_TIMESTAMP, 3, 3, 3, 20);
INSERT INTO supply_movement ("date", totem_id, employee_id, supply_id, quantity) VALUES (CURRENT_TIMESTAMP, 4, 4, 4, -2);
INSERT INTO supply_movement ("date", totem_id, employee_id, supply_id, quantity) VALUES (CURRENT_TIMESTAMP, 5, 5, 5, 15);