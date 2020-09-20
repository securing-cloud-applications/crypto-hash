# crypto-hash

Demo project showing how to use cryptographic hash functions to guarantee data integrity. There
are two sub projects in this repo.

* warehouse - which generates a `refunds.json` file into the `data` folder 
* payments - which reads the `data/refunds.json` and verifies its integrity using SHA-256

## software prerequisites 

* Java 11 JDK 
* Java IDE 

## setup

Apache Maven is the build system in use. You should import the project into your favourite IDE so
you can more easily explore the code.

## run 

Run the applications in the following order.

* run `com.example.warehouse.WarehouseApplicationTests` to generate the `data/refunds.json` and `data/refunds.json.sha256`
* run `com.example.payments.PaymentsApplicationTests` to read the `data/refunds.json` and verify its integrity 
* edit `data/refunds.json` to simulate corruption. you can add a newline at the end of the file.
* run `com.example.payments.PaymentsApplicationTests` to get a data corruption exception 

## interesting files to look at 

* `warehouse/src/main/java/com/example/warehouse/RefundGenerationService.java` to examine the code
that generates the `refund.json` and `refund.json.sha256` file
* `payments/src/main/java/com/example/payments/PaymentService.java` to examine the code that 
 verifies the integrity of the `refund.json` file before consuming it.
