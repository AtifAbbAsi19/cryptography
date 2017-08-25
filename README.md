# Cryptography using Conceal 


## What is Conceal? 
Conceal provides a set of Java APIs to perform cryptography on Android. 
It was designed to be able to encrypt large files on disk in a fast and 
memory efficient manner. 

The major target for this project is typical Android devices which run old 
Android versions, have low memory and slower processors.

Unlike other libraries, which provide a Smorgasbord of encryption algorithms 
and options, Conceal prefers to abstract this choice and use sane defaults. 
Thus Conceal is not a general purpose crypto library, however it aims to provide 
useful functionality.

## Usage

#### Entity and keys

**Entity:** this is a not-secret identifier of your data. It's used for integrity check purposes (to know that the content has not been tampered) and also to verify it was not swapped with another valid encrypted content/file.

**Key:** the key is provided by the KeyChain implementation passed to the Crypto object. So each time a new encryption is requested, the key is requested to the KeyChain. The key is generated randomly the first time on demand. You might change the implementation by we strongly suggest to generate a random value. If the encryption key needs for some reason to be based on a text password, you can try using the PasswordBasedKeyGenerator object.

#### Encryption
```java
// Creates a new Crypto object with default implementations of a key chain
KeyChain keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
Crypto crypto = AndroidConceal.get().createDefaultCrypto(keyChain);

// Check for whether the crypto functionality is available
// This might fail if Android does not load libaries correctly.
if (!crypto.isAvailable()) {
  return;
}

OutputStream fileStream = new BufferedOutputStream(
  new FileOutputStream(file));

// Creates an output stream which encrypts the data as
// it is written to it and writes it out to the file.
OutputStream outputStream = crypto.getCipherOutputStream(
  fileStream,
  Entity.create("entity_id"));

// Write plaintext to it.
outputStream.write(plainText);
outputStream.close();
```

#### Decryption
```java
// Get the file to which ciphertext has been written.
FileInputStream fileStream = new FileInputStream(file);

// Creates an input stream which decrypts the data as
// it is read from it.
InputStream inputStream = crypto.getCipherInputStream(
  fileStream,
  Entity.create("entity_id"));

// Read into a byte array.
int read;
byte[] buffer = new byte[1024];

// You must read the entire stream to completion.
// The verification is done at the end of the stream.
// Thus not reading till the end of the stream will cause
// a security bug. For safety, you should not
// use any of the data until it's been fully read or throw
// away the data if an exception occurs.
while ((read = inputStream.read(buffer)) != -1) {
  out.write(buffer, 0, read);
}

inputStream.close();


If you don't have a lot of data to encrypt, you could
use the convenience functions:

###java
byte[] cipherText = crypto.encrypt(plainText, Entity.create("mytext"));

byte[] plainText = crypto.decrypt(cipherText, Entity.create("mytext"));


#### Integrity
###java
OutputStream outputStream = crypto.getMacOutputStream(fileStream, entity);
outputStream.write(plainTextBytes);
outputStream.close();

InputStream inputStream = crypto.getMacInputStream(fileStream, entity);

// Will throw an exception if mac verification fails.
// You must read the entire stream to completion.
// The verification is done at the end of the stream.
// Thus not reading till the end of the stream will cause
// a security bug. For safety, you should not
// use any of the data until it's been fully read or throw
// away the data if an exception occurs.
while((read = inputStream.read(buffer)) != -1) {
  out.write(buffer, 0, read);
}
inputStream.close();


### Upgrade notes

Starting with v1.1 recommended encryption will use a 256-bit key (instead of 128-bit). This means stronger security.
You should use this default.

If you need to read from an existing file, you still will need 128-bit encryption. You can use the old way of creating `Crypto` objects as it preserves its 128-bit behavior. Although ideally you should re-encrypt that content with a 256-bit key.

Also there's an improved way of creating Entity object which is platform independent. It's strongly recommended for new encrypted items although you need to stick to the old way for already encrypted content.

#### Existing code still with 128-bit keys and old Entity (deprecated)

```java
// this constructor creates a key chain that produces 128-bit keys
KeyChain keyChain = new SharedPrefsBackedKeyChain(context);
// this constructor creates a crypto that uses  128-bit keys
Crypto crypto = new Crypto(keyChain, library);
Entity entity = new Entity(someStringId);


#### New code using 256-keys and Entity.create

We recommend the use of the factory class `AndroidConceal`.

```java
// explicitely create 256-bit key chain
KeyChain keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
// create the default crypto (expects 256-bit key)
AndroidConceal.get().createDefaultCrypto(keyChain);
// factory class also has explicit methods: createCrypto128Bits and ceateCrypto256Bits if desired.
Entity entity = Entity.create(someStringId);

#### New code using 256-keys and Entity.create

###To install conceal on your Android application project, First you need to download the following binaries :

###libconceal.jar from https://raw.github.com/facebook/conceal/gh-pages/downloads/libconceal.jar

###conceal_android.jar from https://raw.github.com/facebook/conceal/gh-pages/downloads/conceal_android.jar

##instructions

1) to Add .jar files in the project, Copy the downloaded .jar file.
and paste the file in Libs folder, now open File>Project Structure>Dependencies ,then Click on add button
select second Option jar Dependencies and add jar files to project
or open build.gradle.
simply Add both jars as dependencies

```compile files('libs/libconceal.jar')```

```compile files('libs/conceal_android.jar')``` and sync project

#Nativebinaries from https://raw.github.com/facebook/conceal/gh-pages/downloads/libs.zip

go to App/src/main/ create jniLibs folder.

Then, unzip libs.zip drop the .so files into a jniLibs/ folder located at src/main/jniLibs.
