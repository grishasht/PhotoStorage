# PhotoStorage

!!! You don't need to install a DB because project uses embedded H2 database which is defined as project dependency. 

To run just start a project. 
I guess that problems with database initialization can occur.
In this case you need to configure datasource using IntellijIdea
and create table by your own.

If you need to test client you can write any 'main' method, create ImageClient using factory 
and tests its methods.