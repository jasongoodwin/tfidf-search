Document Search
===============

Simple application that will take a folder as input, and allow searches against the input

Environment Setup
-----------------

On a mac: 

    brew install sbt

Running
-------

From the folder, invoke sbt or activator with a path containing documents. A sample collection of documents is included.
To run with the sample documents:

    sbt 'run ./src/main/resources/source'

Running Tests
-------------

    sbt test


