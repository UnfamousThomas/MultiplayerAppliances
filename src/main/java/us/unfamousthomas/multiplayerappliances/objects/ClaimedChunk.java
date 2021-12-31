package us.unfamousthomas.multiplayerappliances.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClaimedChunk {
    private UUID owner;
    private String identifier;
    private List<UUID> members = new ArrayList<>();

    public ClaimedChunk(UUID owner, String id, ArrayList<UUID> members) {
        this.owner = owner;
        this.identifier = id;
        this.members = members;
    }

    public ClaimedChunk(UUID owner, String id) {
        this.owner = owner;
        this.identifier = id;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public String getIdentifier() {
        return identifier;
    }

    public UUID getOwner() {
        return owner;
    }

    public void addMember(UUID uuid) {
        this.members.add(uuid);
    }

    public void removeMember(UUID uuid) {
        this.members.remove(uuid);
    }
}
