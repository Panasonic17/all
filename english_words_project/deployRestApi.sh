host_ip="54.242.41.46"

ssh_host="ubuntu@54.242.41.46"

path_to_ssh_key='../SECRET/aws_ec2_virginia.pem'

START_DIR=`pwd`

path_to_jar='RestApi/target/rest_api-1.0-SNAPSHOT.jar' 

echo byild rest api jar 

cd  RestApi 
#mvn package ||  exit 1
cd ..

scp -i $path_to_ssh_key $path_to_jar $ssh_host:rest_api-1.0-SNAPSHOT.jar
scp -i $path_to_ssh_key $path_to_jar.original $ssh_host:rest_api-1.0-SNAPSHOT.jar.original
scp -i $path_to_ssh_key run-jar.sh $ssh_host:run-jar.sh 
ssh -i $path_to_ssh_key $ssh_host 'sh run-jar.sh'
