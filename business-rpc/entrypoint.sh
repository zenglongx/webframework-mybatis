#!/bin/bash

HOST=192.168.72.129

echo "init host ip: $HOSTNAME:$HOST"

cp /etc/hosts /etc/hosts.temp
sed -i 's/[0-9]\+\.[0-9]\+\.[0-9]\+\.[0-9]\+\t'$HOSTNAME'/'$HOST'\t'$HOSTNAME'/g' /etc/hosts.temp
cat /etc/hosts.temp > /etc/hosts
rm -f /etc/hosts/temp

java -jar webframework-business-rpc-2.0.3.RELEASE.jar