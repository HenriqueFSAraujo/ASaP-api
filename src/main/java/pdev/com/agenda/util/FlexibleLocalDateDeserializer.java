package pdev.com.agenda.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class FlexibleLocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final List<DateTimeFormatter> DATE_FORMATS = List.of(
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("d/M/uuuu")
    );

    public FlexibleLocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getValueAsString();
        if (value == null || value.isBlank()) {
            return null;
        }

        String normalized = value.trim();
        for (DateTimeFormatter format : DATE_FORMATS) {
            try {
                return LocalDate.parse(normalized, format);
            } catch (DateTimeParseException ignored) {
                // Try the other supported date formats.
            }
        }

        try {
            return OffsetDateTime.parse(normalized).toLocalDate();
        } catch (DateTimeParseException ignored) {
            try {
                return LocalDateTime.parse(normalized).toLocalDate();
            } catch (DateTimeParseException exception) {
                throw JsonMappingException.from(parser,
                        "Data inválida. Formatos aceitos: yyyy-MM-dd, data/hora ISO-8601 ou dd/MM/yyyy.",
                        exception);
            }
        }
    }
}
