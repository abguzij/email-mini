create table if not exists public.incoming_letters(
    id bigserial primary key,
    is_read boolean not null default false,
    user_id bigint references public.application_user(id),
    letter_id bigint references public.letter(id)
);