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
      String refundsJson = JsonUtils.toJson(refunds);
      Files.writeString(refundsFile, refundsJson);
      String hashValue = CryptoUtils.sha256(refundsJson.getBytes());

      String shaFilename = refundsFile.getFileName() + ".sha256";
      Path hashFile = refundsFile.resolveSibling(shaFilename);
      Files.writeString(hashFile, hashValue);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
