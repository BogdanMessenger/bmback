CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(150) NOT NULL,
    tag      VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS avatar
(
    id             UUID PRIMARY KEY,
    "path"           VARCHAR(255)                NOT NULL,
    uploaded_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id UUID REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS chat
(
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by UUID REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS message
(
    id             UUID PRIMARY KEY,
    send_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    payload           TEXT                NOT NULL,
    pinned         BOOLEAN                     NOT NULL,
    forward_id     UUID REFERENCES message(id),
    chat_id UUID REFERENCES chat(id) NOT NULL,
    author_id      UUID REFERENCES users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chats_users
(
    chat_id UUID NOT NULL REFERENCES chat(id),
    user_id UUID NOT NULL REFERENCES users(id),
    CONSTRAINT pk_chats_users PRIMARY KEY (chat_id, user_id)
);

CREATE TABLE IF NOT EXISTS reaction
(
    id                UUID PRIMARY KEY,
    symbol            VARCHAR(255) NOT NULL,
    message_id UUID REFERENCES message(id) NOT NULL,
    user_id    UUID REFERENCES users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS attachment
(
    id                UUID PRIMARY KEY,
    "path"             VARCHAR(255) NOT NULL,
    type              VARCHAR(10)  NOT NULL,
    message_id UUID REFERENCES message(id)
);

CREATE INDEX idx_message_chat_pinned ON message(chat_id, send_at DESC)
WHERE pinned = TRUE;
