package com.wafflebank.card.model.build;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Build information about account microservice")
@Builder
public class BuildInfo {
    String version;
    String javaVersion;
}
