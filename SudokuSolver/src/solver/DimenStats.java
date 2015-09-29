package solver;

public class DimenStats {
	private String name;
	private float mean;
	private long min , max , count;
	DimenStats(String name) {
		this.name = name;
		mean = 0; count = 0;
		min = Long.MAX_VALUE; max = Long.MIN_VALUE;
	}
	
	public boolean isInitialized() {
		return count > 0;
	}
	
	public void record(long val) {
		min = Math.min(min, val);
		max = Math.max(max, val);
		if(count > 0)
			mean = ((mean * count) + val ) / ( count + 1) ;
		else
			mean = val;
		count ++;
	}

	public String getName() {
		return name;
	}

	public float getMean() {
		return mean;
	}

	public long getMin() {
		return min;
	}

	public long getMax() {
		return max;
	}

	public long getCount() {
		return count;
	}
	@Override
	public String toString() { 
		return name + " : " + mean + " " + max + " " + min + " " + count;
	}
	
}
