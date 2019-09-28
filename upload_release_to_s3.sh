#!/bin/bash

aws s3 cp build/libs/*.jar s3://${BUCKET_NAME}/