package pdev.com.agenda.domain.service;

import com.lowagie.text.Document;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.entity.ParecerSocioeconomico;
import pdev.com.agenda.domain.repository.ParecerSocioeconomicoRepository;
import pdev.com.agenda.util.PdfUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Base64;
import java.util.Map;

@Service
@AllArgsConstructor
public class ParecerPdfService {

    private final ParecerSocioeconomicoRepository repository;

    public String gerarPdfBase64(Long id) throws Exception {
        ParecerSocioeconomico parecer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parecer não encontrado"));

        String html = loadAndFillTemplate("templates/parecer-socioeconomico.html", PdfUtil.getDadosMap(parecer));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(html));

        document.close();

        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    private String loadAndFillTemplate(String path, Map<String, String> valores) throws IOException {
        InputStream inputStream = new ClassPathResource(path).getInputStream();
        String template = new String(inputStream.readAllBytes());
        for (Map.Entry<String, String> entry : valores.entrySet()) {
            template = template.replace("[[" + entry.getKey() + "]]", entry.getValue());
        }
        return template;
    }
}