CREATE TABLE
    transacoes (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        conta_id UUID NOT NULL,
        valor NUMERIC(18, 2) NOT NULL,
        forma_pagamento VARCHAR(1) NOT NULL,
        data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        CONSTRAINT fk_contas FOREIGN KEY (conta_id) REFERENCES contas (id)
    );