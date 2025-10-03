CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(150) NOT NULL,
    tag      VARCHAR(20) NOT NULL UNIQUE,
    last_entry TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS chats
(
    id UUID PRIMARY KEY,
    name VARCHAR(150),
    is_group BOOL NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by UUID REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS avatars
(
    id             UUID PRIMARY KEY,
    "path"           TEXT                NOT NULL,
    uploaded_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id UUID REFERENCES users(id),
    chat_id UUID REFERENCES chats(id)
);



CREATE TABLE IF NOT EXISTS messages
(
    id             UUID PRIMARY KEY,
    sent_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    payload           TEXT                NOT NULL,
    pinned         BOOLEAN                     NOT NULL,
    has_attachments BOOLEAN NOT NULL,
    has_reactions BOOLEAN NOT NULL,
    forward_id     UUID REFERENCES messages(id),
    chat_id UUID REFERENCES chats(id) NOT NULL,
    author_id      UUID REFERENCES users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chats_users
(
    chat_id UUID REFERENCES chats(id) NOT NULL,
    user_id UUID REFERENCES users(id) NOT NULL,
    CONSTRAINT pk_chats_users PRIMARY KEY (chat_id, user_id)
);

CREATE TABLE IF NOT EXISTS reactions
(
    id                UUID PRIMARY KEY,
    payload            VARCHAR(255) NOT NULL,
    message_id UUID REFERENCES messages(id) NOT NULL,
    user_id    UUID REFERENCES users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS attachments
(
    id                UUID PRIMARY KEY,
    "path"             VARCHAR(255) NOT NULL,
    type              VARCHAR(10)  NOT NULL,
    message_id UUID REFERENCES messages(id)
);

CREATE INDEX idx_messages_chats_pinned ON messages(chat_id, sent_at DESC)
WHERE pinned = TRUE;
