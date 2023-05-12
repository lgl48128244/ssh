package com.market.project.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author liglo
 * @date 2023/5/11 16:45
 */
public class Demo implements Comparable<Demo> {
    private Integer id;
    private Integer age;
    private String name;

    public Demo() {
    }

    public Demo(Integer id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Demo o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Demo demo = (Demo) o;
        return id.equals(demo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    static List<Demo> list = new ArrayList<>();

    public static List<Demo> list() {
        if (list.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                list.add(new Demo(i + 1, Integer.parseInt(RandomStringUtils.randomNumeric(2)), RandomStringUtils.randomAlphanumeric(5)));
            }
        }
        Collections.sort(list);
        return list;
    }

    public static Demo getById(Integer id) {
        return list.get(id - 1);
    }

    public static void add(Demo demo) {
        demo.setId(list.size() + 1);
        list.add(demo);
    }

    public static void update(Demo demo) {
        list.remove(demo);
        list.add(demo);
    }

    public static void remove(Demo demo) {
        list.remove(demo);
    }
}