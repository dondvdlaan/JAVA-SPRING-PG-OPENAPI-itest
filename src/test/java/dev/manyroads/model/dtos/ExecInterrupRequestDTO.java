package dev.manyroads.model.dtos;

import dev.manyroads.model.enums.ExecInterrupEnum;

public record ExecInterrupRequestDTO(
        Long customerNr,
        ExecInterrupEnum execInterrupType,
        String matterNr
) {
}
