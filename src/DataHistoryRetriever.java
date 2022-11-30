//this class take a set of times and their corresponding amount data and sent them back to the graph class
//xuanran qi
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class DataHistoryRetriever {
	final String[] categoryConsts = {"hydration", "exercise", "alcohol", "sleep", "fruit&veggie", "calorie", "smoking"};	//universal category string pass parameters, narges check this please
	int categoryInt;
	String queryUsername;
	DataHistoryRetriever dataHistoryRetriever;
	String rawData;
	String healthDBName = "HealthInfoDB.txt";
	TreeMap<String, Float> dataHistoryMap = new TreeMap<String, Float>();	//TreeMap that will be passed back to graph class
	
	public DataHistoryRetriever(String inputCategory, String inputUsername) {
		for(int counter = 0; counter < 7; counter++) {				//find the index of category in the data base
			if(inputCategory.equals(categoryConsts[counter])) {
				categoryInt = counter + 2;
				break;
			}
		}
		queryUsername = inputUsername;
	}
	
	void readRawData() {
		try {
			File healthInfoDB = new File(healthDBName);						//File start
			Scanner fileReader = new Scanner(healthInfoDB);
			while(fileReader.hasNextLine()) {
				rawData = fileReader.nextLine();
				String[] seperatedData = rawData.split(",");
				if(seperatedData[1].equals(queryUsername)) {
					dataHistoryMap.put(seperatedData[0], Float.parseFloat(seperatedData[categoryInt]));
				}
			}
			fileReader.close();												//file close
		} catch (FileNotFoundException e) {
			System.out.println(healthDBName + " is not found, program will now exit");	//no file found exit program
			System.exit(99);
		}
	}
	
	public TreeMap<String, Float> getDataMap(){
		readRawData();
		return dataHistoryMap;
	}

}
