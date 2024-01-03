package org.InarAcademy.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SimpleITestListener implements ITestListener {

    private static final ExtentReports extent = new ExtentReports();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("extent.html");
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        extentTest.get().pass("Test ahahahahahahaha");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finished Test Suite: " + context.getName());
        extent.flush();
    }
}