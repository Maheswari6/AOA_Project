all: Target

Target:
	javac MarsBase.java
	cp test MarsBase
	chmod 777 MarsBase
