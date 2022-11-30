//this class prints the graph for a certain category of data
//xuanran qi
import java.util.*;

public class DataHistoryGraph {
	String category;
	String queryUsername;
	int numOfDisplay = 7;
	DataHistoryRetriever dataHistoryRetriever;
	TreeMap<String, Float> dataMap;
	
	
	public DataHistoryGraph(String inputCategory, String inputUsername) {
		category = inputCategory;
		queryUsername = inputUsername;
		dataHistoryRetriever = new DataHistoryRetriever(category, queryUsername);
	}
	
	public void printHistoryGraph(Scanner scanner) {
		int barDist = 5;									//distance between bars
		int barWidth = 10;						//width of bars
		int graphHeight = 20;								//graph max height
		
		reduceMapToDisplayNum();
		float maxValue = getMaximumValue();					//get max value for scaling
		for(int counter = 20; counter > 0; counter--) {		//loop represent vertical
			System.out.printf("%.1f", maxValue/graphHeight*counter);
			System.out.print("\t|");
			for(Map.Entry<String, Float> dataEntry : dataMap.entrySet()) {		//loop represent horizontal
				float barAmount = dataEntry.getValue()/maxValue * graphHeight;
				for(int barSpace = 0; barSpace < barDist; barSpace++) {
					System.out.print(" ");
				}
				for(int pixel = 0; pixel < barWidth; pixel++) {
					if(barAmount > counter) {
						System.out.print("#");
					} else {
						System.out.print(" ");
					}
				}
			}
			System.out.println();
		}
		System.out.print("\t");
		for(int counter = 0; counter < (barDist+barWidth)*numOfDisplay; counter++) {	//print lower x axis
			System.out.print("-");
		}
		System.out.println();
		System.out.print("\t ");
		for(Map.Entry<String, Float> dataEntry : dataMap.entrySet()) {	//set space for x axis labels
			for(int barSpace = 0; barSpace < barDist; barSpace++) {
				System.out.print(" ");
			}
			System.out.print(dataEntry.getKey());						//print x axis labels
		}
		System.out.println("\n----------------------------------------------Press Enter to continue----------------------------------------------");
		scanner.nextLine();							//press enter to continue here
	}
	
	private void reduceMapToDisplayNum() {
		dataMap = dataHistoryRetriever.getDataMap();
		while(dataMap.size() > numOfDisplay) {												//remove extra entries if it is more than desired display number
			dataMap.pollFirstEntry();
		}
	}
	
	private float getMaximumValue() {								//get the maximum value for graph vertical scaling
		float value = 0;
		
		for(Map.Entry<String, Float> entry : dataMap.entrySet()) {
			if(entry.getValue() > value) {
				value = entry.getValue();
			}
		}
		
		return value;
	}

}
