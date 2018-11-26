#!/bin/bash


display_usage()
{
echo "Usage:$0 [StackName]"
}

if [ $# -lt 1 ];then
	display_usage
	exit 1
fi

stackID=$(aws cloudformation create-stack --template-body file://csye6225-cf-auto-scaling.yml --stack-name $1 --parameters  file://csye6225-cf.conf| grep StackId)

if [ -z "$stackID" ];then 
	echo failed
	exit 1
fi


echo " 
Creating LoadBalancer....
Creating LoadBalancerSecurityGroup...
Creating LoadBalancerListener....
Creating TagetGroup....
Creating AutoScalingGroup....
Creating LaunchConfiguration........
Creating WebappSecurityGroup........
Creating DBSecurityGroup.........
Creating DBInstance.....
Creating S3Bucket.........
Creating DynamoDBTable.......
Creating WebApplicationFirewall.....
"


status=$(aws cloudformation describe-stacks --stack-name  $1| grep StackStatus| cut -d'"' -f4)


while [ "$status" != "CREATE_COMPLETE" ]
do

       echo "StackStatus: $status"

       if [ "$status" == "ROLLBACK_COMPLETE" ];then 
	       echo "$1 Stack_Create_Uncomplete !!"
	       exit 1
       fi

       sleep 3
       status=$(aws cloudformation describe-stacks --stack-name  $1 2>&1 | grep StackStatus| cut -d'"' -f4)

done

echo "$1 Stack_Create_Complete !!"

aws cloudformation describe-stacks --stack-name  $1| grep OutputValue
exit 0

