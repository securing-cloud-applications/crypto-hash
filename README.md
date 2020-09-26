# crypto-hash

Demo project showing how to use cryptographic hash functions to guarantee data integrity. There
are three sub-projects in this repo.

* utils - contains shared utility classes `CrptoUtils` and `JsonUtils`.  
* warehouse - generates `data/refunds.json` file, depends on the utils project. 
* payments -  reads `data/refunds.json` and verifies its integrity using SHA-256, depends on utils.

## software prerequisites 

* Java 11 JDK 
* Java IDE 

## build

Apache Maven is the build system so `mvnw clean package`  builds the applications. You can 
also import the project into your favourite IDE to explore the code. 

## run on the command line

* run warehouse app `java -jar warehouse/target/warehouse-0.0.1-SNAPSHOT.jar` to generate the 
  `data/refunds.json` and `data/refunds.json.sha256` files
* run payments app `java -jar payments/target/payments-0.0.1-SNAPSHOT.jar` to red the 
  `data/refunds.json` and verify it's integrity  against  `data/refunds.json.sha256`

## run from the IDE 

* run `com.example.warehouse.WarehouseApplication` to generate the `data/refunds.json` and `data/refunds.json.sha256`
* run `com.example.payments.PaymentsApplication` to read the `data/refunds.json` and verify its integrity 
* edit `data/refunds.json` to simulate corruption. you can add a newline at the end of the file.
* run `com.example.payments.PaymentsApplication` to get a data corruption exception 

## interesting files to look at 

* `util/src/main/java/com/example/util/CryptoUtils.java` to examine how th sha-256 is computed
* `warehouse/src/main/java/com/example/warehouse/RefundGenerationService.java` to examine the code
that generates the `refund.json` and `refund.json.sha256` file
* `payments/src/main/java/com/example/payments/PaymentService.java` to examine the code that 
 verifies the integrity of the `refund.json` file before consuming it.
