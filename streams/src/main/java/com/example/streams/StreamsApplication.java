package com.example.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamsApplication {

  public static void main(String[] args) {
    List<Person> persons = getPersons();

    //IMPERATIVE APPROACH ❌
    List<Person> females = new ArrayList<>();
    for (Person person : persons) {
      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }
    females.forEach(System.out::println);
    System.out.println();

    //DECLARATIVE APPROACH ✅
    //Filter
    List<Person> filtered = persons.stream()
        .filter(person -> person.getGender().equals(Gender.FEMALE))
        .collect(Collectors.toList());
    System.out.println("Filtered Persons:");
    filtered.forEach(System.out::println);
    System.out.println();

    //Sort
    List<Person> sorted = persons.stream()
        .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
        .collect(Collectors.toList());
    System.out.println("Sorted Persons:");
    sorted.forEach(System.out::println);
    System.out.println();

    //All match
    boolean allMatch = persons.stream()
        .allMatch(person -> person.getAge() > 5);
    System.out.println("All match:");
    System.out.println(allMatch);
    System.out.println();

    //Any match
    boolean anyMatch = persons.stream()
        .anyMatch(person -> person.getAge() > 120);
    System.out.println("Any match:");
    System.out.println(anyMatch);
    System.out.println();

    //None match
    boolean noneMatch = persons.stream()
        .noneMatch(person -> person.getName().equals("Antonio"));
    System.out.println("None match:");
    System.out.println(noneMatch);
    System.out.println();

    //Max
    persons.stream()
        .max(Comparator.comparing(Person::getAge))
        .ifPresent(System.out::println);
    System.out.println();


    //Min
    persons.stream()
        .min(Comparator.comparing(Person::getAge))
        .ifPresent(System.out::println);
    System.out.println();


    //Group
    Map<Gender, List<Person>> genderListMap = persons.stream()
        .collect(Collectors.groupingBy(Person::getGender));

    genderListMap.forEach((gender, personList) -> {
      System.out.println(gender);
      personList.forEach(System.out::println);
      System.out.println();
    });

    //All together
    Optional<String> oldestFemaleAge = persons.stream()
        .filter(person -> person.getGender().equals(Gender.FEMALE))
        .max(Comparator.comparing(Person::getAge))
        .map(Person::getName);

    oldestFemaleAge.ifPresent(System.out::println);
  }

  private static List<Person> getPersons() {
    return List.of(
        new Person("James Bond", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }

}
