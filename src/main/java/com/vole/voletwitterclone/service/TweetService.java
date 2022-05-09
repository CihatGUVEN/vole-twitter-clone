package com.vole.voletwitterclone.service;

import com.vole.voletwitterclone.dto.TweetDto;
import com.vole.voletwitterclone.entity.Tweet;
import com.vole.voletwitterclone.model.Tag;
import com.vole.voletwitterclone.repository.TweetRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TweetService {
    private final TweetRepository tweetRepository;
    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public TweetDto createTweet(TweetDto tweetDto, String username) {

        List<Tag> tagList = new ArrayList<>();
        tweetDto.setUsername(username);
        String message = tweetDto.getMessage();
        List<String> listOfMessage = new ArrayList<>(Arrays.stream(message.split("#")).toList());
        tweetDto.setMessage(listOfMessage.get(0));
        listOfMessage.remove(0);

        for (String s : listOfMessage) {
            Tag tag = new Tag();
            tag.setTag(s.replaceAll("\\s", "").trim());
            tag.setSlug((tag.getTag()).toLowerCase().replaceAll("\\s", "").trim());
            tagList.add(tag);
        }

        tweetDto.setTags(tagList);

        Tweet tweet = tweetRepository.save(dozerBeanMapper.map(tweetDto, Tweet.class));

        tagList.clear();

        return dozerBeanMapper.map(tweet, TweetDto.class);
    }

    public void deleteTweet(String id, String username) {

        TweetDto tweetDtoById = getTweetById(id);

        if (tweetDtoById.getUsername().equals(username)) {
            tweetRepository.deleteById(id);
        } else throw new RuntimeException("You are not authorized to delete this tweet");

    }

    public TweetDto getTweetById(String id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found" + id));

        return dozerBeanMapper.map(tweet, TweetDto.class);
    }

    public List<TweetDto> getAllTweets(Integer pageNo, Integer pageSize, String username, String tag, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        if (username != null && !username.isBlank()) {
            return tweetRepository.findByUsername(paging, username).getContent().stream().map(
                    tweet -> dozerBeanMapper.map(tweet, TweetDto.class)).collect(Collectors.toList());
        }

        Page<Tweet> pageTweet = tweetRepository.findAll(paging);

        List<TweetDto> tweetDtoList = pageTweet.getContent().stream().map(
                tweet -> dozerBeanMapper.map(tweet, TweetDto.class)).collect(Collectors.toList());

        if (tag != null && !tag.isBlank()) {
            return tweetDtoList.stream()
                    .flatMap(tweetDto -> tweetDto.getTags().stream()
                            .filter(tagObject -> tagObject.getTag().equals(tag))
                            .map(tagObject -> tweetDto))
                    .collect(Collectors.toList());

        }
        return tweetDtoList;
    }
}

