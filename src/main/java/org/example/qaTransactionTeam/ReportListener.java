package org.example.qaTransactionTeam;

import org.testng.*;

public class ReportListener implements ISuiteListener,ITestListener {
    private int passedTests = 0;

    @Override
    public void onTestSuccess(ITestResult result) {
        passedTests++;
    }

    @Override
    public void onFinish(ISuite suite) {
        int totalTests = suite.getAllMethods().size();

        double passedPercent = (passedTests * 100.0) / totalTests;

        System.out.println("Всего тестов: " + totalTests);
        System.out.println("Пройдено: " + passedTests);
        System.out.printf("Процент пройденных тестов: %.2f%%\n", passedPercent);
    }
}
