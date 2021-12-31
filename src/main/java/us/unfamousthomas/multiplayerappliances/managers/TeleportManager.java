package us.unfamousthomas.multiplayerappliances.managers;

import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.objects.TeleportRequest;

import java.util.*;

public class TeleportManager {
    MultiplayerAppliances pluginInstance;
    private Map<UUID, List<TeleportRequest>> requestMap = new HashMap<>();

    public TeleportManager(MultiplayerAppliances instance) {
        this.pluginInstance = instance;
    }

    public void addRequest(Player from, Player to) {
        List<TeleportRequest> requests;
        if(requestMap.containsKey(to.getUniqueId())) {
            requests = requestMap.get(to.getUniqueId());
        } else {
            requests = new ArrayList<>();
        }

        requestMap.remove(to.getUniqueId());

        requests.add(new TeleportRequest(to.getUniqueId(), from.getUniqueId()));
        requestMap.put(to.getUniqueId(), requests);

        sendMessages(from, to);
    }

    public TeleportRequest findRequest(UUID from, UUID to) {
        TeleportRequest result = null;
        if(requestMap.containsKey(to)) {
            for (TeleportRequest request : requestMap.get(to)) {
                if(request.getWantsToTeleport().equals(from)) {
                    result = request;
                }
            }
        }

        return result;
    }

    public void removeRequest(TeleportRequest request) {
        if(requestMap.containsKey(request.getTeleportTo())) {
            requestMap.get(request.getTeleportTo()).remove(request);
        }
    }

    private void sendMessages(Player from, Player to) {
        //send tp messages
    }

}
