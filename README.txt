README

Comments:
=========



Commands:
=========

To run tests:  mvn test

To start application:  mvn -Dmaven.test.skip=true wildfly:run


Application Tests:	
==================
http://localhost:8080/templatejee/rest/author/all
http://localhost:8080/templatejee/rest/post/all
http://localhost:8080/templatejee/rest/comment/all

http://localhost:8080/templatejee/rest/author/1
http://localhost:8080/templatejee/rest/post/1
http://localhost:8080/templatejee/rest/comment/1

http://localhost:8080/templatejee/rest/post/author/1
http://localhost:8080/templatejee/rest/comment/post/1

http://localhost:8080/templatejee/rest/author/create
http://localhost:8080/templatejee/rest/post/create
http://localhost:8080/templatejee/rest/comment/create

http://localhost:8080/templatejee/rest/comment/delete/1
http://localhost:8080/templatejee/rest/post/delete/1
http://localhost:8080/templatejee/rest/author/delete/1




