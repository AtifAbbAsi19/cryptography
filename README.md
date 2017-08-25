# Cryptography using Conceal 

To install conceal on your Android application project, First you need to download the following binaries :

#libconceal.jar from https://raw.github.com/facebook/conceal/gh-pages/downloads/libconceal.jar

#conceal_android.jar from https://raw.github.com/facebook/conceal/gh-pages/downloads/conceal_android.jar

Add .jar files in the project, Follow the instructions

1) Copy the downloaded .jar file.
2) paste the file in Libs folder, now open File>Project Structure>Dependencies ,then Click on add button
select second Option jar Dependencies and add jar files to project
or open build.gradle.
simply Add both jars as dependencies

```compile files('libs/libconceal.jar')```

```compile files('libs/conceal_android.jar')``` and sync project

#Nativebinaries from https://raw.github.com/facebook/conceal/gh-pages/downloads/libs.zip

go to App/src/main/ create jniLibs folder.

Then, unzip libs.zip drop the .so files into a jniLibs/ folder located at src/main/jniLibs.
