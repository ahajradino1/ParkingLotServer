create table if not exists public.questions
(
    id          bigint    not null
        constraint questions_pkey
            primary key,
    created_at  timestamp not null,
    updated_at  timestamp not null,
    description text,
    title       varchar(100)
);
create table if not exists public.roles
(
    id   bigserial not null
        constraint roles_pkey
            primary key,
    name varchar(60)
        constraint uk_nb4h0p6txrmfc0xbrd1kglp9t
            unique
);

create table if not exists public.answers
(
    id          bigint    not null
        constraint answers_pkey
            primary key,
    created_at  timestamp not null,
    updated_at  timestamp not null,
    text        text,
    question_id bigint    not null
        constraint fk3erw1a3t0r78st8ty27x6v3g1
            references questions
            on delete cascade
);


create table if not exists public.application_users
(
    id         bigint    not null
        constraint application_users_pkey
            primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    email      varchar(255)
        constraint uk_ha8ehjd5vlqolvals08ssh7o0
            unique,
    first_name text,
    last_name  text,
    password   varchar(100),
    username   varchar(255),
    answer_id  bigint    not null
        constraint uk_ortc4unvsh9wn07pt9d3nv2n4
            unique
        constraint fk8k1bh0dvfnphvin7adchxs1fm
            references answers
            on delete cascade
);

create table if not exists public.user_roles
(
    user_id bigint not null
        constraint fkhm3fc8664fichr5tu1u9566il
            references application_users,
    role_id bigint not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    constraint user_roles_pkey
        primary key (user_id, role_id)
);

INSERT INTO public.roles (id, name) VALUES (1, 'ROLE_ADMIN') ON CONFLICT ON CONSTRAINT roles_pkey DO NOTHING;
INSERT INTO public.roles (id, name) VALUES (2, 'ROLE_USER') ON CONFLICT ON CONSTRAINT roles_pkey DO NOTHING;

INSERT INTO questions (id, created_at, updated_at, title) VALUES
(1, '2020-04-25 14:45:36.674000','2020-03-24 14:45:36.674000',
 'What is your favourite animal?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO questions (id, created_at, updated_at, title) VALUES
(2, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'What were the last four digits of your childhood telephone number?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO questions (id, created_at, updated_at, title) VALUES
(3, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'In what town or city did you meet your spouse/partner?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(4, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'In what town or city was your first full time job?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(5, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'What are the last five digits of your drivers licence number?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(6, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'What is your grandmothers (on your mothers side) maiden name?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(7, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'What is your spouse or partners mothers maiden name?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(8, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'What time of the day were you born? (hh:mm)') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(9, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'What time of the day was your first child born? (hh:mm)') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(10, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'What was the house number and street name you lived in as a child?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;
