JFLAGS = -g -sourcepath ~/
JC = javac

build: 
		$(JC) $(JFLAGS) *.java
run:	build
	java Main
clean:
		$(RM) *.class
