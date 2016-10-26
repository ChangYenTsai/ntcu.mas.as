package forhelp;

public class Split {
	public static void main (String[] args) {
		Split s = new Split();
		s.split("  haha  ha    is ha.  d   .");
	}
	
	public void split(String str){
		String[] strs = str.split("\\s+");
		for (String strr: strs) {
			System.out.print(strr);
		}
	}
	
	
}
