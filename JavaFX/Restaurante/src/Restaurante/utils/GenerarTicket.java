package Restaurante.utils;

import Restaurante.model.Producto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerarTicket {

    public void crearTicket(ObservableList<Producto> listaProductos, double total, int numMesa, String metodoPago) {
        try {
            String fechaNombre = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String nombreArchivo = "Ticket_Mesa" + numMesa + "_" + fechaNombre + ".pdf";

            String carpeta = "src/Restaurante/archivos_restaurante/TicketsRestaurante";
            String rutaCompleta = carpeta + File.separator + nombreArchivo;

            File folder = new File(carpeta);
            if (!folder.exists()) {
                folder.mkdirs(); // Crea la carpeta (y subcarpetas si hacen falta)
                System.out.println("Carpeta creada: " + carpeta);
            }

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(rutaCompleta));
            document.open();

            // Cabecera
            Font fuenteTitulo = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("RESTAURANTE CASA MANOLO", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Mesa: " + numMesa));
            document.add(new Paragraph("Fecha: " + new Date().toString()));
            document.add(new Paragraph("Método de pago: " + metodoPago.toUpperCase()));
            document.add(new Paragraph("------------------------------------------------"));

            // Tabla
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);

            tabla.addCell(crearCelda("Producto", true));
            tabla.addCell(crearCelda("Cant.", true));
            tabla.addCell(crearCelda("Total", true));

            for (Producto p : listaProductos) {
                tabla.addCell(crearCelda(p.getNombre(), false));
                tabla.addCell(crearCelda(String.valueOf(p.getCantidad()), false));
                double totalProd = p.getPrecio() * p.getCantidad();
                tabla.addCell(crearCelda(String.format("%.2f €", totalProd), false));
            }
            document.add(tabla);

            // Total
            document.add(new Paragraph("------------------------------------------------"));
            Paragraph pTotal = new Paragraph("TOTAL A PAGAR: " + String.format("%.2f €", total));
            pTotal.setAlignment(Element.ALIGN_RIGHT);
            document.add(pTotal);

            document.add(new Paragraph(" "));
            Paragraph pie = new Paragraph("¡Gracias por su visita!");
            pie.setAlignment(Element.ALIGN_CENTER);
            document.add(pie);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PdfPCell crearCelda(String texto, boolean esHeader) {
        PdfPCell celda = new PdfPCell(new Phrase(texto));
        celda.setBorder(Rectangle.NO_BORDER);
        if (esHeader) celda.setPaddingBottom(10);
        return celda;
    }
}
