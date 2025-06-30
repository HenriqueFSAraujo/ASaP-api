package pdev.com.agenda.util;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FlywayRepairRunner {

    private final Flyway flyway;

    public FlywayRepairRunner(Flyway flyway) {
        this.flyway = flyway;
    }

    @PostConstruct
    public void repair() {
        System.out.println("🚧 Executando flyway.repair() para corrigir checksum...");
        flyway.repair();
        System.out.println("✅ Flyway repair concluído com sucesso.");
    }
}