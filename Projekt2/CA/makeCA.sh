PRIVATE_NAME=
PRIVATE_PASSWORD=

read -p "Enter privatekey filename: " -r PRIVATE_NAME
openssl genrsa -des -out $PRIVATE_NAME 1045

openssl req -subj "/CN=CA" -x509 -key $PRIVATE_NAME -new > CA.cert

