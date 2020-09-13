package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.Common;
import utils.EmailUtils;

import java.io.IOException;

public class TestBase {
    private static Logger log = LogManager.getLogger(TestBase.class);
    private static EmailUtils emailUtils;

    @BeforeSuite
    public void beforeSuite() throws IOException {
    }

    @BeforeClass
    public void beforeClass() throws Exception {
            try {
                emailUtils = new EmailUtils("jabatalktestemail@gmail.com", "Test@123", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }
        Common.Init();
    }

    @AfterClass
    public void close() {
        Common.closeBrowser();
    }
}
