provider "aws" {
  region = "eu-central-1"
}

resource "aws_iam_role" "iam_for_lambda" {
  name = "iam_for_lambda"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_lambda_function" "grayscale" {
  filename      = "build/distributions/aws-lambda-grayscale-converter.zip"
  function_name = "convert-to-grayscale"
  handler       = "pl.kelog.ConvertToGrayscaleHandler::handleRequest"
  role          = aws_iam_role.iam_for_lambda.arn
  runtime       = "java8"
  timeout       = 30

  source_code_hash = filebase64sha256("build/distributions/aws-lambda-grayscale-converter.zip")
}
