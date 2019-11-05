--To be only used in case of external data storage
-------------------------------------------------------

-- DROP section start----
--Drop Existing tables, if exists
drop table claims if exists

drop table customers if exists

drop table customers_policies if exists

drop table policies if exists

drop table policies_claims if exists

drop table policies_policy_type if exists

drop table policies_vehicle_policy_details if exists

drop table policy_type if exists

drop table policy_type_policies if exists

drop table resolutions if exists

drop table resolutions_claims if exists

drop table vehicle_policy_details if exists

drop sequence if exists hibernate_sequence

drop sequence if exists seq

-- DROP section end----

create sequence hibernate_sequence start with 1 increment by 1

create sequence seq start with 1111 increment by 50

create table claims (
   id integer not null,
    closure_amount bigint,
    closure_date timestamp,
    request_date timestamp,
    requested_amount bigint,
    created_by varchar(255),
    created_date timestamp,
    remarks varchar(255),
    status varchar(255),
    updated_by varchar(255),
    updated_date timestamp,
    policies_id integer,
    resolutions_id integer,
    primary key (id)
)

create table customers (
   id integer not null,
    address varchar(255),
    city varchar(255),
    country varchar(255),
    created_by varchar(255),
    created_date timestamp,
    date_of_birth varchar(255),
    gender varchar(255),
    name varchar(255),
    updated_by varchar(255),
    updated_date timestamp,
    primary key (id)
)

create table customers_policies (
   customers_id integer not null,
    policies_id integer not null
)

create table policies (
   id integer not null,
    created_by varchar(255),
    created_date timestamp,
    end_date timestamp,
    start_date timestamp,
    type_code varchar(255),
    updated_by varchar(255),
    updated_date timestamp,
    customers_id integer,
    primary key (id)
)

create table policies_claims (
   policies_id integer not null,
    claims_id integer not null
)

create table policies_policy_type (
   policies_id integer not null,
    policy_type_id integer not null,
    primary key (policies_id, policy_type_id)
)

create table policies_vehicle_policy_details (
   policies_id integer not null,
    vehicle_policy_details_id integer not null
)

create table policy_type (
   id integer not null,
    category varchar(255),
    created_by varchar(255),
    created_date timestamp,
    name varchar(255),
    sub_type varchar(255),
    type_code varchar(255),
    updated_by varchar(255),
    updated_date timestamp,
    primary key (id)
)

create table policy_type_policies (
   policy_type_id integer not null,
    policies_id integer not null,
    primary key (policy_type_id, policies_id)
)

create table resolutions (
   id integer not null,
    category varchar(255),
    created_by varchar(255),
    created_date timestamp,
    resolution varchar(255),
    severity varchar(255),
    updated_by varchar(255),
    updated_date timestamp,
    primary key (id)
)

create table resolutions_claims (
   resolutions_id integer not null,
    claims_id integer not null
)

create table vehicle_policy_details (
   id integer not null,
    created_by varchar(255),
    created_date timestamp,
    manufacturer varchar(255),
    manufacuring varchar(255),
    model varchar(255),
    registration_no varchar(255),
    updated_by varchar(255),
    updated_date timestamp,
    policies_id integer,
    primary key (id)
)


alter table customers_policies 
   add constraint UK_sld5fq237x5nj8a70re0ku4b0 unique (policies_id)

alter table policies_claims 
   add constraint UK_g38uva3gbuayq1uyipxn2gxfv unique (claims_id)

alter table policies_vehicle_policy_details 
   add constraint UK_j54plulr29vvbr6bg9k9bsm2c unique (vehicle_policy_details_id)

alter table resolutions_claims 
   add constraint UK_kpm0geywxbs8mmuwh012uwres unique (claims_id)

alter table claims 
   add constraint FKi9thqby5cr9twxgib8kdphb53 
   foreign key (policies_id) 
   references policies

alter table claims 
   add constraint FKapowh2d6myjdwc760m49wt6m2 
   foreign key (resolutions_id) 
   references resolutions

alter table customers_policies 
   add constraint FKiwojw8ucgc6g3in030a5rgkku 
   foreign key (policies_id) 
   references policies

alter table customers_policies 
   add constraint FK4b4co7v16cpp8a8kmlbyg2ung 
   foreign key (customers_id) 
   references customers

alter table policies 
   add constraint FK1wk0aq4pdmbbqhseu704k4grv 
   foreign key (customers_id) 
   references customers

alter table policies_claims 
   add constraint FKrracjumj1rk5aitgy13am9j8a 
   foreign key (claims_id) 
   references claims

alter table policies_claims 
   add constraint FK2qmb1286gm0jntn38idcrkt54 
   foreign key (policies_id) 
   references policies

alter table policies_policy_type 
   add constraint FK3h19lf4itncdvwxf3esfyss7o 
   foreign key (policy_type_id) 
   references policy_type

alter table policies_policy_type 
   add constraint FKot1qcwo2m8ffncd098rpd4t3d 
   foreign key (policies_id) 
   references policies

alter table policies_vehicle_policy_details 
   add constraint FKj47yk3o1heb0kpq84evpa5t7x 
   foreign key (vehicle_policy_details_id) 
   references vehicle_policy_details

alter table policies_vehicle_policy_details 
   add constraint FKo4xwhvd5esgg4005ncp969f0a 
   foreign key (policies_id) 
   references policies

alter table policy_type_policies 
   add constraint FKajvmq2ycalh7bl08u0e5m5sq2 
   foreign key (policies_id) 
   references policies

alter table policy_type_policies 
   add constraint FKitomwvb9dimke10g975wbxyft 
   foreign key (policy_type_id) 
   references policy_type

alter table resolutions_claims 
   add constraint FK5336x12j1v5o7b8bfesd8v3xj 
   foreign key (claims_id) 
   references claims

alter table resolutions_claims 
   add constraint FKd8ln1ynidieu42sbm7xlxtv0a 
   foreign key (resolutions_id) 
   references resolutions

alter table vehicle_policy_details 
   add constraint FK708u89ux47j3f08c82vyqwq16 
   foreign key (policies_id) 
   references policies