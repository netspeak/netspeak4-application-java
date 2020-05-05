
package org.netspeak;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.netspeak.generated.NetspeakMessages.Phrase;
import org.netspeak.generated.NetspeakMessages.Phrase.Word;
import org.netspeak.generated.NetspeakMessages.Query;
import org.netspeak.generated.NetspeakMessages.Query.Unit;


public class TestNetspeakUtil {

	private final Query.Unit.Builder newUnit(Unit.Tag tag, String text) {
		return Unit.newBuilder().setTag(tag).setText(text);
	}

	private final Word.Builder newWord(Word.Tag tag, String text) {
		return Word.newBuilder().setTag(tag).setText(text);
	}

	@Test
	public final void testToStringPhrase() {
		Phrase.Builder builder = Phrase.newBuilder();

		builder.addWord(newWord(Word.Tag.WORD, "w1"));
		builder.addWord(newWord(Word.Tag.WORD, "w2"));
		builder.addWord(newWord(Word.Tag.WORD, "w3"));

		assertEquals("w1 w2 w3", NetspeakUtil.toString(builder.build()));
	}

	@Test
	public final void testToStrinQuery() {
		Query.Builder builder = Query.newBuilder();

		builder.addUnit(newUnit(Unit.Tag.WORD, "u1"));
		builder.addUnit(newUnit(Unit.Tag.WORD, "u2"));
		builder.addUnit(newUnit(Unit.Tag.WORD, "u3"));

		assertEquals("u1 u2 u3", NetspeakUtil.toString(builder.build()));
	}

	@Test
	public final void testToStrinQueryDictset() {
		Query.Builder builder = Query.newBuilder();

		builder.addUnit(newUnit(Unit.Tag.WORD, "u1"));
		builder.addUnit(newUnit(Unit.Tag.DICTSET, "u2"));
		builder.addUnit(newUnit(Unit.Tag.DICTSET, "u3"));

		assertEquals("u1 #u2 #u3", NetspeakUtil.toString(builder.build()));
	}

	@Test
	public final void testToStrinQueryOptionset() {
		Query.Builder builder = Query.newBuilder();

		builder.addUnit(newUnit(Unit.Tag.WORD, "u1"));
		builder.addUnit(newUnit(Unit.Tag.OPTIONSET, "u2"));
		builder.addUnit(newUnit(Unit.Tag.OPTIONSET, "u3"));

		assertEquals("u1 [ u2 ] [ u3 ]", NetspeakUtil.toString(builder.build()));
	}

	@Test
	public final void testToStrinQueryOrderset() {
		Query.Builder builder = Query.newBuilder();

		builder.addUnit(newUnit(Unit.Tag.WORD, "u1"));
		builder.addUnit(newUnit(Unit.Tag.ORDERSET, "u2"));
		builder.addUnit(newUnit(Unit.Tag.ORDERSET, "u3"));

		assertEquals("u1 { u2 } { u3 }", NetspeakUtil.toString(builder.build()));
	}

	@Test
	public final void testToStrinQueryWithAllOperators() {
		Query.Builder builder = Query.newBuilder();

		builder.addUnit(newUnit(Unit.Tag.DICTSET, "u1"));
		builder.addUnit(newUnit(Unit.Tag.OPTIONSET, "u2"));
		builder.addUnit(newUnit(Unit.Tag.ORDERSET, "u3"));

		assertEquals("#u1 [ u2 ] { u3 }", NetspeakUtil.toString(builder.build()));
	}

}
