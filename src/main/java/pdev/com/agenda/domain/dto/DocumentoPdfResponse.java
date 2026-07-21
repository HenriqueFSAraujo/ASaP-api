package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DocumentoPdfResponse {

    private Long id;
    private String nomeArquivo;
    private String conteudoBase64;
    private Long userId;
    private String documentType;
    private String mimeType;
}
