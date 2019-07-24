package app.test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static app.google.document.Docs2DimensionalTableParcer.getWordFromDocs;

public class DocsQuickstart {
    private static final String DOCUMENT_ID = "17Pmysd2O9x1gt3dNgrAr0pvOOn2ScLC0qleht4E8Zis";


    public static void main(String... args) throws IOException, GeneralSecurityException {
        String words = getWordFromDocs(DOCUMENT_ID);
        System.out.println(words);
    }

}