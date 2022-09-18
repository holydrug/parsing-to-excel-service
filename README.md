# parsing-to-excel-service

## Table of content ##
- [Overview](#Overview)
- [Logic](#Logic)
  - [ExcelService](#ExcelService)
  - [JavaMailSenderService](#JavaMailSenderService)
- [Controllers](#Controllers)
  - [FormController](#FormController)
  - [TestController](#TestController)
- [Docker](#Docker)
  - [Building DOCKER](#Building-DOCKER)

## Overview ##

- Main purpose of project is parse data from table to xlsx format file and send files to your mail
- Two primary service helps to achieve that
1. ExcelService
2. JavaMailSenderServiceImpl


## STEP BY STEP IMAGE SHOWCASE ##
![1](images/1.%20needed-table-to-import.png)
![2](images/2.%20frontend-showcase.png)
![3](images/3.%20result-of-import.png)
![4](images/4.%20result-of-import-in-mail-storage.png)

## Logic ##

### ExcelService ###

> That service connects to table url with jsoup library and parse all data to local copy of file

    Main process declared in ExcelServiceImpl realization of ExcelService interface

### JavaMailSenderService ###

> That service send all .xlsx files from dir to you mail as attached files

    Main process declared in JavaMailSenderServiceImpl realization of JavaMailSenderService interface

    I configured JavaMailSender bean in BaseConfiguration class with lazy initialization to let you insert data from frontend w/out NPE on start

    Also bean is configured with prototype scope to let you change any data to sender credentials

## Controllers ##

    There are two controller to handle data from frontend:

1. FormController
2. TestController


### FormController ###

    That controller is handled by frontend part of project and setup CommonStructure object with given data
    When it is configured controller setup all other object with proper fields base of CommonStructure object

### TestController ###

    Usually you will initialize all logic of project (setup of files + sending it) when scheduler is triggered with cron expression
    But I also configured /init endpoint to force run

## Frontend ##

    I configured html page base on freemarker template
    You can find out source code in resources/templates/form.ftlh

    css file setup needed margarins
    css file is located in resources/static/css/main.css 

## Docker ##
    
    Whole project also tested in docker
    I configured docker-compose file to let you run it without any troubles
    You must not setup env variable due to moved that function to frontend part

### Building DOCKER ###

> To build local services execute command below from root project dir:

    cd docker/ && docker-compose up -d