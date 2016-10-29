package core.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import core.lib.Entity;
import core.lib.Transition;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 * @author laki
 */
public class DefaultExportManager implements ExportManager {

    boolean png;
    boolean pdf;
    boolean xml;
    int nodes = 0;

    public DefaultExportManager(boolean png, boolean pdf, boolean xml) {
        this.png = png;
        this.pdf = pdf;
        this.xml = xml;
    }

    @Override
    public List<Node> getExportButtons(Scene scene, Collection<Entity> entities, Collection<Transition> transitions) {
        List<Node> nodes = new ArrayList<>();
        if(png) nodes.add(createPngButton(scene));
        if(pdf) nodes.add(createPdfButton(scene));
        if(xml) nodes.add(createXmlButton(entities, transitions));
        return nodes;
    }

    private Button createPngButton(Scene scene) {
        nodes++;
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
        buttonPng.setTranslateX(calcX());
        buttonPng.setTranslateY(150);
        return buttonPng;
    }

    private Button createPdfButton(Scene scene) {
        nodes++;
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
                image.scaleAbsolute(500, (int) (imgH / imgW * 500));

                document.add(image);
                document.close();
            } catch (Exception e) {
            }
        });
        buttonPdf.setTranslateX(calcX());
        buttonPdf.setTranslateY(150);
        return buttonPdf;
    }

    // export as some kind of xml, currently works for ssa
    private Button createXmlButton(Collection<Entity> entities, Collection<Transition> transitions) {
        nodes++;
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
        buttonXml.setTranslateX(calcX());
        buttonXml.setTranslateY(150);
        return buttonXml;
    }

    private int calcX() {
        return nodes * 200;
    }

}
