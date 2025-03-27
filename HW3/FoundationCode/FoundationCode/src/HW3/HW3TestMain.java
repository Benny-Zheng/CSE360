package HW3;

/**
 * HW3TestMain.java
 * 
 * This class contains five automated tests based on TP2 submission.
 * It demonstrates how to use manual test mainlines for checking logic and GUI interaction.
 */
public class HW3TestMain {

    public static void main(String[] args) {
        System.out.println("=================== HW3 Automated Tests ===================");

        testPasswordEvaluator();
        testUserNameRecognizer();
        testFirstPage();
        testInvitationPage();
        testAskQuestionPage();

        System.out.println("===================== End of Tests ========================");
    }

    /**
     * Test 1: Validate password using PasswordEvaluator
     */
    public static void testPasswordEvaluator() {
        System.out.println("Test 1: PasswordEvaluator");
        String input = "Aa1!abcd";
        String result = application.PasswordEvaluator.evaluatePassword(input);
        System.out.println("Input: " + input);
        System.out.println("Expected: Valid (empty string), Actual: " + result);
        System.out.println(result.isEmpty() ? "PASS" : "FAIL");
        System.out.println();
    }

    /**
     * Test 2: Check valid username using UserNameRecognizer
     */
    public static void testUserNameRecognizer() {
        System.out.println("Test 2: UserNameRecognizer");
        String input = "Test_User.1";
        String result = application.UserNameRecognizer.checkForValidUserName(input);
        System.out.println("Input: " + input);
        System.out.println("Expected: Valid (empty string), Actual: " + result);
        System.out.println(result.isEmpty() ? "PASS" : "FAIL");
        System.out.println();
    }

    /**
     * Test 3: Simulate loading FirstPage and check console test output
     */
    public static void testFirstPage() {
        System.out.println("Test 3: FirstPage Title Label");
        javafx.application.Platform.startup(() -> {
            application.FirstPage page = new application.FirstPage(new databasePart1.DatabaseHelper());
            javafx.stage.Stage testStage = new javafx.stage.Stage();
            page.show(testStage);
            System.out.println("Check console output for: 'Test: Verify Continue Button Text'");
        });
    }

    /**
     * Test 4: Generate invitation code using InvitationPage logic
     */
    public static void testInvitationPage() {
        System.out.println("Test 4: InvitationPage Code Generation");
        databasePart1.DatabaseHelper db = new databasePart1.DatabaseHelper();
        String code = db.generateInvitationCode();
        System.out.println("Generated Code: " + code);
        System.out.println(code.length() == 4 ? "PASS" : "FAIL");
        System.out.println();
    }

    /**
     * Test 5: Simulate adding a question with AskQuestionPage logic
     */
    public static void testAskQuestionPage() {
        System.out.println("Test 5: AskQuestionPage Question Submission");
        application.QuestionsManager qm = application.QuestionsManager.getInstance();
        int before = qm.getQuestions().size();
        qm.addQuestion("Test Question", "What is Java?", "Tester");
        int after = qm.getQuestions().size();
        System.out.println("Before: " + before + ", After: " + after);
        System.out.println(after > before ? "PASS" : "FAIL");
        System.out.println();
    }
}
