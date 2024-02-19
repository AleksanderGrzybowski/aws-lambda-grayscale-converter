# AWS Lambda grayscale image converter

## What is this?

This is a simple demo of running Java on AWS Lambda. This function converts JPG images to grayscale.

## How it works?

* Local Java application, with AWS Lambda support (no extra frameworks, only one handler) is built locally into a .zip file
* Terraform creates Lambda function using a .zip file and a custom IAM role
* Local Python script runs the Lambda function with an input file, producing an output file, converting between .jpg and Lambda event expected format for convenience

## How to run it?

Run `./gradlew buildZip && terraform apply`, then `./invoke.py` (`boto3` required). Result can be seen in `output.jpg` file. After playing with it, run `terraform destroy` to clean up.

![Input file](input.jpg)
![Output file](output.jpg)

