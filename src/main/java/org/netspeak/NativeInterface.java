package org.netspeak;

public abstract class NativeInterface {

	/** The name of the native library. */
	static final String JNI_LIB_NAME = "netspeak4";

	static {
		final String fullLibName = System.mapLibraryName(JNI_LIB_NAME);
		try {
			System.loadLibrary(JNI_LIB_NAME);
			System.out.println(fullLibName + " loaded");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Cannot load " + fullLibName + "\nPlease check the following:"
			        + "\n1. Have you run <project>/lib/install-dependencies.sh"
			        + "\n2. Have you copied the native library from <project>/lib/<platform> to /usr/lib"
			        + "\n   or have you set -Djava.library.path=/path/to/lib properly.");
			e.printStackTrace();
		}
	}
}
