INSERT INTO users (user_name, email_address, user_password, available_tokens, admin_privileges, profile_id)
VALUES
    ('testadmin', 'loren.walsh@unosquare.com', 'adminorc', 30,
     1, 1),
    ('test', 'testaccount@gmail.com', 'test', 1,
     0, 2);

INSERT INTO background_images (image_name, url) VALUES
          ('background', 's3://create-an-orc-images/Misc Images/Background.png'),
          ('orc_base', 's3://create-an-orc-images/Misc Images/Orc_Base.png');

INSERT INTO character_body_part_image_types (name) VALUES
            ('head'), ('body'),('legs');

INSERT INTO character_body_part_images (url, character_body_part_type) VALUES
            ('s3://create-an-orc-images/Head Images/Head1.png', 1),
            ('s3://create-an-orc-images/Head Images/Head2.png', 1),
            ('s3://create-an-orc-images/Head Images/Head3.png', 1),
            ('s3://create-an-orc-images/Head Images/Head4.png', 1),
            ('s3://create-an-orc-images/Head Images/Head5.png', 1),
            ('s3://create-an-orc-images/Head Images/Head6.png', 1),
            ('s3://create-an-orc-images/Body Images/Body1.png', 2),
            ('s3://create-an-orc-images/Body Images/Body2.png', 2),
            ('s3://create-an-orc-images/Body Images/Body3.png', 2),
            ('s3://create-an-orc-images/Body Images/Body4.png', 2),
            ('s3://create-an-orc-images/Body Images/Body5.png', 2),
            ('s3://create-an-orc-images/Body Images/Legs1.png', 3),
            ('s3://create-an-orc-images/Body Images/Legs2.png', 3),
            ('s3://create-an-orc-images/Body Images/Legs3.png', 3),
            ('s3://create-an-orc-images/Body Images/Legs4.png', 3),
            ('s3://create-an-orc-images/Body Images/Legs5.png', 3);

INSERT INTO catalogue (syllables) VALUES
            ('gak'), ('gik'), ('guk'), ('gek'), ('gok'),
             ('kak'), ('kik'), ('kuk'), ('kek'), ('kok'),
             ('mak'), ('mik'), ('muk'), ('mek'), ('mok'),
             ('nak'), ('nik'), ('nuk'), ('nek'), ('nok'),
             ('brak'), ('brik'), ('bruk'), ('brek'), ('brok'),
             ('lak'), ('lik'), ('luk'), ('lek'), ('lok'),
             ('hak'), ('hik'), ('huk'), ('hek'), ('hok'),
             ('gak'), ('gok'), ('guk'),
             ('gar'), ('gor'), ('gur'),
             ('har'), ('hor'), ('hur'),
             ('ga'), ('ra'), ('ma'), ('na'), ('ha'),
             ('go'), ('ro'), ('mo'), ('no'), ('ho'),
             ('ge'), ('re'), ('me'), ('ne'), ('he');

INSERT INTO prompts (adjectives) VALUES
             ('good'), ('evil'), ('lawful'), ('chaotic'), ('mean'),
             ('adventurous'), ('shy'), ('kind'), ('generous'), ('angry'),
             ('cruel'), ('cautious'), ('clever'), ('selfish'), ('sneaky'),
             ('reliable'), ('ugly'), ('honest'), ('weak'), ('lazy'), ('beautiful');



