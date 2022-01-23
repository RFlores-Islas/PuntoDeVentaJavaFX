package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class GraficoDAO {
	
	public static void graficar(String fecha) {
		Connection connection;
		Conexion conexion = new Conexion();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			String sql  = "SELECT total FROM ventas WHERE fecha = ?";
			connection = conexion.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fecha);
			resultSet = preparedStatement.executeQuery();
			DefaultPieDataset<String> defaultPieDataset = new DefaultPieDataset<String>();
			while(resultSet.next()) {
				defaultPieDataset.setValue(resultSet.getString("total"), resultSet.getDouble("total"));
			}
			JFreeChart jFreeChart = ChartFactory.createPieChart("Reporte de Venta", defaultPieDataset);
            ChartFrame chartFrame = new ChartFrame("Total de Ventas por dia", jFreeChart);
            chartFrame.setSize(1000, 500);
            chartFrame.setLocationRelativeTo(null);
            chartFrame.setVisible(true);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}//FIN TRY/CATCH
	}//FIN METODO
}//FIN CLASE
