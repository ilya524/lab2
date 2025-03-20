package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import alGraph.*;
//import alGraph.ArticPoint;
//import alGraph.CycleBridge;
//import alGraph.TarjanBridge;

/**
 * Этот класс выполняет некоторые алгоритмы для взаимодействия с графами
 * @author ilya Vahrushev
 * @version 1.0
 */
public class Source {
	
	/**
	 * Эта функция выполняет номер выбранного действия
	 * @param operation номер действия
	 * @param graphPath путь к графу
	 * @return В случае успеха возращает 0
	 */
	private static int selectAction(String operation, String graphPath) {
		//Scanner in = new Scanner(System.in);
		if (operation.equals("1")) {
			ArticPoint findArticPoint = new ArticPoint();
			
			Scanner graphRead;
			try {
				graphRead = new Scanner(new File(graphPath));
				
				while (graphRead.hasNextLine()) {
					String line = graphRead.nextLine();
					String[] parts = line.trim().split("\\s+");
					if (parts.length >= 2) {
						for (int i = 1; i < parts.length; i++) {
							//graph.add(new String[]{parts[0], parts[i]});
							findArticPoint.addEdge(parts[0], parts[i]);
						}
					}
				}
				graphRead.close();
				
				Set<String> articPoints = findArticPoint.findArticPoints();
				
				System.out.println("найденные точки сочленения: " + articPoints);
				
			} catch (FileNotFoundException e) {
				System.out.println(e);
				//e.printStackTrace();
			}
			
		}
		else if (operation.equals("2")) {
			TarjanBridge findBridge = new TarjanBridge();
			
			Scanner graphRead;
			try {
				graphRead = new Scanner(new File(graphPath));
				
				while (graphRead.hasNextLine()) {
					String line = graphRead.nextLine();
					String[] parts = line.trim().split("\\s+");
					if (parts.length >= 2) {
						for (int i = 1; i < parts.length; i++) {
							findBridge.addEdge(parts[0], parts[i]);
						}
					}
				}
				graphRead.close();
				
				Set<List<String>> bridges = findBridge.findBridges();
				
				System.out.println("найденные мосты: " + bridges);
				
			} catch (FileNotFoundException e) {
				System.out.println(e);
				//e.printStackTrace();
			}
			
		}
		else if (operation.equals("3")) {
			CycleBridge findBridgePoint = new CycleBridge();
			
			Scanner graphRead;
			try {
				graphRead = new Scanner(new File(graphPath));
				
				while (graphRead.hasNextLine()) {
					String line = graphRead.nextLine();
					String[] parts = line.trim().split("\\s+");
					if (parts.length >= 2) {
						for (int i = 1; i < parts.length; i++) {
							findBridgePoint.addEdge(parts[0], parts[i]);
						}
					}
				}
				graphRead.close();
				
				Set<List<String>> bridges = findBridgePoint.findBridge();
				
				System.out.println("найденные мосты: " + bridges);
				
			} catch (FileNotFoundException e) {
				System.out.println(e);
				//e.printStackTrace();
			}
			
		}
		else if (operation.equals("0")) {
			
		}
		else {
			System.out.println("Неверный номер действия (1-3, 0)");
			//in.next();
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		//List<String[]> graph = new ArrayList<>();
		//Map<String, List<String>> graph = new HashMap<String, List<String>>();
		String graphPath = "data.txt";
		
		Scanner in = new Scanner(System.in);
		
		String operation = "-1";
		
		while (!operation.equals("0")) {
			System.out.print("\n" +
					"1)Поиск точек сочленения\n" +
					"2)Поиск мостов (Тарьян)\n" +
					"3)Поиск мостов (Циклы)\n" +
					"---------------------------\n" +
					"введите номер действия: ");
			
			operation = in.next();
			selectAction(operation, graphPath);
		}
		
		in.close();
	}

}