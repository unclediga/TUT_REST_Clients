@echo off
echo curl http://localhost:8080/books/1 -H Accept:application/*
curl http://localhost:8080/books/1 -H Accept:application/*
echo.
echo curl http://localhost:8080/books/1 -H Accept:application/json
call curl http://localhost:8080/books/1 -H Accept:application/json
echo.
echo curl http://localhost:8080/books/1 -H Accept:application/text
call curl http://localhost:8080/books/1 -H Accept:application/text
echo.
echo curl http://localhost:8080/books/1 -H Accept:application/xml
call curl http://localhost:8080/books/1 -H Accept:application/xml

