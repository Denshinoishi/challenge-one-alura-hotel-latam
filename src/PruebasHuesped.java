import java.util.List;

import com.alura.connectionfactory.ConnectionFactory;
import com.alura.dao.HuespedesDAO;
import com.alura.model.Huesped;

public class PruebasHuesped {
	
	public static void main(String[] args) {
		HuespedesDAO huespedesDAO = new HuespedesDAO(new ConnectionFactory().recuperarConexion());
		List<Huesped> resultado = huespedesDAO.listar();
		for (Huesped huesped : resultado) {
			System.out.println(huesped);
		}
		
	}

}
