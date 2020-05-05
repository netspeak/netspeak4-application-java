package org.netspeak;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.netspeak.generated.NetspeakMessages.RawResponse;
import org.netspeak.generated.NetspeakMessages.Response;
import org.netspeak.generated.SchemaNetspeakMessages;

import com.dyuproject.protostuff.JsonIOUtil;

public final class JsonUtil {

	private JsonUtil() {
	}

	public static Response parseResponse(InputStream jsonStream) throws IOException {
		Response.Builder rb = Response.newBuilder();
		JsonIOUtil.mergeFrom(jsonStream, rb, SchemaNetspeakMessages.Response.MERGE, false);
		return rb.build();
	}

	public static RawResponse parseRawResponse(InputStream jsonStream) throws IOException {
		RawResponse.Builder rb = RawResponse.newBuilder();
		JsonIOUtil.mergeFrom(jsonStream, rb, SchemaNetspeakMessages.RawResponse.MERGE, false);
		return rb.build();
	}

	public static void writeResponse(Response response, Writer writer) throws IOException {
		JsonIOUtil.writeTo(writer, response, SchemaNetspeakMessages.Response.WRITE, false);
	}

	public static void writeRawResponse(RawResponse response, Writer writer) throws IOException {
		JsonIOUtil.writeTo(writer, response, SchemaNetspeakMessages.RawResponse.WRITE, false);
	}

}
