package dev.manyroads.model.dtos;

import java.util.UUID;

public record IntermediateReportMatterRequestDTO(
        String matterNr,
        String intermediateReportExplanation
) {
}
