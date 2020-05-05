package org.netspeak;

import java.util.Properties;

/**
 * Keep this file in synch with its C++ NetspeakConfig.[hpp|cpp] counterpart.
 */
public class Configuration extends Properties {

	private static final long serialVersionUID = -4107207243745381801L;

	public static final String PATH_TO_HOME = "path.to.home";
	public static final String PATH_TO_PHRASE_CORPUS = "path.to.phrase.corpus";
	public static final String PATH_TO_PHRASE_DICTIONARY = "path.to.phrase.dictionary";
	public static final String PATH_TO_PHRASE_INDEX = "path.to.phrase.index";
	public static final String PATH_TO_POSTLIST_INDEX = "path.to.postlist.index";
	public static final String PATH_TO_HASH_DICTIONARY = "path.to.hash.dictionary";
	public static final String CACHE_CAPACITY = "cache.capacity";

}
