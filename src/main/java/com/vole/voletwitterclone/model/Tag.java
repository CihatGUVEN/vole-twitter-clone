package com.vole.voletwitterclone.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private String tag;
    private String slug;
}
