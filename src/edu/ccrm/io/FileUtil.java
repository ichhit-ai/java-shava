
package edu.ccrm.io;

import edu.ccrm.domain.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Simple CSV import/export and backup using NIO.2
 */
public class FileUtil {

    public static List<Map<String,String>> importCsv(Path p) throws IOException {
        if(!Files.exists(p)) return Collections.emptyList();
        return Files.lines(p).skip(1) // skip header
            .map(line -> {
                String[] parts = line.split(",");
                Map<String,String> m = new HashMap<>();
                for(int i=0;i<parts.length;i++) m.put("c"+i, parts[i].trim());
                return m;
            }).collect(Collectors.toList());
    }

    public static void exportSimpleCsv(Path p, List<String> header, List<List<String>> rows) throws IOException {
        Files.createDirectories(p.getParent());
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(",", header)).append(System.lineSeparator());
        for(List<String> r: rows) sb.append(String.join(",", r)).append(System.lineSeparator());
        Files.writeString(p, sb.toString());
    }

    public static Path backupFolder(Path base) throws IOException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        Path dest = base.resolve("backup_" + LocalDateTime.now().format(f));
        Files.createDirectories(dest);
        return dest;
    }

    public static long folderSize(Path dir) throws IOException {
        if(!Files.exists(dir)) return 0L;
        return Files.walk(dir).filter(p->Files.isRegularFile(p)).mapToLong(p-> {
            try { return Files.size(p);} catch(Exception ex){ return 0L; }
        }).sum();
    }
}
