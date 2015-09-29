package solver;

import java.util.LinkedList;

public class Backtracker {
	private static LinkedList<StackImage> moves;
	private static StackImage st;
	private static int tmp, minr, minc , possibs, depth;
	public static DimenStats tdepth = null, branch = null; 
	public static long times , nodes;
	private static void solve1(Board bd) throws SolutionFoundException {
		//bd.printBoard(System.out,false);
		st = new StackImage();
		depth ++; nodes++;
		st.cnt = 0;
		tmp = chooseWisePos(bd);
		if(tmp == -1) {
			tdepth.record(depth);
			if(bd.checkForSolution()) {
				if(!branch.isInitialized()) branch.record(1);
				throw new SolutionFoundException();
				
			}
			--depth;
			return;
		}
		st.r = tmp / 100; st.c = tmp % 100;
		st.cnt = 0;
		for(st.p=0;st.p<bd.getBoard_size();++st.p) {
			//System.out.println("Status check " + st.r + " " + st.c + " " + st.p + "  " + (bd.check(st.r, st.c, st.p)) + " " + (bd.verify(st.r,st.c,st.p)));
			if(bd.check(st.r, st.c, st.p) ) {
				st.cnt ++;
				st.bi = bd.assign(st.r, st.c, st.p);
				//System.out.println("CHoice at " + st.r + " " + st.c + " " + st.p + " " + depth);
				moves.add( st );
				solve1(bd);
				st = moves.removeLast();
				bd.restore(st.r,st.c,st.bi);
			}
		}
		branch.record(st.cnt);
		--depth;
		st = null;
	}

	public static int leastPossibs, leasPosIndex, t;
	private static int chooseWisePos(Board bd) {
		leastPossibs = Integer.MAX_VALUE;
		leasPosIndex = -1;
		for(int i=0;i<bd.getBoard_size();++i)
			for(int j=0;j<bd.getBoard_size();++j) {
				t = bd.getPossibilities(i, j);
				if(t > 0 && leastPossibs > t) {
					leasPosIndex = i*100 + j;
					leastPossibs = t;
				}
			}
		//System.out.println("Taken index " + leasPosIndex + " " + leastPossibs + " * ");
		return leasPosIndex;
	}


	private static void solve2(Board bd) throws SolutionFoundException {
		//bd.printBoard(System.out,true);
		//System.out.println("Depth" + depth);
		st = new StackImage();

		depth ++;

		if(depth < -1) {
			bd.checkForSolution();
			throw new SolutionFoundException();
		}

		st.cnt = 0;
		for( st.r=0;st.r<bd.getBoard_size();++st.r) 
			for( st.c=0;st.c<bd.getBoard_size(); ++st.c) {
				if(bd.getPossibilities(st.r, st.c) >= 1) {

					for(st.p=0;st.p<bd.getBoard_size();++st.p) {
						//System.out.println("Status check " + st.r + " " + st.c + " " + st.p + "  " + (bd.check(st.r, st.c, st.p)) + " " + (bd.verify(st.r,st.c,st.p)));
						if(bd.check(st.r, st.c, st.p) && bd.verify(st.r,st.c,st.p)) {
							st.bi = bd.assign(st.r, st.c, st.p);
							System.out.println("CHoice at " + st.r + " " + st.c + " " + st.p);
							moves.add( st );
							solve2(bd);
							st = moves.removeLast();
							bd.restore(st.r,st.c,st.bi);
						}
					}
					++st.cnt;
					return;
				} else if(bd.getPossibilities(st.r, st.c) == 0 && !bd.isOnlyOne(st.r, st.c)) {
					return ;
				}
			}

		if(st.cnt == 0) {
			bd.checkForSolution();
			throw new SolutionFoundException();
		}
		--depth;		
		st = null;
	}


	public static boolean callBackTrack(Board bd) {
		tdepth = new DimenStats("Depth");
		branch = new DimenStats("Branch");
		depth = 0; times = System.currentTimeMillis(); nodes = 0 ; 
		moves = new LinkedList<StackImage>();
		try {
			solve1(bd);
			System.out.println("No solution found");
		} catch (SolutionFoundException e) {
			times = System.currentTimeMillis() - times;
			if(bd.selfCheck()) {
				//		bd.printBoard(System.out, true);
				//bd.printBoard(System.out, false);
				//System.out.println("Board Solved at depth " + depth);
				System.gc();
				return true;
			} else {
				System.out.println("Wrong answer");
				bd.printBoard(System.out, false);
//				System.exit(-1);
			}
			//e.printStackTrace();
		}
		moves.clear(); moves = null; 
		System.out.println("Times exact " + times);
		times = System.currentTimeMillis() - times;
		System.gc();
		return false;
	}
}
