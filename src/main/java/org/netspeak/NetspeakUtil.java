package org.netspeak;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import org.netspeak.generated.NetspeakMessages.Phrase;
import org.netspeak.generated.NetspeakMessages.Phrase.Word;
import org.netspeak.generated.NetspeakMessages.Properties;
import org.netspeak.generated.NetspeakMessages.Properties.Property;
import org.netspeak.generated.NetspeakMessages.Query;
import org.netspeak.generated.NetspeakMessages.Query.Unit;

public final class NetspeakUtil {

	private NetspeakUtil() {
	}

	private static <E> String join(Iterable<E> iterable, Function<E, String> stringify, String separator) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (E item : iterable) {
			if (first) {
				first = false;
			} else {
				sb.append(separator);
			}

			sb.append(stringify.apply(item));
		}

		return sb.toString();
	}

	public static Map<String, String> toMap(Properties properties) {
		// Use TreeSet instead of HashSet to keep it sorted.
		// TODO: Why not LinkedHashMap if it's just to *keep* it sorted?
		Map<String, String> map = new TreeMap<>();
		for (Property property : properties.getPropertyList()) {
			map.put(property.getKey(), property.getValue());
		}
		return map;
	}

	public static Properties toProperties(Configuration config) {
		Properties.Builder builder = Properties.newBuilder();
		for (Map.Entry<Object, Object> entry : config.entrySet()) {
			final String key = (String) entry.getKey();
			final String value = (String) entry.getValue();
			builder.addProperty(Property.newBuilder().setKey(key).setValue(value));
		}
		return builder.build();
	}

	/**
	 * Concatenates all {@link Word}s of the given phrase using a single whitespace
	 * as separator.
	 *
	 * @param phrase The phrase to stringify.
	 * @return The concatenated string.
	 */
	public static String toString(Phrase phrase) {
		return join(phrase.getWordList(), Word::getText, " ");
	}

	/**
	 * Concatenates all {@link Unit}s of the given query using a single whitespace
	 * as separator.
	 *
	 * @param query The query to stringify.
	 * @return The concatenated string.
	 */
	public static String toString(Query query) {
		return join(query.getUnitList(), NetspeakUtil::format, " ");
	}

	private static String format(Query.Unit unit) {
		switch (unit.getTag()) {
		case DICTSET:
			return '#' + unit.getText();
		case OPTIONSET:
			return "[ " + unit.getText() + " ]";
		case ORDERSET:
			return "{ " + unit.getText() + " }";
		default:
			return unit.getText();
		}
	}
}
