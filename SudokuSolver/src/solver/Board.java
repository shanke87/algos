package solver;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Board {
	int[][] possibs;
	private int board_size;
	public int getBoard_size() {
		return board_size;
	}
	private int all_possibs ;
	private int sq_board_size;
	Board(int size) {
		board_size = size;
		possibs = new int[(3 + board_size)][board_size];
		sq_board_size =  (int) Math.sqrt(board_size);
		all_possibs =  (( 1 << (board_size) ) - 1);
		for(int i=0;i<possibs.length;++i)
			for(int j=0;j<possibs[i].length;++j)
				possibs[i][j] = all_possibs;
	}
	private int innerBoxAddr(int r, int c) {
		return ((r/sq_board_size)*sq_board_size) + (c/sq_board_size);
	}
	public BoardImage assign(int r, int c, int u) {
			assert(check(r, c, u));
			BoardImage bi = storeMinState(r,c);
			possibs[r][c] = (1<<u);
			possibs[board_size][r] -= (1<<u);
			possibs[board_size + 1][c] -= (1<<u);
			possibs[board_size + 2][innerBoxAddr(r, c)]  -=  (1<<u);
/*			assert(possibs[board_size][r] >= 0);
			assert(possibs[board_size][r] >= 0); */
			return bi;
	}
	private BoardImage storeMinState(int r, int  c) {
		BoardImage bi = new BoardImage();
		bi.r = possibs[board_size][r];
		bi.c = possibs[board_size + 1][c];
		bi.box = possibs[board_size + 2][innerBoxAddr(r, c)];
		bi.cell = possibs[r][c];
		return bi;
	}
	public boolean check(int r, int c, int u) {
		return ((superImposed(r, c) & (1<<u)) > 0);
	}
	public void restore(int r, int c, BoardImage bi) {
		possibs[board_size][r] = bi.r;
		possibs[board_size + 1][c] = bi.c;
		possibs[board_size + 2][innerBoxAddr(r, c)] = bi.box;
		possibs[r][c] = bi.cell;
	}
	public int superImposed(int r, int c) {
		return possibs[r][c] & possibs[board_size][r] & possibs[board_size + 1][c] & possibs[board_size + 2][innerBoxAddr(r, c)];
	}
	private void rippleConstraint(int r, int c) {
		
	}
	void readBoard(char[] boardChars){
		for(int i=0;i<boardChars.length;++i)
			if(boardChars[i] != '0' && Character.isDigit(boardChars[i])) {
				assign( (i/board_size), (i%board_size),  (boardChars[i] - '1'));
				
			} 
	}
	void printBoard(PrintStream out,boolean state) {
		for(int i=0;i<board_size+( state ? 3 : 0);++i,out.print((state ? "\n" : "\n")))
		for(int j=0;j<board_size;++j){
			if(!state) {
				if(isOnlyOne(i,j))
					out.print(getOnlyOne(i,j)+1);
				else 
					out.print(".");  
			} else {
				long st = 0;
				for(int l =0 ;l<9; ++l)
					if((possibs[i][j] & (1<<l)) > 0)
						st = st*10 + (l+1);
				out.print(st + " ");
			}
		}
		System.out.println("\n");
	}
	private int getOnlyOne(int i, int j) {
		int tmp = possibs[i][j];
		assert(isOnlyOne(i,j));
		for(int ii=0, val = 1;ii<board_size;++ii, val = (val << 1))
			if( (tmp & val) != 0)
				return  ( ii);
		return -1;
	}
	public boolean isOnlyOne(int i, int j) {
		assert(possibs[i][j] > 0);
		return (possibs[i][j] & (possibs[i][j] - 1)) == 0;
	}
	public void clear() {
		for(int i=0;i<possibs.length;++i)
			for(int j=0;j<possibs[i].length;++j)
				possibs[i][j] = all_possibs;
}
	@Override
	public void finalize() {
		possibs = null;
	}
	public void getBoardAsCharArray(char[] tmp) {
		
		int cnt = 0;
		for(int i=0;i<board_size;++i)
		for(int j=0;j<board_size;++j){
			if(isOnlyOne(i,j))
				tmp[cnt++] = (char) (getOnlyOne(i,j) + '1');
			else 
				tmp[cnt++] = '.';
		}
	}
	public boolean checkForSolution() {
		for(int i=0;i<board_size;++i)
			for(int j=0;j<board_size;++j)
				if(!isOnlyOne(i, j)) return false;
		return true;
	}
	public static int tmp,c;
	public int getPossibilities(int r, int c) {
		tmp = superImposed(r, c);
		//System.out.println("Possibilities " + r + " " + c + " " + tmp);
		c = 0 ;
		while(tmp > 0)  {
			c++;
			tmp &= tmp - 1;
		}
		return c;
	}
	public static int t1,t2,t3;
	public boolean verify(int r, int c, int p) {
		tmp = 1 << p;
		//System.out.println("Verifying " + r + " " + c + " " + p);
		for(int i=0;i<board_size;++i)
			if((i!=r && superImposed(i,c) == tmp) || (i!=c && superImposed(r,i) == tmp) ) 
				return false;
		t1 = (r/sq_board_size)*sq_board_size;
		t2 = (c/sq_board_size)*sq_board_size;
		for(int i=t1;i<t1 + sq_board_size;++i)
			for(int j=t2;j<t2 + sq_board_size;++j)
				if(!( i== r && j==c ) && superImposed(i,j) == tmp)
					return false;
		return true;
	}
	public boolean selfCheck() {
		for(int r=0;r<board_size;++r)
			for(int c=0;c<board_size;++c) {
				t1 = t2 = 0;
				for(int i=0;i<board_size;++i) {
					t1 |= possibs[r][i];
					t2 |= possibs[i][c];
				}
				if(t1 != all_possibs || t2 != all_possibs)
					return false;	
				t1 = (r/sq_board_size)*sq_board_size;
				t2 = (c/sq_board_size)*sq_board_size;
				t3 = 0;
				for(int i=t1;i<t1 + sq_board_size;++i)
					for(int j=t2;j<t2 + sq_board_size;++j)
						t3 |= possibs[i][j];
				if(t3 != all_possibs)
					return false;
			}
		return true;
	}
	
}