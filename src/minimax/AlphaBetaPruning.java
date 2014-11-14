package minimax;


public class AlphaBetaPruning {
	static String max = "";
	static String min = "";
	static String visited = "";
	public static void alphaBetaSearch(State current) {
		System.out.println("ROOT NODE: "+maxValue(current, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println("MAX: "+max);
		System.out.println("MIN: "+min);
		System.out.println(visited);
		max = min = visited = "";
	}

	private static int maxValue(State s, int alpha, int beta){
		int tmp=0;
		Actions action=null;
		if(terminalTest(s)){
			visited+=s.toString()+" ";
			return utility(s);
		}
		int v = Integer.MIN_VALUE;
		for(Actions a: Actions.values()){
			tmp=v;
			v = Math.max(v, minValue(results(s, a), alpha, beta));
			if(tmp!=v)
				action=a;
			if(v >= beta){
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		max+=s.toString()+": "+action;
		return v;
	}

	private static int minValue(State s, int alpha, int beta){
		int tmp=0;
		Actions action=null;
		if(terminalTest(s)){
			visited+=s.toString()+" ";
			return utility(s);
		}
		int v = Integer.MAX_VALUE;
		for(Actions a: Actions.values()){
			tmp=v;
			v = Math.min(v, maxValue(results(s, a), alpha, beta));
			if(tmp!=v)
				action=a;
			if(v <= alpha){
				return v;
			}
			beta = Math.min(beta, v);
		}
		min+=s.toString()+": "+action.toString();
		return v;
	}

	private static State results(State s, Actions a) {
		if(a==Actions.LEFT)
			return s.leftChild;
		else if(a==Actions.MID)
			return s.midChild;
		else
			return s.rightChild;
	}

	private static int utility(State s) {
		return s.value;
	}

	private static boolean terminalTest(State s) {
		if(s.hasNoChildren())
			return true;
		else 
			return false;
	}
}
