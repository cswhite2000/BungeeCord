package net.md_5.bungee.protocol.packet;

import io.github.waterfallmc.travertine.protocol.MultiVersionPacketV17;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EncryptionResponse extends MultiVersionPacketV17
{

    private byte[] sharedSecret;
    private byte[] verifyToken;

    // Travertine start
    @Override
    public void v17Read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        sharedSecret = v17readArray( buf );
        verifyToken = v17readArray( buf );
    }
    // Travertine end

    @Override
    public void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        sharedSecret = readArray( buf, 128 );
        verifyToken = readArray( buf, 128 );
    }

    // Travertine start
    @Override
    public void v17Write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        v17writeArray( sharedSecret, buf, false );
        v17writeArray( verifyToken, buf, false );
    }
    // Travertine end

    @Override
    public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        writeArray( sharedSecret, buf );
        writeArray( verifyToken, buf );
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception
    {
        handler.handle( this );
    }
}
