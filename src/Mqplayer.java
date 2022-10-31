package src;



public class Mqplayer implements Comparable<Mqplayer> {
	public int id;
	public int skillLevel;
	public int waiting_time;
	public double current_request_time;
	public double current_training_time;
	boolean busy;
	int number_taked_massage=0;
	int invalid_attempt=0;
	int canceled_attempt=0;
	double sum_waiting_time_coach_queue;
	double sum_waiting_time_physiotherapist_queue;
	double sum_waiting_time_masseur_queue;
	double sum_waiting_time_coach;
	double sum_waiting_time_physiotherapist; 
	double sum_waiting_time_masseur;  
	double general_waiting_time;
	
	
	
	public Mqplayer(int id, int skillLevel,boolean busy) {
		this.id = id;
		this.skillLevel = skillLevel;
		this.busy=busy;
	}
	
	// for masseur queeu
	public Mqplayer(int id, int skillLevel,boolean busy,double current_request_time,double current_training_time) {
		this.id = id;
		this.skillLevel = skillLevel;
		this.busy=busy;
		this.current_request_time=current_request_time;
		this.current_training_time=current_training_time;
		
	}
	
	
	public Mqplayer(int id, double general_waiting_time ) {
		this.id=id;
		this.general_waiting_time=general_waiting_time;
	}

	
	// for masseur queeu sorting
	@Override
	public int compareTo(Mqplayer xxx) {
		if(skillLevel != xxx.skillLevel) {
			return Integer.compare(xxx.skillLevel, skillLevel );  // higher to lower 
			
		}else if(Math.abs(current_request_time-xxx.current_request_time)>0.000001){
			return Double.compare(current_request_time, xxx.current_request_time);   // lower to higher
		}else {
			return Integer.compare(id, xxx.id);
		}
		
		
	}
	

	public double getSum_waiting_time_masseur() {
		return sum_waiting_time_masseur;
	}

	public void setSum_waiting_time_masseur(double sum_waiting_time_masseur) {
		this.sum_waiting_time_masseur = sum_waiting_time_masseur;
	}

	public double getSum_waiting_time_physiotherapist() {
		return sum_waiting_time_physiotherapist;
	}

	public void setSum_waiting_time_physiotherapist(double sum_waiting_time_physiotherapist) {
		this.sum_waiting_time_physiotherapist = sum_waiting_time_physiotherapist;
	}

	public double getSum_waiting_time_coach() {
		return sum_waiting_time_coach;
	}

	public void setSum_waiting_time_coach(double sum_waiting_time_coach) {
		this.sum_waiting_time_coach = sum_waiting_time_coach;
	}

	public double getSum_waiting_time_masseur_queue() {
		return sum_waiting_time_masseur_queue;
	}

	public void setSum_waiting_time_masseur_queue(double sum_waiting_time_masseur_queue) {
		this.sum_waiting_time_masseur_queue = sum_waiting_time_masseur_queue;
	}

	public double getSum_waiting_time_physiotherapist_queue() {
		return sum_waiting_time_physiotherapist_queue;
	}

	public void setSum_waiting_time_physiotherapist_queue(double sum_waiting_time_physiotherapist_queue) {
		this.sum_waiting_time_physiotherapist_queue = sum_waiting_time_physiotherapist_queue;
	}

	public double getSum_waiting_time_coach_queue() {
		return sum_waiting_time_coach_queue;
	}

	public void setSum_waiting_time_coach_queue(double sum_waiting_time_coach_queue) {
		this.sum_waiting_time_coach_queue = sum_waiting_time_coach_queue;
	}

	public int getInvalid_attempt() {
		return invalid_attempt;
	}

	public void setInvalid_attempt(int invalid_attempt) {
		this.invalid_attempt = invalid_attempt;
	}

	public int getCanceled_attempt() {
		return canceled_attempt;
	}

	public void setCanceled_attempt(int canceled_attempt) {
		this.canceled_attempt = canceled_attempt;
	}

	public int getNumber_taked_massage() {
		return number_taked_massage;
	}

	public void setNumber_taked_massage(int number_taked_massage) {
		this.number_taked_massage = number_taked_massage;
	}

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getSkillLevel() {
		return skillLevel;
	}




	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}




	public int getWaiting_time() {
		return waiting_time;
	}




	public void setWaiting_time(int waiting_time) {
		this.waiting_time = waiting_time; 
	}




	public boolean isBusy() {
		return busy;
	}




	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	

	




	


	
	
	
	
	
	

}
