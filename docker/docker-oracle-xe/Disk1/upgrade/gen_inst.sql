Rem  Copyright (c) Oracle Corporation 1999 - 2011. All Rights Reserved.
Rem
Rem    NAME
Rem      gen_inst.sql
Rem
Rem    DESCRIPTION
Rem      Assist in migrating Application Express from 10.2XE to 11.2XE.
Rem
Rem    NOTES
Rem
Rem      - This utility should be run via SQL*Plus and connected as SYS.
Rem
Rem      - This utility assumes all 10.2XE schemas except for Application Express schemas
Rem      - will be exported and then imported into 11.2XE. It also assumes that a table level
Rem      - export of FLOWS_FILES.WWV_FLOW_FILE_OBJECTS$ has been done from 10.2XE and will be
Rem      - imported using TABLE_EXISTS_ACTION=APPEND after install.sql has been invoked in 11.2XE.
Rem
Rem
Rem    MODIFIED    (MM/DD/YYYY)
Rem      jstraub    02/11/2011 - Created

set verify off
set feedback off
set serveroutput on size 1000000
set linesize 1000
set termout off

define UFROM     = 'FLOWS_010500'
column foo_usr new_val UFROM

select username foo_usr from
(select username, 2 weight from dba_users where username in ('APEX_030200', 'APEX_040000')
union
select username, 1 weight from dba_users where username in ('FLOWS_010500','FLOWS_010600','FLOWS_020000','FLOWS_020100','FLOWS_020200','FLOWS_030000','FLOWS_030100')
order by 2 desc, 1 desc) x where rownum = 1;

whenever sqlerror exit
declare
    l_version   number;
begin
    l_version := to_number(replace(replace('&UFROM','FLOWS_',null),'APEX_',null));

    if l_version < 20100 or l_version > 40000 then
        dbms_output.put_line('This release of Application Express cannot be upgraded to 11.2 XE using this utility.');
        execute immediate 'bogus statement to force exit';
    end if;
end;
/
whenever sqlerror continue

alter session set current_schema = &UFROM;

create or replace procedure init_http_buffer_for_clob wrapped
a000000
b2
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
7
17e 11b
oVBFUbeZkCdCJOSPM6R9nJE1+QswgxDILsusfC8CTMcYuak2oaIf29TpPh0hJmIzW4YnuaqZ
mfsCL5CRyU4Zz4JC7bN2JyCp52x2n/+BVDro+9bw34Vj4mY479PyUeC/Zz7Y76IkkyK4kdRQ
k3Z10gNFb9URAoPNSbCJcSAU36WlcKSbSoiZ0Rlqb0m3wRWY6ORUK0vgmbZZLarbMRwRxkR4
P3pE3bo3DkAahTxJwrLKY7Og0qQzAU7/gWuEnxY18FuXmfBnPZ3WYwd4Tg5wlA==

/
show errors

create or replace function get_http_buffer_as_clob wrapped
a000000
b2
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
8
2e3 1ca
Yh9lQu5A4cw6RmEQuXrP3jOuDpcwg2MJr0jWfHRVWGSlf1rnDZCgSIQANnwv9Bv3tiuBiSwp
xpaovdi3n+wor0pPzf5ZQOmfKxa976N4kwpMmHQd8YbUMgMnN1DRFKjOAnxEMBMAUIw0F7no
QdjisbRSURv7WlVfttNGoFdmr8FEIlEBonYdpZUmE1eQtB4p5J/RHQnRTnY44cJjYL4jY4ti
zxNRlcyFKuvE6obkTFXzwG1vZKPzsNoMKpR0z99UzWfW1DZzAV7YpDzvHp4Ms8+ipxTqmKBl
CYiOQqeHKfP8I3Jg7PIpZpm0jhI3E7UwXQFl96tIdkF2f91cgVnDuM8kibg3HbS9Q6eqJT/v
OPf28MIXkzNfwKC22bfZJ+8IC7kgQfjQ54sFQhx/yz/InKeLKXkyFanDnzsQi84kP0KSqxqA
iG1zTFr5CKkTnCCLmjtp

/
show errors

create or replace procedure gen_app_import wrapped
a000000
b2
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
7
4f0 284
tkgYFzbr77F8JY4oCrVb6rSOUKAwg5DDBUoTfC9VF7zgGW+gPp6T3up5iY1ff3D6tKeWOPhd
tbVtkqx46N7vDe83S3LOR+5HqcPht6/F4bbuy6EpfWYCZQ1ZbfsWW0icQU3o5kXhLAMpzee6
R2BhUmqoY//z6uA93FS2UpeyWOOsLB99nrQD+cuqrvfxdU6JjFvmcfcVggmwkoHS+TMZRpFI
iAxYjhi0fdMVnOHz1SdkzhublxypVjR6jASIxVNqWdKm7kHMechlAVXXUV+2IqZ63YjuKFIg
QaCY4QJExoYtreoAWBZk8R1uq3j973hyaBaJDMDvuZypmFCFo7ZgS/suS1x35PW309+fQsPg
NzLtRfc6M917tiTTP7XhQi+rcOaFJ+u3XkzoJ0m/cvz9ApgzSCTtyXw+aUjFnQTryiVeDCKG
FIvxh6HbKbg6zgkyFsxrb5HdjHKn63LBG3+1a1iHsaIzQahMo6e6irYHP7P/72JDg02SQc2p
F1y/xotQpIcTczbk6wg7iwyxiQsGPtMFxFbFax5ZiDEO0AzUJa9RrsQ4xQ0RBbXpWVbsY37m
5tuM9CaBnAuJt3vHH0ICUqEFDPmKILzk0NWY09DPPujFerm1J8GwD6qo0Vs=

