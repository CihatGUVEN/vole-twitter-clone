package com.vole.voletwitterclone.entity;

import com.vole.voletwitterclone.model.Tag;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Tweet {

    @Id
    private String id;

    private String username;

    private String message;

    private List<Tag> tags;

}

