package org.ktc.codestory2013;

import static org.fest.assertions.api.Assertions.assertThat;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CodeStoryAcceptanceTestsWithServer {
    private static CodeStoryWebServer server;
    private WebConversation wc = new WebConversation();

    @BeforeClass
    public static void startWebServer() throws IOException {
        server = new CodeStoryWebServer();
        server.start(8090);
    }

    @AfterClass
    public static void stopWebServer() {
        server.stop();
    }

    @Test
    public void ask_server_with_no_query() throws Exception {
        WebRequest req = new GetMethodWebRequest("http://localhost:8090/");
        WebResponse resp = wc.getResponse(req);
        assertThat(resp.getText()).isEqualTo("You must ask something if you want me to answer");
    }
    
    @Test
    public void what_is_my_email_address() throws Exception {
        WebRequest req = new GetMethodWebRequest("http://localhost:8090/?q=Quelle+est+ton+adresse+email");
        WebResponse resp = wc.getResponse(req);
        assertThat(resp.getText()).isEqualTo("red4ktc-codestory2013@yahoo.fr");
    }

}
