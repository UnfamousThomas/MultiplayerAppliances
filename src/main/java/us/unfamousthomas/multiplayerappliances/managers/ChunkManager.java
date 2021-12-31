package us.unfamousthomas.multiplayerappliances.managers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.objects.ClaimedChunk;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChunkManager {
    private MultiplayerAppliances pluginInstance;
    private static String fileName = "claimedchunks.json";

    public ChunkManager(MultiplayerAppliances plugin) {
        pluginInstance = plugin;
        loadChunks();
    }

    private Map<String, ClaimedChunk> chunksMap = new HashMap<>();

    public String generateChunkId(Chunk chunk) {
        return chunk.getX() + "-" + chunk.getZ() + "-" + chunk.getWorld().getName();
    }

    public void addChunk(Chunk chunk, UUID owner) {
        chunksMap.put(generateChunkId(chunk), createNewChunk(owner, chunk));
        saveChunks();
    }

    public boolean isChunk(String chunkId) {
        return chunksMap.containsKey(chunkId);
    }

    public ClaimedChunk getChunk(String chunkId) {
        return chunksMap.get(chunkId);
    }

    public boolean isChunk(Chunk chunk) {
        return chunksMap.containsKey(generateChunkId(chunk));
    }

    public UUID getOwner(String chunkId) {
        return chunksMap.get(chunkId).getOwner();
    }

    public UUID getOwner(Chunk chunk) {
        return chunksMap.get(generateChunkId(chunk)).getOwner();
    }

    public void unclaimChunk(String chunkId) {
        chunksMap.remove(chunkId);
        saveChunks();
    }

    private ClaimedChunk createNewChunk(UUID owner, Chunk chunk) {
        return new ClaimedChunk(owner, generateChunkId(chunk));
    }


    public void saveChunks() {
        Gson gson = new Gson();
        File file = new File(pluginInstance.getDataFolder().getAbsolutePath() +"/" + fileName);
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            gson.toJson(chunksMap, writer);
            writer.flush();
            writer.close();
            System.out.println("Saved.");
        } catch (IOException ex) {
            System.out.println("ERROR while writing data.");
            ex.printStackTrace();
    }
}

    public void loadChunks() {
        Gson gson = new Gson();
        File file = new File(pluginInstance.getDataFolder().getAbsolutePath() +"/" + fileName);
        try {
            if(file.exists()) {
                Reader reader = new FileReader(file);
                Type typeOfHashMap = new TypeToken<Map<String, ClaimedChunk>>() { }.getType();
                Map claimed = gson.fromJson(reader,typeOfHashMap);
                chunksMap = claimed;
                System.out.println("Loaded.");

                for (Map.Entry<String, ClaimedChunk> entry : chunksMap.entrySet()) {
                    System.out.println("Owner:" + Bukkit.getOfflinePlayer(entry.getValue().getOwner()).getName());
                    System.out.println("ID: " + entry.getKey());
                    System.out.println("Members:");
                    entry.getValue().getMembers().forEach(member -> {
                        System.out.println("- " + Bukkit.getOfflinePlayer(member).getName());
                    });
                    System.out.println("-----------------------------------------");
                }            }
        } catch (IOException e) {
            System.out.println("Error loading.");
            e.printStackTrace();
        }

    }
}
