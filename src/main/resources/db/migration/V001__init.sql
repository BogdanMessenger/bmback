CREATE TABLE IF NOT EXISTS users
(
    id         UUID PRIMARY KEY,
    email      VARCHAR(100)                NOT NULL UNIQUE,
    password   VARCHAR(255)                NOT NULL,
    fio        VARCHAR(150)                NOT NULL,
    nickname   VARCHAR(150)                NOT NULL,
    tag        VARCHAR(20)                 NOT NULL UNIQUE,
    last_entry TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS auth_codes
(
    id          uuid PRIMARY KEY,
    user_id     uuid       NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    passcode    varchar(6) NOT NULL,
    expire_date bigint     NOT NULL
);

CREATE TABLE IF NOT EXISTS chats
(
    id             UUID PRIMARY KEY,
    name           VARCHAR(150),
    is_group       BOOL                        NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by     UUID REFERENCES users (id),
    thread_root_id UUID
);

CREATE TABLE IF NOT EXISTS avatars
(
    id          UUID PRIMARY KEY,
    "path"      TEXT                        NOT NULL,
    uploaded_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id     UUID REFERENCES users (id),
    chat_id     UUID REFERENCES chats (id)
);

CREATE TABLE IF NOT EXISTS messages
(
    id              UUID PRIMARY KEY,
    sent_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    payload         TEXT                        NOT NULL,
    pinned          BOOLEAN                     NOT NULL,
    has_attachments BOOLEAN                     NOT NULL,
    has_reactions   BOOLEAN                     NOT NULL,
    forward_id      UUID REFERENCES messages (id),
    chat_id         UUID REFERENCES chats (id)  NOT NULL,
    author_id       UUID REFERENCES users (id)  NOT NULL
);

ALTER TABLE chats
    ADD CONSTRAINT fk_chats_thread_root FOREIGN KEY (thread_root_id) REFERENCES messages (id);

CREATE TABLE IF NOT EXISTS chats_users
(
    chat_id UUID REFERENCES chats (id) NOT NULL,
    user_id UUID REFERENCES users (id) NOT NULL,
    CONSTRAINT pk_chats_users PRIMARY KEY (chat_id, user_id)
);

CREATE TABLE IF NOT EXISTS reactions_handbook
(
    id      UUID PRIMARY KEY,
    payload VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS reactions
(
    id                   UUID PRIMARY KEY,
    position             SMALLINT                                NOT NULL,
    reaction_handbook_id UUID REFERENCES reactions_handbook (id) NOT NULL,
    message_id           UUID REFERENCES messages (id)           NOT NULL,
    user_id              UUID REFERENCES users (id)              NOT NULL
);

CREATE TABLE IF NOT EXISTS attachments
(
    id         UUID PRIMARY KEY,
    "path"     VARCHAR(255) NOT NULL,
    type       VARCHAR(10)  NOT NULL,
    message_id UUID REFERENCES messages (id)
);

CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_avatars_email ON avatars (path);
CREATE INDEX idx_messages_chats_pinned ON messages (chat_id, sent_at DESC)
WHERE pinned = TRUE;
