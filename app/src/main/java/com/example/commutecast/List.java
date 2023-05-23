package com.example.commutecast;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class List extends AppCompatActivity {
    ListView lvProgram;
    String[] programName = {
            "Natural Disasters", "ABC News Update", "This Day in Weather History", "This Date in Weather History",
            "Weather Geeks", "The Weather Guru", "Weather Duo", "Stormdar Weather", "Fox Weather Update", "Weather Weekly"
    };
    String[] programDescription = {
            "Tsunamis, volcanoes, tornadoes, earthquakes...these are real-life monsters.",
            "The latest headlines from ABC News, refreshed hourly 24/7.",
            "This Day In Weather History is a daily podcast from The Weather Network that features unique and informative stories written and produced by host Chris Mei.",
            "AccuWeather Meteorologist, Evan Myers takes a look back on weather events that impacted this date in the past, uncovering history that were shaped by unbelievable weather conditions.",
            "From the people behind The Weather Channel TV network.",
            "This exciting podcast series will go through all of the wonders Mother Nature can provide.",
            "WeatherDuo (天氣豆) is a podcast program focused on interviews and discussions in the field of atmospheric science.",
            "The purpose of the podcast is to provide weather insight, facts, and trivia in a manner that is entertaining, fun and easily understood by everyone.",
            "America's Weather Team is now in the palm of your hand. Utilizing the resources of over 100 meteorologists!",
            "Marcus covers all things weather with “Weather Weekly”. From looking back on historical weather events to looking ahead and planning and being prepared."
    };

    String[] urls = {
            "https://open.spotify.com/show/2vPbtummXL9L4UbLiezZmh?si=08c9447c74e743e9",
            "https://open.spotify.com/show/28hRDWxeu4r8LF3vubetm3?si=d0c5c93b84e14d4e",
            "https://open.spotify.com/show/7EtHVv8f3CkJZV7flZy71z?si=7f5b791a8701451f",
            "https://open.spotify.com/show/3pYMBmIpdejOSGnMQSWG7D?si=dd4b3e402f1e4978",
            "https://open.spotify.com/show/0abQ9Q8vyOyUlMH2SNl7ub?si=54f46a889dad48a4",
            "https://open.spotify.com/show/7xM4pP6VChy7nQ4VXlDmQK?si=49227ebebdec4c14",
            "https://open.spotify.com/show/2HviUX44tuuFaGEonwXduh?si=9955e66c06aa43a1",
            "https://open.spotify.com/show/0ccFPFdjzoBXsxS5e2yVcb?si=fe3e6f56242a4bf0",
            "https://open.spotify.com/show/2VWszNUm4qUrx2NVbDkijP?si=29d23f3b61ad417a",
            "https://open.spotify.com/show/7z7Htk8D0KpdJ303w2HS5s?si=90dc83bfd2874aec"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvProgram = findViewById(R.id.lvProgram);
        ProgramAdapter programAdapter = new ProgramAdapter(this, programName, programDescription, urls);
        lvProgram.setAdapter(programAdapter);
    }
}