package src;


public class Worker {
	int worker_id;
	double worker_service_time;
	boolean busy;
	
	public Worker(int worker_id, double worker_service_time, boolean busy) {   
		
		this.worker_id = worker_id;
		this.worker_service_time = worker_service_time;
		this.busy = busy;
	}

	public Worker(int worker_id, boolean busy) {    
		
		this.worker_id = worker_id;
		this.busy = busy;
	}

	public int getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(int worker_id) {
		this.worker_id = worker_id;
	}

	public double getWorker_service_time() {
		return worker_service_time;
	}

	public void setWorker_service_time(double worker_service_time) {
		this.worker_service_time = worker_service_time;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	
	
	
	
	
	
	

}
