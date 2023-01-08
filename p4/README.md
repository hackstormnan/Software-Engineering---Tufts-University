JRails and Books
================
This project contains two subprojects: jrails, which contains the framework code 
for the JRails library, and books, which contains the code for the books server.

## Gradle
Gradle is the current "state of the art" build and dependency tool in java.
This project uses a somewhat complex but very common setup for gradle. The top level 
project "p4" has a settings.gradle and a build.gradle as well as the "gradle wrapper,"
which will install gradle onto your system if it has not done so already. Each
project then also has its own build.gradle file that defines its own dependencies and gradle
tasks.

To see a list of gradle tasks run `./gradlew tasks`. 

To build and run the server run `./gradlew run`.

To run your unit tests run `./gradlew test`

## Gradle and Intellij
To import a gradle project into intellij follow the instructions [here](https://www.jetbrains.com/help/idea/gradle.html#).

Once imported you should see an elephant symbol and the word gradle on the right side
of the Intellij window. Clicking it will allow you to run gradle builds and tests through the IDE.
