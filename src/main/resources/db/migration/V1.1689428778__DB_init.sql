create table if not exists public.application_user(
    id bigserial primary key,
    username varchar not null,
    user_password varchar not null,
    e_mail varchar not null
);

create table if not exists public.user_role(
    id bigserial primary key,
    role_title varchar not null
);

create table if not exists public.letter(
    id bigserial primary key,
    created_at timestamp not null,
    letter_text text not null
);

create table if not exists public.m2m_user_letter(
    user_id bigint references public.application_user (id),
    letter_id bigint references public.letter (id)
);

create table if not exists public.m2m_user_role(
    user_id bigint references public.application_user (id),
    role_id bigint references public.user_role (id)
);