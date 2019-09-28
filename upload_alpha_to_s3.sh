#!/bin/bash

export BUILD_DATE=$(date +%Y%m%d%H%M)

aws s3 cp build/libs/*.jar s3://${BUCKET_NAME}/alpha/morecreeps-alpha-${BUILD_DATE}.jar

aws lambda invoke --function-name morecreeps-post-alpha-build --payload "{\"buildDate\": \"${BUILD_DATE}\"}" /dev/stdout