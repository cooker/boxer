package cooker.tool.utils.string;

import java.util.Map;

/**
 * Created by yu.kequn on 2017/8/21.
 */
public class HtmlUtils {
    /**
     * Converts all HTML entities to applicable characters.
     *
     * @param encodedHtml
     *            The encoded HTML
     * @param encodedType
     *            0:html code, 1:decimal code ; default:1
     * @return The decoded HTML
     */
    private static String htmlDecode(final String encodedHtml, int encodedType) {
        String str = encodedHtml;
        for (Map.Entry<String, HtmlChar> entry : HtmlCharCode.DECODED_ENTITIES
                .entrySet()) {
            switch (encodedType) {
                case 0:
                    str = str.replaceAll(entry.getKey(), entry.getValue().getName());
                    break;
                case 1:
                    str = str.replaceAll(entry.getKey(), entry.getValue()
                            .getDecimalCode());
                    break;
                default:
                    str = str.replaceAll(entry.getKey(), entry.getValue()
                            .getDecimalCode());
                    break;
            }
        }
        return str;
    }

    /**
     * Convert all applicable characters to HTML entities.
     *
     * @param html
     *            The HTML to encode
     * @param encodedType
     *            0:html code, 1:decimal code ; default:1
     * @return The encoded data
     */
    private static String htmlEncode(final String html, int encodedType) {
        String str = html;
        for (Map.Entry<String, HtmlChar> entry : HtmlCharCode.DECODED_ENTITIES
                .entrySet()) {
            switch (encodedType) {
                case 0:
                    str = str.replaceAll(entry.getValue().getName(), entry.getKey());
                    break;
                case 1:
                    str = str.replaceAll(entry.getValue().getDecimalCode(),
                            entry.getKey());
                    break;
                default:
                    str = str.replaceAll(entry.getValue().getDecimalCode(),
                            entry.getKey());
                    break;
            }
        }
        return str;
    }
}
