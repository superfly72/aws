#!/bin/bash
if [ "$#" -ne 3 ]; then
    echo "Usage : createStack profile stack-name template-file"
    exit 1
fi

export AWS_DEFAULT_PROFILE=$1

echo "Running as profile $AWS_DEFAULT_PROFILE"
echo "Configuration for profile:"
aws configure list


echo "Running validate cloudformation script on $3"
aws cloudformation validate-template --template-body file://../cloudformation/$3
if [ $? -ne 0 ]; then
    echo "Error: validation failed..."
    exit 1
fi

echo "Creating stack using $3"
aws cloudformation create-stack --stack-name $2 --template-body file://../cloudformation/$3
if [ $? -ne 0 ]; then
    echo "Error: creation failed..."
    exit 1
fi

echo "Waiting for creation of stack $2 to complete.."
aws cloudformation wait stack-create-complete --stack-name $2
if [ $? -ne 0 ]; then
    echo "Error: creation failed..."
    exit 1
fi
echo "creation completed."
