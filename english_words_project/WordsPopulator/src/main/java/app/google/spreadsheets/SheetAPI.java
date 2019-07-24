package app.google.spreadsheets;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SheetAPI {
    public static String getAllWords(String spreadsheetId) {

        try {
            Sheets service = service = RaffClass.serviceProvider();

          Spreadsheet s = service.spreadsheets().get(spreadsheetId).execute();
            List<String>  ranges= s.getSheets().stream().map(dd->dd.getProperties().getTitle()+"!A1:C200").collect(Collectors.toList());
          //            sheet_metadata = service.spreadsheets().get(spreadsheetId=spreadsheet_id).execute()
//            sheets = sheet_metadata.get('sheets', '')
//            title = sheets[0].get("properties", {}).get("title", "Sheet1")
//            sheet_id = sheets[0].get("properties", {}).get("sheetId", 0)
//            List<String> ranges = Arrays.asList(
//                    "Лист3!A1:C200"
//            );
            StringBuilder retValue= new StringBuilder();
            BatchGetValuesResponse result = service.spreadsheets().values().batchGet(spreadsheetId)
                    .setRanges(ranges).execute();
            for (int i = 0; i <ranges.size() ; i++) {

            ValueRange v1 = result.getValueRanges().get(i);

            String values = v1.getValues()
                    .stream()
                    .map(list -> list.get(0) + "->" + list.get(1))
                    .reduce((a, b) -> a + "\n" + b).get();

//            System.out.println(values);
//            System.out.println(v1.getValues().get(1).get(0));
//            List<Object> engWord = v1.getValues().get(0);
//            List<Object> transleteWord = v1.getValues().get(1);
                retValue.append("\n").append(values);
            }
            System.out.println(retValue);

            return retValue.toString();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
