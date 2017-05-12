#!/bin/bash
set -e
set -E

. /etc/profile

echo "select 0 from dual;" | sqlplus -S system/manager@localhost/XE