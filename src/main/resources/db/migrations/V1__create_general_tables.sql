CREATE TABLE totem (
  id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_unidade  INTEGER NOT NULL
);

COMMENT ON COLUMN totem.id IS 'Identificador do totem';
COMMENT ON COLUMN totem.id_unidade IS 'Identificador da unidade em que o totem está instalado';

CREATE TABLE alteracao (
  id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_funcionario  INTEGER NOT NULL,
  data            TIMESTAMP NOT NULL,
  totem_id        INTEGER NOT NULL,
  CONSTRAINT alteracao_totem_fk FOREIGN KEY (totem_id)
    REFERENCES totem (id)
);

COMMENT ON COLUMN alteracao.id IS 'Identificador da alteração realizada';
COMMENT ON COLUMN alteracao.id_funcionario IS 'Identificador do funcionário que realizou a alteração';
COMMENT ON COLUMN alteracao.data IS 'Data e hora de quando a alteração ocorreu';

CREATE TABLE movimentacao_insumo (
  id            INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_insumo     INTEGER NOT NULL,
  quantidade    INTEGER NOT NULL,
  tipo          CHAR(1) NOT NULL,
  alteracao_id  INTEGER NOT NULL,
  CONSTRAINT movimentacao_alteracao_fk FOREIGN KEY (alteracao_id)
    REFERENCES alteracao (id),
  CONSTRAINT movimentacao_tipo_ck CHECK (tipo IN ('E','B'))
);

COMMENT ON COLUMN movimentacao_insumo.id IS 'Identificador da movimentação';
COMMENT ON COLUMN movimentacao_insumo.id_insumo IS 'Identificador do insumo movimentado';
COMMENT ON COLUMN movimentacao_insumo.quantidade IS 'Quantidade do insumo movimentado';
COMMENT ON COLUMN movimentacao_insumo.tipo IS 'Tipo de movimentação. Pode ser "B" (baixa) ou "E" (entrada).';

-- TABELAS PROVENIENTES DA DASA

CREATE TABLE supplies (
  id       INTEGER      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name     VARCHAR(100) NOT NULL,
  quantity INTEGER      DEFAULT 0,
  CONSTRAINT supplies_name_uk UNIQUE (name)
);

COMMENT ON COLUMN supplies.id IS 'Identificador do insumo (gerado automaticamente)';
COMMENT ON COLUMN supplies.name IS 'Nome do insumo';

-- DADOS DE EXEMPLO
INSERT INTO totem (id_unidade) VALUES
  (1),
  (2),
  (3),
  (4),
  (5)
;

INSERT INTO alteracao (id_funcionario, data, totem_id) VALUES
  (1, CURRENT_TIMESTAMP, 1),
  (1, CURRENT_TIMESTAMP, 1),
  (1, CURRENT_TIMESTAMP, 1),
  (1, CURRENT_TIMESTAMP, 1),
  (1, CURRENT_TIMESTAMP, 1)
;

INSERT INTO movimentacao_insumo (id_insumo, quantidade, tipo, alteracao_id) VALUES
  (1, 1, 'E', 1),
  (1, 1, 'B', 2),
  (2, 1, 'E', 3),
  (2, 1, 'B', 4),
  (3, 1, 'E', 5)
;

INSERT INTO supplies (name, quantity) VALUES
  ('Seringa 5ml', 100),
  ('Luvas cirúrgicas', 100),
  ('Máscara N95', 100),
  ('Álcool em gel 70%', 100),
  ('Termômetro digital', 100),
  ('Estetoscópio', 100),
  ('Esfigmomanômetro', 100),
  ('Gaze estéril', 100),
  ('Fita micropore', 100),
  ('Ampola de soro fisiológico', 100)
;