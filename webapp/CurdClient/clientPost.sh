#!/bin/bash



usage()
{
	echo "Usage:$0 [URL] [(PostContent).json]"
}


if [ $# -lt 2 ];then
	usage
        exit 1
fi

url=$1


URL1=$(cat $2 | grep url | cut -d'"' -f4| sed -n '1 p')
URL2=$(cat $2 | grep url | cut -d'"' -f4| sed -n '2 p')

aws s3 cp $URL1  s3://csye6225-fall2018-chengl.me.csye6225.com/ --grants read=uri=http://acs.amazonaws.com/groups/global/AllUsers 

aws s3 cp $URL1  s3://csye6225-fall2018-chengl.me.csye6225.com/ --grants read=uri=http://acs.amazonaws.com/groups/global/AllUsers


curl -iX POST "$url" -H "Content-Type: application/json" -d @$2




