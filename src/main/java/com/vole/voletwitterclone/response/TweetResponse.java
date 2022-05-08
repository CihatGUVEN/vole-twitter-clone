package com.vole.voletwitterclone.response;

import com.vole.voletwitterclone.model.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TweetResponse {

    private String id;

    private String username;

    private String message;

    private List<Tag> tags;

}
