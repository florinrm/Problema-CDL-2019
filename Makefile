build:
	javac *.java

clean:
	rm -rf *.o *class

run:
	java Main $(var)