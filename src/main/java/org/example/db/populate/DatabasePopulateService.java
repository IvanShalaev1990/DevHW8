package org.example.db.populate;

import lombok.SneakyThrows;
import org.example.db.Database;
import org.example.dto.ClientDTO;
import org.example.dto.ProjectDTO;
import org.example.dto.ProjectWorkerDTO;
import org.example.dto.WorkerDTO;
import org.example.enums.Level;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DatabasePopulateService {
    public static final String WORKER_STATEMENT = "INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES(?,?,?,?)";
    public static final String CLIENT_STATEMENT = "INSERT INTO client (NAME) VALUES(?)";
    public static final String PROJECT_STATEMENT = "INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES(?,?,?)";
    public static final String PROJECT_WORKER_STATEMENT = "INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES(?,?)";
    private PreparedStatement preparedStatement;

    public void insertInToProjectWorker(List<ProjectWorkerDTO> projectWorkers) {
        try {
            preparedStatement = Database
                    .getInstance()
                    .getConnection()
                    .prepareStatement(PROJECT_WORKER_STATEMENT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (ProjectWorkerDTO projectWorker : projectWorkers) {
            try {
                preparedStatement.setInt(1, projectWorker.getProjectID());
                preparedStatement.setInt(2, projectWorker.getWorkerID());
                preparedStatement.addBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void insertInToProject(List<ProjectDTO> projects) {
        try {
            preparedStatement = Database
                    .getInstance()
                    .getConnection()
                    .prepareStatement(PROJECT_STATEMENT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (ProjectDTO project : projects) {
            try {
                preparedStatement.setInt(1, project.getClientID());
                preparedStatement.setDate(2, Date.valueOf(project.getStartDate()));
                preparedStatement.setDate(3, Date.valueOf(project.getFinishDate()));
                preparedStatement.addBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void insertInToClient(List<ClientDTO> clients) {
        try {
            preparedStatement = Database
                    .getInstance()
                    .getConnection()
                    .prepareStatement(CLIENT_STATEMENT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (ClientDTO client : clients) {
            try {
                preparedStatement.setString(1, client.getName());
                preparedStatement.addBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void insertInToWorker(List<WorkerDTO> workers) {
        try {
            preparedStatement = Database
                    .getInstance()
                    .getConnection()
                    .prepareStatement(WORKER_STATEMENT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (WorkerDTO worker : workers) {
            try {
                preparedStatement.setString(1, worker.getName());
                preparedStatement.setDate(2, Date.valueOf(worker.getBirthday()));
                preparedStatement.setString(3, worker.getLevel().getLevel());
                preparedStatement.setInt(4, worker.getSalary());
                preparedStatement.addBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @SneakyThrows
    public static void main(String[] args) {
        List<WorkerDTO> workerList = List.of(WorkerDTO.builder()
                        .name("Rick Sanchez")
                        .birthday(LocalDate.of(1945, 1, 1))
                        .level(Level.SENIOR)
                        .salary(10000)
                        .build(),
                WorkerDTO.builder()
                        .name("Morty Smith")
                        .birthday(LocalDate.of(2002, 7, 3))
                        .level(Level.TRAINEE)
                        .salary(950)
                        .build(),
                WorkerDTO.builder()
                        .name("Summer Smith")
                        .birthday(LocalDate.of(2000, 3, 3))
                        .level(Level.MIDDLE)
                        .salary(3500)
                        .build(),
                WorkerDTO.builder()
                        .name("Beth Smith")
                        .birthday(LocalDate.of(1970, 11, 17))
                        .level(Level.SENIOR)
                        .salary(7500)
                        .build(),
                WorkerDTO.builder()
                        .name("Jerry Smith")
                        .birthday(LocalDate.of(1968, 5, 5))
                        .level(Level.JUNIOR)
                        .salary(300)
                        .build(),
                WorkerDTO.builder()
                        .name("Birdperson")
                        .birthday(LocalDate.of(1978, 8, 25))
                        .level(Level.MIDDLE)
                        .salary(5000)
                        .build(),
                WorkerDTO.builder()
                        .name("Squanchy")
                        .birthday(LocalDate.of(1980, 12, 10))
                        .level(Level.TRAINEE)
                        .salary(1100)
                        .build(),
                WorkerDTO.builder()
                        .name("Mr. Meeseeks")
                        .birthday(LocalDate.of(2010, 2, 20))
                        .level(Level.JUNIOR)
                        .salary(800)
                        .build(),
                WorkerDTO.builder()
                        .name("Unity")
                        .birthday(LocalDate.of(1985, 4, 15))
                        .level(Level.MIDDLE)
                        .salary(4000)
                        .build(),
                WorkerDTO.builder()
                        .name("Evil Morty")
                        .birthday(LocalDate.of(2002, 7, 1))
                        .level(Level.SENIOR)
                        .salary(10000)
                        .build()
        );
        List<ClientDTO> clientsList = List.of(ClientDTO.builder()
                        .name("Philip J. Fry")
                        .build(),
                ClientDTO.builder()
                        .name("Turanga Leela")
                        .build(),
                ClientDTO.builder()
                        .name("Bender Bending Rodriguez")
                        .build(),
                ClientDTO.builder()
                        .name("Professor Hubert J. Farnsworth")
                        .build(),
                ClientDTO.builder()
                        .name("Amy Wong")
                        .build()
        );
        List<ProjectDTO> projectList = List.of(ProjectDTO.builder()
                        .clientID(1)
                        .startDate(LocalDate.of(2023, 1, 1))
                        .finishDate(LocalDate.of(2023, 2, 28))
                        .build(),
                ProjectDTO.builder()
                        .clientID(2)
                        .startDate(LocalDate.of(2023, 3, 15))
                        .finishDate(LocalDate.of(2023, 5, 30))
                        .build(),
                ProjectDTO.builder()
                        .clientID(3)
                        .startDate(LocalDate.of(2023, 6, 1))
                        .finishDate(LocalDate.of(2023, 8, 15))
                        .build(),
                ProjectDTO.builder()
                        .clientID(4)
                        .startDate(LocalDate.of(2023, 9, 1))
                        .finishDate(LocalDate.of(2023, 11, 30))
                        .build(),
                ProjectDTO.builder()
                        .clientID(5)
                        .startDate(LocalDate.of(2023, 1, 1))
                        .finishDate(LocalDate.of(2023, 3, 31))
                        .build(),
                ProjectDTO.builder()
                        .clientID(3)
                        .startDate(LocalDate.of(2024, 4, 15))
                        .finishDate(LocalDate.of(2024, 6, 30))
                        .build(),
                ProjectDTO.builder()
                        .clientID(1)
                        .startDate(LocalDate.of(2024, 7, 1))
                        .finishDate(LocalDate.of(2024, 9, 30))
                        .build(),
                ProjectDTO.builder()
                        .clientID(2)
                        .startDate(LocalDate.of(2024, 10, 15))
                        .finishDate(LocalDate.of(2025, 1, 15))
                        .build(),
                ProjectDTO.builder()
                        .clientID(5)
                        .startDate(LocalDate.of(2025, 2, 1))
                        .finishDate(LocalDate.of(2025, 4, 30))
                        .build(),
                ProjectDTO.builder()
                        .clientID(1)
                        .startDate(LocalDate.of(2025, 5, 15))
                        .finishDate(LocalDate.of(2025, 8, 15))
                        .build());
        List<ProjectWorkerDTO> projectWorkerList = List.of(ProjectWorkerDTO.builder()
                        .projectID(1)
                        .workerID(1)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(1)
                        .workerID(2)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(1)
                        .workerID(3)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(1)
                        .workerID(4)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(1)
                        .workerID(5)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(2)
                        .workerID(5)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(2)
                        .workerID(6)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(3)
                        .workerID(7)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(3)
                        .workerID(8)
                        .build(), ProjectWorkerDTO.builder()
                        .projectID(4)
                        .workerID(9)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(5)
                        .workerID(10)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(5)
                        .workerID(1)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(5)
                        .workerID(2)
                        .build(), ProjectWorkerDTO.builder()
                        .projectID(6)
                        .workerID(3)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(6)
                        .workerID(4)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(7)
                        .workerID(5)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(8)
                        .workerID(6)
                        .build(), ProjectWorkerDTO.builder()
                        .projectID(8)
                        .workerID(7)
                        .build(),
                ProjectWorkerDTO.builder()
                        .projectID(9)
                        .workerID(8)
                        .build(), ProjectWorkerDTO.builder()
                        .projectID(10)
                        .workerID(8)
                        .build());
        DatabasePopulateService dbPopulate = new DatabasePopulateService();
        dbPopulate.insertInToWorker(workerList);
        dbPopulate.insertInToClient(clientsList);
        dbPopulate.insertInToProject(projectList);
        dbPopulate.insertInToProjectWorker(projectWorkerList);
    }
}
