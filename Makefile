# Makefile for Image Processing Project starting point
#
# make            builds .class files
# make jar        builds ImageBase.jar file
# make clean      remove all .class and .jar files
#

JFLAGS = 

JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        ImageBase.java \
        Picture.java \
        PictureFrame.java \
        Pixel.java \
        SimpleButton.java 

default: classes

classes: $(CLASSES:.java=.class)

jar:  $(CLASSES)
	jar cvfm ImageBase.jar manifest.mft *.class

clean:
	$(RM) *.class ImageBase.jar
