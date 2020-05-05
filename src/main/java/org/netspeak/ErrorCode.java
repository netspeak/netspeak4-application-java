package org.netspeak;

import org.netspeak.generated.NetspeakMessages.Response;

/**
 * Error codes used by {@link Response#getErrorCode()}. Keep this file in sync
 * with its C++ error.[hpp|cpp] counterpart.
 */
public enum ErrorCode {

	NO_ERROR, INVALID_QUERY, SERVER_ERROR, UNKNOWN_ERROR;

	public int getErrorCode() {
		return this.ordinal();
	}

	public static final ErrorCode fromCode(int errorCode) {
		if (errorCode < 0)
			throw new IllegalArgumentException("The error code is an uint32.");
		return (errorCode < ErrorCode.values().length) ? ErrorCode.values()[errorCode] : UNKNOWN_ERROR;
	}

}
