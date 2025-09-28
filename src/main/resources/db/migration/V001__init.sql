CREATE TABLE IF NOT EXISTS user_entity
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL UNIQUE,
    nickname VARCHAR(255) NOT NULL,
    tag      VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS avatar_entity
(
    id             UUID PRIMARY KEY,
    path           VARCHAR(255)                NOT NULL,
    date           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_entity_id UUID REFERENCES user_entity (id)
);

CREATE TABLE IF NOT EXISTS chat_entity
(
    id UUID PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS message_entity
(
    id             UUID PRIMARY KEY,
    date           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    text           VARCHAR(255)                NOT NULL,
    pinned         BOOLEAN                     NOT NULL,
    forward_id     UUID REFERENCES message_entity (id),
    chat_entity_id UUID REFERENCES chat_entity (id),
    author_id      UUID REFERENCES user_entity (id)
);

CREATE TABLE IF NOT EXISTS chats_users
(
    chat_id UUID NOT NULL REFERENCES chat_entity (id),
    user_id UUID NOT NULL REFERENCES user_entity (id),
    CONSTRAINT pk_chats_users PRIMARY KEY (chat_id, user_id)
);

CREATE TABLE IF NOT EXISTS reaction_entity
(
    id                UUID PRIMARY KEY,
    symbol            VARCHAR(255) NOT NULL,
    message_entity_id UUID REFERENCES message_entity (id),
    user_entity_id    UUID REFERENCES user_entity (id)
);

CREATE TABLE IF NOT EXISTS attachment_entity
(
    id                UUID PRIMARY KEY,
    path              VARCHAR(255) NOT NULL,
    type              VARCHAR(10)  NOT NULL,
    message_entity_id UUID REFERENCES message_entity (id)
);



