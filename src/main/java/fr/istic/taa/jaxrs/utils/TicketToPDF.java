package fr.istic.taa.jaxrs.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
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
			// Création du document
			Document document = new Document();

			PdfWriter.getInstance(document, baos);

			document.open();

			EvenementService evenementService = new EvenementService();
			System.out.println("Ticket evenement id: " + ticket.getEvenement().getId());
			EvenementDTO evenement = evenementService.getEvenementById(ticket.getEvenement().getId());

			Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
			Paragraph title = new Paragraph("🎟️ Ticket de Concert", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			document.add(new Chunk(new LineSeparator()));

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);

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

			BarcodeQRCode qrCode = new BarcodeQRCode("Ticket ID: " + ticket.getId(), 100, 100, null);
			Image qrCodeImage = qrCode.getImage();
			qrCodeImage.setAlignment(Element.ALIGN_CENTER);
			qrCodeImage.scaleToFit(100f, 100f);
			document.add(qrCodeImage);

			Font messageFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
			Paragraph message = new Paragraph("Merci pour votre achat !\nPrésentez ce ticket à l'entrée.", messageFont);
			message.setAlignment(Element.ALIGN_CENTER);
			document.add(message);

			document.close();

			return baos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	private static PdfPCell getStyledCell(String text, boolean isHeader) {
		text = (text != null) ? text : "Non spécifié";

		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setPadding(5);
		if (isHeader) {
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setPhrase(new Phrase(text, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
		}
		return cell;
	}
}
