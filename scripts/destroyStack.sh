#!/bin/bash
export AWS_DEFAULT_PROFILE=royh-qa

if [ "$#" -ne 1 ]; then
    echo "Usage : destroyStack stack-name"
    exit 1
fi
echo "Running as profile $AWS_DEFAULT_PROFILE"
echo "Configuration for profile:"
aws configure list

echo "Destroying stack $1"
aws cloudformation delete-stack --stack-name $1

echo "Waiting for stack deletion to complete.."
aws cloudformation wait stack-delete-complete --stack-name $1
if [ $? -ne 0 ]; then
    echo "deletion failed..."
    exit 1
fi
echo "deletion completed."
