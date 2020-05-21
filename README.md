# lambda-traning

## prerequisite
* aws cli 

## create bucket
> aws s3api create-bucket --bucket  hongxing-deployment-bucket --region  ap-southeast-1 --create-bucket-configuration LocationConstraint=ap-southeast-1

## build project and upload jar to bucket
> ./gradlew shadowJar
> aws s3 cp build/libs/com.xing-1.0-SNAPSHOT.jar s3://hongxing-deployment-bucket/func/v1/

## create stack
> aws cloudformation create-stack --stack-name hongxing-stack --template-body  file://cloudformation.yml

## update stack
> aws cloudformation update-stack --stack-name hongxing-stack --template-body file://cloudformation.yml