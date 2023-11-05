

# Defining some variables to prevent typos
SRC_DIR=.
SOURCE_FILE=sources
JUNIT_JAR=lib/junit-platform-console-standalone-1.9.3.jar
HTML_GEN_JAR=lib/html2image-0.9.jar
ENV_FORMAT="| %-20s | %-47s |\n"
OUT_DIR=target

# Little utility to print out my variables
env:
	@echo "Current environment configuration:"
	@head -c 74 < /dev/zero | tr '\0' '-'
	@echo
	@printf $(ENV_FORMAT) "OUT_DIR" $(COMPILE_DIR)
	@printf $(ENV_FORMAT) "SRC_DIR" $(SRC_DIR)
	@printf $(ENV_FORMAT) "SOURCE_FILE" $(SOURCE_FILE)
	@printf $(ENV_FORMAT) "JUNIT_JAR" $(JUNIT_JAR)
	@printf $(ENV_FORMAT) "HTML_GEN_JAR" $(HTML_GEN_JAR)
	@head -c 74 < /dev/zero | tr '\0' '-'
	@echo

clean:
	clear
	@echo "Deleting all class files..."
	@find . -name "*.class" -type f -delete
	@echo "Deleting compiled files..."
	@rm -rf $(OUT_DIR)

server: clean
	@javac -d $(OUT_DIR) driver/ServerStartup.java

run: server
	@clear
	@java -cp $(OUT_DIR) driver.ServerStartup -p 9876 -r "./docroot" 


# Pretty much the same as compile, but we want to include the tests
#     and their dependencies (the junit jar)
# find adds all files with the ".java" extension from the current 
#     directory (instead of the ./src directory) to the "sources.txt"
#     file. could I have used the compile directive? sure, but I'm
#     lazy, and this was easier for me
# since there are some additional dependencies, we need to set the 
#     classpath (-cp) for the java compiler to include those dependencies
# then we clean up the temporary "sources.txt" file, as before
all-tests: clean
	@echo "Compiling for testing..."
	@find . -name "*.java" > $(SOURCE_FILE)
	@javac -d $(OUT_DIR) -cp $(OUT_DIR):$(JUNIT_JAR):$(HTML_GEN_JAR):. @$(SOURCE_FILE)
	@rm $(SOURCE_FILE)

# Depends on all-tests to get the tests compiled as well as
#     the src directory
# When we run, we want to run the junit jar in order to run the tests,
#     but we need to tell java that the classpath that should be used
#     is the target directory. --scan-classpath just looks in the classpath
#     for _any_ tests, and executes those
test-all: all-tests
	@echo "Running tests..."
	@java -jar $(JUNIT_JAR) -cp $(OUT_DIR) --disable-banner --include-classname=.* --scan-classpath

# we can run individual tests by specifying them using the --select-class flag
# the TEST_NAME environment variable must be the fully qualified class name, 
#     like tests.packageName.TestClassName
# to run a single test, us:
#     make test TEST_NAME=tests.packageName.TestClassName
test: all-tests
	@echo "Running $(TEST_NAME)..."
	@java -jar $(JUNIT_JAR) -cp $(OUT_DIR) -c $(TEST_NAME)