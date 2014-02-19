CA_CERT="CA/CA.cert"
CA_KEY="CA/privatekey"
CA_PW="CA/keypw"
CA_SER="CA/CAserial"

NAME=
KEYSTORE_PW=
TRUSTSTORE_PW=

read -p "Enter name: " -r NAME
read -p "Enter keystore password: " -r KEYSTORE_PW
read -p "Enter truststore password: " -r TRUSTSTORE_PW

mkdir "Client/${NAME}"

KEYSTORE="Client/${NAME}/keystore"
TRUSTSTORE="Client/${NAME}/truststore"

CERT="Client/${NAME}/${NAME}.cert"
CERTRQ="Client/${NAME}/${NAME}.certrq"


#Gen keypair
keytool -genkeypair -alias $NAME -keystore $KEYSTORE -storepass $KEYSTORE_PW -keysize 1024 -keypass $KEYSTORE_PW
#Gen rq
keytool -certreq -alias $NAME -keystore $KEYSTORE -storepass $KEYSTORE_PW -file $CERTRQ -keypass $KEYSTORE_PW
#sign
openssl x509 -req -CA $CA_CERT -CAkey $CA_KEY -in $CERTRQ -out $CERT -CAcreateserial -CAserial $CA_SER
#import CA
keytool -importcert -alias CA -file $CA_CERT -keystore $KEYSTORE -storepass $KEYSTORE_PW
#import signed
keytool -importcert -alias $NAME -file $CERT -keystore $KEYSTORE -storepass $KEYSTORE_PW

#CA to truststore
keytool -importcert -file $CA_CERT -keystore $TRUSTSTORE -storepass $TRUSTSTORE_PW -alias CA
