package com.bestcoderlol.discordbot;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private JSONObject json;
    private Path path;

    private HashMap<String, String> neededKeys = new HashMap<>() {{
        put("TOKEN", "Token goes here.");
    }};

    public Config(Path path) {
        this.path = path;
    }

    public void load() {
        try {
            this.json = new JSONObject(new String(Files.readAllBytes(path)));
            for (Map.Entry<String, String> entry : neededKeys.entrySet()) {
                if (!json.has(entry.getKey())) json.put(entry.getKey(), entry.getValue());
            }
            new FileWriter("./config.json").write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        return json.getString("TOKEN");
    }
}
