package automationConcepts;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;

public class ExcelRead {

	
	//Editing- adding - Editing
	
    int col,Column_Count,Row_Count;
    int colnNum=0;
    Sheet sheet1 ;
    Workbook wb = null;       
      
    public ExcelRead(String Filename, String SheetName){
        File fp = new File(Filename);
        try {
            wb = Workbook.getWorkbook(fp);
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sheet1 = wb.getSheet(SheetName);
        Row_Count = sheet1.getRows();
        Column_Count = sheet1.getColumns();
    }
    
    public void writeToExcel(String sFileName, String sSheetName, int iCol, int iRow, String sData) 
    	      throws BiffException, IOException, WriteException
    	   {
    	      WritableWorkbook wworkbook;
    	      wworkbook = Workbook.createWorkbook(new File(sFileName));
    	      WritableSheet wsheet = wworkbook.createSheet(sSheetName, 0);
    	      //Repeat following 2 lines for multiple entries
    	      Label label = new Label(iCol, iRow, sData);
    	      wsheet.addCell(label);    	      
    	      wworkbook.write();
    	      wworkbook.close();
    	   }
   
    public void writeToExistingExcel(String sFileName, String sSheetName, int iCol, int iRow, String sData) throws BiffException, IOException, WriteException{
    	Workbook workbook = Workbook.getWorkbook(new File(sFileName));
    	WritableWorkbook copy = Workbook.createWorkbook(new File("C:\\Class\\H2K Infosys\\Selenium\\B6\\temp.xls"), workbook);
    	WritableSheet sheet2 = copy.getSheet(sSheetName); 
    	WritableCell cell = sheet2.getWritableCell(iCol, iRow); 

    	if (cell.getType() == CellType.LABEL) 
    	{ 
    	  Label l = (Label) cell; 
    	  l.setString(sData); 
    	}
    	copy.write(); 
    	copy.close();
    	workbook.close();
    }
      
    public int getCoulmnNumber(String strCoulmn){
        for(colnNum=0 ; colnNum<this.sheet1.getColumns();colnNum++){
            if(this.sheet1.getCell(colnNum,0).getContents().equals(strCoulmn))	{
                break;
            }
        }
        return colnNum;
    }

    public int getRowNumber(String strRowData){
        int rowNum;
        for(rowNum=1;rowNum<this.sheet1.getRows();rowNum++)
            if(this.sheet1.getCell(0,rowNum).getContents().toString().equals(strRowData))
                break;
        return rowNum;
    }

    public String getCellData(int iRow, int iColumn){
        return this.sheet1.getCell(iColumn, iRow).getContents().toString();
    }

    public String getCellData(String strColumn, int iRow){
        return this.sheet1.getCell(this.getCoulmnNumber(strColumn), iRow).getContents().toString();
    }

    public int rowCount(){
        return Row_Count;
    }

    public int columnCount(){
        return sheet1.getColumns();
    }

}
