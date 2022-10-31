package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class discreteEventSimulatorApp {
	


	public static void main(String[] args) throws IOException {
		
		PriorityQueue<Request> request_queue = new PriorityQueue<Request>();		
		
		ArrayList<Player> player = new ArrayList<Player>();  
		
		ArrayList<Worker> physiotherapist  = new ArrayList<Worker>();
		ArrayList<Worker> coach  = new ArrayList<Worker>();
		ArrayList<Worker> masseur  = new ArrayList<Worker>();
		
		PriorityQueue<Cqplayer> coach_queue = new PriorityQueue<Cqplayer>();    // coach training queue and Cqplayer class
		PriorityQueue<Pqplayer> physiotherapist_queue = new PriorityQueue<Pqplayer>();  // physiotherapist queue and Pqplayer class 
		PriorityQueue<Mqplayer> masseur_queue = new PriorityQueue<Mqplayer>();      // massue queue and mqplayer class 
		
		PriorityQueue<Mostplayer> player_spend_most_time = new PriorityQueue<Mostplayer>();
		PriorityQueue<Minplayer> player_spend_min_time = new PriorityQueue<Minplayer>();
		

		String inputFileName=args[0];
		String outputFileName=args[1];
		
		Scanner sc2 = null;	
		try {
			sc2 = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Scanner s2 = new Scanner(sc2.nextLine());
    	int playerNumber=Integer.parseInt(s2.nextLine());    	
    	
    	
    	for(int i=0;i<playerNumber;i++) {
    		Scanner s3 = new Scanner(sc2.nextLine());
        	int id=Integer.parseInt(s3.next());              //taking id from line   	
        	int skillLevel=Integer.parseInt(s3.next());       // taking skilllevel from line
        	Player player1 = new Player(id,skillLevel,false);
        	player.add(player1);        	
        	s3.close();
    	}
    	
    	Collections.sort(player);    // we  sorted arraylist due to their id

    	Scanner s4 = new Scanner(sc2.nextLine());
    	int totalArrivals=Integer.parseInt(s4.nextLine());
    	
    	for(int i=0;i<totalArrivals;i++) {
    		Scanner s5 = new Scanner(sc2.nextLine());
        	
    		String request_type2=s5.next();
        	int player_id2=Integer.parseInt(s5.next());
        	double request_time2=Double.parseDouble(s5.next());
        	double request_time_length2=Double.parseDouble(s5.next());
        	int skillLevel2=player.get(player_id2).skillLevel;          // taking skill level from player arraylist
        	Request request1=new Request(request_type2,player_id2,request_time2,request_time_length2 ,skillLevel2 );
        	
        	
        	
        	request_queue.add(request1);
        	
        	s5.close();		

    	}

    	
    	Scanner s6 = new Scanner(sc2.nextLine());
    	int physiotherapist_number=Integer.parseInt(s6.next());
    	
    	
    	for(int i=0;i<physiotherapist_number;i++) {
    		
    		double physiotherapist_service_time=Double.parseDouble(s6.next());
    		Worker worker1=new Worker(i, physiotherapist_service_time, false);
    		physiotherapist.add(worker1);
    		 	
    	}
    	
    	Scanner s7 = new Scanner(sc2.nextLine());
    	int coach_number=Integer.parseInt(s7.next());
    	int masseur_number=Integer.parseInt(s7.next());
    	
    	for(int i=0;i<coach_number;i++) {
    		
    		Worker worker1=new Worker(i, false);    		
    		coach.add(worker1);
    		  		
    	}    	
    	
    	for(int i=0;i<masseur_number;i++) {
    		
    		Worker worker1=new Worker(i, false);    		
    		masseur.add(worker1);
    		 //System.out.println(worker1.worker_id);  //dogru calisti kontrol    		
    	}
    	

    	
    	
    	int player_id;
    	int busy_coach_number=0;
    	int busy_physiotherapist_number=0;
    	int busy_masseur_number=0;
    	double new_request_time;
    	
    	int max_coach_queue_length=0;
    	int max_physiotherapy_queue_length=0;
    	int max_massage_queue_length=0;
    	int number_of_avaible_t_enter=0;
    	int number_of_avaible_p_enter=0;
    	int number_of_avaible_m_enter=0;
    	double total_seconds_whole_simulation=0;
    	
    	
    	while(!request_queue.isEmpty()) {   // control events from request queue 

    		Request request1=request_queue.poll();
    		player_id=request1.player_id;
    		
    		if(request_queue.isEmpty()) {    // for the last element save the time for give the 15. answer
    			total_seconds_whole_simulation=request1.request_time;
    		}
    		
    		
    		if(request1.request_type.equals("t")) {
    			if(!player.get(player_id).busy) {    // checked busy status of player
    				player.get(player_id).setBusy(true); // make player busy
    				number_of_avaible_t_enter++;
    				//coach service training time calculation for that player
    				player.get(request1.player_id).setSum_waiting_time_coach(player.get(request1.player_id).getSum_waiting_time_coach()+request1.request_time_length);  //increase player training coach time
    				
    				int c=coach_number-busy_coach_number;  // number of avaible coach
    				if( c==0  || (c>0 && !coach_queue.isEmpty())) {   // first it must look for queue  					
        				
    					double current_training_time=request1.request_time_length;
    					double current_request_time=request1.request_time;
    					Cqplayer new_player=new Cqplayer(request1.player_id,request1.skillLevel,true,current_request_time,current_training_time);
    					coach_queue.add(new_player);
    					if(max_coach_queue_length<coach_queue.size()) {   // look for max length of queue after adding element
    						max_coach_queue_length=coach_queue.size();
    					}
    					
    				}else  {    //match player with a coach but start to look from id:0 coach
    					for(int i=0;i<coach_number;i++) {
    						if(!coach.get(i).busy) {     // checked avaibility of coach
    							new_request_time=request1.request_time+request1.request_time_length;   // determine the exit time of player     				
    							Request request_new=new Request("t_exit",request1.player_id,new_request_time,request1.request_time_length,request1.skillLevel,i);
    							request_queue.add(request_new);     							
    							coach.get(i).setBusy(true);        //setting that coach busy status true
    							busy_coach_number++;   // increase busy coach number
    							break;
    						}
    					}
    				}
    			}else {
    				player.get(request1.player_id).setCanceled_attempt(player.get(request1.player_id).getCanceled_attempt()+1);  // canceled item increase.
    			}
    			
    		}else if(request1.request_type.equals("t_exit")) { //training coach exit
    			
    			
    			coach.get(request1.service_mark).setBusy(false);   // set that coach busy status false
    			busy_coach_number--;
    			number_of_avaible_p_enter++;

    			
    		// send physiotherapist immediately
    			int p=physiotherapist_number-busy_physiotherapist_number;
    			if(p==0  || (p>0 && !physiotherapist_queue.isEmpty())) {    
    				
    				double current_training_time=request1.request_time_length;     // time which reamining in training coach of that player
    				double current_request_time=request1.request_time;
    				
    				Pqplayer new_player=new Pqplayer(request1.player_id,request1.skillLevel,true,current_request_time,current_training_time);
    				physiotherapist_queue.add(new_player);
    				if(max_physiotherapy_queue_length<physiotherapist_queue.size()) {   // after add new element look to the size 
    					max_physiotherapy_queue_length=physiotherapist_queue.size();
    				}
    			}else  {
    				for(int i=0;i<physiotherapist_number;i++) {    // search for available phys, look from id:0
    					if(!physiotherapist.get(i).busy) {
    						physiotherapist.get(i).setBusy(true);
    						busy_physiotherapist_number++;
    						
    						
    						player.get(request1.player_id).setSum_waiting_time_physiotherapist(player.get(request1.player_id).getSum_waiting_time_physiotherapist()+ physiotherapist.get(i).worker_service_time);
    						
    						new_request_time=request1.request_time+physiotherapist.get(i).worker_service_time;
    						Request request_new=new Request("p_exit",request1.player_id,new_request_time,request1.request_time_length,request1.skillLevel,i);  //sorun
    						request_queue.add(request_new);
    						break;
    					}
    				}
    			}
    			// now t is empty, look element from queue
    			if(!coach_queue.isEmpty()) { 
    				coach.get(request1.service_mark).setBusy(true);
    				busy_coach_number++;
    				Cqplayer player1=coach_queue.poll();
    				
    				//passing time in queue
    				double current_wait_time=request1.request_time-player1.current_request_time;      // current waiting=current time-player's queue enter time 
    				
    				player.get(player1.id).setSum_waiting_time_coach_queue(player.get(player1.id).getSum_waiting_time_coach_queue()+current_wait_time);
    				
    				double new_request_time2=request1.request_time+ player1.current_training_time; // t_exit time from coach
    				Request request2=new Request("t_exit",player1.id,new_request_time2,0,player1.skillLevel,request1.service_mark);
    				request_queue.add(request2);
    				
    		
    			} 
    			
    			
    			
    		}else if(request1.request_type.equals("p_exit")) {    			
    			physiotherapist.get(request1.service_mark).setBusy(false);
    			busy_physiotherapist_number--;
    			player.get(request1.player_id).setBusy(false);
    			
    			//now physiotherapist is empty, look element from queue
    			if(!physiotherapist_queue.isEmpty()) {        
    				physiotherapist.get(request1.service_mark).setBusy(true); 
    				player.get(request1.player_id).setSum_waiting_time_physiotherapist(player.get(request1.player_id).getSum_waiting_time_physiotherapist()+ physiotherapist.get(request1.service_mark).worker_service_time);
    				busy_physiotherapist_number++;
    				Pqplayer player1=physiotherapist_queue.poll();
    				
    			// setSum_waiting_time_physiotherapist_queue setting
    				
    				double current_wait_time=request1.request_time-player1.current_request_time;	
    				player.get(player1.id).setSum_waiting_time_physiotherapist_queue(player.get(player1.id).getSum_waiting_time_physiotherapist_queue()+current_wait_time);
    				
    				double new_request_time2=request1.request_time+physiotherapist.get(request1.service_mark).worker_service_time;
    				Request request2=new Request("p_exit", player1.id, new_request_time2,0,player1.skillLevel,request1.service_mark);
    				request_queue.add(request2);
    			}
    			
    			
    			
    			
    		}else if(request1.request_type.equals("m")) {
    			
    		  if(player.get(request1.player_id).getNumber_taked_massage()<3) {
    			
    			if(!player.get(request1.player_id).busy) {
    				number_of_avaible_m_enter++;
    				
    				player.get(request1.player_id).setNumber_taked_massage(player.get(request1.player_id).getNumber_taked_massage()+1); //+1 taked masssage of player 
    				player.get(request1.player_id).setSum_waiting_time_masseur(player.get(request1.player_id).getSum_waiting_time_masseur()+request1.request_time_length);
    				
    				player.get(player_id).setBusy(true);
    				
    				int m=masseur_number-busy_masseur_number;  // avaible masseur number
    				if(m==0  || (m>0 && !masseur_queue.isEmpty())){    
    					double current_training_time=request1.request_time_length;
    					double current_request_time=request1.request_time;
    					Mqplayer new_player=new Mqplayer(request1.player_id,request1.skillLevel,true,current_request_time,current_training_time);
    					masseur_queue.add(new_player);   
    					if(max_massage_queue_length<masseur_queue.size()) {   // after adding something to queue check for maks length
    						max_massage_queue_length=masseur_queue.size();
    					}
    				}else{   // enter masseur
    					for(int i=0;i<masseur_number;i++) {    // look masseur from lowest id
    						if(!masseur.get(i).busy) {
    							masseur.get(i).setBusy(true);
    							busy_masseur_number++;
    							    							
    							new_request_time=request1.request_time+request1.request_time_length;
    							Request request_new=new Request("m_exit",request1.player_id,new_request_time,request1.request_time_length,request1.skillLevel,i);
    							request_queue.add(request_new);    							
    							break;    							
    						}
    					}
    				} 
    				
    				
    				
    			}else {
    				player.get(request1.player_id).setCanceled_attempt(player.get(request1.player_id).getCanceled_attempt()+1);  // increase canceled attempt for that player
    			}
    		  }else {
    			player.get(request1.player_id).setInvalid_attempt(player.get(request1.player_id).getInvalid_attempt()+1); // increase invalidttempt for that player
    		  }
    			
    		}else if(request1.request_type.equals("m_exit")) {
    			masseur.get(request1.service_mark).setBusy(false);
    			busy_masseur_number--;
    			player.get(request1.player_id).setBusy(false);     // set the player free, (is not busy)
    			
    			//now masseur is avaible, look element from queue
    			if(!masseur_queue.isEmpty()) {
    				masseur.get(request1.service_mark).setBusy(true);    				
    				busy_masseur_number++;     // increase one busy masseur number
    				Mqplayer player1=masseur_queue.poll();
    				// need to determine waiting queue time for outputs			
    				double current_wait_time=request1.request_time-player1.current_request_time;
    				
    				player.get(player1.id).setSum_waiting_time_masseur_queue(player.get(player1.id).getSum_waiting_time_masseur_queue()+current_wait_time);  // set waiting masseur time to player
    				    				
    				double new_request_time2=request1.request_time+ player1.current_training_time; //determine the mexit request time
    				Request request2=new Request("m_exit", player1.id, new_request_time2, player1.current_training_time,player1.skillLevel,request1.service_mark); // hata burada olabilir 0 duzeltildi
    				request_queue.add(request2);
    				
    				
    			}
    			
    		}
    		
    	
    		
    	}  // end of while
 
    	
    	double total_coach_queue_waiting=0;
    	double total_physiotherapist_queue_waiting=0;
    	double total_masseur_queue_waiting=0;
    	double total_coach_service_waiting=0;
    	double total_physiotherapist_service_waiting=0;
    	double total_masseur_service_waiting=0;
    	
    	
    	for(int i=0;i<playerNumber;i++) {
    		total_coach_queue_waiting=total_coach_queue_waiting+player.get(i).getSum_waiting_time_coach_queue();
    		
    	}    	
    	
    	
    	for(int i=0;i<playerNumber;i++) {    		
    		total_physiotherapist_queue_waiting=total_physiotherapist_queue_waiting+player.get(i).getSum_waiting_time_physiotherapist_queue();
    		}   
    	
    	double result5=roundAvoid(total_physiotherapist_queue_waiting/number_of_avaible_p_enter,3);
    	
    	
    	for(int i=0;i<playerNumber;i++) {    		
    		total_masseur_queue_waiting=total_masseur_queue_waiting+player.get(i).getSum_waiting_time_masseur_queue();    		
    	}   
    	
    	
    	double result6=roundAvoid(total_masseur_queue_waiting/number_of_avaible_m_enter,3);
    	 
    	
    	for(int i=0;i<playerNumber;i++) {    	
    		total_coach_service_waiting=total_coach_service_waiting+player.get(i).getSum_waiting_time_coach();    	
    	}   
    	
    	
    	double result7=roundAvoid(total_coach_service_waiting/(number_of_avaible_t_enter),3);
    	
    	
    	for(int i=0;i<playerNumber;i++) {
    		total_physiotherapist_service_waiting=total_physiotherapist_service_waiting+player.get(i).getSum_waiting_time_physiotherapist();    		
    	}   
    	
    	double result8=roundAvoid(total_physiotherapist_service_waiting/number_of_avaible_p_enter,3);
    	
    	for(int i=0;i<playerNumber;i++) {
    		total_masseur_service_waiting=total_masseur_service_waiting+player.get(i).getSum_waiting_time_masseur();
    	}   
    	
    	double result9=roundAvoid(total_masseur_service_waiting/number_of_avaible_m_enter,3);
    	
    	double result10=roundAvoid((total_coach_queue_waiting+total_coach_service_waiting+total_physiotherapist_queue_waiting+total_physiotherapist_service_waiting)/number_of_avaible_t_enter,3);
    	
    	
    	for(int i=0;i<playerNumber;i++) {    		
    		int id2=player.get(i).id;
    		double wait_time1=player.get(i).getSum_waiting_time_physiotherapist_queue();    		
    		Mostplayer player3=new Mostplayer(id2,wait_time1);    		
    		player_spend_most_time.add(player3);   		
    		
    	}    	
    	
    	int result11=player_spend_most_time.peek().id;
    	double result11b=roundAvoid(player_spend_most_time.peek().general_waiting_time,3);
    	
    	    	
    	
    	for(int i=0;i<playerNumber;i++) {    		
    		if(player.get(i).number_taked_massage>2) {    			
    		int id3=player.get(i).id;
    		double wait_time2=player.get(i).getSum_waiting_time_masseur_queue();    				
    		Minplayer player4=new Minplayer(id3, wait_time2 );    		
    		player_spend_min_time.add(player4);
    		}
    	}
    	
    	int result12=player_spend_min_time.peek().id;
    	double result12b=roundAvoid(player_spend_min_time.peek().general_waiting_time,3);
    	
    	
    	int total_invalid_attempt=0;
    	for(int i=0;i<playerNumber;i++) {
    		total_invalid_attempt=total_invalid_attempt+player.get(i).getInvalid_attempt();
    		
    	}
    	
    	int result13=total_invalid_attempt;
    	
    	
    	int total_canceled_attempt=0;
    	for(int i=0;i<playerNumber;i++) {
    		total_canceled_attempt=total_canceled_attempt+player.get(i).getCanceled_attempt();
    		
    	}
    	
    	int result14=total_canceled_attempt;
    	
    	
    	
    	double result15=roundAvoid(total_seconds_whole_simulation,3);
    	
    	
    	double result4=roundAvoid(total_coach_queue_waiting/number_of_avaible_t_enter,3);
    	
    	
    	
    	FileWriter myWriter = null;
    	myWriter = new FileWriter(outputFileName);
    	
    	myWriter.write(Integer.toString(max_coach_queue_length));
    	myWriter.write("\n");
    	myWriter.write(Integer.toString(max_physiotherapy_queue_length));
    	myWriter.write("\n");
    	myWriter.write(Integer.toString(max_massage_queue_length));
    	myWriter.write("\n");
    	myWriter.write(Double.toString(result4));
    	myWriter.write("\n");
    	myWriter.write(Double.toString(result5));
    	myWriter.write("\n");
    	myWriter.write(Double.toString(result6));
    	myWriter.write("\n");
    	myWriter.write(Double.toString(result7));
    	myWriter.write("\n");
    	myWriter.write(Double.toString(result8));
    	myWriter.write("\n");
    	myWriter.write(Double.toString(result9));
    	myWriter.write("\n");
    	myWriter.write(Double.toString(result10));
    	myWriter.write("\n");
    	myWriter.write(Integer.toString(result11)); 
    	myWriter.write(" ");
    	myWriter.write(Double.toString(result11b));
    	myWriter.write("\n");
    	myWriter.write(Integer.toString(result12)); 
    	myWriter.write(" ");
    	myWriter.write(Double.toString(result12b));
    	myWriter.write("\n");
    	myWriter.write(Integer.toString(result13));
    	myWriter.write("\n");
    	myWriter.write(Integer.toString(result14));
    	myWriter.write("\n");
    	myWriter.write(Double.toString(result15));
    	
    
    	myWriter.close();
    	
	}  // endof main
	
	
	public static double roundAvoid(double value, int places) {   // for rounding numbers
	    double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}
	
	
}

	