package com.gmail.kovalev.util.saver;

import java.io.File;

public interface Saver {
    void save(File filePath, String fileName, String[] strings);
}
