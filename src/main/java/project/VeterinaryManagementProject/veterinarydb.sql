PGDMP       4                |            veterinarydb    16.3    16.3 =    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    49921    veterinarydb    DATABASE     �   CREATE DATABASE veterinarydb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1254';
    DROP DATABASE veterinarydb;
                postgres    false            �            1259    50252    animal    TABLE     >  CREATE TABLE public.animal (
    id bigint NOT NULL,
    animal_breed character varying(255),
    animal_colour character varying(255),
    animal_date_of_birth date,
    animal_gender character varying(255),
    animal_name character varying(255),
    animal_species character varying(255),
    customer_id bigint
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    50251    animal_id_seq    SEQUENCE     v   CREATE SEQUENCE public.animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.animal_id_seq;
       public          postgres    false    216            �           0    0    animal_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.animal_id_seq OWNED BY public.animal.id;
          public          postgres    false    215            �            1259    50261    appointment    TABLE     �   CREATE TABLE public.appointment (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id bigint,
    doctor_id bigint
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    50260    appointment_id_seq    SEQUENCE     {   CREATE SEQUENCE public.appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.appointment_id_seq;
       public          postgres    false    218            �           0    0    appointment_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.appointment_id_seq OWNED BY public.appointment.id;
          public          postgres    false    217            �            1259    50268    customer    TABLE       CREATE TABLE public.customer (
    customer_id bigint NOT NULL,
    customer_address character varying(255),
    customer_city character varying(50),
    customer_email character varying(100),
    customer_name character varying(100),
    customer_phone character varying(20)
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    50267    customer_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.customer_customer_id_seq;
       public          postgres    false    220            �           0    0    customer_customer_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.customer_customer_id_seq OWNED BY public.customer.customer_id;
          public          postgres    false    219            �            1259    50277    doctor    TABLE       CREATE TABLE public.doctor (
    doctor_id bigint NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_email character varying(255),
    doctor_name character varying(255),
    doctor_phone character varying(255)
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    50276    doctor_doctor_id_seq    SEQUENCE     }   CREATE SEQUENCE public.doctor_doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.doctor_doctor_id_seq;
       public          postgres    false    222            �           0    0    doctor_doctor_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.doctor_doctor_id_seq OWNED BY public.doctor.doctor_id;
          public          postgres    false    221            �            1259    50286    report    TABLE     �   CREATE TABLE public.report (
    id bigint NOT NULL,
    diagnosis character varying(255),
    price double precision NOT NULL,
    title character varying(255),
    appointment_id bigint
);
    DROP TABLE public.report;
       public         heap    postgres    false            �            1259    50285    report_id_seq    SEQUENCE     v   CREATE SEQUENCE public.report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.report_id_seq;
       public          postgres    false    224            �           0    0    report_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.report_id_seq OWNED BY public.report.id;
          public          postgres    false    223            �            1259    50295    vaccination    TABLE     �   CREATE TABLE public.vaccination (
    id bigint NOT NULL,
    code character varying(255),
    name character varying(255),
    protection_finish_date date,
    protection_start_date date,
    animal_id bigint,
    report_id bigint
);
    DROP TABLE public.vaccination;
       public         heap    postgres    false            �            1259    50294    vaccination_id_seq    SEQUENCE     {   CREATE SEQUENCE public.vaccination_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.vaccination_id_seq;
       public          postgres    false    226            �           0    0    vaccination_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.vaccination_id_seq OWNED BY public.vaccination.id;
          public          postgres    false    225            �            1259    50304    work_day    TABLE     c   CREATE TABLE public.work_day (
    id bigint NOT NULL,
    work_date date,
    doctor_id bigint
);
    DROP TABLE public.work_day;
       public         heap    postgres    false            �            1259    50303    work_day_id_seq    SEQUENCE     x   CREATE SEQUENCE public.work_day_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.work_day_id_seq;
       public          postgres    false    228            �           0    0    work_day_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.work_day_id_seq OWNED BY public.work_day.id;
          public          postgres    false    227            8           2604    50255 	   animal id    DEFAULT     f   ALTER TABLE ONLY public.animal ALTER COLUMN id SET DEFAULT nextval('public.animal_id_seq'::regclass);
 8   ALTER TABLE public.animal ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            9           2604    50264    appointment id    DEFAULT     p   ALTER TABLE ONLY public.appointment ALTER COLUMN id SET DEFAULT nextval('public.appointment_id_seq'::regclass);
 =   ALTER TABLE public.appointment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            :           2604    50271    customer customer_id    DEFAULT     |   ALTER TABLE ONLY public.customer ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_customer_id_seq'::regclass);
 C   ALTER TABLE public.customer ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    219    220    220            ;           2604    50280    doctor doctor_id    DEFAULT     t   ALTER TABLE ONLY public.doctor ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctor_doctor_id_seq'::regclass);
 ?   ALTER TABLE public.doctor ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    221    222    222            <           2604    50289 	   report id    DEFAULT     f   ALTER TABLE ONLY public.report ALTER COLUMN id SET DEFAULT nextval('public.report_id_seq'::regclass);
 8   ALTER TABLE public.report ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            =           2604    50298    vaccination id    DEFAULT     p   ALTER TABLE ONLY public.vaccination ALTER COLUMN id SET DEFAULT nextval('public.vaccination_id_seq'::regclass);
 =   ALTER TABLE public.vaccination ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225    226            >           2604    50307    work_day id    DEFAULT     j   ALTER TABLE ONLY public.work_day ALTER COLUMN id SET DEFAULT nextval('public.work_day_id_seq'::regclass);
 :   ALTER TABLE public.work_day ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227    228            �          0    50252    animal 
   TABLE DATA           �   COPY public.animal (id, animal_breed, animal_colour, animal_date_of_birth, animal_gender, animal_name, animal_species, customer_id) FROM stdin;
    public          postgres    false    216   G       �          0    50261    appointment 
   TABLE DATA           Q   COPY public.appointment (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public          postgres    false    218   _H       �          0    50268    customer 
   TABLE DATA              COPY public.customer (customer_id, customer_address, customer_city, customer_email, customer_name, customer_phone) FROM stdin;
    public          postgres    false    220   �H       �          0    50277    doctor 
   TABLE DATA           q   COPY public.doctor (doctor_id, doctor_address, doctor_city, doctor_email, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    222   �J       �          0    50286    report 
   TABLE DATA           M   COPY public.report (id, diagnosis, price, title, appointment_id) FROM stdin;
    public          postgres    false    224   �L       �          0    50295    vaccination 
   TABLE DATA           z   COPY public.vaccination (id, code, name, protection_finish_date, protection_start_date, animal_id, report_id) FROM stdin;
    public          postgres    false    226   �M       �          0    50304    work_day 
   TABLE DATA           <   COPY public.work_day (id, work_date, doctor_id) FROM stdin;
    public          postgres    false    228   TN                   0    0    animal_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.animal_id_seq', 12, true);
          public          postgres    false    215                       0    0    appointment_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.appointment_id_seq', 10, true);
          public          postgres    false    217                       0    0    customer_customer_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customer_customer_id_seq', 14, true);
          public          postgres    false    219                       0    0    doctor_doctor_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doctor_doctor_id_seq', 13, true);
          public          postgres    false    221                       0    0    report_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.report_id_seq', 8, true);
          public          postgres    false    223                       0    0    vaccination_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.vaccination_id_seq', 7, true);
          public          postgres    false    225                       0    0    work_day_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.work_day_id_seq', 13, true);
          public          postgres    false    227            @           2606    50259    animal animal_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    216            B           2606    50266    appointment appointment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    218            D           2606    50275    customer customer_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    220            F           2606    50284    doctor doctor_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (doctor_id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    222            H           2606    50293    report report_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.report DROP CONSTRAINT report_pkey;
       public            postgres    false    224            J           2606    50311 #   report uk_n60tl23bf9da1jsuybplvrg99 
   CONSTRAINT     h   ALTER TABLE ONLY public.report
    ADD CONSTRAINT uk_n60tl23bf9da1jsuybplvrg99 UNIQUE (appointment_id);
 M   ALTER TABLE ONLY public.report DROP CONSTRAINT uk_n60tl23bf9da1jsuybplvrg99;
       public            postgres    false    224            L           2606    50302    vaccination vaccination_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.vaccination
    ADD CONSTRAINT vaccination_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.vaccination DROP CONSTRAINT vaccination_pkey;
       public            postgres    false    226            N           2606    50309    work_day work_day_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.work_day
    ADD CONSTRAINT work_day_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.work_day DROP CONSTRAINT work_day_pkey;
       public            postgres    false    228            P           2606    50317 '   appointment fk2kkeptdxfuextg5ch7xp3ytie    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie;
       public          postgres    false    218    216    4672            O           2606    50312 "   animal fk6pvxm5gfjqxclb651be9unswe    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);
 L   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk6pvxm5gfjqxclb651be9unswe;
       public          postgres    false    216    4676    220            U           2606    50342 $   work_day fk9a2ak4uapocwxmmjvpc4dh4a7    FK CONSTRAINT     �   ALTER TABLE ONLY public.work_day
    ADD CONSTRAINT fk9a2ak4uapocwxmmjvpc4dh4a7 FOREIGN KEY (doctor_id) REFERENCES public.doctor(doctor_id);
 N   ALTER TABLE ONLY public.work_day DROP CONSTRAINT fk9a2ak4uapocwxmmjvpc4dh4a7;
       public          postgres    false    222    4678    228            S           2606    50332 '   vaccination fkda4g4jfeyi4g9uw4vi4xh40o0    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccination
    ADD CONSTRAINT fkda4g4jfeyi4g9uw4vi4xh40o0 FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 Q   ALTER TABLE ONLY public.vaccination DROP CONSTRAINT fkda4g4jfeyi4g9uw4vi4xh40o0;
       public          postgres    false    216    4672    226            Q           2606    50322 '   appointment fkoeb98n82eph1dx43v3y2bcmsl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(doctor_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl;
       public          postgres    false    4678    222    218            R           2606    50327 "   report fkorag1ww0f2a059d8w1rkwb8j2    FK CONSTRAINT     �   ALTER TABLE ONLY public.report
    ADD CONSTRAINT fkorag1ww0f2a059d8w1rkwb8j2 FOREIGN KEY (appointment_id) REFERENCES public.appointment(id);
 L   ALTER TABLE ONLY public.report DROP CONSTRAINT fkorag1ww0f2a059d8w1rkwb8j2;
       public          postgres    false    218    224    4674            T           2606    50337 &   vaccination fkuvem57vc5xfe7pkv6740n7pn    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccination
    ADD CONSTRAINT fkuvem57vc5xfe7pkv6740n7pn FOREIGN KEY (report_id) REFERENCES public.report(id);
 P   ALTER TABLE ONLY public.vaccination DROP CONSTRAINT fkuvem57vc5xfe7pkv6740n7pn;
       public          postgres    false    226    4680    224            �   N  x�=�͎�0��ӧ�������=�ƨ�^�2�D+ݔ���ok���0��T��c��X_A�R�4�"�5�P��5��\@0	��9���8w)O�\f�W;|�n$���F=Ae	o~S,yZp1�vF�'T� c9�i��U�F;��U�V+z�sV��l�����g /�&��޷C��A9h���Ҫ.(D�3.��>�����}�����9jF#9ˉ�B�G�d��?<Y�k�}�1���^���[���(���s���S�S��`|�A%g�MJ���>5.�����A�`B��N�mrl�u-����بWc�/�'��1��c����      �   j   x�U��	AгU��`�33I-鿎l��t�C ���e����}#�[�e��l�[�H�&$�#�dKJ�FJ�-%�5��Ѳd��j9r@���v`�j+�i����Tc(�      �   �  x�u�Mn�0���S�*��?��v�"H۠F�'�$iH�ߪ���:���*����y&��~��pݷD=������g_;��=��fg)�|����Sx���l�䄟��r{�;�a�d��g�(���1Gd\��xH*��=���k˚+���-W�i:�F�28x{v�l:)TI�H$){����^j6~mZI7�,�Ay�&��4Nq�mU���-tk�����Z��)2����<;�
΀��LjY�ᒌn�N���6dw5k0������VAǫ���$��)�q��l�?�>�Z;Y��cd�2g��j�by��j����7�$��q�e�#YKg�I���^F��ǰ��M��DfSsx�ݓ�Ҙ����֖ܟ`�D�h�#�[�'�П�%�d�#� �p
�྆��;n.`)l��"���O�r$�K�?-n.���[���	#��      �   �  x�u��n�0@�ï�(A�Z�[�%i��A\ԗ^���b,�-[�ߗ� �ڋ ⍞fcI:�es�U�[X�6�7�M��-Kg7���W�C�c�rk�p���kO!��/^�����g�8��93�ꋏ�������A���6�\MyQ����_�	�
�h��lkr�f�nks�f���{���E�Jp���z���\t�0T�I�����E]6J�C�R�i��}����*x�X,W�?ˍ�p!��Ɖ�P0R#\{��pMW�E>�+�u{�7m}~���@w�b��E	��pz:����~']1�غK��0��>B�ui�&b�����Ýu|j���dղ�u��C��0�
���@�>��6�g��v�_v�߲�kc������(���(�((U�/�m�º�R�ύ����1�OSP~"�7�;w�⅗8Ý���GR!fQ�4I
u��ز���5tv$���<4>�/���$Q�B�5�.I      �   �   x�]�MN�0��3���l�i���� l���F��v@�=��b��ӼL�3���s��<��H���X��]��8���B�U?��"�́�q�j\�cP۔&N`:��3I�h�l�)� �j�������?���g�	��5��ѫk����������^E*����_$`��7 ^8���\8w�\�����@��?���2]d      �   �   x�E�M� �񳾊�@������Ak��]��Bih۫_�%^~�����z��*��`A!����fR;׃)*���\�W�O?��1k"g��5+�P~�K��	�~�c��G��ښ��q�z�Jj��H�H���u��w o�0��Q�m/j�Z��լ�~u�Z�5�;a��,�Ep      �   W   x�E���0�3����.�	��M���8�8�p�Zls8#�dEeK64�dG�H����J.,�~�*�>������Kj�/��y�     