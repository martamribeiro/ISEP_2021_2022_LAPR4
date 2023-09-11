#!/bin/bash
STOREPASS="forgotten"
for ENT in serverAgvManager_J clientTwin_J clientBackoffice_J; do
 ##rm -f ${ENT}.jks ${ENT}.pem
 echo -e "${ENT}\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias ${ENT} -keyalg RSA -keysize 2048 \
	-validity 365 -keystore ${ENT}.jks -storepass ${STOREPASS}
 keytool -exportcert -alias ${ENT} -keystore ${ENT}.jks -storepass ${STOREPASS} -rfc -file ${ENT}.pem
done
####
echo "Creating trust relations"
### IMPORTING TRUSTED CERTIFICATES
### (Every client trusts server_J)
for ENT in clientTwin_J clientBackoffice_J ; do
 echo "yes"|keytool -import -alias ${ENT} -keystore serverAgvManager_J.jks -file ${ENT}.pem -storepass ${STOREPASS}
 echo "yes"|keytool -import -alias serverAgvManager_J -keystore ${ENT}.jks -file serverAgvManager_J.pem -storepass ${STOREPASS}
done
echo "############################################################################"
keytool -list -keystore serverAgvManager_J.jks -storepass ${STOREPASS}
echo "############################################################################"
#######
