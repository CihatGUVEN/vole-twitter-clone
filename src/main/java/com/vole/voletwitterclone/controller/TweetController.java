package com.vole.voletwitterclone.controller;

import com.vole.voletwitterclone.dto.TweetDto;
import com.vole.voletwitterclone.request.TweetCreateRequest;
import com.vole.voletwitterclone.response.TweetResponse;
import com.vole.voletwitterclone.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class TweetController {
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<TweetResponse> createTweet(@RequestBody TweetCreateRequest tweetCreateRequest,
                                                     @RequestHeader("x-username") String username) {

        TweetDto tweetDto = tweetService.createTweet(dozerBeanMapper.map(tweetCreateRequest, TweetDto.class), username);

        return ResponseEntity.status(HttpStatus.CREATED).body(dozerBeanMapper.map(tweetDto, TweetResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTweet(@PathVariable String id, @RequestHeader("x-username") String username) {

        tweetService.deleteTweet(id, username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetResponse> getTweetById(@PathVariable("id") String id) {
        return ResponseEntity.ok(dozerBeanMapper.map(tweetService.getTweetById(id), TweetResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<TweetResponse>> getAllTweets(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String tag,
            @RequestParam(defaultValue = "message", required = false) String sortBy
    ) {

        List<TweetDto> tweetDtoList = tweetService.getAllTweets(pageNo, pageSize, username, tag, sortBy);

        List<TweetResponse> tweetResponseList = tweetDtoList.stream().map(tweetDto ->
                dozerBeanMapper.map(tweetDto, TweetResponse.class)).collect(Collectors.toList());

        return ResponseEntity.ok(tweetResponseList);

    }

}
