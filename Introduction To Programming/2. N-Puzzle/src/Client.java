
public class Client {

	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]);
		int d = Integer.parseInt(args[1]);

//		Text Based
		if (d == 1) {
			String mode = Library.mode();

			if (mode.equals("1")) {
				int NPuzzle[][] = Library.initializePuzzle(N);
				System.out.print("Give level of difficulty:");
				int k = StdIn.readInt();
				System.out.println("*******************************");
				System.out.println("\n");
				NPuzzle = Library.shufflePuzzle(NPuzzle, k);
				while (Library.isSolution(NPuzzle)) {
					NPuzzle = Library.shufflePuzzle(NPuzzle, k);
				}
				Library.displayPuzzle(NPuzzle);
				System.out.println("\n");
				Library.play(N, NPuzzle);

			} else if (mode.equals("2")) {
				System.out.print("Give kmin: ");
				int kmin = StdIn.readInt();
				System.out.print("Give kmax: ");
				int kmax = StdIn.readInt();
				System.out.print("Give p: ");
				int p = StdIn.readInt();
				System.out.print("Give q: ");
				int q = StdIn.readInt();

				int NPuzzle[][] = new int[N][N];
				Library.automaticPlay(NPuzzle, kmin, kmax, p, q);

			}
		}
//		Graphics Based
		else if (d == 2) {
			Graphics.graphicsOutput(N);
		}
	}

}
