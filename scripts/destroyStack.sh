#!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "Usage : destroyStack profile stack-name"
    exit 1
fi

export AWS_DEFAULT_PROFILE=$1

echo "Running as profile $AWS_DEFAULT_PROFILE"
echo "Configuration for profile:"
aws configure list

echo "Destroying stack $2"
aws cloudformation delete-stack --stack-name $2

echo "Waiting for stack deletion to complete.."
aws cloudformation wait stack-delete-complete --stack-name $2
if [ $? -ne 0 ]; then
    echo "deletion failed..."
    exit 1
fi
echo "deletion completed."
