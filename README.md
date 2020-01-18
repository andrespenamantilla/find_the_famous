# Celebrities

Find a celebrity in a population. 

A celebrity is a person who is known by every one, but doesn't know anybody.

I modeled it in two tables the first table contains all the people info (Id, and name)
The second table shows person that know other person.

ID,NAME <br/>
1,JAUME <br/>
2,FELIPE <br/>
3,HEMER <br/>
4,SCARLETT JOHANSSON <br/>

ID_PERSON,KNOWN_PERSON <br/>
1,4 <br/>
2,4 <br/>
3,4 <br/>
2,1 <br/>
1,3 <br/>

For this case SCARLETT JOHANSSON is the celebrity all people meet her but she doesn't kwnow anyone


BUILD

Download or clone Git repo: https://github.com/andrespenamantilla/find_the_famous.git
Java 11 is required
Import project as a maven project
Run Maven -> build with goals "clean compile assembly:single"


EXECUTION

Go to the unit test, and see all the casses that I build for test the scenarios. 
	
