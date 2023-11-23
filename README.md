# AWS Lambda grayscale image converter

## What is this?

This is a simple demo of running Java on AWS Lambda. This function converts JPG images to grayscale.

## How to run it?

Run `./gradlew buildZip && terraform apply`, then `./invoke.py`. Result can be seen in `output.jpg` file. After playing with it, run `terraform destroy` to clean up.

![Input file](input.png)
![Output file](output.png)

