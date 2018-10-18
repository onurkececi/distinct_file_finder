package distinctImsi;

public class DistinctFinder {
	public static void main(String[] args) {

		DistinctImsiOperation distict = new DistinctImsiOperation();
		
		if (args.length < 2) {
			System.out.println(
					"Input variable is not satisfied! Provide the folder path! Ex: /argela/data/imsi_values distinct_imsi.txt 0");
			return;
		}

		boolean operationMode = false;
		if (args.length == 3) {
			operationMode = true;
		}
		distict.findDistinctValuesThenExport(args[0], args[1], operationMode);

	}

}
