#! /usr/bin/python3
import boto3
import base64

client = boto3.client('lambda')
INPUT_FILE='./input.jpg'
OUTPUT_FILE='./output.jpg'
FUNCTION_NAME='convert-to-grayscale'

print('Reading file ' + INPUT_FILE + '...')
input_data = open(INPUT_FILE, 'rb').read()

print('Triggering function...')
payload = "\"" + base64.b64encode(input_data).decode('utf-8') + "\""
response = client.invoke(FunctionName=FUNCTION_NAME, Payload=payload)

with open(OUTPUT_FILE, 'wb') as output_file:
    output_payload = response['Payload'].read().replace(b'"', b'')
    output_data = base64.b64decode(output_payload)
    output_file.write(output_data)
    print('Wrote output file ' + OUTPUT_FILE)
