package ru.dreamer;

import java.util.*;
import java.util.stream.Collectors;

public class Homework {

    /**
     * Используя классы Person и Department, реализовать методы ниже:
     */

    private static class Person {
        private String name;
        private int age;
        private double salary;
        private Department department;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    ", department=" + department +
                    '}';
        }
    }

    private static class Department {
        private String name;

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Department that = (Department) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    /**
     * Найти количество сотрудников, старше x лет с зарплатой больше, чем d
     */
    static int countPersons(List<Person> persons, int x, double d) {
        // TODO: Реализовать метод
        try {
            return (int) persons.stream()
                    .filter(p -> p.getAge() > x && p.getSalary() > d)
                    .count();
        }catch (UnsupportedOperationException e){
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Найти среднюю зарплату сотрудников, которые работают в департаменте X
     */
    static OptionalDouble averageSalary(List<Person> persons, int x) {
        // TODO: Реализовать метод
        try {
            return persons.stream()
                    .filter(p -> p.getDepartment() != null && p.getDepartment().getName().equals(x))
                    .mapToDouble(Person::getSalary)
                    .average();
        }catch (UnsupportedOperationException e){
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> persons) {
        // TODO: Реализовать метод
        try {
            return persons.stream().collect(Collectors.groupingBy(Person::getDepartment)).
                    entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().collect(Collectors.toList())));
        }catch (UnsupportedOperationException e){
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Найти максимальные зарплаты по отделам
     */
    static Map<Department, Double> maxSalaryByDepartment(List<Person> persons) {
        // TODO: Реализовать метод

        try {
            return persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.maxBy(Comparator.
                    comparingDouble(Person::getSalary)))).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get().getSalary()));
        }catch (UnsupportedOperationException e){
            throw new UnsupportedOperationException();
        }
    }

    /**
     * ** Сгруппировать имена сотрудников по департаментам
     */
    static Map<Department, List<String>> groupPersonNamesByDepartment(List<Person> persons) {
        // TODO: Реализовать метод
        try {
            return persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.mapping(Person::getName, Collectors.toList())));
        }catch (UnsupportedOperationException e){
            throw new UnsupportedOperationException();
        }
    }

    /**
     * ** Найти сотрудников с минимальными зарплатами в своем отделе
     */
    static List<Person> minSalaryPersons(List<Person> persons) {
        // TODO: Реализовать метод
        // В каждом департаменте ищем сотрудника с минимальной зарплатой.
        // Всех таких сотрудников собираем в список и возвращаем из метода.
        try {
            return persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.minBy(Comparator.
                    comparingDouble(Person::getSalary)))).entrySet().stream().map(e -> e.getValue().get()).collect(Collectors.toList());
        }catch (UnsupportedOperationException e){
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Person person = new Person();
        Department department = new Department("IT");
        person.setDepartment(department);
        person.setName("Ivan");
        person.setAge(25);
        person.setSalary(1000);


        Person person2 = new Person();
        person2.setDepartment(department);
        person2.setName("Petr");
        person2.setAge(30);
        person2.setSalary(2000);

        Person person3 = new Person();
        person3.setDepartment(department);
        person3.setName("Sidor");
        person3.setAge(35);
        person3.setSalary(3000);

        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person2);
        persons.add(person3);

        System.out.println(countPersons(persons, 30, 2000));
        System.out.println(averageSalary(persons, department.hashCode()));
        System.out.println(groupByDepartment(persons));
        System.out.println(maxSalaryByDepartment(persons));
        System.out.println(groupPersonNamesByDepartment(persons));
        System.out.println(minSalaryPersons(persons));
    }

}