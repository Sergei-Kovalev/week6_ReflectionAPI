package com.gmail.kovalev.util.saver;

import java.io.File;

public class Context {
    private Saver saver;

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    public void executeSaving(File filePath, String fileName, String[] strings) {
        saver.save(filePath, fileName, strings);
    }
}
