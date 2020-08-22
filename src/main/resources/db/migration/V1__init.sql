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

create table if not exists public.banks
(
    id bigint not null constraint banks_pkey primary key,
    bank_name varchar(255)
);

create table if not exists public.bank_accounts
(
    id bigint not null constraint bank_accounts_pkey primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    account_owner text,
    balance double precision not null constraint bank_accounts_balance_check check (balance >= (0)::double precision ),
    card_number text,
    cvc text,
    expiry_date timestamp not null,
    bank_id bigint not null constraint fk8ngd2pjw12xdt5wasywldwjy3 references banks on delete cascade
);

create table if not exists public.bank_account_users
(
    id bigint not null constraint bank_account_users_pkey primary key,
    application_user_id bigint not null constraint fkjvnkuxpowqf9445fnwobvub83 references application_users on delete cascade,
    bank_account_id bigint not null constraint uk_emykmc2ulup7oy6tje2l4u1vx unique constraint fk74pijj9nly4jh4lu9pauqjwmy references bank_accounts on delete cascade
);

create table if not exists public.parking_lots
(
    id bigint not null constraint parking_lots_pkey primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    zone_code text,
    address text,
    free_spots integer,
    price double,
    max_stoptime integer
);

INSERT INTO public.roles (id, name) VALUES (1, 'ROLE_ADMIN') ON CONFLICT ON CONSTRAINT roles_pkey DO NOTHING;
INSERT INTO public.roles (id, name) VALUES (2, 'ROLE_USER') ON CONFLICT ON CONSTRAINT roles_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(1, '2020-04-25 14:45:36.674000','2020-03-24 14:45:36.674000',
 'What is your favourite animal?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
(2, '2020-04-25 14:45:36.674000','2020-04-25 14:45:36.674000',
 'What were the last four digits of your childhood telephone number?') ON CONFLICT ON CONSTRAINT questions_pkey DO NOTHING;

INSERT INTO public.questions (id, created_at, updated_at, title) VALUES
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

INSERT INTO public.banks (id, bank_name) VALUES (1, 'UniCredit Bank') ON CONFLICT ON CONSTRAINT banks_pkey DO NOTHING;
INSERT INTO public.banks (id, bank_name) VALUES (2, 'Raiffeisen Bank') ON CONFLICT ON CONSTRAINT banks_pkey DO NOTHING;
INSERT INTO public.banks (id, bank_name) VALUES (3, 'Sparkasse Bank') ON CONFLICT ON CONSTRAINT banks_pkey DO NOTHING;
INSERT INTO public.banks (id, bank_name) VALUES (4, 'Sberbank BH') ON CONFLICT ON CONSTRAINT banks_pkey DO NOTHING;
INSERT INTO public.banks (id, bank_name) VALUES (5, 'ZiraatBank BH') ON CONFLICT ON CONSTRAINT banks_pkey DO NOTHING;

INSERT INTO public.parking_lots (id, created_at, updated_at, zone_code, address, free_spots, price, max_stoptime) VALUES
(1, '2020-08-20 14:38:20.674000', '2020-08-20 14:38:20.674000', "101", "Džemala Bijedića 101", 20, 2.5, 300);

INSERT INTO public.parking_lots (id, created_at, updated_at, zone_code, address, free_spots, price, max_stoptime) VALUES
(2, '2020-08-20 14:38:20.674000', '2020-08-20 14:38:20.674000', "102", "Zmaja od Bosne 20", 20, 2.5, 300);

INSERT INTO public.parking_lots (id, created_at, updated_at, zone_code, address, free_spots, price, max_stoptime) VALUES
(3, '2020-08-20 14:38:20.674000', '2020-08-20 14:38:20.674000', "103", "Džemala Čelića 44", 20, 2.5, 300);

INSERT INTO public.parking_lots (id, created_at, updated_at, zone_code, address, free_spots, price, max_stoptime) VALUES
(4, '2020-08-20 14:38:20.674000', '2020-08-20 14:38:20.674000', "104", "Neka ulica", 20, 2.5, 300);

INSERT INTO public.parking_lots (id, created_at, updated_at, zone_code, address, free_spots, price, max_stoptime) VALUES
(5, '2020-08-20 14:38:20.674000', '2020-08-20 14:38:20.674000', "105", "Neka druga ulica", 20, 2.5, 240);

