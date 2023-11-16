package com.gmail.kovalev.cache.impl;

import com.gmail.kovalev.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LFUCacheTest {

    private Cache<Integer, String> cache;

    @BeforeEach
    void setUp() {
        cache = new LFUCache<>(3);
        cache.set(1, "Hello");      // removed (capacity)
        cache.set(2, "World");
        cache.set(3, "Hi");
        cache.set(4, "Bro");
        cache.get(3);
        cache.get(4);
        // в итоге количество обращений ключ:количество будет выглядеть так: 2:1, 3:2, 4:2
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 1})
    void get(int key) {
        // given
        String expected = "Hi";

        // when
        String actual = cache.get(key);

        // then
        if (key == 3) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).isNull();                // при key = 1 null потому что уже вышел из-за меньшего счетчика
        }
    }

    @ParameterizedTest
    @MethodSource("getArgsForSetMethodTests")
    void set(int key, String value) {

        // given, when
        cache.set(key, value);
        String actual = cache.get(key);
        String actual2 = cache.get(2);

        // then
        if (key == 5) {                             // добавляется новый - удаляется с меньшим счётчиком (с индексом 2)
            assertThat(actual).isEqualTo(value);
            assertThat(actual2).isNull();
        } else {                                    // меняется старый - с индексом 2 всё еще в коллекции
            assertThat(actual).isEqualTo(value);
            assertThat(actual2).isNotNull();
        }
    }

    @Test
    void remove() {

        // given, when
        cache.remove(2);
        String actual = cache.get(2);

        // then
        assertThat(actual).isNull();
    }

    public static Stream<Arguments> getArgsForSetMethodTests() {
        return Stream.of(
                Arguments.of(5, "New Message"),         // для проверки на добавление нового ключ-значение
                Arguments.of(3, "Change Message")       // для проверки на изменение объекта по ключу
        );
    }
}