package com.example.warehouse;

import com.example.util.CryptoUtils;
import com.example.util.JsonUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RefundGenerationService {

  public void generateReport(Path refundsFile, List<Refund> refunds) {
    try {
      this.generateRefundFile(refundsFile, refunds);
      this.generateSha256HashFile(refundsFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void generateRefundFile(Path refundsFile, List<Refund> refunds) throws IOException {
    String refundsJson = JsonUtils.toJson(refunds);
    Files.writeString(refundsFile, refundsJson);
  }

  private void generateSha256HashFile(Path refundsFile) throws IOException {
    Path hashFile = refundsFile.resolveSibling(refundsFile.getFileName() + ".sha256");
    String hashValue = CryptoUtils.sha256(Files.readAllBytes(refundsFile));
    Files.writeString(hashFile, hashValue);
  }
}
