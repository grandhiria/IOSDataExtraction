/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.ntu.medicine.lkc.mobile.search;



/**
 *
 * @author Venugopal Giridharan 
 * 
 */
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
import sg.edu.ntu.medicine.lkc.mobile.search.ios.data.DataIOS;
import sg.edu.ntu.medicine.lkc.mobile.search.ios.data.ResultsIOS;
/*
"Diabetes","Diabetics","Diabetic","glucose","glycaemic","glycemic","blood sugar","HbA1c","A1c","insulin"
*/
public class IOSDataExtractioninTXT 
{
        private Properties prop = new Properties();
        String keywords[]={"胰島素", "dummy-12"};
        boolean excelFileCreated;
        private HSSFWorkbook wb;
        private HSSFSheet sheet[]=new HSSFSheet[keywords.length];
        private int rowNumber = 1;
        private int searchStartFrom = 0;
        public int sheetNum=0;
        //textfile test
        private static final String FILENAME = "/opt/Chinese_胰島素.txt";
        BufferedWriter bw = null;
	FileWriter fw = null;
        
        public void createExcelIO() {
        InputStream input = null;
        try {
            Scanner getInput = new Scanner(System.in);
            System.out.println("Example: /opt/config.properties");
            System.out.print("Enter the properties file name with its location on your computer: ");
            String inputString = getInput.nextLine();
            System.out.print("You entered: " + inputString + "\n");
            input = new FileInputStream(inputString); //get the file from here
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOSDataExtractioninTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            Logger.getLogger(IOSDataExtractioninTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("The script name is :" + prop.getProperty("FileName") + "\n");
        System.out.println("The output file is :" + prop.getProperty("outputFile"));
}
        
        private void createWorkSheet() 
        {
        wb = new HSSFWorkbook();
        for(int SheetNumber=0;SheetNumber<keywords.length-1;SheetNumber++){
        sheet[SheetNumber] = wb.createSheet(keywords[SheetNumber]);
        // Create row at index zero ( Top Row)
        HSSFRow row = sheet[SheetNumber].createRow((short) 0);
        String[] headers = {"Title", "Category", "Developer", "Description", "Release Date", "currentVersionReleaseDate", "Version", "Website", "Rating Counts", "Average User Rating", "Average User Rating For Current Version", "Market URL", "Size"};
        for (int i = 0; i <= 12; i++) {

            HSSFCell cell = row.createCell((short) i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(headers[i]);
            excelFileCreated = false;
        }
        }
        }
        
        
 
        public void executeScript(int shnum) throws IOException, InterruptedException
        {
        Gson gson = new Gson();
        Process p;
        p = Runtime.getRuntime().exec(prop.getProperty("FileName"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder jsonStringResult = new StringBuilder();
        String line = "";
            while ((line = reader.readLine()) != null) {
                   jsonStringResult.append(line);
            }
            
        p.waitFor();
        ResultsIOS results = gson.fromJson(jsonStringResult.toString(), ResultsIOS.class);
        List<DataIOS> tempData = results.getResults();
        updateExcelFile(tempData, shnum);
        int newSearchFrom = 0;
        
        
         if (results.getPage() < results.getNumPages() + 1) {
                System.out.println("Page Number is : "+results.getPage());
                newSearchFrom = searchStartFrom + 100;
                readAndUpdateScriptFile(searchStartFrom + ",", newSearchFrom + ",");
                searchStartFrom = newSearchFrom;
                executeScript(shnum);
        
        FileOutputStream fileOutput = new FileOutputStream(prop.getProperty("outputFile"));
        wb.write(fileOutput);
        fileOutput.flush();
        fileOutput.close();
         }
        
              
        System.out.println("File Created ..");
        updateKeyword(searchStartFrom + ",", 0 + ",", keywords[shnum], keywords[shnum+1]); 
        searchStartFrom=0;
        }
        int RowNumReSetter=0;
        int zz=1;
        private void updateExcelFile(List<DataIOS> tempData, int sheetNo) {
        if(RowNumReSetter!=sheetNo)
        {
            rowNumber=1;
            RowNumReSetter=sheetNo;
        }
        
        if (sheet[sheetNo] != null) {
            for (DataIOS data : tempData) {

                HSSFRow row1 = sheet[sheetNo].createRow((short) rowNumber++);
                int k = 0;
                
                //ANDROID BELOW *********************
                //cell 1
                 if (data.getTrackCensoredName() != null) 
                 {
                HSSFCell cell1 = row1.createCell((short) (k));
                cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell1.setCellValue(data.getTrackCensoredName());
                 }
                 else
                 {
                HSSFCell cell1 = row1.createCell((short) (k));
                cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell1.setCellValue("NULL");                 
                 }
                //cell 2
                if (data.getGenres() != null) {
                
                HSSFCell cell2 = row1.createCell((short) (k + 1));
                cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2.setCellValue(data.getGenres());
                }
                else
                {
                HSSFCell cell2 = row1.createCell((short) (k + 1));
                cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2.setCellValue("NULL");                
                }
                
                //cell 3
                if (data.getArtistName() != null) {
                
                HSSFCell cell3 = row1.createCell((short) (k + 2));
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell3.setCellValue(data.getArtistName());
                }
                else
                    
                {
                HSSFCell cell3 = row1.createCell((short) (k + 2));
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell3.setCellValue("NULL");
                }
                
                
                //cell 4
                
                if (data.getDescription() != null) {
                HSSFCell cell4 = row1.createCell((short) (k + 3));
                cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell4.setCellValue(data.getDescription());
                }
                else
                {
                 HSSFCell cell4 = row1.createCell((short) (k + 3));
                cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell4.setCellValue("NULL");               
                }
                //cell 5
                if (data.getReleaseDate() != null) 
                {
                HSSFCell cell5 = row1.createCell((short) (k + 4));
                cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell5.setCellValue(data.getReleaseDate());
                }
                else
                {
                HSSFCell cell5 = row1.createCell((short) (k + 4));
                cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell5.setCellValue("NULL");              
                }
                //cell 6
                if (data.getMarketUpdate() != null) {
                HSSFCell cell6 = row1.createCell((short) (k + 5));
                cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell6.setCellValue(data.getMarketUpdate());
                }
                else
                {
                HSSFCell cell6 = row1.createCell((short) (k + 5));
                cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell6.setCellValue("NULL");
                }
                //cell 7
                
                if (data.getVersion() != null) {
                HSSFCell cell7 = row1.createCell((short) (k + 6));
                cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell7.setCellValue(data.getVersion());
                }
                
                else 
                {
                HSSFCell cell7 = row1.createCell((short) (k + 6));
                cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell7.setCellValue("NULL");
                
                }
                
                
                //cell 8
                if (data.getWebsite() != null) {
                HSSFCell cell8 = row1.createCell((short) (k + 7));
                cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell8.setCellValue(data.getWebsite());
                }
                
                else 
                {
                HSSFCell cell8 = row1.createCell((short) (k + 7));
                cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell8.setCellValue("NULL");
                }
                
                
                //cell 9
                /*
                if (data.getRating() != null) {
                */
                
                if (data.getUserRatingCount() != null) {
                
                HSSFCell cell9 = row1.createCell((short) (k + 8));
                cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell9.setCellValue(data.getUserRatingCount());
                
                } else //data.getDescription(), data.getReleaseDate(), data.getMarketUpdate(), data.getVersion(), data.getWebsite(), data.getUserRatingCount(), data.getAverageUserRating(), data.getaverageUserRatingForCurrentVersion(), data.getMarketUrl(), data.getSize(), 
                {
                HSSFCell cell9 = row1.createCell((short) (k + 8));
                cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell9.setCellValue("NULL");
                }
                    
                //cell 10
                if (data.getAverageUserRating() != null) {
                    HSSFCell cell10 = row1.createCell((short) (k + 9));
                    cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell10.setCellValue(data.getAverageUserRating());
                } else {
                    HSSFCell cell10 = row1.createCell((short) (k + 9));
                    cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell10.setCellValue("NULL");
                };

                //cell 11
                if (data.getaverageUserRatingForCurrentVersion() != null) {
                    HSSFCell cell11 = row1.createCell((short) (k + 10));
                    cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell11.setCellValue(data.getaverageUserRatingForCurrentVersion());
                } else {
                    HSSFCell cell11 = row1.createCell((short) (k + 10));
                    cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell11.setCellValue("NULL");
                }
                //cell 12
                if(data.getMarketUrl() != null) {
                HSSFCell cell12 = row1.createCell((short) (k + 11));
                cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell12.setCellValue(data.getMarketUrl());
                }
                else
                {
                HSSFCell cell12 = row1.createCell((short) (k + 11));
                cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell12.setCellValue("NULL"); 
                }
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
              System.out.println( "Title: " + "   " + data.getTrackCensoredName());
              System.out.println("Category: " + "   " + data.getGenres());
              System.out.println(" Developer: " + "   " + data.getArtistName());
              System.out.println(" Size: " + "   " + data.getSize());
              System.out.println("**************************************************************\n");
              
              
              try {

			File file = new File(FILENAME);
//data.getDescription(), data.getReleaseDate(), data.getMarketUpdate(), data.getVersion(), data.getWebsite(), data.getUserRatingCount(), data.getAverageUserRating(), data.getaverageUserRatingForCurrentVersion(), data.getMarketUrl(), data.getSize()
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
                        bw.write("\n");
                        bw.write("App Number "+zz+", Title: "+ data.getTrackCensoredName()+", Category: "+data.getGenres()+", Developer: "+ data.getArtistName()+", Description: "+data.getDescription()+", Release Date: "+data.getReleaseDate()+", currentVersionReleaseDate: "+ data.getMarketUpdate()+", Version: "+data.getVersion()+", Website: "+ data.getWebsite()+", Rating Counts: "+data.getUserRatingCount()+", Average User Rating: "+data.getAverageUserRating()+ ", Average User Rating For Current Version: "+data.getaverageUserRatingForCurrentVersion()+", Market URL: "+ data.getMarketUrl()+", Size: "+data.getSize()+" \n");
                        fw = new FileWriter(file.getAbsoluteFile(), true);
                        System.out.println("Done");
                        

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
              
              
              
              
              
              
              
            }
              
              
              
              zz=zz+1;
              
              
              
            }
           // rowNumber=1;
        } //rowNumber=1;
    }
        public void readAndUpdateScriptFile(String oldSearchFrom, String newSearchFrom) 
        {
        String fileName = prop.getProperty("FileName");
        String line = null;
        StringBuilder builder = new StringBuilder();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader  = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("\"from\":")) {
                    String newLine = line.replaceFirst(oldSearchFrom, newSearchFrom);
                    line = newLine;
                }
                builder.append(line + "\n");
                System.out.println(line);
            }
            // Always close files.
            bufferedReader.close();
            createNewScriptFile(builder.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '"+ fileName + "'");
        }
        }
        
        public void updateKeyword(String oldSearchFrom, String newSearchFrom, String oldKeyword, String newKeyword) 
        {
        String fileName = prop.getProperty("FileName");
        String line = null;
        StringBuilder builder = new StringBuilder();
        String newLine;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader  = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("\"full_text_term\":")) {

                    newLine = line.replace(oldKeyword, newKeyword);
                    line = newLine;
                    
                  //line = newLine;
                }
                
                
                
                else if (line.contains("\"from\":")) {
                    newLine = line.replaceFirst(oldSearchFrom, newSearchFrom);
                    line = newLine;
                }
                builder.append(line + "\n");
                System.out.println(line);
            }
            // Always close files.
            bufferedReader.close();
            createNewScriptFile(builder.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '"+ fileName + "'");
        }
        }
        
        
        
        void createNewScriptFile(String scriptContent)
        {
        // The name of the file to write.
        String fileName = prop.getProperty("FileName");
        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically append a newline character.
            bufferedWriter.write(scriptContent);
            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println( "Error writing to file '"+ fileName + "'");
        }
        }
        
        public static void main(String args[]) throws IOException, InterruptedException
        {
        String keywords[]={"胰島素" , "dummy-12"};
        IOSDataExtractioninTXT obj=new IOSDataExtractioninTXT();
        obj.createExcelIO();
        obj.createWorkSheet();
        for(int i=0;i<keywords.length-1;i++)
        {
        obj.executeScript(i);
        }
        }
}
