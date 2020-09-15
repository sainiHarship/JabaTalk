package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.Common;
import utils.EmailUtils;

import javax.mail.MessagingException;
import java.io.IOException;

public class TestBase {
    private static Logger log = LogManager.getLogger(TestBase.class);
    private static EmailUtils emailUtils;

    @BeforeSuite
    public void beforeSuite() throws IOException, MessagingException {
        EmailUtils.connect();
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        Common.Init();
    }

    @AfterClass
    public void close() {
        Common.closeBrowser();
    }
}
