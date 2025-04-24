CREATE TABLE
    contas (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        numero_conta INTEGER UNIQUE NOT NULL,
        saldo NUMERIC(18, 2) NOT NULL,
        data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );