package com.example.microservice;

import com.example.microservice.controller.ShowGuiController;
import com.example.microservice.controller.ShowRestController;
import com.example.microservice.model.Show;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MicroserviceArchitectureTest {
    private JavaClasses importedClasses;

    @BeforeEach
    void init(){
        importedClasses=new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.example.microservice");
    }

    @Test
    void testFollowingLayersArchitecture(){
        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..controller")
                .layer("Service").definedBy("..service")
                .layer("Repository").definedBy("..repository")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller","Service")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
                .check(importedClasses);
    }

    @Test
    void controllerClassesShouldHaveNamesXController(){
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .haveSimpleNameEndingWith("Controller")
                .check(importedClasses);
    }

    @Test
    void repositoryClassesShouldHaveNamesXRepository(){
        classes()
                .that().resideInAPackage("..repository..")
                .should()
                .haveSimpleNameEndingWith("Repository")
                .check(importedClasses);
    }

    @Test
    void serviceClassesShouldHaveNamesXService(){
        classes()
                .that().resideInAPackage("..service..")
                .should()
                .haveSimpleNameEndingWith("Service")
                .check(importedClasses);
    }
    @Test
    void configurationClassesShouldHaveNamesXService(){
        classes()
                .that().resideInAPackage("..configuration..")
                .should()
                .haveSimpleNameEndingWith("Configuration")
                .check(importedClasses);
    }

    @Test
    void checkControllerAnotation(){
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .beAnnotatedWith(RestController.class)
                .orShould()
                .beAnnotatedWith(Controller.class)
                .andShould()
                .beAnnotatedWith(RequestMapping.class)
                .check(importedClasses);
    }

    @Test
    void checkModelAnotation(){
        classes()
                .that().resideInAPackage("..model..")
                .should()
                .beAnnotatedWith(Document.class)
                .check(importedClasses);
    }
    @Test
    void checkRepositoryAnotation(){
        classes()
                .that().resideInAPackage("..repository..")
                .should()
                .beAnnotatedWith(Repository.class)
                .check(importedClasses);
    }
    @Test
    void checkServiceAnotation(){
        classes()
                .that().resideInAPackage("..service..")
                .should()
                .beAnnotatedWith(Service.class)
                .check(importedClasses);
    }
    @Test
    public void testModelClassesContainFieldId() {
        Show show=new Show();
        try {
            Field privateFieldId = Show.class.getDeclaredField("id");
            privateFieldId.setAccessible(true);
            privateFieldId.set(show, "22easkdjadj1nasdk1en1nd12n41");
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testModelClassesContainFieldName() {
        Show show=new Show();
        try {
            Field privateFieldName = Show.class.getDeclaredField("name");
            privateFieldName.setAccessible(true);
            privateFieldName.set(show, "МвйстерШеф");
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testModelClassesContainDiscription() {
        Show show=new Show();
        try {
            Field privateFieldDescription = Show.class.getDeclaredField("description");
            privateFieldDescription.setAccessible(true);
            privateFieldDescription.set(show, "КУлінарне шоу");
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testModelClassesContainFieldCreatedAt() {
        Show show=new Show();
        try {
            Field privateFieldCreatedAt = Show.class.getDeclaredField("createdAt");
            privateFieldCreatedAt.setAccessible(true);
            privateFieldCreatedAt.set(show, LocalDateTime.now());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testModelClassesContainFieldUpdatedAt() {
        Show show=new Show();
        try {
            Field privateFieldUpdatedAt = Show.class.getDeclaredField("updatedAt");
            privateFieldUpdatedAt.setAccessible(true);
            privateFieldUpdatedAt.set(show, LocalDateTime.now());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testReturnTypeMethodGuiController() {
        Method[] methods = ShowGuiController.class.getDeclaredMethods();
        for (Method method : methods) {
            assertSame(method.getReturnType(), String.class, "");
        }
    }

    @Test
    void testReturnTypeMethodRestConrollerSayHallo(){
        try {
            Method method = ShowRestController.class.getDeclaredMethod("sayHello");
            assertEquals(String.class, method.getReturnType(), "");
        } catch (NoSuchMethodException  e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testReturnTypeMethodRestConrollerGetShows(){
        try {
            Method method = ShowRestController.class.getDeclaredMethod("getShows");
            assertEquals(List.class, method.getReturnType(), "");
        } catch (NoSuchMethodException  e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testReturnTypeMethodRestConrollerShowOne(){
        try {
            Method method = ShowRestController.class.getDeclaredMethod("showOne", String.class);
            assertEquals(Show.class, method.getReturnType(), "");
        } catch (NoSuchMethodException  e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testReturnTypeMethodRestConrollerDeleteOne(){
        try {
            Method method = ShowRestController.class.getDeclaredMethod("deleteOne", String.class);
            assertEquals(void.class, method.getReturnType(), "");
        } catch (NoSuchMethodException  e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testReturnTypeMethodRestConrollerCreate(){
        try {
            Method method = ShowRestController.class.getDeclaredMethod("create", Show.class);
            assertEquals(Show.class, method.getReturnType(), "");
        } catch (NoSuchMethodException  e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testReturnTypeMethodRestConrollerUpdate(){
        try {
            Method method = ShowRestController.class.getDeclaredMethod("update", Show.class);
            assertEquals(Show.class, method.getReturnType(), "");
        } catch (NoSuchMethodException  e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAutowiredAnitation(){
        noFields()
                .should()
                .beAnnotatedWith(Autowired.class)
                .check(importedClasses);
    }

}