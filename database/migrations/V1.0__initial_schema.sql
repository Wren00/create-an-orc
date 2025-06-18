CREATE TABLE IF NOT EXISTS public.user_profile
(
    id serial constraint user_profile_pk primary key
);

CREATE TABLE IF NOT EXISTS public.users
(
    id serial constraint users_pk primary key,
    user_name text not null UNIQUE,
    email_address text not null UNIQUE,
    user_password text not null,
    available_tokens int not null,
    admin_privileges bit not null,
    profile_id int constraint user_profile_fk references user_profile(id)
);

CREATE TABLE IF NOT EXISTS public.character_body_part_image_types
(
    id serial constraint character_body_part_image_types_pk primary key,
    name text not null
);

CREATE TABLE IF NOT EXISTS public.character_body_part_images
(
    id serial constraint character_body_part_images_pk primary key,
    url text not null,
    character_body_part_type int constraint character_body_part_types_fk references character_body_part_image_types(id)
);

CREATE TABLE IF NOT EXISTS public.character_images
(
    id serial constraint character_images_pk primary key,
    head int constraint head_image_fk references character_body_part_images(id),
    body int constraint body_image_fk references character_body_part_images(id),
    legs int constraint legs_image_fk references character_body_part_images(id)
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
    id serial constraint prompts_collection_pk primary key,
    prompt1 text not null,
    prompt2 text not null,
    prompt3 text not null
);

CREATE TABLE IF NOT EXISTS public.characters
(
    id serial constraint characters_pk primary key,
    name text not null,
    prompts_collection_id int constraint prompts_collection_id_fk references public.prompts_collection(id),
    character_images_id int constraint character_images_id_fk references character_images(id),
    user_id int constraint user_id_fk references users(id)
);

CREATE TABLE IF NOT EXISTS public.catalogue
(
    id serial constraint catalogue_pk primary key,
    syllables text not null
);

CREATE TABLE IF NOT EXISTS public.character_catalogue
(
    id serial constraint character_catalogue_pk primary key,
    character_id int constraint character_id_fk references public.characters(id)
);
