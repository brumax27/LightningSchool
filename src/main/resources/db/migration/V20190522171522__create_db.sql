create table lightning_school.constante (
                                            id_constante integer not null auto_increment,
                                            id_t_constante integer,
                                            libelle varchar(255),
                                            value varchar(255),
                                            primary key (id_constante)
);

create table lightning_school.cours (
                                        id_cours integer not null auto_increment,
                                        libelle_cours varchar(255),
                                        deadline datetime,
                                        primary key (id_cours)
);

create table lightning_school.cours_exercices (
                                                  cours_id_cours integer not null,
                                                  exercices_id_exercice integer not null
);

create table lightning_school.cours_medias (
                                               cours_id_cours integer not null,
                                               medias_id_media integer not null
);

create table lightning_school.cours_pages (
                                              cours_id_cours integer not null,
                                              pages_id_page integer not null
);

create table lightning_school.cours_sections (
                                                 cours_id_cours integer not null,
                                                 sections_id_section integer not null
);

create table lightning_school.exercice (
                                           id_exercice integer not null auto_increment,
                                           coefficient float,
                                           libelle_exercice varchar(255),
                                           path varchar(255),
                                           id_t_exercice integer,
                                           primary key (id_exercice)
);

create table lightning_school.exercice_cours (
                                                 exercice_id_exercice integer not null,
                                                 cours_id_cours integer not null
);

create table lightning_school.media (
                                        id_media integer not null auto_increment,
                                        libelle varchar(255),
                                        path varchar(255),
                                        primary key (id_media)
);

create table lightning_school.media_cours (
                                              media_id_media integer not null,
                                              cours_id_cours integer not null
);

create table lightning_school.message (
                                          id_message integer not null auto_increment,
                                          message varchar(255),
                                          primary key (id_message)
);

create table lightning_school.page (
                                       id_page integer not null auto_increment,
                                       contenu_page varchar(255),
                                       primary key (id_page)
);

create table lightning_school.page_cours (
                                             page_id_page integer not null,
                                             cours_id_cours integer not null
);

create table lightning_school.section (
                                          id_section integer not null auto_increment,
                                          year_promotion integer,
                                          libelle_section varchar(255),
                                          primary key (id_section)
);

create table lightning_school.section_users (
                                                section_id_section integer not null,
                                                users_user_id integer not null
);

create table lightning_school.user (
                                       user_id integer not null auto_increment,
                                       mail varchar(255) not null,
                                       name varchar(255),
                                       password varchar(255),
                                       surname varchar(255),
                                       id_t_user integer,
                                       photo_path varchar(255),
                                       primary key (user_id)
);

create table lightning_school.user_exercice (
                                                id integer not null auto_increment,
                                                mark float,
                                                path_rendu varchar(255),
                                                user_user_id integer,
                                                exercice_id_exercice integer,
                                                primary key (id)
);


create table lightning_school.user_message (
                                               id_user_message integer not null auto_increment,
                                               message_id_message integer,
                                               receiver_user_id integer,
                                               sender_user_id integer,
                                               primary key (id_user_message)
);

create table lightning_school.user_sections (
                                                user_user_id integer not null,
                                                sections_id_section integer not null
);

alter table lightning_school.cours_exercices
    add constraint FKiwevjei02evewh5ldrvfo7uq1
        foreign key (exercices_id_exercice)
            references lightning_school.exercice (id_exercice);

alter table lightning_school.cours_exercices
    add constraint FK8x0pfexcwxtqgpma91xbdiwdx
        foreign key (cours_id_cours)
            references lightning_school.cours (id_cours);

alter table lightning_school.cours_medias
    add constraint FKpw76t4wkiwyc5jxl6xqqpvedw
        foreign key (medias_id_media)
            references lightning_school.media (id_media);

alter table lightning_school.cours_medias
    add constraint FK7ieldfn3w9eg8ovy83hac3wp9
        foreign key (cours_id_cours)
            references lightning_school.cours (id_cours);

alter table lightning_school.cours_pages
    add constraint FKl5qacsmckc4g9ioqfy1elhipf
        foreign key (pages_id_page)
            references lightning_school.page (id_page);

alter table lightning_school.cours_pages
    add constraint FKnwquduaf82ee26u63nbuww2st
        foreign key (cours_id_cours)
            references lightning_school.cours (id_cours);

alter table lightning_school.cours_sections
    add constraint FKc7otfhkitufi4u28qr2bgnmxp
        foreign key (sections_id_section)
            references lightning_school.section (id_section);

alter table lightning_school.cours_sections
    add constraint FKn7v74527upmj7kqetxya0x89
        foreign key (cours_id_cours)
            references lightning_school.cours (id_cours);

alter table lightning_school.exercice_cours
    add constraint FK9nj2fq5viq5pcevx4q1p4h5ao
        foreign key (cours_id_cours)
            references lightning_school.cours (id_cours);

alter table lightning_school.exercice_cours
    add constraint FKj3ja5m7vedryaeloo4f07kwbm
        foreign key (exercice_id_exercice)
            references lightning_school.exercice (id_exercice);

alter table lightning_school.media_cours
    add constraint FKabjtf1ctbok327r8p8w6yfxqh
        foreign key (cours_id_cours)
            references lightning_school.cours (id_cours);

alter table lightning_school.media_cours
    add constraint FKb7o2j4nbue1ms96ydjcegsiv2
        foreign key (media_id_media)
            references lightning_school.media (id_media);

alter table lightning_school.page_cours
    add constraint FK6463q3gw9r7s5eo8rmbik3o8b
        foreign key (cours_id_cours)
            references lightning_school.cours (id_cours);

alter table lightning_school.page_cours
    add constraint FK9nim27bsq62618fwlwjm38e5c
        foreign key (page_id_page)
            references lightning_school.page (id_page);

alter table lightning_school.section_users
    add constraint FKp8kkix6gv9vm8rord567y5ogb
        foreign key (users_user_id)
            references lightning_school.user (user_id);

alter table lightning_school.section_users
    add constraint FK642hxs2xdvkoyk7153owtqb2v
        foreign key (section_id_section)
            references lightning_school.section (id_section);

alter table lightning_school.user_exercice
    add constraint FK2n5jkklvcr2kxcibl22xa2x2j
        foreign key (user_user_id)
            references lightning_school.user (user_id);

alter table lightning_school.user_exercice
    add constraint FKo4cq9qatsi7poqfmctlxtjujd
        foreign key (exercice_id_exercice)
            references lightning_school.exercice (id_exercice);

alter table lightning_school.user_message
    add constraint FKljeuwubbn73q87vywqmtj88p2
        foreign key (message_id_message)
            references lightning_school.message (id_message);

alter table lightning_school.user_message
    add constraint FKr4foqow3phwmvao8km3sh8vp5
        foreign key (receiver_user_id)
            references lightning_school.user (user_id);

alter table lightning_school.user_message
    add constraint FKdq2kp4tu8g3kot081mhfy9ckv
        foreign key (sender_user_id)
            references lightning_school.user (user_id);

alter table lightning_school.user_sections
    add constraint FKgfrk6ua5atn08oys0fnbisafq
        foreign key (sections_id_section)
            references lightning_school.section (id_section);

alter table lightning_school.user_sections
    add constraint FKo9avckcyb3ihw3yhp96bq7xvr
        foreign key (user_user_id)
            references lightning_school.user (user_id);