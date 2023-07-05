# Telegram Bot Latex OCR
This is a Java project that utilizes a Telegram bot to process photos and return LaTeX formulas.

## Description
The user sends the telegram bot a photo of formulas to be translated into latex. The bot returns the formula in a reply message.  
  
The project consists of three modules: **Dispatcher**, **Telegram-node**, and **Latex-ocr**.  
**Dispatcher** receives messages from the user's telegram and sends them to rabbitmq for processing.  
**Telegram-node** processes the telegram photos from the queue and sends them to the latex-ocr module via REST.  
**Latex-ocr** translates the photos into latex using the python library [pix2tex](https://github.com/lukas-blecher/LaTeX-OCR).  

## Prerequisites
Before running the project, make sure you have the following installed:  
- Docker  
- Maven  
- Python 3.x  

## Installation
To set up the project, follow these steps:  
  
Clone the repository: `git clone https://github.com/corenlix/latexocrtelegram.git`  
Change into the project directory: `cd telegram-bot-latex-ocr`  
Build the Docker containers: `docker-compose build`  
Start the Docker environment: `docker-compose up`  

Set environment variables:  
`TG_BOT_NAME=<your_telegram_token>`  
`TG_BOT_TOKEN=6238743291:AAFY1Tdb7rwxHtzWEVpwo3_3MBqhLdor72Y`  
`RABBITMQ_USERNAME=rmuser`  
`RABBITMQ_PASSWORD=rmpassword`  

## Running application

#### Dispatcher
To start the Dispatcher service, follow these steps:
  
1. Change into the dispatcher directory: `cd dispatcher`  
2. Run the Spring Boot application: `mvn spring-boot:run`  

#### Telegram Node
To start the Telegram Node service, follow these steps:  
1. Change into the telegram-node directory: `cd telegram-node`
2. Run the Spring Boot application: `mvn spring-boot:run`

#### Latex OCR
To start the Latex OCR service, follow these steps:  

1. Change into the latex-ocr directory: `cd latex-ocr`
2. Run the FastAPI application using Uvicorn: `uvicorn main:app`

## Usage example
Send photo with formula:  
![image](https://github.com/Corenlix/LatexOcrTelegram/assets/58521600/bfbb4645-52c0-429c-b41b-d5647e49587f)  
When the photo is processed, the bot will send the formula  
![image](https://github.com/Corenlix/LatexOcrTelegram/assets/58521600/75762c85-613a-4763-808d-76742d10dbe7)
