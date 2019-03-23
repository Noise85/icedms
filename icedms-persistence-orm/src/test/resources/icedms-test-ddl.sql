CREATE TABLE document (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, archive_date DATE, confidential BOOLEAN NOT NULL, creation_date DATE NOT NULL, creation_user VARCHAR(255) NOT NULL, last_revision VARCHAR(255) NOT NULL, modification_date DATE, modification_user VARCHAR(255), name VARCHAR(255) NOT NULL, status VARCHAR(255) NOT NULL, document_type_id BIGINT, language_id BIGINT, PRIMARY KEY (id));
CREATE TABLE document_group (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, code VARCHAR(255) NOT NULL, creation_date DATE NOT NULL, creation_user VARCHAR(255) NOT NULL, description VARCHAR(255), modification_date DATE, modification_user VARCHAR(255), PRIMARY KEY (id));
CREATE TABLE document_type (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, code VARCHAR(255) NOT NULL, creation_date DATE NOT NULL, creation_user VARCHAR(255) NOT NULL, description VARCHAR(255), modification_date DATE, modification_user VARCHAR(255), PRIMARY KEY (id));
CREATE TABLE document_version (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, content LONGVARBINARY, creation_date DATE NOT NULL, creation_user VARCHAR(255) NOT NULL, lock_time DATE, max_lock_time BIGINT, modification_date DATE, modification_user VARCHAR(255), revision_number VARCHAR(255), document_id BIGINT NOT NULL, lock_user_id BIGINT, mimetype_id BIGINT, PRIMARY KEY (id));
CREATE TABLE document_index (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, text_value VARCHAR(255) NOT NULL, document_id BIGINT NOT NULL, index_type_id BIGINT NOT NULL, PRIMARY KEY (id));
CREATE TABLE index_type (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, code VARCHAR(255) NOT NULL, description VARCHAR(255), validation_rule VARCHAR(255) NOT NULL, PRIMARY KEY (id));
CREATE TABLE index_type_document_type (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, mandatory BOOLEAN NOT NULL, multivalued BOOLEAN NOT NULL, document_type_id BIGINT NOT NULL, index_type_id BIGINT NOT NULL, PRIMARY KEY (id));
CREATE TABLE document_language (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, code VARCHAR(255) NOT NULL, description VARCHAR(255), iso_code VARCHAR(255) NOT NULL, PRIMARY KEY (id));
CREATE TABLE mimetype (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, code VARCHAR(255) NOT NULL, description VARCHAR(255), iso_code VARCHAR(255) NOT NULL, PRIMARY KEY (id));
CREATE TABLE sys_user (id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL, creation_date DATE NOT NULL, creation_user VARCHAR(255) NOT NULL, email VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255), modification_date DATE, modification_user VARCHAR(255), password VARCHAR(255), pseudo VARCHAR(255), PRIMARY KEY (id));
CREATE TABLE document_type_document_group (document_type_id BIGINT NOT NULL, document_group_id BIGINT NOT NULL, PRIMARY KEY (document_type_id, document_group_id));
ALTER TABLE document_group ADD CONSTRAINT UNQ_document_group_1 UNIQUE (code);
ALTER TABLE document_type ADD CONSTRAINT UNQ_document_type_1 UNIQUE (code);
ALTER TABLE index_type ADD CONSTRAINT UNQ_index_type_1 UNIQUE (code);
ALTER TABLE document ADD CONSTRAINT FK_document_language_id FOREIGN KEY (language_id) REFERENCES document_language (id);
ALTER TABLE document ADD CONSTRAINT FK_document_document_type_id FOREIGN KEY (document_type_id) REFERENCES document_type (id);
ALTER TABLE document_version ADD CONSTRAINT FK_document_version_document_id FOREIGN KEY (document_id) REFERENCES document (id);
ALTER TABLE document_version ADD CONSTRAINT FK_document_version_mimetype_id FOREIGN KEY (mimetype_id) REFERENCES mimetype (id);
ALTER TABLE document_version ADD CONSTRAINT FK_document_version_lock_user_id FOREIGN KEY (lock_user_id) REFERENCES sys_user (id);
ALTER TABLE document_index ADD CONSTRAINT FK_document_index_index_type_id FOREIGN KEY (index_type_id) REFERENCES index_type (id);
ALTER TABLE document_index ADD CONSTRAINT FK_document_index_document_id FOREIGN KEY (document_id) REFERENCES document (id);
ALTER TABLE index_type_document_type ADD CONSTRAINT FK_index_type_document_type_index_type_id FOREIGN KEY (index_type_id) REFERENCES index_type (id);
ALTER TABLE index_type_document_type ADD CONSTRAINT FK_index_type_document_type_document_type_id FOREIGN KEY (document_type_id) REFERENCES document_type (id);
ALTER TABLE document_type_document_group ADD CONSTRAINT FK_document_type_document_group_document_type_id FOREIGN KEY (document_type_id) REFERENCES document_group (id);
ALTER TABLE document_type_document_group ADD CONSTRAINT FK_document_type_document_group_document_group_id FOREIGN KEY (document_group_id) REFERENCES document_type (id);