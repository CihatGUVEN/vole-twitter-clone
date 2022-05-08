package com.vole.voletwitterclone.dto;

import com.vole.voletwitterclone.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetDto {

    private String id;

    private String username;

    private String message;

    private List<Tag> tags;
}