/
show errors

declare
    l_sql varchar2(32767);
begin
    if '&UFROM' = 'APEX_040000' then
    l_sql := q'!
create or replace procedure gen_ws_import wrapped
a000000
b2
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
7
5c3 2b9
rw9ebx4RxCUqr0HbVMDysb3cTNwwg5DDs0gayo5VF52sxCd79tTfMejaWBvjTRTnXayDIgi1
qswvdQb/PJH+G8UjuQD+MFPlZ9OD12ezZwJMDQG38XDyQ7FUPtl72zbTTncJTZ7YmV3gIc1o
jASMzIEz3eCp2UDW9T3A3/3/RmsdRNL4GOx0n9iqFWSo5tPESsK/qdBEHYh/eo1yrpadh8VP
Vsn/hBuEk6JFqeM+hF0YHgLP9HHO+4Vz2cdgSzgJVBUBbcgkScP9dBu7/WLyV5Sk5ltXsAYv
v4smQrHKA4/PIuqcUWMdtum4Z3wLgAvD0kcz037ImO65b/fUIJEmPrDbyi76dEfPtPtdNM3A
KMDmHkW0QdkquT9lZ4wyGkhgcYOUAYzMqNFjxfHbujLgZFsSmoXNpN78mMHu1+0gQYKecoFQ
vyRYMlMOmzwqLD34U7xDvbDYxS8xy/16ph5h6SuBpQi4ifACyBvsTnlhFR1VARzA1XCZ33fh
gcR76sAM4qx/qoiuFx5n/l6pMiI23hFM4LHcg3sayeb9Frsx3JFGwq3qsIvScc0Xkk+XC3LG
6Jdbmsw2YU+hw2ex91Kra8T2FeY4IhfEcu+xUcEz/X+66xRsnggtjTnog6SngjXZ25BDAdRE
edApgDND2bVTwvZSXQwzzM5Dua4QOgi9pckPyA==
!';
    else
        l_sql := 'create or replace procedure gen_ws_import(p_ws_app_id in number) as begin null; end;';
    end if;

    execute immediate l_sql;
end;
/

begin
    execute immediate 'create public synonym gen_ws_import for gen_ws_import';
exception when others then
    null;
end;
/

create or replace procedure gen_workspace_imports wrapped
a000000
b2
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
7
9b3 3d5
G6pPoBg366KnVsRsoJ+34k5g5Lswg5VcTCCDfI5Vgp0VIKjVhEa8sg3xt3XbuuCAYeuJSQrs
tEB3pyZLK7fOCJhKtqeWUJJLKtMvVXKGx7kqWI/7vcc1aaJ10VPOVoJ2Ly7rX5lsgFRKL3qh
apQUaHjfTphNNAk4a2lOOEL8tsz4vZnze4au59oewb6XmyvW/VasBG06zsAiE9dolYVJ8wGM
bB8WpS8BEVah5uECZa8gTRpuzG8pqbCIydUuaswKBQmljpSV8IcbNE1RFvc7DmYbZ+v0wH2r
QoeYGxW4EYgd8OzuicrUXPTZ99UpWUfEZiaGRRbuev2NvonMYwtjZc3B2Wzzq0qMYnJjteNp
8cofwzvoswhcgKzBWDebCJ4WdUGmI+jxPXFe6Kh406LyXvL0bvW3p1Z3vjbPV4xewLroPd/0
1IAcZUzavloILHPi4ve3giMeU1xDGc81huOgvt3CoekAxy8EkiZ3j1gJG40Ev/YurFRf7Vja
CBucSBLKD/dxgZilaItH5vu6HoSyhSNtdRsawEyx0RMX2+JCV8uftuNHczUUAcargAIVegFR
O4IC0NwW74ZFwO/ir3VEnzc06LF1h65aCq+t8l/6drwlZHF3QsfMmr9hfCveUUT6UxoYbVwN
qnNSJHjNSBO7NeVu14ZIc6CAE8Zzb+00eh5k/gUfcxC1uGRPPCRhmNc/+NKZAJpVtXG7cBIo
B3AiY+7uptpxvJxwE0Ac22ru46bgdv1cHK7u55tuSj1xH3nHQQFL+0yweSi36L61vRbYzHUa
JxjKprmkZjjOAFMb2aK/7lNMLvnc8WeXxF5d2Uj+rkR/Bx4pOF+wXX3O+ZirIZXzQtoA3yi/
mjxkw5GpnxIYIsY8GbbbCZoMqxSqDv1qnhbduFw4b0NpGgM/RmAtJwWeq+VTOHuMY7JXYSqw
pt9Y392KtRC2ud1Uy9yEHnkIuXhzr8hW

