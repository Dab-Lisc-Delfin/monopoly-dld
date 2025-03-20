package com.dld.monopoly.model;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameManager {
    private final Map<String, Game> activeGames = new ConcurrentHashMap<>();
}
