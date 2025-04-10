package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class QuestionsManagerStaffTest {

    private QuestionsManager manager;

    @BeforeEach
    public void setup() {
        manager = QuestionsManager.getInstance();

        // 清空旧数据（如果需要）
        manager.getAllReviews().clear();  
    }

    @Test
    public void testAddAndDeleteReview() {
        manager.addReview("alice", 1, -1, "Nice post.");
        List<Review> all = manager.getAllReviews();
        assertEquals(1, all.size());

        int id = all.get(0).getReviewId();
        manager.deleteReview(id);
        assertEquals(0, manager.getAllReviews().size());
    }

    @Test
    public void testRestoreReview() {
        manager.addReview("bob", 2, -1, "Needs improvement.");
        int id = manager.getAllReviews().get(0).getReviewId();

        manager.deleteReview(id);
        assertEquals(0, manager.getAllReviews().size());

        manager.restoreReview(id);
        assertEquals(1, manager.getAllReviews().size());
    }

    @Test
    public void testBanUser() {
        manager.banUser("offender");
        assertTrue(manager.isUserBanned("offender"));

        manager.banUser("offender"); // 再次尝试重复添加
        assertEquals(1, manager.getBannedUserCount());

    }

}
