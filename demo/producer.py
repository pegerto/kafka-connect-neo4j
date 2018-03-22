import time
import json

from random import randint
from kafka import KafkaProducer

def produce():
    producer = KafkaProducer(bootstrap_servers='broker:9092')
    while True:
        uid = randint(0, 100)
        user = {'id': uid, 'name': "user"+str(uid)}
        producer.send('users', bytes(json.dumps(user),'utf-8'))
        producer.flush()
        time.sleep(1);

while True:
    try:
        produce()
    except Exception as e:
        print(e)
