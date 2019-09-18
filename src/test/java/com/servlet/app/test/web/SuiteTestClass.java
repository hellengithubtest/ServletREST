package com.servlet.app.test.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClearTests.class,
        PersonControllerTests.class,
        CarControllerTests.class,
        StatisticsControllerTests.class} )
public class SuiteTestClass {

}