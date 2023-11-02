import java.util.*;
import java.io.*;

// Yunho cho
// TA: Ben Wang
// CSE 122 AF
// August 3 2023
// TwitterTrends
// This class is TwitterTrends. It takes all the tweeted words and then scan through
// which specific word has been most tweeted and if tweeted word violates rule by
// putting in inappropriate word can cause to delete the word and leaving comment.
public class TwitterTrends {
    // TODO: Your Code Here
    private TweetBot bot;
    private Set<String> violationDiction;

    // It intitialize the tweeterBot bot.
    // no return
    // no exception
    // Parameter:
    //     - bot: able to delete, add, tweet next tweet, reset, and number of tweeted
    public TwitterTrends(TweetBot bot) {
        this.bot = bot;
    }

    // It breaks down word by word in each single tweets and store into mostWord map 
    // and occurence of that specific word is in tweets.
    // no exception
    //   Return:
    // - String: most frequent tweeted word
    // no parameter
    public String getMostFrequentWord() {
        Map<String, Integer> mostWord = new TreeMap<>();
        for(int i = 0; i < bot.numTweets(); i++) {
            String tweets = bot.nextTweet().toLowerCase();
            Scanner scan = new Scanner(tweets);
            while(scan.hasNext()) {
                String word = "";
                word = scan.next();
                if(!mostWord.containsKey(word)) {
                    mostWord.put(word, 1);
                } else {
                    mostWord.put(word, mostWord.get(word) + 1); 
                }
            }
        }
        return mostCountedWord(mostWord);
    }

    // Finds which tweeted word has been most tweeted frequently but if there is tie case
    // it will return in alphabet order. 
    // no exception
    //   Retrun:
    // - String: most frequent tweeted word
    //  Parameter:
    // - mostWord: all the word from each tweets and number of occurence overall tweet
    public String mostCountedWord(Map<String, Integer> mostWord) {
        int max = 0;
        String frequentWord = "";
        for(String tweetedWord : mostWord.keySet()) {
            if(mostWord.get(tweetedWord) > max) {
                max = mostWord.get(tweetedWord);
                frequentWord = tweetedWord;
            }
        }   
        return frequentWord;
    }

    // Adds all the violating words in tweeter's violation rules.
    // no return
    // no exception
    // no parameter
    public void violatedWords() {
        violationDiction = new HashSet<>();
        violationDiction.add("stupid");
        violationDiction.add("ugly");
        violationDiction.add("idiot");
        violationDiction.add("fathead");
        violationDiction.add("suck");
    }

    // Removes if the tweets has one of following violation word from the rule and
    // thwn after it get deleted from tweets leaving a warning message from twitter.
    // no return
    // no exception
    // no parameter
    public void removeInappropriateTweet() {
        for(int i = 0; i < bot.numTweets(); i++) {
            String tweets = bot.nextTweet().toLowerCase();
            Scanner scan = new Scanner(tweets);
            while(scan.hasNext()) {
                String word = "";
                word = scan.next();
                if(violationDiction.contains(word)) {
                    bot.removeTweet(word);
                    bot.addTweet("*This Tweet has violated rules - Twitter's team*");
                }
            }
        }
    }
}