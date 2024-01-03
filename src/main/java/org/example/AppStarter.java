package org.example;

import org.example.constance.Constance;
import org.example.db.migration.FlywayMigration;
import org.example.util.PropertiesUtil;

public class AppStarter {
    private FlywayMigration flywayMigration;
    public AppStarter(){
        flywayMigration = new FlywayMigration( PropertiesUtil.get(Constance.DB_URL),
                PropertiesUtil.get(Constance.DB_USER_NAME),
                PropertiesUtil.get(Constance.DB_PASSWORD));
    }
}
