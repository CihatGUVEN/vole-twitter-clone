package com.vole.voletwitterclone.request;

import com.vole.voletwitterclone.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetCreateRequest {

    private String message;

}
