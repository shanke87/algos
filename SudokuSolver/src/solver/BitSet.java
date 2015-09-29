package solver;


import java.util.Scanner;

public class BitSet {
	public static int setBreak = 30;
	public static int setSize;
	public static void setSize(int maxVars) {
		setSize = (maxVars + setBreak - 1) / setBreak;
	}
	
	long[] a;
	BitSet() {
		a = new long[setSize];
		for(int i=0;i<setSize;++i)
			a[i] = 0L;
	}
	BitSet(BitSet t) {
		a = new long[setSize];
		for(int i=0; i<a.length; ++i)
			a[i] = t.a[i];
	}
	
	BitSet(String str) {
		parseBitSet(str);
	}

	public void parseBitSet(String str) {
		Scanner sc = new Scanner(str);
		sc.useDelimiter("\\*");
		for(int i=0;i<setSize;++i)
			a[i] = sc.nextLong();
		sc.close();
		sc = null;	
	}
	
	public String toRawString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<setSize;++i)
			sb.append(a[i] + "*");
		return sb.toString();
	}
	
	
	public void union(BitSet variables_inst_set) {
		for(int i=0; i<a.length; ++i)
			a[i] |= variables_inst_set.a[i];
	}
	public void add(int index) {
		a[index/setBreak] |=  (1<<(index%setBreak));
	}
	
	public boolean contains(int index) {
		return (a[index/setBreak] &  (1<<(index%setBreak))) != 0;
	}
	
	public void setClear() {
		for(int i=0; i<a.length;++i)
			a[i] = 0L;
	}
	
	protected void finalize() {
		a = null;
	}
	
	public void show() {
		System.out.print("  This is the set : ");
		for(int i=0; i< a.length; ++i) {
			for(int j=0; j<BitSet.setBreak;++j)
				if((a[i] & (1<<j)) != 0 )
				System.out.print( (i*setBreak+j) + " ");
			System.out.print( " " );
		}
		System.out.print( "" );
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		int cnt = 0;
		for(int i=0; i< a.length; ++i) {
			for(int j=0; j<BitSet.setBreak;++j)
				if((a[i] & (1<<j)) != 0 ) {
					sb.append((i*setBreak+j) + ",");
					++cnt;
				}
		}
		sb.append("]");
		String s = sb.toString();
		sb.delete(0,cnt-1);
		sb = null;
		return s;
	}
	
	public boolean intersects(BitSet b) {
		// TODO Auto-generated method stub
		for(int i=0; i< a.length; ++i) {
			if((a[i] & b.a[i]) != 0) return true;
		}
		return false;
	}

	public void intersection(BitSet b) {
		// TODO Auto-generated method stub
		for(int i=0; i< a.length; ++i) {
			a[i] &= b.a[i];
		}
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for(int i=0;i<a.length;++i)
			if(a[i] != 0)
				return false;
		return true;
	}
	public int size() {
		// TODO Auto-generated method stub
		int size = 0;
		for(int i=0;i<a.length;++i) {
			long tmp = a[i];
			while(tmp != 0) {
				++size;
				tmp = tmp & (tmp - 1);
			}
		}
		return size;
	}
	public void remove(int index) {
		a[index/setBreak] -=  (1<<(index%setBreak));
	}
	public void remove(BitSet t) {
		long s = 0;
		for(int i=0;i<a.length;++i) {
			s = t.a[i] & a[i];
			a[i] -= s;
			assert(a[i] >= 0);
		}
	}
	public void copy(BitSet var_set) {
		for(int i=0;i<a.length;++i) {
			a[i] = var_set.a[i];
		}
	}
	public void add(BitSet s) {
		// TODO Auto-generated method stub
		for(int i=0;i<a.length;++i) {
			a[i] |= s.a[i];
		}
	}
}
