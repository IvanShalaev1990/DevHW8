package org.example.util;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Util {
    @SneakyThrows
    public String readStringFromFile(final String path){
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()){
            sb.append(scanner.nextLine());
        }
        return String.valueOf(sb);
    }
    public List<String> listSQLCommandsFromFile(final String path){
        return Arrays.stream(readStringFromFile(path).split(";"))
                .map(it -> it + ";")
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.listSQLCommandsFromFile("sql/find_longest_project.sql").forEach(System.out::println);
    }
}
