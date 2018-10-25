#!/bin/bash 


usage()
{
echo "Usage:$0 [URL]"
}


if [ $# -lt 1 ];then
	usage
        exit 1
fi


url=$1


curl -iX GET "$url"


