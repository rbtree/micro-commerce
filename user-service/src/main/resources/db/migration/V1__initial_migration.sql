CREATE SEQUENCE IF NOT EXISTS confirmation_tokens_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS user_roles_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE confirmation_tokens
(
    id              BIGINT NOT NULL,
    token           UUID,
    expiration_date TIMESTAMP WITHOUT TIME ZONE,
    used            BOOLEAN,
    CONSTRAINT pk_confirmation_tokens PRIMARY KEY (id)
);

CREATE TABLE roles
(
    role_id   UUID NOT NULL,
    authority VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (role_id)
);

CREATE TABLE user_roles
(
    id      BIGINT NOT NULL,
    user_id UUID   NOT NULL,
    role_id UUID   NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    user_id               UUID         NOT NULL,
    email                 VARCHAR(255) NOT NULL,
    password              VARCHAR(255) NOT NULL,
    first_name            VARCHAR(255),
    last_name             VARCHAR(255),
    date_of_birth         TIMESTAMP WITHOUT TIME ZONE,
    is_enabled            BOOLEAN,
    confirmation_token_id BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_CONFIRMATION_TOKEN FOREIGN KEY (confirmation_token_id) REFERENCES confirmation_tokens (id);

ALTER TABLE user_roles
    ADD CONSTRAINT FK_USER_ROLES_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (role_id);

ALTER TABLE user_roles
    ADD CONSTRAINT FK_USER_ROLES_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);