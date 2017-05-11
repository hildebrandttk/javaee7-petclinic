#!/bin/bash

########### SIGTERM handler ############
function _term() {
   echo "Stopping container."
   echo "SIGTERM received, shutting down database!"
   service oracle-xe stop
}

########### SIGKILL handler ############
function _kill() {
   echo "SIGKILL received, shutting down database!"
   service oracle-xe stop
}

# Set SIGTERM handler
trap _term SIGTERM

# Set SIGKILL handler
trap _kill SIGKILL

adjustOracleHostname.sh;
service oracle-xe start | grep -qc "Oracle Database 11g Express Edition is not configured"

echo "#########################"
echo "DATABASE IS READY TO USE!"
echo "#########################"

tail -f -n 0 /u01/app/oracle/diag/rdbms/xe/XE/trace/alert_XE.log /u01/app/oracle/product/11.2.0/xe/log/diag/rdbms/xe/XE/trace/alert_XE.log &
childPID=$!
wait $childPID