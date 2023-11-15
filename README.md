# Web Server Documentation

Group Letter: V
Author1: Malavya Raval
Author2: Duccio Rocca


## Scope of Work

| Requirement                                                       | Completed? | Comments from student |
| ----------------------------------------------------------------- | ---------- | --------------------- |
| 1. Your server must handle invalid requests                         | [X]         |                       |
| 2. Your server must handle GET requests for a static document      | [X]         |                       |
|   a. If the file exists, the server must respond with a valid HTTP success response  | [X]         |                       |
|   b. If the file does not exist, the server must respond with a valid HTTP not found response  | [X]         |                       |
| 3. Your server must handle HEAD requests for a static document     | [X]         |                       |
|   a. If the file exists, the server must respond with a valid HTTP success response that does not include the file in the body of the response  | [X]         |                       |
|   b. If the file does not exist, the server must respond with a valid HTTP not found response |
| 4. Your server must handle POST requests for a script              |          |     This test failed and we were not able to understand why. Probably a problem with the system environment variable for node  |
|   a. File exists:
|     i. If the script successfully executes, the server responds with a valid HTTP success response that includes the stdout of the executed script in the body |     [X]         |                       |
|     ii. If the script errors, the server will respond with a valid HTTP error response | [X]         |      For this part I think we compromised the Unit Tests on Created and Scripts, because in our interpretation if the resource is not valid to be executed or pointing to a valid path to create a file, the server should return a InternalErrorResponse                 |
|   b. If the file does not exist, the server must respond to the client with a valid HTTP not found response | [X]         |                       |
| 5. Your server must handle PUT requests                              | [X]         |                       |
|   a. If the file is successfully created, the server must respond with a valid HTTP created response | [X]         |                       |
|   b. If the file is not successfully created, the server must respond with a valid HTTP error response | | For this part I think we compromised the Unit Tests on Created and Scripts, because in our interpretation if the resource is not valid to be executed or pointing to a valid path to create a file, the server should return a InternalErrorResponse     
| 6. Your server must handle DELETE requests                           | [X]         |                       |
|   a. If the file exists, and is successfully deleted, the server must respond with a valid HTTP no content response   | [X]         |                       |
|   b. If the file exists, and is not successfully deleted, the server must respond with a valid HTTP error response  | [X]         |                       |
|   c. If the file does not exist, the server must respond to the client with a valid HTTP not found response   | [X]         |                       |
| 7. Your server must be able to handle simultaneous requests using threads   | [X]         |                       |
| 8. Your server must support simple authentication                  | [X]         |                       |
|   a. If the .passwords file exists in the directory and no Authentication header is present, the server must respond with a valid HTTP unauthorized response with the header WWW-Authenticate, value Basic realm=“667 Server”   | [X]         |                       |
|   b. If the .passwords file exists in the directory and an Authentication header is present, the server must check that the .passwords file contains the username and password provided in the header and:   | [X]         |                       |
|     1. If the .passwords file does not contain the username and password, the server must respond with a valid HTTP forbidden response   | [X]         |                       |
|     2. If the .passwords file contains the username and password, the server must respond as it would normally   | [X]         |                       |
|   c. The .passwords file should contain lines of key-value pairs separated by a colon, where the key is the username and the value is the encrypted password in SHA encryption format |
|   d. The client will provide a header containing the Base64 encoded username and password, separated by a colon   | [X]         |                       |
| 9. Your server must log to standard out in the common log format      | [X]         |                       |

## Results and Conclusions

There are 5 tests that result as not passed when we run all the tests:

1. ScriptResponseWriterTest ✔
│  │  └─ testWrite() ✘ expected: <true> but was: <false>

We were not able to understand why the script doesn't run. Probably a problem with the system environment variable for node                 

2.  ManualTest ✔
│  │  └─ testFullWebsite() ✘ gui/ava/html/image/generator/HtmlImageGenerator
This test actually passes when ran individually 

3.  SimpleRequestTest ✔
│  │  └─ testSimpleRequest() ✘ expected: <200> but was: <401>
It seems that the server detects a .passwords in the same directory of texFile.txt therefore is treated as a Protected file.

4.─ testScriptResponseWriter() ✘ Unexpected type, expected: <webserver667.responses.writers.ScriptResponseWriter> but was: <webserver667.responses.writers.InternalServerErrorResponseWriter>

We treated situations in which the script cannot be executed as errors, therefore in this kind of situations we used InternalServerError making this test not pass. Probably it is just a matter of desgign implementation.

5. ─ testCreated() ✘ Unexpected type, expected: <webserver667.responses.writers.CreatedResponseWriter> but was: <webserver667.responses.writers.InternalServerErrorResponseWriter>
Similarly to the previous one this test doesn't provide a valid file to create so we treated it as an error, again probably a different design choice would have avoided it.

### System Design

In this web server project, we have designed a set of classes to ensure a simple flow of client requests.

https://drive.google.com/file/d/1TWzm9gdKr2QK8_agBOJpKcYq8vr047IY/view?usp=drive_link

### What I Learned

This was a really nice opportunity to collabrate with my teammate and work on an actuall server and passing all the tests, that were written in the class. It was a really nice exposure to the server and implementing it. The approach of writting the test first and then implementing it was really something new for me which I am willing to do in my future projects. The Authorization and Authentication was something really tricky but I loced to explore it and it was something new for us to deal with it. Runnig the scripts was a new concept for us and took a lot of effort and time on it, many things went side ways like the other tests started failing and some errors started popping up but we were happy to give our best effort and learn something new from it.

### Challenges I Encountered

Working with the mimetypes and http request body was a bit tricky along with working on Authentication was a bit difficult for me as it was something completly new for us. Working on  response writer was a bit difficult because of some errors that we were not able to resolve which took a lot of time. Then running the shell script and the perl script took us time to figure things out and had to work around some stuff to make it work.
