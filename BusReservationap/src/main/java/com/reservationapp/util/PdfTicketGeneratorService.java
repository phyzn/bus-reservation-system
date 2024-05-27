package com.reservationapp.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.reservationapp.entity.Passenger;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfTicketGeneratorService {

    public byte[] generateTicket(Passenger passenger,String fromLocation,
                                 String toLocation,String fromDate)  {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc,baos);
            doc.open();
            doc.add(new Paragraph("Ticket Details"));
            doc.add(new Paragraph("Passenger ID: " + passenger.getId()));
            doc.add(new Paragraph("Name: " + passenger.getFirstName() + " " + passenger.getLastName()));
            doc.add(new Paragraph("Email: " + passenger.getEmail()));
            doc.add(new Paragraph("Mobile: " + passenger.getMobile()));
            doc.add(new Paragraph("Bus ID: " + passenger.getBusId()));
            doc.add(new Paragraph("Route ID: " + passenger.getRouteId()));
            doc.add(new Paragraph("From Location"+fromLocation));
            doc.add(new Paragraph("To Location"+toLocation));
            doc.add(new Paragraph("From Date"+fromDate));

            doc.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
