@echo off
echo ------- BOOK ---------
echo -- application/*  =  XML  =  JSON
echo.
call curl http://localhost:8081/books -H Accept:application/*
echo.
echo ----------------------
call curl http://localhost:8081/books/1 -H Accept:application/*
echo.
echo ----------------------
call curl http://localhost:8081/books -H Accept:application/xml
echo.
echo ----------------------
call curl http://localhost:8081/books/1 -H Accept:application/xml
echo.
echo ----------------------
call curl http://localhost:8081/books -H Accept:application/json 
echo.
echo ----------------------
call curl http://localhost:8081/books/1 -H Accept:application/json 
echo.
echo ----------------------
echo ------- LIBS ---------
echo -- application/*  =  XML  =  JSON
echo.
call curl http://localhost:8081/libs -H Accept:application/* 
echo.
echo ----------------------
call curl http://localhost:8081/libs/1 -H Accept:application/* 
echo.
echo ----------------------
call curl http://localhost:8081/libs -H Accept:application/xml
echo.
echo ----------------------
call curl http://localhost:8081/libs/1 -H Accept:application/xml
echo.
echo ----------------------
call curl http://localhost:8081/libs -H Accept:application/json 
echo.
echo ----------------------
call curl http://localhost:8081/libs/1 -H Accept:application/json 
echo.
echo ----------------------