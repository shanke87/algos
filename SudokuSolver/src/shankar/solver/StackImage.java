package solver;

public class StackImage {
	public int possibid , r , c, p, cnt;
	public BoardImage bi;
	public StackImage() {
		
	}
	@Override
	public void finalize() {
		bi = null;
	}
	
}

