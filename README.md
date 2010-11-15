This is just a sample project to toy around with writing a classloader.

Directory Structure
===================

  * builder - Contains the Java source for the Language Builder
  * languages - Contains various language files to be loaded at runtime
  * loader - Contains the core app and the custom classloader

Running the Program
===================

Simply clone the project and run `ant` from the top-level directory. Each of the files in the `languages` folder will be compiled and subsequently loaded at runtime.

Each of the Java files in the `languages` folder defines a class that has two methods: `getLanguage()` and `getGreeting()`. Each class is loaded at runtime and these methods are called and the values they return outputted.

Adding Custom Languages
=======================

If you want to add custom languages, use the `addlang.sh` shell script to do so, using `./addlang.sh Language Greeting`, as so:

    ./addlang.sh Polish "Dzien dobry"

If you prefer, check the source of `addlang.sh` to see the Ant command you can use to build new languages. Or, build them by hand.