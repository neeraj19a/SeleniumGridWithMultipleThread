package main.java.com.seleniumgridmultiplethread.project;
import java.util.List;
import lombok.Data;
import net.rcarz.jiraclient.Comment;

@Data
public class JiraIssueDetails {

    private String issueKey;
    private String issueSummary;
    private String issueDescription;
    private List<Comment> comments;
}

