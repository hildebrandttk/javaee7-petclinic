#!/usr/bin/env bash

#!/bin/bash

########### SIGTERM handler ############
function _term() {
   echo "Stopping container."
   echo "SIGTERM received, shutting down database!"
   mysqladmin -uroot shutdown
}

########### SIGKILL handler ############
function _kill() {
   echo "SIGKILL received, shutting down database!"
   mysqladmin -uroot shutdown
}

# Set SIGTERM handler
trap _term SIGTERM

# Set SIGKILL handler
trap _kill SIGKILL

echo "=> Starting MySQL Server"
/etc/init.d/mysql start

echo "#########################"
echo "DATABASE IS READY TO USE!"
echo "#########################"

tail -f -n 0 /var/log/syslog &
childPID=$!
wait $childPID