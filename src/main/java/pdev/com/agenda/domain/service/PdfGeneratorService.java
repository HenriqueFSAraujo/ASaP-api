package pdev.com.agenda.domain.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.entity.ParecerSocioeconomico;
import pdev.com.agenda.domain.repository.ParecerSocioeconomicoRepository;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PdfGeneratorService {

    private final ParecerSocioeconomicoRepository parecerRepository;

    public String generateParecerPdfBase64(Long parecerId) throws Exception {
        Optional<ParecerSocioeconomico> optional = parecerRepository.findById(parecerId);
        if (optional.isEmpty()) {
            throw new Exception("Parecer não encontrado");
        }

        ParecerSocioeconomico parecer = optional.get();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font fieldFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("PARECER SOCIOECONÔMICO", titleFont));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Nome do Aluno: " + parecer.getNomeAluno(), fieldFont));
        document.add(new Paragraph("Data de Nascimento: " + parecer.getDataNascimentoAluno(), fieldFont));
        document.add(new Paragraph("Segmento: " + parecer.getSegmentoCursar2025(), fieldFont));
        document.add(new Paragraph("Nome do Responsável: " + parecer.getNomeResponsavel(), fieldFont));
        document.add(new Paragraph("CPF do Responsável: " + parecer.getCpfResponsavel(), fieldFont));
        document.add(new Paragraph("Telefone do Responsável: " + parecer.getTelefoneResponsavel(), fieldFont));
        document.add(new Paragraph("Renda Bruta Familiar: " + parecer.getRendaBrutaFamiliar(), fieldFont));
        document.add(new Paragraph("Renda per Capita: " + parecer.getRendaPerCapita(), fieldFont));
        document.add(new Paragraph("Percentual LC187: " + parecer.getPercentualLc187(), fieldFont));
        document.add(new Paragraph("Resultado: " + parecer.getResultadoSocioeconomico(), fieldFont));

        document.close();

        byte[] pdfBytes = out.toByteArray();
        return Base64.getEncoder().encodeToString(pdfBytes);
    }
}