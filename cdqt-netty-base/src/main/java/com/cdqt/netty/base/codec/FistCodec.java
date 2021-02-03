package com.cdqt.netty.base.codec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.base.message.FistProtocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

/**
 * FistCodec
 *
 * @author LiuGangQiang Create in 2020/06/06
 */
public class FistCodec extends ByteToMessageCodec<FistProtocol> {
	private final static Logger LOGGER = LoggerFactory.getLogger(FistCodec.class);

	/**
	 * base length eq {@value} plase reference {@link FistProtocol#BASE_HEAD_LENGTH}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public static final int BASE_LENGTH = FistProtocol.BASE_HEAD_LENGTH;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() <= BASE_LENGTH) {
			// ctx.close();
			return;
		}
		int pos = 0;
		while (true) {
			pos = in.readerIndex();
			in.markReaderIndex();
			if (in.readInt() == FistProtocol.HEAD_DEFAULT) {
				break;
			}
			in.resetReaderIndex();
			in.readByte();
			if (in.readableBytes() < BASE_LENGTH) {
				return;
			}
		}
		int len = in.readInt();
		if (in.readableBytes() < len) {
			in.readerIndex(pos);
			return;
		}
		byte[] data = new byte[len];
		in.readBytes(data);
		FistProtocol protocol = new FistProtocol(data.length, data);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("<< fist decode bytebuf to fistprotocol message {}", protocol.toString());
		}
		out.add(protocol);
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, FistProtocol protocol, ByteBuf out) throws Exception {
		out.writeInt(protocol.getHead());
		out.writeInt(protocol.getLength());
		out.writeBytes(protocol.getContent());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(">> fist encode fistprotocol to bytebuf message {}", protocol.toString());
		}
	}
}
