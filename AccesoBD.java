import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccesoBD {
public static void insertarProvincias(Map<String, String> mapa_provincias)
{
	try {
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdaemet", "root", "");
		Statement stmt=c.createStatement();
		for(String clave:mapa_provincias.keySet())
		{
			String sql="Insert into provincias values('"+clave+"', '"+mapa_provincias.get(clave)+"')";
			stmt.executeUpdate(sql);
		}
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static Map<String,String> recuperarProvincias()
{
	Map<String, String> mapa=new HashMap();
	Connection c;
	try {
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdaemet", "root", "");
		Statement stmt=c.createStatement();
		String query="SELECT * from PROVINCIAS";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next())
		{
			String codigo=rs.getString("codigo");
			String nombre=rs.getString("nombre");
			mapa.put(codigo, nombre);
		}
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return mapa;
}
public static ArrayList<Municipio> recuperarMunicipios(String codigo_provincia) {
	ArrayList<Municipio> lista_municipios=new ArrayList();
	Connection c;
	try {
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdaemet", "root", "");
		Statement stmt=c.createStatement();
		String query="SELECT * FROM municipios WHERE codigo_provincia='"+codigo_provincia+"'";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next())
		{
			String codigo=rs.getString("codigo");
			String nombre=rs.getString("nombre");
			Municipio m=new Municipio();
			Loine l=new Loine();
			l.setCodigo(Integer.parseInt(codigo));
			m.setNombre(nombre);
			m.setLoine(l);
			lista_municipios.add(m);
		}
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return lista_municipios;
}
public static void insertarMunicipios(ClaseMunicipiero m, String codigo_provincia) {
	try {
		List<Municipio> lista_muncipios=m.getLista_municipios();
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdaemet", "root", "");
		Statement stmt=c.createStatement();
		for(Municipio muni:lista_muncipios)
		{
			String sql="Insert into municipios values('"+muni.getLoine().getCodigo()+"', '"+
		muni.getNombre()+"','"+codigo_provincia+"')";
			stmt.executeUpdate(sql);
		}
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
