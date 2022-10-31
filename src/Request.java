package src;


public class Request implements Comparable<Request> {
	
	String request_type;
	int player_id;
	double request_time;
	double request_time_length;  // bastaki verilen requesterýn coachta ne kadar sure gecirecegi
	int skillLevel;
	int service_mark;
	 
	
	// for taking initial values at the beginning
	public Request(String request_type, int player_id, double request_time, double request_time_length, int skillLevel) {
		this.request_type = request_type;
		this.player_id = player_id;
		this.request_time = request_time;
		this.request_time_length = request_time_length;
		this.skillLevel=skillLevel;
	}


	// 
	public Request(String request_type, int player_id, double request_time, double request_time_length, int skillLevel, int service_mark) {
		
		this.request_type = request_type;
		this.player_id = player_id;
		this.request_time = request_time;
		this.request_time_length = request_time_length;
		this.skillLevel=skillLevel;
		this.service_mark=service_mark;
	}
	
	//for sorting request queue
	@Override
	public int compareTo(Request xxx) {
		if(Math.abs(request_time-xxx.request_time)>0.00001) {   
			return Double.compare( request_time, xxx.request_time);    // lower first
			
		}else {
			return Integer.compare(player_id, xxx.player_id);
		}
		
		
	}
	
	
	

}
