package cooker.tool.utils.nio.server.http;


import cooker.tool.utils.nio.server.IMessageReader;
import cooker.tool.utils.nio.server.IMessageReaderFactory;

/**
 * Created by jjenkov on 18-10-2015.
 */
public class HttpMessageReaderFactory implements IMessageReaderFactory {

    public HttpMessageReaderFactory() {
    }

    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
