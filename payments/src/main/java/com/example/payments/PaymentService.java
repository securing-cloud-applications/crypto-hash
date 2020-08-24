package com.example.payments;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public void processRefunds(Path refundsFile) {
    if (!isValid(refundsFile)) {
      throw new CorruptRefundFileException();
    }

    try {
      System.out.println("Issuing Refund to");
      System.out.println(Files.readString(refundsFile));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean isValid(Path refundsFile) {
    var actualHash = computeSha256(refundsFile);
    var exceptedHash = readExpectedHash(refundsFile);
    return actualHash.equals(exceptedHash);
  }

  private String computeSha256(Path refundsFile) {
    try {
      MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
      byte[] hash = sha256.digest(Files.readAllBytes(refundsFile));
      return String.valueOf(Hex.encode(hash));
    } catch (NoSuchAlgorithmException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String readExpectedHash(Path refundsFile) {
    try {
      var hashFile = refundsFile.resolveSibling(refundsFile.getFileName() + ".sha256");
      return Files.readString(hashFile);
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}
