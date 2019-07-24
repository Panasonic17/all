package app.google.document;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static app.google.document.DocsConnector.getsDoxService;

public class Docs2DimensionalTableParcer {

    /**
     * Returns the text in the given ParagraphElement.
     *
     * @param element a ParagraphElement from a Google Doc
     */
    private static String readParagraphElement(ParagraphElement element) {
        TextRun run = element.getTextRun();
        if (run == null || run.getContent() == null) {
            // The TextRun can be null if there is an inline object.
            return "";
        }
        return run.getContent();
    }

    /**
     * Recurses through a list of Structural Elements to read a document's text where text may be in
     * nested elements.
     *
     * @param elements a list of Structural Elements
     */
    private static String readStructrualElements(List<StructuralElement> elements) {
        StringBuilder sb = new StringBuilder();
        for (StructuralElement element : elements) {
            if (element.getParagraph() != null) {
                for (ParagraphElement paragraphElement : element.getParagraph().getElements()) {
                    sb.append(readParagraphElement(paragraphElement));
                }
            } else if (element.getTable() != null) {
                // The text in table cells are in nested Structural Elements and tables may be
                // nested.
                for (TableRow row : element.getTable().getTableRows()) {

                    List<TableCell> columns = row.getTableCells();
//                    for (TableCell cell : row.getTableCells()) {
//                        sb.append(readStructrualElements(cell.getContent()));
//                    }
                    sb.append(readStructrualElements(columns.get(0).getContent()).replace("\n", " "));
                    sb.append(" -> ");
                    sb.append(readStructrualElements(columns.get(1).getContent()).replace("\n", " "));
                    sb.append(" \n ");

                }
            } else if (element.getTableOfContents() != null) {
                // The text in the TOC is also in a Structural Element.
                sb.append(readStructrualElements(element.getTableOfContents().getContent()));
            }
        }
        return sb.toString();
    }

    public static String getWordFromDocs(String dockID) throws GeneralSecurityException, IOException {
        Docs service = getsDoxService();
        Document response = service.documents().get(dockID).execute();
        return readStructrualElements(response.getBody().getContent());
    }
}
