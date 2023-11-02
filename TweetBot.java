import java.util.*;
import java.io.*;

// Yunho cho
// TA: Ben Wang
// CSE 122 AF
// August 3 2023
// TweetBot
// This class is TweetBot. It's able to delete the tweets and add the tweets
// also able to print out as well and it can reset and then print out same tweets again.
public class TweetBot {
    // TODO: Your Code Here
    private List<String> tweets;
    private int order = 0;

    // Intitalizes the tweets.
    // exception:
    // - Throw new IllegalArgumentException if tweets is empty.
    // no return 
    // Parameter:
    //  - tweets: list of all the tweets
    public TweetBot(List<String> tweets) {
        if(tweets.size() < 1) {
            throw new IllegalArgumentException("tweets are empty!");
        }
        this.tweets =  new ArrayList<>(tweets);
    }
    
    // Number of tweets it has.
    // no exception
    // return:
    //  - int: size of tweets
    // no parameter
    public int numTweets() {
        return tweets.size();
    }

    // Add specific tweet into a tweets list.
    // no return
    // no exception
    // Parameter:
    //   - tweet: a tweet that want to be added
    public void addTweet(String tweet) {
        tweets.add(tweet);
    }

    // Print out next tweet in order of tweets list.
    // no exception
    //   Return:
    // - String: next tweeted sentence
    // no parameter
    public String nextTweet() {
        if(order == numTweets()) {
            reset();
        }
        String tweet = tweets.get(order);
        order++;
        return tweet;
    }

    // Removes specific tweet from the tweets list.
    // no return
    // no exception
    // Parameter:
    //   - tweet: a tweet that want to be deleted
    public void removeTweet(String tweet) {
        int index = tweets.indexOf(tweet);
        if(tweets.contains(tweet)) {
            tweets.remove(index);
            if(index < order) {
                order--;
            }
        }
    }

    // Resets the printint out order system to beginning.
    // no return
    // no exception
    // no parameter
    public void reset() {
        order = 0;
    }
    
}   
