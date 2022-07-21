FROM python:3.7-alpine

COPY /BitcoinPrice /BitcoinPrice

WORKDIR /BitcoinPrice

RUN pip install -r requirements.txt
RUN pip install python-dotenv

EXPOSE 5000

CMD ["python3", "app.py"]