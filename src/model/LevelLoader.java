package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {
    private final List<char[][]> levels = new ArrayList<>();

    public LevelLoader(Path levels) {
        StringBuilder strings = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            String s;
            while ((s = reader.readLine()) != null) {
                strings.append(s);
                strings.append("\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл");
            System.exit(1);
        }
        String[] levelStrings = new String(strings).split("\\*{37}");
        for (String levelString : levelStrings) {
            int width = 0;
            int height = 0;
            String[] parts = levelString.split("\n{2}");
            if (parts.length >= 2) {
                String[] lines = parts[0].split("\n");
                for (String line : lines) {
                    if (line.contains("Size X:")) {
                        width = Integer.parseInt(line.substring(8));
                    }
                    if (line.contains("Size Y:")) {
                        height = Integer.parseInt(line.substring(8));
                    }
                }
                char[][] level = new char[height][width];
                lines = parts[1].split("\n");
                int i = 0;
                for (String line : lines) {
                    if (i < height) {
                        for (int j = 0; j < width; j++) {
                            level[i][j] = line.charAt(j);
                        }
                    }
                    i++;
                }
                this.levels.add(level);
            }
        }
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        char[][] currentLevel = levels.get(level - 1);
        for (int i = 0; i < currentLevel.length; i++)
            for (int j = 0; j < currentLevel[0].length; j++) {
                int x = Model.FIELD_CELL_SIZE / 2 + j * Model.FIELD_CELL_SIZE;
                int y = Model.FIELD_CELL_SIZE / 2 + i * Model.FIELD_CELL_SIZE;
                switch (currentLevel[i][j]) {
                    case 'X' -> walls.add(new Wall(x, y));
                    case '*' -> boxes.add(new Box(x, y));
                    case '.' -> homes.add(new Home(x, y));
                    case '&' -> {
                        boxes.add(new Box(x, y));
                        homes.add(new Home(x, y));
                    }
                    case '@' -> player = new Player(x, y);
                }
            }
        return new GameObjects(walls, boxes, homes, player);
    }
}
