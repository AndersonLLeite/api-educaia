package com.api.educaia.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ClassIdentifierDTO {
        private UUID id;
        private String name;

        public ClassIdentifierDTO(UUID id, String name) {
            this.id = id;
            this.name = name;
        }
}
