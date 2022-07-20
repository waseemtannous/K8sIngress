FROM python:3.7-alpine

# install git
# RUN apk update
# RUN apk add git

# RUN git clone https://github.com/waseemtannous/BitcoinPrice.git
COPY /BitcoinPrice /BitcoinPrice

WORKDIR /BitcoinPrice

RUN pip install -r requirements.txt
RUN pip install python-dotenv

EXPOSE 5000

CMD ["python3", "app.py"]