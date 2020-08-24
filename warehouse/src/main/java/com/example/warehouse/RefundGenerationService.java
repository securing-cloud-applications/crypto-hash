package com.example.warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

@Component
public class RefundGenerationService {

  private ObjectMapper objectMapper;

  public RefundGenerationService() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
  }

  public void generateReport(Path refundsFile, List<Refund> refunds) {
    this.generateRefundFile(refundsFile, refunds);
    this.generateSha256HashFile(refundsFile);
  }

  private void generateRefundFile(Path refundsFile, List<Refund> refunds) {
    try {
      objectMapper.writeValue(refundsFile.toFile(), refunds);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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

  private void generateSha256HashFile(Path refundsFile) {
    try {
      var hashValue = computeSha256(refundsFile);
      var hashFile = refundsFile.resolveSibling(refundsFile.getFileName() + ".sha256");
      Files.writeString(hashFile, hashValue);
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}
