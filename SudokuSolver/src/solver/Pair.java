package solver;

public class Pair<T1,T2> implements Comparable<Pair>{
	public static int cnt = 0;
	T1 first;
	T2 second;
	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public int compareTo(Pair o) {
		return ((Comparable) second).compareTo(o.second);
	}
	
	@Override
	public String toString() {
		return "" + first.toString() + " " + second.toString();
	}
	protected void finalize(){
		cnt++;
	}
}
