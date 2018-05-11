package com.randomrobotics.jokesourcelibrary;

public class JokeRepository {

    private int idx = 0;

    String[] jokes = new String[] {
            "How many cats can you put in an empty box?\r\nOne. After that, it isn't empty anymore.",
            "What starts with 'e', ends with 'e', and contains one letter?\r\nAn envelope.",
            "What has one head, one foot, and four legs?\r\nA bed.",
            "I travel all over the world, but always stay in my corner. What am I?\r\nA stamp.",
            "Which month has 28 days?\r\nAll of them!",
            "What do dogs do after they finish obience school?\r\nThey get their masters.",
            "What do you call a frozen dog?\r\nA pupsicle."
    };

    /**
     * Get a joke from the repository
     * @return a joke
     */
    public String GetJoke(){
        if (idx>=jokes.length){
            idx=0;
        }
        return jokes[idx++];
    }
}
