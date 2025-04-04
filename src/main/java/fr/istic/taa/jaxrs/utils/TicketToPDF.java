package fr.istic.taa.jaxrs.utils;


import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.EvenementDTO;
import fr.istic.taa.jaxrs.service.business.EvenementService;

import java.io.ByteArrayOutputStream;

public class TicketToPDF {

	/**
	 * Generates a PDF document from a Ticket object.
	 *
	 * @param ticket The Ticket object to convert to PDF.
	 * @return A ByteArrayOutputStream containing the PDF data.
	 */

	public static ByteArrayOutputStream generateTicketPdf(Ticket ticket) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			PdfWriter writer = new PdfWriter(baos);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);

			EvenementService evenementService = new EvenementService();
			System.out.println("Ticket evenement id: " + ticket.getEvenement().getId());
			EvenementDTO evenement = evenementService.getEvenementById(ticket.getEvenement().getId());


			PdfFont font = PdfFontFactory.createFont();
			Paragraph title = new Paragraph("🎟️ Ticket de Concert")
					.setFont(font)
					.setFontSize(18)
					.setBold()
					.setFontColor(ColorConstants.BLUE)
					.setTextAlignment(TextAlignment.CENTER);
			document.add(title);

			document.add(new LineSeparator(new SolidLine()));

			Table table = new Table(new float[]{3, 7});
			table.setHorizontalAlignment(HorizontalAlignment.CENTER);
			table.setWidth(UnitValue.createPercentValue(100));

			table.addCell(getStyledCell("Événement :", true));
			table.addCell(getStyledCell(evenement.getNom(), false));

			table.addCell(getStyledCell("Date :", true));
			table.addCell(getStyledCell(evenement.getDate().toString(), false));

			table.addCell(getStyledCell("Lieu :", true));
			table.addCell(getStyledCell(evenement.getLieu(), false));

			table.addCell(getStyledCell("Artiste :", true));
			table.addCell(getStyledCell(evenement.getArtiste(), false));

			table.addCell(getStyledCell("Prix :", true));
			table.addCell(getStyledCell(ticket.getPrix() + "€", false));

			document.add(table);

			BarcodeQRCode qrCode = new BarcodeQRCode("Ticket ID: " + ticket.getId());
			Image qrCodeImage = new Image(qrCode.createFormXObject(pdf)).setWidth(100).setHeight(100);
			qrCodeImage.setTextAlignment(TextAlignment.CENTER);
			document.add(new Paragraph("\n").add(qrCodeImage).setTextAlignment(TextAlignment.CENTER));

			Paragraph message = new Paragraph("Merci pour votre achat !\nPrésentez ce ticket à l'entrée.")
					.setFontSize(12)
					.setItalic()
					.setTextAlignment(TextAlignment.CENTER);
			document.add(message);

			document.close();
			return baos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Cell getStyledCell(String text, boolean isHeader) {
		text = (text != null) ? text : "Non spécifié";

		Cell cell = new Cell().add(new Paragraph(text));
		cell.setPadding(5);
		if (isHeader) {
			cell.setBold();
			cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
		}
		return cell;
	}

}
