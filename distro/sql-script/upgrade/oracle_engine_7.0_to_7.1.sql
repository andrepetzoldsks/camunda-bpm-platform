-- add new column to historic activity instance table --
alter table ACT_HI_ACTINST
    add ACT_INST_STATE_ INTEGER;

-- add follow-up date to tasks --
alter table ACT_RU_TASK
    add FOLLOW_UP_DATE_ TIMESTAMP(6);
alter table ACT_HI_TASKINST
    add FOLLOW_UP_DATE_ TIMESTAMP(6);

-- add JOBDEF table --
create table ACT_RU_JOBDEF (
    ID_ NVARCHAR2(64) NOT NULL,
    REV_ INTEGER,
    PROC_DEF_ID_ NVARCHAR2(64) NOT NULL,
    PROC_DEF_KEY_ NVARCHAR2(255) NOT NULL,
    ACT_ID_ NVARCHAR2(255) NOT NULL,
    JOB_TYPE_ NVARCHAR2(255) NOT NULL,
    JOB_CONFIGURATION_ NVARCHAR2(255),
    SUSPENSION_STATE_ INTEGER,
    primary key (ID_)
);

-- add new columns to job table -- 
alter table ACT_RU_JOB
    add PROCESS_DEF_ID_ NVARCHAR2(64);

alter table ACT_RU_JOB
    add PROCESS_DEF_KEY_ NVARCHAR2(64);

alter table ACT_RU_JOB
    add SUSPENSION_STATE_ INTEGER;

alter table ACT_RU_JOB
    add JOB_DEF_ID_ NVARCHAR2(64);

-- update job table with values from execution table --

UPDATE
    ACT_RU_JOB J
SET
    PROCESS_DEF_ID_  = (
        SELECT
            PI.PROC_DEF_ID_
        FROM
            ACT_RU_EXECUTION PI
        WHERE
            PI.ID_ = J.PROCESS_INSTANCE_ID_
    );

UPDATE
    ACT_RU_JOB J
SET
    SUSPENSION_STATE_  = (
        SELECT
            PI.SUSPENSION_STATE_
        FROM
            ACT_RU_EXECUTION PI
        WHERE
            PI.ID_ = J.PROCESS_INSTANCE_ID_
    );

UPDATE
    ACT_RU_JOB J
SET
    PROCESS_DEF_KEY_  = (
        SELECT
            PD.KEY_
        FROM
            ACT_RE_PROCDEF PD
        WHERE
            PD.ID_ = J.PROCESS_DEF_ID_
    );

-- create HIST OP LOG table

create table ACT_HI_OP_LOG (
    ID_ NVARCHAR2(64) not null,
    PROC_DEF_ID_ NVARCHAR2(64),
    PROC_INST_ID_ NVARCHAR2(64),
    EXECUTION_ID_ NVARCHAR2(64),
    TASK_ID_ NVARCHAR2(64),
    USER_ID_ NVARCHAR2(255),
    TIMESTAMP_ TIMESTAMP(6) not null,
    OPERATION_TYPE_ NVARCHAR2(255),
    OPERATION_ID_ NVARCHAR2(64),
    ENTITY_TYPE_ NVARCHAR2(30),
    PROPERTY_ NVARCHAR2(64),
    ORG_VALUE_ NVARCHAR2(2000),
    NEW_VALUE_ NVARCHAR2(2000),
    primary key (ID_)
);

-- add new column to ACT_HI_VARINST --

alter table ACT_HI_VARINST
    add ACT_INST_ID_ NVARCHAR2(64);

alter table ACT_HI_DETAIL
    add VAR_INST_ID_ NVARCHAR2(64);
