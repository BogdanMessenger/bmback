CREATE TABLE attachment_entity
(
    id                UUID         NOT NULL,
    path              VARCHAR(255) NOT NULL,
    type              SMALLINT     NOT NULL,
    message_entity_id UUID,
    CONSTRAINT pk_attachmententity PRIMARY KEY (id)
);

CREATE TABLE avatar_entity
(
    id             UUID         NOT NULL,
    path           VARCHAR(255) NOT NULL,
    date           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_entity_id UUID,
    CONSTRAINT pk_avatarentity PRIMARY KEY (id)
);

CREATE TABLE chat_entity
(
    id UUID NOT NULL,
    CONSTRAINT pk_chatentity PRIMARY KEY (id)
);

CREATE TABLE chats_users
(
    chat_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT pk_chats_users PRIMARY KEY (chat_id, user_id)
);

CREATE TABLE message_entity
(
    id             UUID         NOT NULL,
    date           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    text           VARCHAR(255) NOT NULL,
    pinned         BOOLEAN      NOT NULL,
    forward_id     UUID,
    chat_entity_id UUID,
    author_id      UUID,
    CONSTRAINT pk_messageentity PRIMARY KEY (id)
);

CREATE TABLE reaction_entity
(
    id                UUID         NOT NULL,
    symbol            VARCHAR(255) NOT NULL,
    message_entity_id UUID,
    user_entity_id    UUID,
    CONSTRAINT pk_reactionentity PRIMARY KEY (id)
);

CREATE TABLE user_entity
(
    id       UUID         NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    tag      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_userentity PRIMARY KEY (id)
);

ALTER TABLE user_entity
    ADD CONSTRAINT uc_userentity_email UNIQUE (email);

ALTER TABLE user_entity
    ADD CONSTRAINT uc_userentity_password UNIQUE (password);

ALTER TABLE user_entity
    ADD CONSTRAINT uc_userentity_tag UNIQUE (tag);

ALTER TABLE attachment_entity
    ADD CONSTRAINT FK_ATTACHMENTENTITY_ON_MESSAGEENTITY FOREIGN KEY (message_entity_id) REFERENCES message_entity (id);

ALTER TABLE avatar_entity
    ADD CONSTRAINT FK_AVATARENTITY_ON_USERENTITY FOREIGN KEY (user_entity_id) REFERENCES user_entity (id);

ALTER TABLE message_entity
    ADD CONSTRAINT FK_MESSAGEENTITY_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES user_entity (id);

ALTER TABLE message_entity
    ADD CONSTRAINT FK_MESSAGEENTITY_ON_CHATENTITY FOREIGN KEY (chat_entity_id) REFERENCES chat_entity (id);

ALTER TABLE message_entity
    ADD CONSTRAINT FK_MESSAGEENTITY_ON_FORWARD FOREIGN KEY (forward_id) REFERENCES message_entity (id);

ALTER TABLE reaction_entity
    ADD CONSTRAINT FK_REACTIONENTITY_ON_MESSAGEENTITY FOREIGN KEY (message_entity_id) REFERENCES message_entity (id);

ALTER TABLE reaction_entity
    ADD CONSTRAINT FK_REACTIONENTITY_ON_USERENTITY FOREIGN KEY (user_entity_id) REFERENCES user_entity (id);

ALTER TABLE chats_users
    ADD CONSTRAINT fk_chause_on_chat_entity FOREIGN KEY (chat_id) REFERENCES chat_entity (id);

ALTER TABLE chats_users
    ADD CONSTRAINT fk_chause_on_user_entity FOREIGN KEY (user_id) REFERENCES user_entity (id);