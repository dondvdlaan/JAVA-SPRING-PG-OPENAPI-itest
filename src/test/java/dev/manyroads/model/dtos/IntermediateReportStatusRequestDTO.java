package dev.manyroads.model.dtos;

import java.util.List;
import java.util.UUID;

public record IntermediateReportStatusRequestDTO(
        UUID chargeID,
        String statusIntermediateReport,
        List<IntermediateReportMatterRequestDTO> mattersIntermediateReport
) {
}
