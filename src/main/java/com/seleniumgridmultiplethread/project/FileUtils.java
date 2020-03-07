package main.java.com.seleniumgridmultiplethread.project;

import static org.apache.commons.io.FileUtils.listFiles;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.stream.Stream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;

public class FileUtils {
    public static String executeFromJira = "false";

    //TODO
  /*  public static String readFileUTF8(String filePath) {
        String fileData = "";
        try {
            URL resource = Resources.getResource(filePath);
            URI uri = resource.toURI();

            fileData = new String(Files.readAllBytes(Paths.get(uri)), StandardCharsets.UTF_8);
        } catch (MalformedURLException e) {
            throw new UtilitiesException("Malformed URL Exception", e);
        } catch (URISyntaxException e) {
            throw new UtilitiesException("URI Syntax Exception", e);
        } catch (IOException e) {
            throw new UtilitiesException("IO Exception", e);
        }
        return fileData;
    }

    public static String readVxoLocaleFile() {
        String locale = DataStore.get("locale");
        if ((locale == null) || locale.equalsIgnoreCase("default")) {
            locale = "en-US";
        }
        return readFileUTF8("locale_translation/locales/" + locale + "/translation.json");
    }*/

    //TODO
   /* public static String readVxoLocaleFile(String locale) {
        return readFileUTF8("locale_translation/locales/" + locale + "/translation.json");
    }
*/
    public static void clearOutputs() {
        File[] filesDel = {
                new File("target/output")
                , new File("output")
                , new File("video")
                , new File("VideoRecordings")
        };
        Stream.of(filesDel).forEach(file -> {
            try {
                org.apache.commons.io.FileUtils.deleteDirectory(file);
                org.apache.commons.io.FileUtils.forceMkdir(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //TODO
   /* public static String jsonTranslation(String locale, String key) {
        String jsonContent = readVxoLocaleFile(locale);
        return JsonRetriever.get(jsonContent, key).toString();
    }
*/

    public static void zipReport() throws IOException, ArchiveException {
        //Change Source and Destination , they are just sample data
        File source = new File("target/output/ExecReport.zip");
        File destination = new File("target/output/ExecReport.zip");
        destination.delete();
        addFilesToZip(source, destination);
        addJiraExecReportZip();
    }

    public static void addJIRAcomment(String comment) {
        JiraUtils jiraUtils = new JiraUtils();
        jiraUtils.addComment(comment);
    }

    private static void addFilesToZip(File source, File destination)
            throws IOException, ArchiveException {
        OutputStream archiveStream = new FileOutputStream(destination);
        ArchiveOutputStream archive = new ArchiveStreamFactory()
                .createArchiveOutputStream(ArchiveStreamFactory.ZIP, archiveStream);

        Collection<File> fileList = listFiles(source, null, true);

        for (File file : fileList) {
            String entryName = getEntryName(source, file);
            ZipArchiveEntry entry = new ZipArchiveEntry(entryName);
            archive.putArchiveEntry(entry);

            BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

            IOUtils.copy(input, archive);
            input.close();
            archive.closeArchiveEntry();
        }

        archive.finish();
        archiveStream.close();
    }

    private static String getEntryName(File source, File file) throws IOException {
        int index = source.getAbsolutePath().length() + 1;
        String path = file.getCanonicalPath();

        return path.substring(index);
    }

    public static void addJiraExecReportZip() {
        File zipFile = new File("target/output/ExecReport.zip");
        if (zipFile.exists() && executeFromJira.equalsIgnoreCase("true")) {
            JiraUtils jiraUtils = new JiraUtils();
            System.out.println("Zip file exists");
            jiraUtils.addAttachment(zipFile);
        } else {
            System.out.println("Zip file doesn't exists. Revisit code logic to upload attachment");
        }
    }
}
