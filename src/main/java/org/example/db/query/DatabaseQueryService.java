package org.example.db.query;

import lombok.SneakyThrows;
import org.example.db.Database;
import org.example.dto.*;
import org.example.util.Util;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static final String FIND_MAX_PROJECTS_CLIENT_PATH = "sql/find_max_projects_client.sql";
    public static final String FIND_LONGEST_PROJECT_PATH = "sql/find_longest_project.sql";
    public static final String FIND_MAX_SALARY_WORKER_PATH = "sql/find_max_salary_worker.sql";
    public static final String FIND_YOUNGEST_ELDEST_WORKERS_PATH = "sql/find_youngest_eldest_workers.sql";
    public static final String PRINT_PROJECT_PRICE_PATH = "sql/print_project_prices.sql";
    private Util util;

    public DatabaseQueryService() {
        this.util = new Util();
    }
    @SneakyThrows
    public List<YoungestEldestWorker> findYoungestEldestWorker() {
        List<YoungestEldestWorker> list = new ArrayList<>();
        Statement statement = Database.getInstance()
                .getConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery(util.readStringFromFile(FIND_YOUNGEST_ELDEST_WORKERS_PATH));
        while (resultSet.next()) {
            list.add(YoungestEldestWorker.builder()
                    .type(resultSet.getString("type"))
                    .name(resultSet.getString("name"))
                    .birthday(resultSet.getTimestamp("birthday").toLocalDateTime().toLocalDate())
                    .build());
        }
        return list;
    }
    @SneakyThrows
    public List<ProjectPrices> projectPrices() {
        List<ProjectPrices> list = new ArrayList<>();
        Statement statement = Database.getInstance()
                .getConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery(util.readStringFromFile(PRINT_PROJECT_PRICE_PATH));
        while (resultSet.next()) {
            list.add(ProjectPrices.builder()
                    .name(resultSet.getInt("name"))
                    .price(resultSet.getInt("price"))
                    .build());
        }
        return list;
    }
    @SneakyThrows
    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> list = new ArrayList<>();
        Statement statement = Database.getInstance()
                .getConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery(util.readStringFromFile(FIND_MAX_SALARY_WORKER_PATH));
        while (resultSet.next()) {
            list.add(MaxSalaryWorker.builder()
                    .name(resultSet.getString("name"))
                    .salary(resultSet.getInt("salary"))
                    .build());
        }
        return list;
    }

    @SneakyThrows
    public List<LongestProject> findLongestProject() {
        List<LongestProject> list = new ArrayList<>();
        Statement statement = Database.getInstance()
                .getConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery(util.readStringFromFile(FIND_LONGEST_PROJECT_PATH));
        while (resultSet.next()) {
            list.add(LongestProject.builder()
                    .name(resultSet.getInt("name"))
                    .monthCount(resultSet.getInt("month_count"))
                    .build());
        }
        return list;
    }

    @SneakyThrows
    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> list = new ArrayList<>();
        Statement statement = Database.getInstance()
                .getConnection()
                .createStatement();
        ResultSet resultSet = statement.executeQuery(util.readStringFromFile(FIND_MAX_PROJECTS_CLIENT_PATH));
        while (resultSet.next()) {
            list.add(MaxProjectCountClient.builder()
                    .name(resultSet.getString("client_name"))
                    .projectCount(resultSet.getInt("project_count"))
                    .build());
        }
        return list;
    }

    public static void main(String[] args) {
        DatabaseQueryService dbQueryService = new DatabaseQueryService();
        dbQueryService.findMaxProjectsClient().forEach(System.out::println);
        dbQueryService.findLongestProject().forEach(System.out::println);
        dbQueryService.findMaxSalaryWorker().forEach(System.out::println);
        dbQueryService.projectPrices().forEach(System.out::println);
        dbQueryService.findYoungestEldestWorker().forEach(System.out::println);
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().findMaxProjectsClient();
        List<LongestProject> longestProjects = new DatabaseQueryService().findLongestProject();
        List<MaxSalaryWorker> maxSalaryWorker = new DatabaseQueryService().findMaxSalaryWorker();
        List<ProjectPrices> projectPrices = new DatabaseQueryService().projectPrices();
        List<YoungestEldestWorker> youngestEldestWorker = new DatabaseQueryService().findYoungestEldestWorker();
    }
}
