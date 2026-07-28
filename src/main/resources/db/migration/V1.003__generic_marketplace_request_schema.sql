drop table if exists onboarding_request;
drop table if exists publish_request;
drop table if exists new_api_request;
drop table if exists contact_request;

create table marketplace_request (
    id              uuid        primary key not null,
    type            text        not null,
    payload         text        not null,
    status          text        not null,
    submitted_at    timestamp   not null
);
