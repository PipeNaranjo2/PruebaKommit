import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase Principal, la cual halla si hay o no ciclos dentro de un grafo dirigido
 * @author Pipe Naranjo
 * @Date: 15/04/2021
 *
 */
public class Prueba {

	private static String archivo = "C:\\Users\\Pipe Naranjo\\eclipse-workspace\\PruebaKommit\\src\\input.txt";

	/**
	 * Metodo main que se encarga de leer el archivo input.txt y hacer sus respectivas acciones
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			FileReader fileReader = new FileReader(new File(archivo));

			BufferedReader buffer = new BufferedReader(fileReader);

			ArrayList<String> lineas = new ArrayList<>();

			String linea = "";

			while ((linea = buffer.readLine()) != null) {
				lineas.add(linea);
			}

			int i = -1;
			boolean ward = false;
			for (i = 0; i < lineas.size() && !ward; i++) {
				String nodos[] = lineas.get(i).split(",");
				if (nodos[0].equals(nodos[1])) {
					ward = true;
				} else {
					ward = hallarCiclo(lineas, lineas.get(i), i, lineas.get(i));
				}

			}

			if (ward && i != -1) {
				System.err.println("Si hay ciclos");
			} else {
				System.err.println("No hay Ciclos");
			}
			fileReader.close();
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * Metodo recursivo que halla si hay un ciclo dentro del grafo
	 * @param lineas
	 * @param inicial
	 * @param i
	 * @param actual
	 * @return
	 */
	public static boolean hallarCiclo(List<String> lineas, String inicial, int i, String actual) {

		String[] cadenaActual = actual.split(",");
		String[] cadenaInicial = inicial.split(",");
		List<String> auxiliar = buscarCamino(lineas, cadenaActual[1]);
		boolean resul = false;
		if (i != lineas.size()) {

			if (cadenaInicial[0].equals(cadenaActual[1])) {
				return true;
			} else {

				for (int j = 0; j < auxiliar.size() && !resul; j++) {
					String aux = auxiliar.get(j);
					if (cadenaActual[0].equals(aux.split(",")[1])) {
						resul = true;
					} else {
						resul = hallarCiclo(lineas, inicial, j, aux);
					}

				}
			}
		}

		return resul;

	}
	
	/**
	 * Método que halla el camino hacia donde puede dirigirse dentro del grafo
	 * @param lineas
	 * @param nodo
	 * @return
	 */

	public static List<String> buscarCamino(List<String> lineas, String nodo) {
		List<String> resul = new ArrayList<>();

		for (int i = 0; i < lineas.size(); i++) {
			String cadena = lineas.get(i).split(",")[0];
			if (nodo.equals(cadena)) {
				resul.add(lineas.get(i));
			}
		}

		return resul;
	}

}
