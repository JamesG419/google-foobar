package googleProject7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Answer {
	
	public static void main(String args[]){
		int[] dim = {2,5};
		int[] cap = {1,2};
		int[] bad = {1,4};
		int dist = 11;
		
		System.out.println(answer(dim,cap,bad,dist));
	}
	
	
    public static int answer(int[] dimensions, int[] captain_position, int[] badguy_position, int distance) { 

        // Your code goes here.
    	
    	//initialize
    	
    	int counter = 0;
    	
    	Map<String,Integer> slopes = new HashMap<String,Integer>();
    	ArrayList<int[]> mirrorPositions = new ArrayList<int[]>();
    	Map<String,Integer> badGuyHits = new HashMap<String,Integer>();
    	Map<String,Integer> vectors = new HashMap<String,Integer>();
    	
    	//first slope
    	int dx = badguy_position[0] - captain_position[0];
    	int dy = badguy_position[1] - captain_position[1];
    	
    	slopes = addSlope(dx,dy,distance,slopes);
    	if(slopes.size() == 1){
    		
    		counter++;
    		badGuyHits.put("(" + badguy_position[0] + "," + badguy_position[1] + ")", 1);
    		vectors.put("<" + dx + "," + dy + ">" , 1);
    		
    	}
    	
    	
    	//create virtual rooms
    	
    	//x-direction num of rooms
    	int horizontalRooms = 1 + (captain_position[0] + distance)/dimensions[0];
    	//y-direction num of rooms 
    	int verticalRooms = 1 + (captain_position[1] + distance)/dimensions[1];
    	
    	
    	
    	//fill virtual rooms with good guys
        //fill x direction for good guy
        slopes = addHorCapt(captain_position[0],captain_position[1],captain_position,horizontalRooms,dimensions,distance,slopes);
        //fill y direction for good guy
        slopes = addVertCapt(captain_position[0],captain_position[1],captain_position,horizontalRooms,verticalRooms,dimensions,distance,slopes);
        
    	//fill virtual rooms with bad guys
        //fill x direction for bad guy
        mirrorPositions = addHorBad(badguy_position[0],badguy_position[1],badguy_position,captain_position,horizontalRooms,dimensions,distance,mirrorPositions);
        //fill y direction for bad guy    	
    	mirrorPositions = addVertBad(badguy_position[0],badguy_position[1],badguy_position,captain_position,horizontalRooms,verticalRooms,dimensions,distance,mirrorPositions);
    	
    	
    	//slope iterator
    	Integer slopeFactor;
    	int[] currentRoom;
    	int factor;
    	
    	for(int i = 0;i < mirrorPositions.size();i++){
    		currentRoom = mirrorPositions.get(i);
    		
    		if (badGuyHits.get("(" + currentRoom[0] + "," + currentRoom[1] + ")") == null){
    			dx = currentRoom[0] - captain_position[0];
    			dy = currentRoom[1] - captain_position[1];
    			factor = gcf(dx,dy);
    			
    			dx /= factor;
    			dy /= factor;
    			
    			slopeFactor = slopes.get(dy + "/" + dx);
    			if(vectors.get("<" + dx + "," + dy + ">") == null){
    				if (slopeFactor == null){
    					counter++;
    					vectors.put("<" + dx + "," + dy + ">",1);
    				}
    				else{
    					if(factor<slopeFactor){
    						counter++;
    						vectors.put("<" + dx + "," + dy + ">",1);
    					}
    				}
    			}
    			
    			badGuyHits.put("(" + currentRoom[0] + "," + currentRoom[1] + ")",1);
    			
    		}
    	}
    	
    	//output count
    	return counter;
    } 
    
    //GCF calculator
    public static int gcf(int a, int b){
    	if (b==0){
    		return Math.abs(a);
    	}
    	else {
    		return Math.abs(gcf(b,a%b));
    	}
    	
    }
    
    //helper method-add possible slopes
    public static Map<String,Integer> addSlope(int dx, int dy, int distance, Map<String,Integer> slopes){
    	int factor = gcf(dx,dy);
    	
    	double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    	
    	if (factor != 0){
    		dx /= factor;
    		dy /= factor;
    		
    		if (distance >= dist){
    			if (slopes.get(dy + "/" + dx) == null){
    				slopes.put(dy + "/" + dx, factor);
    			}
    			else if (factor < slopes.get(dy + "/" + dx)){
    				slopes.put(dy + "/" + dx,factor);
    			}
    		}
    	}
    	
    	return slopes;
    }
    
    //helper method-add mirror bad guy positions
    public static ArrayList<int[]> addRooms(int x, int y, int[] pos, int distance, ArrayList<int[]> rooms){
    	int dx = x - pos[0];
    	int dy = y - pos[1];
    	int[] target = {x,y};
    	
    	double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    	
    	if (distance >= dist){
    		rooms.add(target);
    	}
    	
    	return rooms;
    }
    
    //mirror position creation for both capt and guard
    public static Map<String,Integer> addHorCapt(int x,int y,int[] capPos, int numRoomsH, int[] dimensions, int distance, Map<String, Integer> slopes ){
    	int rightMargin = dimensions[0] - capPos[0];
    	int leftMargin = capPos[0];
    	
    	int currentX = x;
    	int currentY = y;
    	
    	int dx;
    	int dy;
    	
    	for(int i = 1; i <= numRoomsH;i++){
    		currentX += 2*rightMargin;
    		rightMargin = dimensions[0] - rightMargin;
    		dx = currentX - capPos[0];
    		dy = currentY - capPos[1];
    		
    		slopes = addSlope(dx,dy,distance,slopes);    		
    	}
    	
    	currentX = x;
    	
    	for (int i = 1; i<= numRoomsH; i++){
    		currentX -= 2*leftMargin;
    		leftMargin = dimensions[0] - leftMargin;
    		
    		dx = currentX - capPos[0];
    		dy = currentY - capPos[1];
    		
    		slopes = addSlope(dx,dy,distance,slopes);
    	}
    	
    	return slopes;
    }
    
    public static Map<String,Integer> addVertCapt(int x,int y,int[] capPos, int numRoomsH, int numRoomsV, int[] dimensions, int distance, Map<String, Integer> slopes ){
    	int topMargin = dimensions[1] - capPos[1];
    	int bottomMargin = capPos[1];
    	
    	int currentX = x;
    	int currentY = y;
    	
    	int dx;
    	int dy;
    	
    	for(int i = 1; i <= numRoomsV;i++){
    		currentY += 2*topMargin;
    		topMargin = dimensions[1] - topMargin;
    		dx = currentX - capPos[0];
    		dy = currentY - capPos[1];
    		
    		slopes = addSlope(dx,dy,distance,slopes); 
    		slopes = addHorCapt(currentX,currentY,capPos,numRoomsH,dimensions,distance,slopes);
    	}
    	
    	currentY = y;
    	
    	for (int i = 1; i<= numRoomsV; i++){
    		currentY -= 2*bottomMargin;
    		bottomMargin = dimensions[1] - bottomMargin;
    		
    		dx = currentX - capPos[0];
    		dy = currentY - capPos[1];
    		
    		slopes = addSlope(dx,dy,distance,slopes);
    		slopes = addHorCapt(currentX,currentY,capPos,numRoomsH,dimensions,distance,slopes);

    	}
    	
    	return slopes;
    }
    
    public static ArrayList<int[]> addHorBad(int x,int y,int[] badPos,int[] capPos, int numRoomsH, int[] dimensions, int distance, ArrayList<int[]> rooms ){
    	int rightMargin = dimensions[0] - badPos[0];
    	int leftMargin = badPos[0];
    	
    	int currentX = x;
    	int currentY = y;
    	
    	for(int i = 1; i <= numRoomsH;i++){
    		currentX += 2*rightMargin;
    		rightMargin = dimensions[0] - rightMargin;
    		
    		rooms = addRooms(currentX,currentY,capPos,distance,rooms);    		
    	}
    	
    	currentX = x;
    	
    	for (int i = 1; i<= numRoomsH; i++){
    		currentX -= 2*leftMargin;
    		leftMargin = dimensions[0] - leftMargin;
    		
    		rooms = addRooms(currentX,currentY,capPos,distance,rooms);
    	}
    	
    	return rooms;
    }
    
    public static ArrayList<int[]> addVertBad(int x,int y,int[] badPos,int[] capPos, int numRoomsH,int numRoomsV ,int[] dimensions, int distance, ArrayList<int[]> rooms ){
    	int topMargin = dimensions[1] - badPos[1];
    	int bottomMargin = badPos[1];
    	
    	int currentX = x;
    	int currentY = y;
    	
    	for(int i = 1; i <= numRoomsV;i++){
    		currentY += 2*topMargin;
    		topMargin = dimensions[1] - topMargin;
    		
    		rooms = addRooms(currentX,currentY,capPos,distance,rooms);
    		rooms = addHorBad(currentX,currentY,badPos,capPos,numRoomsH,dimensions,distance,rooms);
    	}
    	
    	currentY = y;
    	
    	for (int i = 1; i<= numRoomsV; i++){
    		currentY -= 2*bottomMargin;
    		bottomMargin = dimensions[1] - bottomMargin;
    		
    		rooms = addRooms(currentX,currentY,capPos,distance,rooms);
    		rooms = addHorBad(currentX,currentY,badPos,capPos,numRoomsH,dimensions,distance,rooms);
    	}
    	
    	return rooms;
    }
        
}