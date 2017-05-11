#!/usr/bin/env bash
adjustOracleHostname.sh;
service oracle-xe start;
tail -f /u01/app/oracle/diag/rdbms/xe/XE/trace/alert_XE.log
