package com.example.payments;

import com.example.util.CryptoUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

  public void processRefunds(Path refundsFile) {
    try {
      if (!isValid(refundsFile)) {
        throw new CorruptRefundFileException();
      }
      System.out.println("Issuing Refund to");
      System.out.println(Files.readString(refundsFile));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean isValid(Path refundsFile) throws IOException {
    Path hashFile = refundsFile.resolveSibling(refundsFile.getFileName() + ".sha256");
    String actualHash = CryptoUtils.sha256(Files.readAllBytes(refundsFile));
    String exceptedHash = Files.readString(hashFile);
    return actualHash.equals(exceptedHash);
  }
}
