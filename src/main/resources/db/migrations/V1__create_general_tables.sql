CREATE TABLE totem ( 
  id                  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  supply_warehouse_id INTEGER  NOT NULL
);

COMMENT ON COLUMN totem.id IS 'Identificador do totem';
COMMENT ON COLUMN totem.supply_warehouse_id IS 'Identificador do almoxarifado em que o totem está instalado.';

CREATE UNIQUE INDEX totem__IDX ON totem (supply_warehouse_id);

CREATE TABLE employee ( 
  id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
  name         VARCHAR(100) NOT NULL, 
  access_level INTEGER      NOT NULL
);

COMMENT ON COLUMN employee.id IS 'Identificador do funcionário.';
COMMENT ON COLUMN employee.name IS 'Nome do funcionário.';
COMMENT ON COLUMN employee.access_level IS 'Nível de acesso do funcionário. Pode ir de 1 a 3.';

CREATE TABLE supply ( 
  id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
  name       VARCHAR(250) NOT NULL, 
  lot_number INTEGER      NOT NULL, 
  quantity   INTEGER      NOT NULL
);

COMMENT ON COLUMN supply.id IS 'Identificador do insumo.';
COMMENT ON COLUMN supply.name IS 'Nome do insumo.';
COMMENT ON COLUMN supply.lot_number IS 'Número do lote do insumo.';
COMMENT ON COLUMN supply.quantity IS 'Quantidade do insumo no almoxarifado.';

CREATE TABLE supply_movement ( 
  id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "date"      TIMESTAMP   NOT NULL,
  totem_id    INTEGER     NOT NULL,
  employee_id INTEGER     NOT NULL,
  supply_id   INTEGER     NOT NULL,
  quantity    INTEGER     NOT NULL
);

COMMENT ON COLUMN supply_movement.id IS 'Identificador único da movimentação.';
COMMENT ON COLUMN supply_movement."date" IS 'Data e hora de quando a alteração ocorreu.';
COMMENT ON COLUMN supply_movement.totem_id IS 'Identificador do totem que registrou a alteração.';
COMMENT ON COLUMN supply_movement.employee_id IS 'Identificador do funcionário que registrou a alteração.';
COMMENT ON COLUMN supply_movement.supply_id IS 'Identificador do insumo que está sendo alterado.';
COMMENT ON COLUMN supply_movement.quantity IS 'Quantidade do insumo que está sendo alterado.';

ALTER TABLE supply_movement 
  ADD CONSTRAINT supply_movement_employee_FK FOREIGN KEY (employee_id) REFERENCES employee (id)
;

ALTER TABLE supply_movement 
  ADD CONSTRAINT supply_movement_supply_FK FOREIGN KEY (supply_id) REFERENCES supply (id)
;

ALTER TABLE supply_movement 
  ADD CONSTRAINT supply_movement_totem_FK FOREIGN KEY (totem_id) REFERENCES totem (id)
;

CREATE TABLE supply_warehouse ( 
  id      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  unit_id INTEGER NOT NULL
);

COMMENT ON COLUMN supply_warehouse.id IS 'Identificador do almoxarifado.';

CREATE TABLE supply_warehouse__supply ( 
  supply_warehouse_id INTEGER  NOT NULL,
  supply_id           INTEGER  NOT NULL,
  CONSTRAINT "POSSUI_(ALM_-_IN)_PK" PRIMARY KEY (supply_warehouse_id, supply_id)
);

CREATE TABLE unit (
  id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  CEP      VARCHAR(8)   NOT NULL,
  "number" INTEGER      NOT NULL
);

COMMENT ON COLUMN unit.id IS 'Identificador da unidade.';
COMMENT ON COLUMN unit.CEP IS 'CEP da unidade.';
COMMENT ON COLUMN unit."number" IS 'Número da unidade (no endereço).';

CREATE TABLE unit_employee ( 
  unit_id     INTEGER  NOT NULL,
  employee_id INTEGER  NOT NULL,
  CONSTRAINT "POSSUI_(UN_-_FU)_PK" PRIMARY KEY (unit_id, employee_id)
);

ALTER TABLE supply_warehouse__supply 
  ADD CONSTRAINT "POSSUI_(ALM_-_IN)_supply_FK" FOREIGN KEY (supply_id) REFERENCES supply (id)
;

ALTER TABLE supply_warehouse__supply 
  ADD CONSTRAINT "POSSUI_(ALM_-_IN)_supply_warehouse_FK" FOREIGN KEY (supply_warehouse_id) REFERENCES supply_warehouse (id)
;

ALTER TABLE unit_employee 
  ADD CONSTRAINT "POSSUI_(UN_-_FU)_employee_FK" FOREIGN KEY (employee_id) REFERENCES employee (id)
;

ALTER TABLE unit_employee 
  ADD CONSTRAINT "POSSUI_(UN_-_FU)_unit_FK" FOREIGN KEY (unit_id) REFERENCES unit (id)
;

ALTER TABLE supply_warehouse 
  ADD CONSTRAINT supply_warehouse_unit_FK FOREIGN KEY (unit_id) REFERENCES unit (id)
;

ALTER TABLE totem
  ADD CONSTRAINT totem_supply_warehouse_FK FOREIGN KEY (supply_warehouse_id) REFERENCES supply_warehouse (id)
;