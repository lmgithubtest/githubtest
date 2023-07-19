module githubtest.m {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires java.prefs;
    requires org.slf4j;
    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires uk.co.xfactorylibrarians.coremidi4j;

    opens githubtest.m to com.fasterxml.jackson.databind;
    opens githubtest.m.engine to javafx.fxml, com.fasterxml.jackson.databind;
    opens githubtest.m.ui to javafx.fxml; //, org.testfx.junit5;
    exports githubtest.m.ui;
    exports githubtest.javafxutil;
}