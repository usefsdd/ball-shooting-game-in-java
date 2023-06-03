package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Player {

    private static Player currentPlayer;
    private static ArrayList<Player> allPlayers = new ArrayList<>();
    private String username;
    private String password;
    private String email;
    private int score;
    private long playTime;
    private int weightedScore;
    private int avatar;
    private Game savedGame;
    private boolean blackWhite;
    private boolean mute;
    private int difficulty;
    private int startBallsNum;
    private int map;
    private String language;
    private String[] controlKeys = new String[2];

    public Player(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.score = 0;
        this.playTime = 0;
        this.weightedScore = 0;
        Random random = new Random();
        this.avatar = random.nextInt(6) + 1;
        this.blackWhite = false;
        this.mute = false;
        this.language = "eng";
        this.difficulty = 2;
        this.map = 1;
        this.startBallsNum = 8;
        controlKeys[0] = "Space";
        controlKeys[1] = "Tab";
        this.savedGame = null;
        allPlayers.add(this);
    }

    public Player(String username, String password, String email, int score,long playTime, int weightedScore,
                  int avatar, boolean blackWhite, boolean mute, int difficulty, int startBallsNum, int map,
                  String language, String shootKey, String freezeKey, Game savedGame) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.score = score;
        this.playTime = playTime;
        this.weightedScore = weightedScore;
        this.avatar = avatar;
        this.blackWhite = blackWhite;
        this.mute = mute;
        this.difficulty = difficulty;
        this.savedGame = savedGame;
        this.startBallsNum = startBallsNum;
        this.map = map;
        this.language = language;
        this.controlKeys[0] = shootKey;
        this.controlKeys[1] = freezeKey;
        allPlayers.add(this);
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Player.currentPlayer = currentPlayer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public static Player getPlayerByUserName(String username) {
        for (Player player : allPlayers)
            if (player.username.toLowerCase().equals(username.toLowerCase()))
                return player;
        return null;
    }

    public void addPlayer(Player player) {
        allPlayers.add(player);
    }

    public void removeCurrentPlayer() {
        allPlayers.remove(currentPlayer);
    }

    public void removePlayer() {
        allPlayers.remove(this);
    }

    public static ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public String getPassword() {
        return password;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public boolean isBlackWhite() {
        return blackWhite;
    }

    public void setColor(boolean blackWhite) {
        this.blackWhite = blackWhite;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Game getSavedGame() {
        return savedGame;
    }

    public void setSavedGame(Game savedGame) {
        this.savedGame = savedGame;
    }

    public int getStartBallsNum() {
        return startBallsNum;
    }

    public void setStartBallsNum(int startBallsNum) {
        this.startBallsNum = startBallsNum;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public String[] getControlKeys() {
        return controlKeys;
    }

    public void setControlKeys(String shootKey, String frozenKey) {
        this.controlKeys[0] = shootKey;
        this.controlKeys[1] = frozenKey;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void addPlayTime(int playTime) {
        this.playTime += playTime;
    }

    public int getWeightedScore() {
        return weightedScore;
    }

    public void addWeightedScore(int weightedScore) {
        this.weightedScore += weightedScore;
    }

    public static void recoveryPlayers() {
        allPlayers = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            if (new FileReader("src/main/resources/dataBase/players.json").read() == -1) return;
            org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) parser.parse(
                    new FileReader("src/main/resources/dataBase/players.json"));
            if (jsonArray == null) allPlayers = new ArrayList<>();
            else {
                for (Object obj : jsonArray) {
                    JSONObject object = (JSONObject) obj;
//                    String savedGameJsonString = (String) object.get("savedGame");
//                    Game savedGame = null;
//                    if (savedGameJsonString != null && !savedGameJsonString.isEmpty()) {
//                        JSONObject savedGameJsonObject = (JSONObject) parser.parse(savedGameJsonString);
//                        savedGame = new Game(
//                                (ArrayList<Double>) savedGameJsonObject.get("ballsAngle"),
//                                ((Long) savedGameJsonObject.get("leftBalls")).intValue(),
//                                ((Long) savedGameJsonObject.get("difficulty")).intValue(),
//                                ((Long) savedGameJsonObject.get("phase")).intValue());
//                    }
                    new Player((String) object.get("username"),
                            (String) object.get("password"),
                            (String) object.get("email"),
                            ((Long) object.get("score")).intValue(),
                            ((Long) object.get("playTime")).intValue(),
                            ((Long) object.get("weightedScore")).intValue(),
                            ((Long) object.get("avatar")).intValue(),
                            (boolean) object.get("blackWhite"),
                            (boolean) object.get("mute"),
                            ((Long) object.get("difficulty")).intValue(),
                            ((Long) object.get("ballsNum")).intValue(),
                            ((Long) object.get("map")).intValue(),
                            (String) object.get("language"),
                            (String) object.get("shootKey"),
                            (String) object.get("freezeKey"),
                            null);

                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static void savePlayers() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (Player player : allPlayers) {
                json.put("username", player.username);
                json.put("password", player.password);
                json.put("email", player.email);
                json.put("score", player.score);
                json.put("playTime", player.playTime);
                json.put("weightedScore", player.weightedScore);
                json.put("avatar", player.avatar);
                json.put("blackWhite", player.blackWhite);
                json.put("mute", player.mute);
                json.put("difficulty", player.difficulty);
                json.put("ballsNum", player.startBallsNum);
                json.put("map", player.map);
                json.put("language", player.language);
                json.put("shootKey", player.controlKeys[0]);
                json.put("freezeKey", player.controlKeys[1]);
                jsonArray.put(json);
                FileWriter writer = new FileWriter("src/main/resources/dataBase/players.json");
                writer.write(jsonArray.toString());
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getStackTrace();
        }
    }

    private static String getSavedGameJson(Player player) {
        Game savedGame = player.savedGame;
        JSONObject jsonObject = new JSONObject((Map) savedGame);
        return jsonObject.toJSONString();
    }


}