/
show errors

create or replace package gen_install_global
as
    g_applications  wwv_flow_global.vc_arr2;
    g_websheets     wwv_flow_global.vc_arr2;
    g_ws_schemas    wwv_flow_global.vc_arr2;
    g_ws_sgid       wwv_flow_global.vc_arr2;
end;
/
show errors

declare
    l_sql varchar2(32767);
begin
    for c1 in (select id from wwv_flows where security_group_id > 11 ) loop
        gen_install_global.g_applications(gen_install_global.g_applications.count+1) := c1.id;
    end loop;

    if '&UFROM' = 'APEX_040000' then
        l_sql := 'begin
        for c1 in (select w.id, s.schema, w.security_group_id
                     from wwv_flow_ws_applications w, wwv_flow_company_schemas s
                    where w.security_group_id > 11
                      and w.security_group_id = s.security_group_id
                      and s.is_apex$_schema = ''Y''
                    order by 2) loop
            gen_install_global.g_websheets(gen_install_global.g_websheets.count+1) := c1.id;
            gen_install_global.g_ws_schemas(gen_install_global.g_ws_schemas.count+1) := c1.schema;
            gen_install_global.g_ws_sgid(gen_install_global.g_ws_sgid.count+1) := c1.security_group_id;
        end loop; end;';
        execute immediate l_sql;
    end if;
end;
/

spool ws.sql

begin
    gen_workspace_imports;
end;
/

spool off

spool gen_apps.sql

declare
    i number;
begin
    dbms_output.put_line('set verify off');
    dbms_output.put_line('set feedback off');
    dbms_output.put_line('set serveroutput on size 1000000');
    dbms_output.put_line('set linesize 1000');
    dbms_output.put_line(' ');
    for i in 1.. gen_install_global.g_applications.count loop
        dbms_output.put_line(' ');
        dbms_output.put_line('spool f'||gen_install_global.g_applications(i)||'.sql');
        dbms_output.put_line('begin gen_app_import('||gen_install_global.g_applications(i)||'); end;');
        dbms_output.put_line('/');
        dbms_output.put_line('spool off');
    end loop;
    for i in 1.. gen_install_global.g_websheets.count loop
        dbms_output.put_line(' ');
        dbms_output.put_line('spool w'||gen_install_global.g_websheets(i)||'.sql');
        dbms_output.put_line('alter session set current_schema = '||gen_install_global.g_ws_schemas(i)||';');
        dbms_output.put_line('begin gen_ws_import('||gen_install_global.g_websheets(i)||'); end;');
        dbms_output.put_line('/');
        dbms_output.put_line('spool off');
    end loop;
end;
/

spool off

@gen_apps.sql

alter session set current_schema = &UFROM;

spool install.sql

declare
    i number;
begin
    dbms_output.put_line('set define off');
    dbms_output.put_line('alter session set current_schema = APEX_040000;');
    dbms_output.put_line('@ws.sql');
    for i in 1.. gen_install_global.g_ws_schemas.count loop
        if i = 1 then
            dbms_output.put_line('update wwv_flow_company_schemas set is_apex$_schema = ''Y'' where schema = '''||gen_install_global.g_ws_schemas(i)||''' and security_group_id = '||gen_install_global.g_ws_sgid(i)||';');
        elsif gen_install_global.g_ws_schemas(i) != gen_install_global.g_ws_schemas(i-1) then
            dbms_output.put_line('update wwv_flow_company_schemas set is_apex$_schema = ''Y'' where schema = '''||gen_install_global.g_ws_schemas(i)||''' and security_group_id = '||gen_install_global.g_ws_sgid(i)||';');
        elsif gen_install_global.g_ws_schemas(i) = gen_install_global.g_ws_schemas(i-1) and gen_install_global.g_ws_sgid(i) != gen_install_global.g_ws_sgid(i-1) then
            dbms_output.put_line('update wwv_flow_company_schemas set is_apex$_schema = ''Y'' where schema = '''||gen_install_global.g_ws_schemas(i)||''' and security_group_id = '||gen_install_global.g_ws_sgid(i)||';');
        end if;
    end loop;
    for i in 1.. gen_install_global.g_applications.count loop
        dbms_output.put_line('@f'||gen_install_global.g_applications(i)||'.sql');
    end loop;
    for i in 1.. gen_install_global.g_websheets.count loop
        dbms_output.put_line('alter session set current_schema = '||gen_install_global.g_ws_schemas(i)||';');
        dbms_output.put_line('@w'||gen_install_global.g_websheets(i)||'.sql');
    end loop;
    dbms_output.put_line('commit;');
end;
/

spool off

set termout on
