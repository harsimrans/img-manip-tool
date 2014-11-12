IMAGE PROCESSING TOOL
=================================================

Starting point can be built using the supplied Makefile:
--------------------------------------------------------
make		builds .class files
make jar	builds ImageBase.jar file
make clean      remove all .class and .jar files

or, to build the jar file by hand: jar cvfm ImageBase.jar manifest.mft *.class

To run:
-------
java ImageBase  <image_file_name>  
 
java -jar ImageBase.jar  <image_file_name>  

The idea and starter code has been taken from http://www.cs.swarthmore.edu/~newhall/imagemanip/


