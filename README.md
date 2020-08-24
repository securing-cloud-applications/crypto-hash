# crypto-hash

Demo project showing how to use cryptographic hash functions to gurantee data integrity. There
are two sub projects in this repo.

* warehouse - which generates a `refunds.csv` file into the `data` folder 
* payments - which reads the `data/refunds.csv` and verifies its integrity using SHA-256

## software prerequisites 

* Java 11 JDK 
* Java IDE 


## setup

Apache Maven is the build system in use. You should import the project into your favourite IDE so
you can more easily explore the code.

## run 

Run the applications in the following order.

* run warehouse to generate the `data/refunds.csv` and `data/refunds.csv.sha256`
* run payments to read the `data/refunds.csv` and verify its integrity 
* edit `data/refunds.csv` so simulate corruption you can just add a newline at the end of the file
* run payments to get a data corruption exception 

## interesting files to look at 

* 
