FROM python:3.7-alpine

RUN pip install python-dotenv

WORKDIR /code

COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt

EXPOSE 5000

COPY . .

CMD ["python3", "app.py"]