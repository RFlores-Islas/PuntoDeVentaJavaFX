package io.diamons.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.filechooser.FileSystemView;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import io.diamons.modelo.dao.Conexion;

public class Pdf {
	
	Connection connection;
	Conexion conexion = new Conexion();
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public void pdfVenta(int idVenta, int cliente, double total, String usuario) {
		try {
			Date date = new Date();
			FileOutputStream archivo;
			String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
			File salida = new File(url + "venta.pdf");
			archivo = new FileOutputStream(salida);
			Document document = new Document();
			PdfWriter.getInstance(document, archivo);
			document.open();
			Image img = Image.getInstance(getClass().getResource("/Img/logo_pdf.png"));
			//Fecha
			Paragraph fecha = new Paragraph();
			Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
			fecha.add(Chunk.NEWLINE);
			fecha.add("Vendedor: " + usuario + "\nFolio: " + idVenta + "\nFecha: "
					+ new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
			PdfPTable Encabezado = new PdfPTable(4);
			Encabezado.setWidthPercentage(100);
			Encabezado.getDefaultCell().setBorder(0);
			float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
			Encabezado.setWidths(columnWidthsEncabezado);
			Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
			Encabezado.addCell(img);
			Encabezado.addCell("");
			//info empresa
			String sqlConfiguracion = "SELECT * FROM configuracion";
			String mensaje = "";
			try {
				connection = conexion.getConnection();
				preparedStatement = connection.prepareStatement(sqlConfiguracion);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					mensaje = resultSet.getString("mensaje");
					Encabezado.addCell("RFC:    " + resultSet.getString("rfc") + "\nNombre: " + resultSet.getString("nombre") + "\nTelefono: " + resultSet.getString("telefono") + "\nDireccion: " + resultSet.getString("direccion") + "\n\n");
				}
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
			//
			Encabezado.addCell(fecha);
			document.add(Encabezado);
			//cliente
			Paragraph paragraph = new Paragraph();
			paragraph.add(Chunk.NEWLINE);
			paragraph.add("DATOS DEL CLIENTE" + "\n\n");
			document.add(paragraph);

			PdfPTable proveedor = new PdfPTable(3);
			proveedor.setWidthPercentage(100);
			proveedor.getDefaultCell().setBorder(0);
			float[] columnWidthsCliente = new float[]{50f, 25f, 25f};
			proveedor.setWidths(columnWidthsCliente);
			proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell clienteNombre = new PdfPCell(new Phrase("Nombre", negrita));
			PdfPCell clienteTelefono = new PdfPCell(new Phrase("Telefono", negrita));
			PdfPCell clienteDireccion = new PdfPCell(new Phrase("Direccion", negrita));
			clienteNombre.setBorder(Rectangle.OUT_BOTTOM);
			clienteTelefono.setBorder(Rectangle.OUT_BOTTOM);
			clienteDireccion.setBorder(Rectangle.OUT_BOTTOM);
			proveedor.addCell(clienteNombre);
			proveedor.addCell(clienteTelefono);
			proveedor.addCell(clienteDireccion);
			String prove = "SELECT * FROM provedores WHERE id = ?";//SELECT * FROM clientes WHERE id = ?
			try {
				preparedStatement = connection.prepareStatement(prove);
				preparedStatement.setInt(1, cliente);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					proveedor.addCell(resultSet.getString("nombre"));
					proveedor.addCell(resultSet.getString("telefono"));
					proveedor.addCell(resultSet.getString("direccion") + "\n\n");
				} else {
					proveedor.addCell("Publico en General");
					proveedor.addCell("S/N");
					proveedor.addCell("S/N" + "\n\n");
				}

			} catch (SQLException e) {
				System.out.println(e.toString());
			}
			document.add(proveedor);

			PdfPTable tabla = new PdfPTable(4);
			tabla.setWidthPercentage(100);
			tabla.getDefaultCell().setBorder(0);
			float[] columnWidths = new float[]{10f, 50f, 15f, 15f};
			tabla.setWidths(columnWidths);
			tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell pdfCell1 = new PdfPCell(new Phrase("Cant.", negrita));
			PdfPCell pdfCell2 = new PdfPCell(new Phrase("Descripción.", negrita));
			PdfPCell pdfCell3 = new PdfPCell(new Phrase("P. unt.", negrita));
			PdfPCell pdfCell4 = new PdfPCell(new Phrase("P. Total", negrita));
			pdfCell1.setBorder(Rectangle.OUT_BOTTOM);
			pdfCell2.setBorder(Rectangle.OUT_BOTTOM);
			pdfCell3.setBorder(Rectangle.OUT_BOTTOM);
			pdfCell4.setBorder(Rectangle.OUT_BOTTOM);
			pdfCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pdfCell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pdfCell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pdfCell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tabla.addCell(pdfCell1);
			tabla.addCell(pdfCell2);
			tabla.addCell(pdfCell3);
			tabla.addCell(pdfCell4);
			String product = "SELECT detal.id, detal.id_pro, detal.id_venta, detal.precio, detal.cantidad, product.id, product.nombre FROM detalle detal INNER JOIN productos product ON detal.id_pro = product.id WHERE detal.id_venta = ?";
			try {
				preparedStatement = connection.prepareStatement(product);
				preparedStatement.setInt(1, idVenta);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					double subTotal = resultSet.getInt("cantidad") * resultSet.getDouble("precio");
					tabla.addCell(resultSet.getString("cantidad"));
					tabla.addCell(resultSet.getString("nombre"));
					tabla.addCell(resultSet.getString("precio"));
					tabla.addCell(String.valueOf(subTotal));
				}

			} catch (SQLException e) {
				System.out.println(e.toString());
			}
			document.add(tabla);
			Paragraph paragraphInfo = new Paragraph();
			paragraphInfo.add(Chunk.NEWLINE);
			paragraphInfo.add("Total S/: " + total);
			paragraphInfo.setAlignment(Element.ALIGN_RIGHT);
			document.add(paragraphInfo);
			Paragraph firma = new Paragraph();
			firma.add(Chunk.NEWLINE);
			firma.add("Cancelacion \n\n");
			firma.add("------------------------------------\n");
			firma.add("Firma \n");
			firma.setAlignment(Element.ALIGN_CENTER);
			document.add(firma);
			Paragraph gr = new Paragraph();
			gr.add(Chunk.NEWLINE);
			gr.add(mensaje);
			gr.setAlignment(Element.ALIGN_CENTER);
			document.add(gr);
			document.close();
			archivo.close();
			Desktop.getDesktop().open(salida);
		} catch (DocumentException | IOException e) {
			System.out.println(e.toString());
		}
	}
}//FIN CLASE
