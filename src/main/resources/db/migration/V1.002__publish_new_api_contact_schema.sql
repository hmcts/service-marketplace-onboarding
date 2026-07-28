drop table if exists onboarding_request;

create table marketplace_request (
    id              uuid        primary key not null,
    type            text        not null,
    payload         text        not null,
    status          text        not null,
    submitted_at    timestamp   not null
);
