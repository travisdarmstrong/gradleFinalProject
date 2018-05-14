package com.udacity.gradle.builditbigger;

import com.randomrobotics.jokesourcelibrary.JokeRepository;

import org.junit.Test;

import static junit.framework.Assert.assertNotSame;

public class UnitTests {

    @Test
    public void testJokeLibrary(){
        JokeRepository jokeRepository = new JokeRepository();
        String joke = jokeRepository.GetJoke();
        assertNotSame("", joke);
    }

    @Test
    public void testTwoJokes(){
        JokeRepository jokeRepository = new JokeRepository();
        String joke1 = jokeRepository.GetJoke();
        String joke2 = jokeRepository.GetJoke();
        assertNotSame(joke1, joke2);
    }

}
