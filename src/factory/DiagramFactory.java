package factory;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import exception.RuleChecker;
import handler.MouseEventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import lib.Entity;
import lib.transition.Transition;

/**
 * @author laki
 */
public class DiagramFactory {

    public static void createStage(Stage primaryStage, Collection<Entity> entities, Collection<Transition> transitions, boolean wantExportButtons) {
        Group root = new Group();

        // add transitions
        for (Transition transition : transitions) {
            RuleChecker.checkTransitionRules(transition);
            root.getChildren().add(transition.getTransitionView());
        }
        MouseEventHandler.setTransitions(transitions);

        // add entities
        for (Entity entity : entities) {
            RuleChecker.checkEntityRules(entity);
            entity.getEntityGroup().setOnMousePressed(MouseEventHandler.onMousePressedEventHandler);
            entity.getEntityGroup().setOnMouseDragged(MouseEventHandler.onMouseEntityDraggedEventHandler);
            root.getChildren().add(entity.getEntityGroup());
        }

        Scene scene = new Scene(createScrollPane(root), 600, 600);
        if (wantExportButtons) {
            root.getChildren().add(createPngButton(scene));
            root.getChildren().add(createPdfButton(scene));
            root.getChildren().add(createXmlButton(entities, transitions));
        }
        primaryStage.setScene(scene);
    }

    private static Button createPngButton(Scene scene) {
        Button buttonPng = new Button("export as png");
        buttonPng.setTooltip(new Tooltip("Click to save scene as png image in root of project."));
        buttonPng.setOnAction((ActionEvent event) -> {
            WritableImage img = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
            scene.snapshot(img);
            File file = new File("ssa.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
            } catch (Exception s) {
            }
        });
        buttonPng.setTranslateX(200);
        buttonPng.setTranslateY(150);
        return buttonPng;
    }

    private static Button createPdfButton(Scene scene) {
        Button buttonPdf = new Button("export as pdf");
        buttonPdf.setTooltip(new Tooltip("Click to save scene as pdf file in root of project."));
        buttonPdf.setOnAction((ActionEvent event) -> {
            WritableImage img = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
            scene.snapshot(img);
            try {
                File imgFile = new File("ssaImageForPdf.png");
                ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", imgFile);
                File file = new File("ssa.pdf");
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                
                Image image = Image.getInstance(imgFile.getName());
                double imgW = image.getPlainWidth();
                double imgH = image.getPlainHeight();
                image.scaleAbsolute(500, (int)(imgH / imgW * 500));
                
                document.add(image);
                document.close();
            } catch (Exception e) {
            }
        });
        buttonPdf.setTranslateX(400);
        buttonPdf.setTranslateY(150);
        return buttonPdf;
    }

    private static Button createXmlButton(Collection<Entity> entities, Collection<Transition> transitions) {
        Button buttonXml = new Button("export as xml");
        buttonXml.setTooltip(new Tooltip("Click to save scene as xml file in root of project."));
        buttonXml.setOnAction((ActionEvent event) -> {
            File file = new File("ssa.xml");
            try {
                PrintWriter pw = new PrintWriter(file);
                pw.write("<SSA>");
                pw.write("<Entities>");
                for (Entity entity : entities) {
                    pw.write("<Entity>");
                    pw.write("<Name>");
                    pw.write(entity.getName());
                    pw.write("</Name>");
                    pw.write("<Type>");
                    pw.write(entity.getClass().getSimpleName());
                    pw.write("</Type>");
                    pw.write("</Entity>");
                }
                pw.write("</Entities>");
                pw.write("<Transitions>");
                for (Transition transition : transitions) {
                    pw.write("<Transition>");
                    pw.write("<Name>");
                    pw.write(transition.getName());
                    pw.write("</Name>");
                    pw.write("<Type>");
                    pw.write(transition.getClass().getSimpleName());
                    pw.write("</Type>");
                    pw.write("<EntityFrom>");
                    pw.write(transition.getEntityFrom().getName());
                    pw.write("</EntityFrom>");
                    pw.write("<EntityTo>");
                    pw.write(transition.getEntityTo().getName());
                    pw.write("</EntityTo>");
                    pw.write("</Transition>");
                }
                pw.write("</Transitions>");
                pw.write("</SSA>");
                pw.close();
            } catch (FileNotFoundException ex) {
            }
        });
        buttonXml.setTranslateX(600);
        buttonXml.setTranslateY(150);
        return buttonXml;
    }

    private static ScrollPane createScrollPane(Group root) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setOnScroll(MouseEventHandler.onMouseScrolledEventHandler);

        flowPane.getChildren().add(root);

        scrollPane.setContent(flowPane);
        return scrollPane;
    }

}
