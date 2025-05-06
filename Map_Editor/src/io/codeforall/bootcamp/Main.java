package io.codeforall.bootcamp;

import io.codeforall.bootcamp.utils.KeyboardController;

public class Main {

    public static void main(String[] args) {

        MapEditor mapEditor = new MapEditor(20, 20);
        new KeyboardController(mapEditor);

    }
}
