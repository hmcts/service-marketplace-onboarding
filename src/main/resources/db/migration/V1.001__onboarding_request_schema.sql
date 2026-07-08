create table onboarding_request (
    id              uuid        primary key not null,
    name            text        not null,
    organisation    text        not null,
    email           text        not null,
    job_title       text        not null,
    phone           text,
    api_requested   text        not null,
    environment     text        not null,
    call_volume     text,
    use_case        text        not null,
    status          text        not null,
    submitted_at    timestamp   not null
);
