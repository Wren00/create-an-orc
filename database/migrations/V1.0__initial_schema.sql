CREATE TABLE IF NOT EXISTS public.user_profile
(
    id serial constraint user_profile_pk primary key
);

CREATE TABLE IF NOT EXISTS public.users
(
    id bigserial constraint users_pk primary key,
    user_name text not null UNIQUE,
    email_address text not null UNIQUE,
    user_password text not null,
    available_tokens int not null,
    is_admin boolean NOT NULL,
    profile_id int constraint user_profile_fk references user_profile(id)
);

CREATE TABLE IF NOT EXISTS public.orc_head
(
    id serial constraint orc_head_images_pk primary key,
    url text not null
);

CREATE TABLE IF NOT EXISTS public.orc_torso
(
    id serial constraint orc_torso_images_pk primary key,
    url text not null
);

CREATE TABLE IF NOT EXISTS public.orc_legs
(
    id serial constraint orc_legs_images_pk primary key,
    url text not null
);

CREATE TABLE IF NOT EXISTS public.orc_images
(
    id bigserial constraint orc_images_pk primary key,
    head int constraint head_image_fk references orc_head(id),
    body int constraint body_image_fk references orc_torso(id),
    legs int constraint legs_image_fk references orc_legs(id)
);

CREATE TABLE IF NOT EXISTS public.background_images
(
    id serial constraint background_images_pk primary key,
    image_name text not null,
    url text not null
);

CREATE TABLE IF NOT EXISTS public.prompts
(
    id serial constraint prompts_pk primary key,
    adjectives text not null
);

CREATE TABLE IF NOT EXISTS public.prompts_collection
(
    id bigserial constraint prompts_collection_pk primary key,
    prompt1 int constraint prompt1_id_fk references prompts(id),
    prompt2 int constraint prompt2_id_fk references prompts(id),
    prompt3 int constraint prompt3_id_fk references prompts(id)
);

CREATE TABLE IF NOT EXISTS public.orcs
(
    id bigserial constraint orcs_pk primary key,
    name text not null,
    description text not null,
    prompts_collection_id bigserial constraint prompts_collection_id_fk references public.prompts_collection(id),
    orc_images_id bigserial constraint orc_images_id_fk references orc_images (id),
    user_id bigserial constraint user_id_fk references users(id)
);

CREATE TABLE IF NOT EXISTS public.catalogue
(
    id bigserial constraint catalogue_pk primary key,
    syllables text not null
);

CREATE TABLE IF NOT EXISTS public.orc_catalogue
(
    id bigserial constraint orc_catalogue_pk primary key,
    orc_id bigserial constraint orc_id_fk references public.orcs(id)
);
