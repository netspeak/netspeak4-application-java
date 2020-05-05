package org.netspeak;

import java.util.Map;

import org.netspeak.generated.NetspeakMessages.Properties;
import org.netspeak.generated.NetspeakMessages.RawResponse;
import org.netspeak.generated.NetspeakMessages.Request;
import org.netspeak.generated.NetspeakMessages.Response;

import com.google.protobuf.InvalidProtocolBufferException;

public class Netspeak extends NativeInterface {

	private long nativePointer;

	public Netspeak(Configuration config) {
		nativePointer = native_start(NetspeakUtil.toProperties(config).toByteArray());
		if (nativePointer == 0) {
			throw new RuntimeException("Error starting Netspeak");
		}
	}

	/**
	 * Never returns null.
	 *
	 * @param request
	 * @return
	 * @throws InvalidProtocolBufferException
	 */
	public Response search(Request request) throws InvalidProtocolBufferException {
		return Response.parseFrom(native_search(nativePointer, request.toByteArray()));
	}

	/**
	 * Never returns null.
	 *
	 * @param request
	 * @return
	 * @throws InvalidProtocolBufferException
	 */
	public RawResponse searchRaw(Request request) throws InvalidProtocolBufferException {
		return RawResponse.parseFrom(native_searchRaw(nativePointer, request.toByteArray()));
	}

	/**
	 * Never returns null.
	 *
	 * @return
	 * @throws InvalidProtocolBufferException
	 */
	public Map<String, String> getProperties() throws InvalidProtocolBufferException {
		return NetspeakUtil.toMap(Properties.parseFrom(native_getProperties(nativePointer)));
	}

	/**
	 * Cleanly shutdown the native Netspeak service.
	 */
	public void stop() { // TODO: Implement AutoClosable?
		native_stop(nativePointer);
		nativePointer = 0;
	}

	@Override
	protected void finalize() throws Throwable {
		stop(); // TODO: Won't this result in a native NullPointer of sorts if disposed properly
				// before?
		super.finalize();
	}

	// -------------------------------------------------------------------------
	// JNI functions - TOUCH AND DIE
	// -------------------------------------------------------------------------

	static native long native_start(byte[] properties);

	static native byte[] native_getProperties(long nativePointer);

	static native byte[] native_searchRaw(long nativePointer, byte[] request);

	static native byte[] native_search(long nativePointer, byte[] request);

	static native void native_stop(long nativePointer);
}
