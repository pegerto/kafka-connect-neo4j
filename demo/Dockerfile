FROM python:3.6-stretch

WORKDIR /producer
ADD . /producer
RUN pip install -r requirements.txt

ENTRYPOINT python producer.py
