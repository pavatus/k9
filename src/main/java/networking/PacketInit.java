package networking;

import mdt.k9mod.entity.K9Entity;
import net.minecraft.util.Identifier;

public class PacketInit {

    public record K9Battery(int batteryLevel, K9Entity k9Entity, Identifier packetName) {
    }

    //public record ArbitraryExample(int someData, String otherData, Identifier aMinecraftClass) {}

}
