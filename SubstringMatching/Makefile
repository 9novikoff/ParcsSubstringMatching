all: run

clean:
	rm -f out/Main.jar out/SubstringMatcher.jar

out/Main.jar: out/parcs.jar src/Main.java
	@javac -cp out/parcs.jar src/Main.java
	@jar cf out/Main.jar -C src Main.class -C src Node.class
	@rm -f src/Main.class src/Node.class

out/SubstringMatcher.jar: out/parcs.jar src/SubstringMatcher.java
	@javac -cp out/parcs.jar src/SubstringMatcher.java
	@jar cf out/SubstringMatcher.jar -C src SubstringMatcher.class -C src Node.class
	@rm -f src/SubstringMatcher.class src/Node.class

build: out/Main.jar out/DSubstringMatcherFS.jar

run: out/Main.jar out/SubstringMatcher.jar
	@cd out && java -cp 'parcs.jar:Main.jar' Main
