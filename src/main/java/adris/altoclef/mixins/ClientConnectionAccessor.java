package adris.altoclef.mixins;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChannelHandlerContext.class)
public interface ClientConnectionAccessor {
    @Accessor("channel")
    Channel getChannel();
}
