#!/bin/bash
STOREPASS="forgotten"
for ENT in serverOrder_J clientOrder_J ; do
 rm -f ${ENT}.jks ${ENT}.pem
 echo -e "${ENT}\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias ${ENT} -keyalg RSA -keysize 2048 \
	-validity 365 -keystore ${ENT}.jks -storepass ${STOREPASS}
 keytool -exportcert -alias ${ENT} -keystore ${ENT}.jks -storepass ${STOREPASS} -rfc -file ${ENT}.pem
done
####
echo "Creating trust relations"
### IMPORTING TRUSTED CERTIFICATES
### (The server trusts all clients except for client4_J)
### (Every client trusts serverOrder_J)
for ENT in clientOrder_J ; do
 echo "yes"|keytool -import -alias ${ENT} -keystore serverOrder_J.jks -file ${ENT}.pem -storepass ${STOREPASS}
 echo "yes"|keytool -import -alias serverOrder_J -keystore ${ENT}.jks -file serverOrder_J.pem -storepass ${STOREPASS}
done
echo "############################################################################"
keytool -list -keystore serverOrder_J.jks -storepass ${STOREPASS}
echo "############################################################################"
#######