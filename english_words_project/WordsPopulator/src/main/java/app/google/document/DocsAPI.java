package app.google.document;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DocsAPI {

    public static String getAllWords(String dockID) {
        String result = "";
        try {
            result = Docs2DimensionalTableParcer.getWordFromDocs(dockID);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
