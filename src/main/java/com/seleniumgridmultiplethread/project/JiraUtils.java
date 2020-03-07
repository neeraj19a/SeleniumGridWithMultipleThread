package main.java.com.seleniumgridmultiplethread.project;



import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

import java.io.File;

public class JiraUtils {

    private String featureBasePath = "src/test/resources/com.visa/vxo/ui/features/Destination/";
    private String bddExtn = ".feature";
    private String username = "masadhas";
    private String password = "";
    public static String issueID = "VCO-12363";
    private BasicCredentials creds = null;
    private JiraClient jira = null;
    private JiraIssueDetails issueDetails = null;

    public JiraUtils() {
        initJiraConn();
    }

    public void initJiraConn() {
        creds = new BasicCredentials(username, password);
        jira = new JiraClient("https://jira.trusted.visa.com/", creds);
        issueDetails = new JiraIssueDetails();
    }

    public JiraIssueDetails getJiraIssueDetails() {
        issueDetails = new JiraIssueDetails(); // Using the above created Class which has all information about //the JIRA ticket

        //TODO get issueID
       // String issueID = DataStore.get("issueID");
        String issueID = "issueID";

        try {
            Issue issue = jira.getIssue(issueID);
            issueDetails.setIssueKey(issueID);
            issueDetails.setIssueSummary(issue.getSummary());
            issueDetails.setIssueDescription(issue.getDescription());
            issueDetails.setComments(issue.getComments());
        } catch (JiraException e) {
            System.out.println(e.getMessage());
        }
        return issueDetails;
    }

    public void createLocalFeatureFiles(JiraIssueDetails issueDetails) {
        String bddContent = issueDetails.getIssueDescription();
        String featureFile = featureBasePath + issueDetails.getIssueKey() + bddExtn;
        File file = new File(featureFile);
        if (file.exists()) {
            //TODO
            // FileUtils.deleteQuietly(file);
        }
        //TODO
        // FileUtils.writeStringToFile(file, bddContent, StandardCharsets.UTF_8);
    }

    public void addComment(String comment) {

        //TODO get issueID
        String issueID ="issueID";
        try {
            Issue issue = jira.getIssue(issueID);
            issue.addComment(comment);
        } catch (JiraException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAttachment(File execReport) {
        try {
            Issue issue = jira.getIssue(issueID);
            issue.addAttachment(execReport);
            System.out.println("Execution report attached to JIRA sucessfully");
        } catch (JiraException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        JiraUtils jiraUtils = new JiraUtils();
        JiraIssueDetails jiraIssueDetails = jiraUtils.getJiraIssueDetails();
        jiraUtils.createLocalFeatureFiles(jiraIssueDetails);
        File zipFile = new File("target/output/ExecReport.zip");
        jiraUtils.addAttachment(zipFile);
        String comment = "RXO - Consumer Automation Execution - completed. PFA the execution report ( Please ignore - POC)";
        jiraUtils.addComment(comment);
    }
}

