
import java.util.HashMap;

public class SpanningUSA
{
	private String[] strings;
	
	private HashMap<String, Integer> integerMap;
	private int V;
	
	private EdgeWeightedGraph graph;
	
	public SpanningUSA(In in)
	{
		//strings = in.readAll().split("\n");							//We accumulate a string array containing each seperate line in the file.
		
		integerMap = new HashMap<String, Integer>();
		
		
		
		while(in.hasNextLine())
		{
			String string = in.readLine();
			
			if(string.contains("--"))
			{
				if(graph == null)
					graph = new EdgeWeightedGraph(V);
				
				string = string.replaceAll("\\[", "<");
				string = string.replaceAll("\\]", ">");
				
				//Process first city
				String city1 = string.split("--")[0];
				//StdOut.println(city1);
				int city1Integer = integerMap.get(city1);
				
				//Process second city
				String city2 = string;
				city2 = city2.split("--")[1];
				city2 = city2.split(" <")[0];
				//StdOut.println(city2);
				int city2Integer = integerMap.get(city2);
				
				//Process weight
				String weightString = string;
				weightString = weightString.split("<")[1];
				weightString = weightString.split(">")[0];
				double weight = Double.parseDouble(weightString);
				
				graph.addEdge(new Edge(city1Integer, city2Integer, weight));
			}
			else
			{
				String name = string.trim();
				
				if(!integerMap.containsKey(name))
				{	
					//StdOut.println(string);
					integerMap.put(name, V);
					V++;
				}
			}
		}
		
		KruskalMST mst = new KruskalMST(graph);
		
		StdOut.println(mst.weight());
		
	}
	
	public static void main(String[] args)
	{
		In in = new In(args[0]);													
		
		Stopwatch timer = new Stopwatch();											//We begin our stopwatch
	
		new SpanningUSA(in);
		
		StdOut.println("Making an MST took = " + timer.elapsedTime());					//We print out the total amount of time the processing took
		
		
	}
}