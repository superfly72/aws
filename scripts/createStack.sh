#!/bin/bash
export AWS_DEFAULT_PROFILE=royh-qa

if [ "$#" -ne 2 ]; then
    echo "Usage : createStack stack-name template-file"
    exit 1
fi


echo "Running as profile $AWS_DEFAULT_PROFILE"
echo "Configuration for profile:"
aws configure list


echo "Running validate cloudformation script on $2"
aws cloudformation validate-template --template-body file://../cloudformation/$2
if [ $? -ne 0 ]; then
    echo "Error: validation failed..."
    exit 1
fi

echo "Creating stack using $2"
aws cloudformation create-stack --stack-name $1 --template-body file://../cloudformation/$2
if [ $? -ne 0 ]; then
    echo "Error: creation failed..."
    exit 1
fi

echo "Waiting for creation of stack $1 to complete.."
aws cloudformation wait stack-create-complete --stack-name $1
if [ $? -ne 0 ]; then
    echo "Error: creation failed..."
    exit 1
fi
echo "creation completed."
