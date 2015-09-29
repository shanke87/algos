package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class SolverApp {

	public static void pain(String[] args) throws FileNotFoundException {

		/*Board b = readBoard();
		PrintStream ps = new PrintStream(new File("out.txt"));
		b.printBoard(ps,true);
		ps.close(); 
		Backtracker.callBackTrack(b);
		//test();

		//b.printBoard(System.out);

		 */
		test(args[0]);
		System.out.println("Done");
	}

	public static void printBoard(int[][] board) {
		for(int i=0;i<9;++i) { 
			for(int j = 0; j<9; ++j) System.out.print(board[i][j]);
			System.out.println("");
		}
		//System.exit(-1);

	}

	public static Board readBoard() throws FileNotFoundException {
		char[] cbuf = new char[81];
		File f = new File("inp\\test-bad-sudoku.txt");
		Board b = new Board((short) 9);
		Scanner sc = new Scanner(f);
		int cnt = 0;
		for(int i=0;i<9;++i) { 
			String row = sc.nextLine();
			for(int j = 0; j<9; ++j) {
				cbuf[cnt++] = row.charAt(j);
			}	
		}
		b.readBoard(cbuf);
		sc.close();
		return b;
	}
	public static 		char[] cbuf = new char[81];

	public static char[] readBoardStringFromFile(String loadFile) {
		File f = new File(loadFile);
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Unable to load file given argument" );
			System.exit(-1);
		}
		int cnt = 0;
		for(int i=0;i<9;++i) { 
			String row = sc.nextLine();
			for(int j = 0; j<9; ++j) {
				cbuf[cnt++] = row.charAt(j);
			}	
		}
		sc.close();
		return cbuf;
	}
	public static void test(String args) {
		try {
			Scanner sc = new Scanner(new File(args));
			String ln = null;
			Board bd = new Board((short) 9);
			char[] tmp = new char[81];
			int cn = 0;
			DimenStats times = new DimenStats("Time") , nodes = new DimenStats("Nodes"),
				branch = new DimenStats("MaxBranch") , depth = new DimenStats("MaxDepth");
			while(sc.hasNextLine()) {
				ln = sc.nextLine();
				bd.clear();
				bd.readBoard(ln.toCharArray());
				Arrays.fill(tmp, '*');
				bd.getBoardAsCharArray(tmp);
				for(int i=0;i<tmp.length;++i) 
					if(tmp[i] == '0')
						tmp[i] = '.';
				if(!ln.equals(new String(tmp))) {
					System.out.println("Error : " + ln + "\n" + new String(tmp));
					break;
				} else {
				//	System.out.print("Inp : " + ln + "\n");
					
					if(!Backtracker.callBackTrack(bd)) {
						System.out.println("Unsuccessful execution");
						break;
					}
					times.record(Backtracker.times);
					nodes.record(Backtracker.nodes);
					branch.record(Backtracker.branch.getMax());
					depth.record(Backtracker.tdepth.getMax());
				//	System.out.println(Backtracker.times);
				//	System.out.println(Backtracker.tdepth);
				//	System.out.println(Backtracker.branch);
					System.out.println(times + " \n" + nodes + "\n" + depth + "\n" + branch);
				}
				System.out.println(cn++);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to read input file");
		}


	}



}
