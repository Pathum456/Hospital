drop
database if exists Hospital;
create
database if not exists Hospital;
use Hospital;
create table patient
(
    P_ID      VARCHAR(10) not null,
    P_Name    VARCHAR(45),
    P_NIC     VARCHAR(25) not null,
    P_Contact VARCHAR(15),
    P_Address VARCHAR(45),
    CONSTRAINT primary key (P_ID)
);
desc patient;
create table doctor
(
    D_Id      varchar(10),
    D_Name    varchar(45),
    D_Contact varchar(15),
    D_Salary  double,
    D_days    varchar(100),
    D_Time    varchar(100),

    CONSTRAINT primary key (D_Id)
);
desc doctor;
drop TABLE bed;
drop TABLE room;
drop table ward;
create table ward
(
    W_Id       varchar(10),
    W_Name     varchar(45),
    W_D_Id     varchar(10) not null,
    W_Room_Qty varchar(15),

    constraint primary key (W_Id)


);
desc ward;
drop TABLE room;
create table room
(
    R_Id     varchar(10),
    W_Id     varchar(10),
    R_Charge DOUBLE,

    constraint primary key (R_Id)
);
desc room;
create table bed
(
    B_Id     varchar(10),

    B_Charge DOUBLE,
    R_Id     varchar(10),
    constraint primary key (B_Id),
    constraint foreign key (R_Id) references room (R_Id) ON DELETE CASCADE ON UPDATE CASCADE

);
desc bed;
drop table medicine;
drop table pharmacy;
create table pharmacy
(
    PH_Id      varchar(20),
    PH_Name    varchar(45),
    PH_Address varchar(60),
    PH_Contact varchar(15),

    constraint primary key (PH_Id)
);
desc pharmacy;
create table medicine
(
    M_Id          varchar(10),
    M_Name        varchar(50),
    M_Expire_Date date,
    M_Unit_Price  int,
    PH_Id         varchar(10),
    CONSTRAINT primary key (M_Id),
    CONSTRAINT foreign key (PH_Id) references pharmacy (PH_Id) on delete cascade on update cascade

);
desc medicine;
drop table `medical_report`;
create table medical_report
(
    MR_Id               varchar(10),
    P_id                varchar(10),
    P_NIc varchar (45),
    P_Name VARCHAR (45),
    reason              varchar(1000),
    Date_OF_Examination varchar (20),

    constraint primary key (MR_Id)

);

create table bill
(
    B_Id            varchar(10),
    P_Id            varchar(10),
    P_Name          varchar(45),
    W_Id            varchar(10),
    R_Id            varchar(10),
    Bed_ID          varchar(10),
    Description     varchar(1000),
    D_Charge        double,
    Room_Charge     double,
    Bed_Charge      double,
    Medicine_Charge double,
    Extra_Charges   double,
    Amount          double,
    date            varchar(15),
    constraint primary key (B_Id),
    constraint foreign key (P_Id) references patient (P_ID) on delete cascade on update cascade,
    constraint foreign key (W_Id) references ward (W_Id) on delete cascade on update cascade,
    constraint foreign key (R_Id) references Room (R_Id) on delete cascade on update cascade,
    constraint foreign key (Bed_Id) references Bed (B_Id) on delete cascade on update cascade

);
desc bill;

DROP TABLE IF EXISTS `loginDetail`;
CREATE TABLE IF NOT EXISTS `loginDetail`
(
    firstName VARCHAR
(
    15
),
    lastName VARCHAR
(
    15
),
    userType VARCHAR
(
    15
),
    email VARCHAR
(
    30
),
    userName VARCHAR
(
    10
),
    password VARCHAR
(
    15
)
    );
SHOW
TABLES;
DESCRIBE `loginDetail`;
drop table admitPatient;
create table admitPatient
(

    P_ID           varchar(10),
    P_NIC          varchar(25),
    P_Name         varchar(45),
    W_Id           varchar(10),
    R_Id           varchar(10),
    B_ID           Varchar(10),
    Male_or_Female varchar(10),
    Admit_Date     DATE,
    constraint foreign key (B_ID) references bed (B_Id) on delete cascade on update cascade,

    constraint foreign key (P_ID) references patient (P_ID) on delete cascade on update cascade,
    constraint foreign key (W_Id) references ward (W_Id) on delete cascade on update cascade,
    constraint foreign key (R_ID) references room (R_Id) on delete cascade on update cascade
);
drop table dischargePatient;
create table dischargePatient
(

    P_ID           varchar(10),
    P_NIC          varchar(25),
    P_Name         varchar(45),
    W_Id           varchar(10),
    R_Id           varchar(10),
    Male_or_Female varchar(10),
    Discharge_Date DATE,
    constraint foreign key (P_ID) references patient (P_ID) on update cascade,
    constraint foreign key (W_Id) references ward (W_Id) on update cascade,
    constraint foreign key (R_ID) references room (R_Id) on update cascade
);
create table patientShedul
(
    P_ID      varchar(10),
    P_Name    varchar(45),
    P_NIC     varchar(25),
    P_Contact VARCHAR(15),
    reason    varchar(1000),
    D_Id      varchar(10),
    D_Name    varchar(45),
    Date      varchar(20),
    Time      varchar(20),
    constraint foreign key (P_ID) references patient (P_ID) on delete cascade,
    constraint foreign key (D_Id) references doctor (D_Id) on delete cascade on update cascade
);



insert into patient
values ('P00-001', 'Pathum', '200030803620', 0777921473, 'Galle');
insert into ward
values ('W00-001', 'Male Ward', 'D00-001', 10);
insert into ward
values ('W00-002', 'Male Ward', 'D00-001', 15);
insert into ward
values ('W00-003', 'Male Ward', 'D00-001', 20);
insert into doctor
values ('D00-001', 'Kaleesha', 0755363461, 500000, 'monday,wensday', '4.00 PM - 7.00 PM');
insert into room
values ('R00-001', 'W00-001', 800.00);
insert into room
values ('R00-002', 'W00-002', 800.00);
insert into room
values ('R00-003', 'W00-003', 800.00);
insert into room
values ('R00-004', 'W00-002', 800.00);
insert into room
values ('R00-005', 'W00-001', 800.00);
insert into bed
values ('B00-001', 600.00, 'R00-001');
insert into bed
values ('B00-002', 600.00, 'R00-002');
insert into bed
values ('B00-003', 600.00, 'R00-003');
insert into bed
values ('B00-004', 600.00, 'R00-004');
insert into bed
values ('B00-005', 600.00, 'R00-005');
select *
from patient;
select *
from ward;
select *
from room;
select *
from doctor;

