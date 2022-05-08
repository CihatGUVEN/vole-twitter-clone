package com.vole.voletwitterclone;

import com.vole.voletwitterclone.dto.TweetDto;
import com.vole.voletwitterclone.entity.Tweet;
import com.vole.voletwitterclone.model.Tag;
import com.vole.voletwitterclone.repository.TweetRepository;
import com.vole.voletwitterclone.service.TweetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceTest {

    @Mock
    TweetRepository tweetRepository;

    @InjectMocks
    @Spy
    private TweetService tweetService;

     Tweet tweet;
     TweetDto tweetDto;
     Tag tag;

    @Before
    public void init() {

          tag = Tag.builder()
                .tag("BlackLivesMatters")
                .slug("blackLivesMatters")
                .build();

          List<Tag> tagList = List.of(tag);


        tweet = Tweet.builder()
                .id("1")
                .username("vole")
                .message("Hello twitter")
                .tags(tagList)
                .build();

        tweetDto = TweetDto.builder()
                .id(tweet.getId())
                .username(tweet.getUsername())
                .message(tweet.getMessage())
                .tags(tweet.getTags())
                .build();

    }

    @Test
    public void createTweet() {
        when(tweetRepository.save(any())).thenReturn(tweet);

        TweetDto tweetReturnDto = tweetService.createTweet(tweetDto, "vole");

        assertEquals(Optional.of("1"), Optional.ofNullable(tweetReturnDto.getId()));

    }

    @Test
    public void deleteTweet() {
        when(tweetRepository.findById("1")).thenReturn(Optional.of(tweet));
        tweetService.deleteTweet("1", "vole");
        verify(tweetRepository).deleteById("1");
    }

    @Test
    public void getTweetById() {

        when(tweetRepository.findById("1")).thenReturn(Optional.of(tweet));

        TweetDto tweetReturnDto = tweetService.getTweetById("1");

        assertEquals(Optional.of("1"), Optional.ofNullable(tweetReturnDto.getId()));
    }


}
