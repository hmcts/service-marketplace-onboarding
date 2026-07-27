create table publish_request (
    id              uuid        primary key not null,
    organisation    text        not null,
    email           text        not null,
    job_title       text        not null,
    phone           text,
    api_name        text        not null,
    repo_name       text        not null,
    version         text        not null,
    domain          text        not null,
    classification  text        not null,
    description     text        not null,
    status          text        not null,
    submitted_at    timestamp   not null
);

create table new_api_request (
    id                   uuid        primary key not null,
    organisation         text        not null,
    email                text        not null,
    job_title            text        not null,
    phone                text,
    need                 text        not null,
    domain               text        not null,
    urgency              text        not null,
    existing_workaround  text,
    status               text        not null,
    submitted_at         timestamp   not null
);

create table contact_request (
    id              uuid        primary key not null,
    organisation    text        not null,
    email           text        not null,
    topic           text        not null,
    message         text        not null,
    status          text        not null,
    submitted_at    timestamp   not null
);
