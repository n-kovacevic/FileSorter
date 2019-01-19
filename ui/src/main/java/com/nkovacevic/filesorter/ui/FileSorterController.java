package com.nkovacevic.filesorter.ui;

import com.nkovacevic.filesorter.core.Destination;
import com.nkovacevic.filesorter.core.FileSorter;
import com.nkovacevic.filesorter.core.Source;
import com.nkovacevic.filesorter.core.local.LocalDirectoryDestination;
import com.nkovacevic.filesorter.core.local.LocalDirectorySource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FileSorterController {

    @FXML
    private Label sourceDirLabel, destinationDirLabel;
    @FXML
    private Pane rootPane;
    @FXML
    private Button runSortButton;


    public void selectSourceDir() {
        showDirectoryChooser(sourceDirLabel);
    }

    private void showDirectoryChooser(Label sourceDirLabel) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File sourceDir = directoryChooser.showDialog(rootPane.getScene().getWindow());
        if (sourceDir != null)
            sourceDirLabel.setText(sourceDir.getAbsolutePath());
        validateDirectories();
    }


    public void selectDestinationDir() {
        showDirectoryChooser(destinationDirLabel);
    }

    public void runSort() {
        Map<String, Collection<String>> config = loadConfig();
        Source source = new LocalDirectorySource(Paths.get(sourceDirLabel.getText()));
        Destination destination = new LocalDirectoryDestination(Paths.get(destinationDirLabel.getText()));
        FileSorter sorter = new FileSorter(source, destination, config);
        sorter.sort();
    }

    private Map<String, Collection<String>> loadConfig(){
        HashMap<String, Collection<String>> config = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("config.xml");
            NodeList nodeList = document.getElementsByTagName("directory");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String directoryName= element.getAttributes().getNamedItem("name").getNodeValue();
                LinkedList<String> extensions = new LinkedList<>();
                NodeList childNodes = element.getElementsByTagName("extension");
                for (int j = 0; j < childNodes.getLength(); j++) {
                    extensions.add(childNodes.item(j).getTextContent().trim());
                }
                config.put(directoryName, extensions);
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return config;
    }

    private void validateDirectories() {
        runSortButton.setDisable(Files.notExists(Paths.get(sourceDirLabel.getText()))
                || Files.notExists(Paths.get(destinationDirLabel.getText())));
    }

}
