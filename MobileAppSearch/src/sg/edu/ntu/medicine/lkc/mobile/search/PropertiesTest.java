/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.ntu.medicine.lkc.mobile.search;

import sg.edu.ntu.medicine.lkc.mobile.search.android.data.Results;
import sg.edu.ntu.medicine.lkc.mobile.search.android.data.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileOutputStream;
import com.google.gson.Gson;
import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class PropertiesTest implements ZhilianProject {

    Properties prop = new Properties();

    private int rowNumber = 1;

    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    
    private int searchStartFrom = 0;

    private boolean excelFileCreated = false;

    public void createExcelIO() {
        InputStream input = null;
        try {
            Scanner get_input = new Scanner(System.in);
            ///opt/MobileAppSearch/Kevin/
            System.out.println("Example: /opt/MobileAppSearch/Kevin/config.properties");
            System.out.print("Enter the properties file name with its location on your computer: ");
            String inputString = get_input.nextLine();
            System.out.print("You entered: " + inputString + "\n");
            input = new FileInputStream(inputString);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("The script name is :" + prop.getProperty("FileName") + "\n");
        System.out.println("The output file is :" + prop.getProperty("outputFile"));
    }

    private void createWorkSheet() {
        wb = new HSSFWorkbook();
        // Make a worksheet in the XL document created
        sheet = wb.createSheet();
        // Create row at index zero ( Top Row)
        HSSFRow row = sheet.createRow((short) 0);

        String[] headers = {"Title", "Category", "Developer", "Description", "Created On", "Market Update", "Version", "website", "Rating", "Maximum Downloads", "Minimum Downloads", "Market URL", "Size"};
        for (int i = 0; i <= 12; i++) {

            // Create a cell at index zero ( Top Left)
            HSSFCell cell = row.createCell((short) i);
            // Lets make the cell a string type
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // Type some content
            cell.setCellValue(headers[i]);

            excelFileCreated = false;
        }
    }

    private void updateExcelFile(List<Data> tempData) {
        int counter1 = 1;
        int counter2 = 1;
        //int counter3 = 1;
        int counter4 = 1;
        int counter5 = 1;

        if (sheet != null) {
            for (Data data : tempData) {

                HSSFRow row1 = sheet.createRow((short) rowNumber++);
                int k = 0;
                //cell 1
                HSSFCell cell1 = row1.createCell((short) (k));
                cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell1.setCellValue(data.getTitle());

                //cell 2
                HSSFCell cell2 = row1.createCell((short) (k + 1));
                cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2.setCellValue(data.getCategory());

                //cell 3
                HSSFCell cell3 = row1.createCell((short) (k + 2));
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell3.setCellValue(data.getDeveloper());

                //cell 4
                HSSFCell cell4 = row1.createCell((short) (k + 3));
                cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell4.setCellValue(data.getDescription());

                //cell 5
                HSSFCell cell5 = row1.createCell((short) (k + 4));
                cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell5.setCellValue(data.getCreated());

                //cell 6
                HSSFCell cell6 = row1.createCell((short) (k + 5));
                cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell6.setCellValue(data.getMarketUpdate());

                //cell 7
                HSSFCell cell7 = row1.createCell((short) (k + 6));
                cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell7.setCellValue(data.getVersion());

                //cell 8
                HSSFCell cell8 = row1.createCell((short) (k + 7));
                cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell8.setCellValue(data.getWebsite());

                //cell 9
                HSSFCell cell9 = row1.createCell((short) (k + 8));
                cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell9.setCellValue(data.getRating());

                //cell 10
                if (data.getDownloadsMax() != null) {
                    HSSFCell cell10 = row1.createCell((short) (k + 9));
                    cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell10.setCellValue(data.getDownloadsMax());
                } else {
                    HSSFCell cell10 = row1.createCell((short) (k + 9));
                    cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell10.setCellValue("NULL");
                };

                //cell 11
                if (data.getDownloadsMin() != null) {
                    HSSFCell cell11 = row1.createCell((short) (k + 10));
                    cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell11.setCellValue(data.getDownloadsMin());
                } else {
                    HSSFCell cell11 = row1.createCell((short) (k + 10));
                    cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell11.setCellValue("NULL");
                }
                //cell 12
                HSSFCell cell12 = row1.createCell((short) (k + 11));
                cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell12.setCellValue(data.getMarketUrl());

                //cell 13
                if (data.getSize() != null) {
                    HSSFCell cell13 = row1.createCell((short) (k + 12));
                    cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell13.setCellValue(data.getSize());
                } else {
                    HSSFCell cell13 = row1.createCell((short) (k + 12));
                    cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell13.setCellValue("NULL");
                }
                //z = z + 1;

//                System.out.println(+counter1++ + ") Title: " + "   " + data.getTitle());
//                System.out.println(+counter2++ + ") Category: " + "   " + data.getCategory());
//                System.out.println(+counter4++ + ") Developer: " + "   " + data.getDeveloper());
//                System.out.println(+counter5++ + ") Size: " + "   " + data.getSize());
                //System.out.println("**************************************************************\n");
            }
        }
    }
    
    private void readAndUpdateScriptFile(String oldSearchFrom, String newSearchFrom) {
        
        // The name of the file to open.
        String fileName = prop.getProperty("FileName");
        
        // This will reference one line at a time
        String line = null;
        
        StringBuilder builder = new StringBuilder();
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if(line.contains("\"from\":")){
                    String newLine = line.replaceFirst(oldSearchFrom, newSearchFrom);
                    System.out.println(newLine + "\t" + oldSearchFrom + "\t" + newSearchFrom);
                    line = newLine;
                }
                builder.append(line + "\n");
                System.out.println(line);
            }   

            // Always close files.
            bufferedReader.close();         
            
            createNewScriptFile(builder.toString());
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    
    private void createNewScriptFile(String scriptContent) {
        
        // The name of the file to write.
        String fileName = prop.getProperty("FileName");
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(scriptContent);

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public void results() {
        try {
            Gson gson = new Gson();
            // Run command and wait till it's done
            Process p;

            p = Runtime.getRuntime().exec(prop.getProperty("FileName"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder result = new StringBuilder();

            String line = "";
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                result.append(line);
            }

            p.waitFor();

            Results results = gson.fromJson(result.toString(), Results.class);

            System.out.print(results.getPage() + "\t");
            System.out.print(results.getNumPages()  + "\t");
            System.out.print(results.getHasNext()  + "\t");
            System.out.println(results.getNumberResults()  + "\t");
            

            List<Data> tempData = results.getResults();

            if (wb == null) {
                createWorkSheet();
            }
            
            updateExcelFile(tempData);
            
            int newSearchFrom = 0;
            if(results.getPage() < results.getNumPages() + 1) {
//                if(searchStartFrom == 0) {
//                    newSearchFrom = 101;
//                } else {
//                    newSearchFrom = searchStartFrom + 100;
//                }
                newSearchFrom = searchStartFrom + 100;
                //newSearchFrom = (results.getPage()) * 100 + 1;
                readAndUpdateScriptFile(searchStartFrom + ",", newSearchFrom + ",");
                searchStartFrom = newSearchFrom;
                results();
            }
            
            
            FileOutputStream fOut = new FileOutputStream(prop.getProperty("outputFile"));
            // Write the XL sheet
            wb.write(fOut);
            fOut.flush();
            // Done Deal..
            fOut.close();
            System.out.println("File Created ..");
        } catch (IOException ex) {
            Logger.getLogger(PropertiesTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PropertiesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void main(String[] args) throws Exception {

        PropertiesTest obj1 = new PropertiesTest();
        obj1.createExcelIO();
        obj1.results();

    }
}
