all: Target

Target:
	javac MarsBase.java
	cp test.txt MarsBase
	chmod 777 MarsBase
