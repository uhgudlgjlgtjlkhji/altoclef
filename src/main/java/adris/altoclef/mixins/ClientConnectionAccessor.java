package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

@Mixin(ChannelHandlerContext.class)
public interface ClientConnectionAccessor {
    @Accessor("channel")
    Channel getChannel();
}
