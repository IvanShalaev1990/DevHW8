package org.example.db.migration;

import lombok.RequiredArgsConstructor;
import org.example.constance.Constance;
import org.example.util.PropertiesUtil;
import org.flywaydb.core.Flyway;

public class FlywayMigration {
    private Flyway flyway;


    public FlywayMigration(String url, String user, String password) {
        flyway = Flyway.configure().dataSource(
                        url,
                        user,
                        password)
                .load();
        flyway.migrate();
    }

}
