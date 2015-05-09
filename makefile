JARNAME=s4.jar

all:
	javac -d bin/ src/*.java

jar: all
	cd bin; jar cfe ../julia.jar Plotter *