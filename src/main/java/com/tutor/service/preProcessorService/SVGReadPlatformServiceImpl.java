package com.tutor.service.preProcessorService;

import com.tutor.model.graphicalSVGObject.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class SVGReadPlatformServiceImpl implements SVGReadPlatformService {

    @Override
    public SVGImage parse(SVGImage svgImage, String svgfilepath) {
        String svgFile = null;
        try {
            svgFile = readSVGFile(svgfilepath);
        } catch (IOException e) {

        }
        InputSource source = new InputSource(new StringReader(svgFile));

        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList list;
        try {
            list = (NodeList)xPath.evaluate("//line", source, XPathConstants.NODESET);

            List<Element> lines = new ArrayList<>(list.getLength());
            for (int i = 0; i < list.getLength(); i++)
            {
                lines.add((Element)list.item(i));
            }

            source = new InputSource(new StringReader(svgFile));
            list = (NodeList)xPath.evaluate("//circle", source, XPathConstants.NODESET);

            List<Element> circles = new ArrayList<>(list.getLength());
            for (int i = 0; i < list.getLength(); i++)
            {
                circles.add((Element)list.item(i));
            }


            source = new InputSource(new StringReader(svgFile));
            list = (NodeList)xPath.evaluate("//ellipse", source, XPathConstants.NODESET);

            List<Element> ellipses = new ArrayList<>(list.getLength());
            for (int i = 0; i < list.getLength(); i++)
            {
                ellipses.add((Element)list.item(i));
            }

            source = new InputSource(new StringReader(svgFile));
            list = (NodeList)xPath.evaluate("//rect", source, XPathConstants.NODESET);

            List<Element> rectangles = new ArrayList<>(list.getLength());
            for (int i = 0; i < list.getLength(); i++)
            {
                rectangles.add((Element)list.item(i));
            }

            source = new InputSource(new StringReader(svgFile));
            list = (NodeList)xPath.evaluate("//g", source, XPathConstants.NODESET);
            List<Element> textg = new ArrayList<>(list.getLength());
            for (int i = 0; i < list.getLength(); i++)
            {
                textg.add((Element)list.item(i));
            }

            source = new InputSource(new StringReader(svgFile));
            list = (NodeList)xPath.evaluate("//g/text/tspan", source, XPathConstants.NODESET);
            List<Element> texts = new ArrayList<>(list.getLength());
            for (int i = 0; i < list.getLength(); i++)
            {
                texts.add((Element)list.item(i));
            }

//
//            source = new InputSource(new StringReader(svgFile));
//            list = (NodeList)xPath.evaluate("/svg", source, XPathConstants.NODESET);
//            Element svg = (Element)list.item(0);
//            svgImage.setSize(
//                    Integer.parseInt(svg.getAttribute("height")),
//                    Integer.parseInt(svg.getAttribute("width")));


            //System.out.println("No of Rectangles: " + rectangles.size());
            //System.out.println("No of Ellipses: " + ellipses.size());
            //System.out.println("No of Texts: " + texts.size());

            //svgImage = new SVGImage();
            String styl;
            String stkval;
            String trsfm;
            Double x1;
            Double x2;
            Double y1;
            Double y2;
            for (int i = 0; i < lines.size(); i++)
            {

                Element lineElement = lines.get(i);
               // System.out.println(i+"***"+lines.get(i).getAttribute("width"));
                //System.out.println(rectangles.get(i).getTagName());
                styl = lineElement.getAttribute("style");
                // Transform Operation
                trsfm=lineElement.getAttribute("transform");
                x1= Double.parseDouble(lineElement.getAttribute("x1"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[0]);
                x2 =Double.parseDouble(lineElement.getAttribute("x2"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[0]);
                y1= Double.parseDouble(lineElement.getAttribute("y1"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[1]);
                y2 =Double.parseDouble(lineElement.getAttribute("y2"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[1]);
                if(styl.equals(null)||styl.equals("")) {
                   stkval=lineElement.getAttribute("stroke-width");

                }else{
                   stkval =(styl.split("stroke-width")[1].split(";")[0]).replace(": ", "");
                }

                SVGLine line = new SVGLine(x1,y1,x2,y2,Integer.parseInt(stkval));
                // If you want to check the values printing well, set the print
               // System.out.println(line.getX1());
               // System.out.println(line.getY1());
                //System.out.println(line.getX2());
               // System.out.println(line.getY2());
               // System.out.println(line.getStroke_width());

                svgImage.addLine(line);
            }


            for (int i = 0; i < rectangles.size(); i++)
            {
                Element rectangleElement = rectangles.get(i);
                //System.out.println(i+"***"+rectangles.get(i).getAttribute("width"));
                //System.out.println(rectangles.get(i).getTagName());

                SVGRectangle rectangle = new SVGRectangle(Double.parseDouble(rectangleElement.getAttribute("x")),
                        Double.parseDouble(rectangleElement.getAttribute("y")),
                        Double.parseDouble(rectangleElement.getAttribute("height")),
                        Double.parseDouble(rectangleElement.getAttribute("width")));

                svgImage.addRectangle(rectangle);
            }

            for (int i = 0; i < circles.size(); i++)
            {
                Element circleElement = circles.get(i);
                //System.out.println(i+"***"+circles.get(i).getAttribute("fill"));
                //System.out.println(rectangles.get(i).getTagName());

                SVGCircle circle = new SVGCircle(
                        Double.parseDouble(circleElement.getAttribute("cx")),
                        Double.parseDouble(circleElement.getAttribute("cy")),
                        circleElement.getAttribute("fill"));

                svgImage.addCircle(circle);
            }

            for (int i = 0; i < ellipses.size(); i++)
            {
                Element ellipseElement = ellipses.get(i);
                //System.out.println(i+"***"+ellipses.get(i).getAttribute("fill"));
                //System.out.println(rectangles.get(i).getTagName());

                SVGEllipse ellipse = new SVGEllipse(Double.parseDouble(ellipseElement.getAttribute("cx")),
                        Double.parseDouble(ellipseElement.getAttribute("cy")),
                        Double.parseDouble(ellipseElement.getAttribute("rx")),
                        Double.parseDouble(ellipseElement.getAttribute("ry")));

                svgImage.addEllipse(ellipse);
            }

            for (int i = 0; i < texts.size(); i++)
            {
                Element gElement = textg.get(i);
                Element textElement = texts.get(i);

                //System.out.println(i+"***"+texts.get(i).getAttribute("id"));
                //System.out.println(texts.get(i).getTextContent());
                trsfm=gElement.getAttribute("transform");
                x1=Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[0])+Double.parseDouble(textElement.getAttribute("x"));
                y1=Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[1])+Double.parseDouble(textElement.getAttribute("y"));




                SVGText text = new SVGText(x1, y1, textElement.getTextContent());
                svgImage.addText(text);
            }

            return svgImage;

        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }



    private String readSVGFile (String svgfilepath) throws IOException {

        String svgFile=null;


        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(svgfilepath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int lineIndex=0;
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                lineIndex++;
            }
            svgFile = sb.toString();

            svgFile = svgFile.replace(" xmlns=\"http://www.w3.org/2000/svg\"", "");
            svgFile=svgFile.replace("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">","");
            svgFile = svgFile.replace("<desc>Created with Fabric.js 1.6.3</desc>", "");
            svgFile = svgFile.replace("<defs></defs>", "");
            //svgFile = svgFile.replaceAll("(?s)(?<=<g>\n)(.*?)(?=\n</g>)", "REPLACE");

            //String str = "sfd\nsdfsdf<g>\nthis it to be\n replaced\n</g>sdfsdf";
            //System.out.println(str.replaceAll("(?s)(?<=<g>\n)(.*?)(?=\n</g>)", "replacement"));

            //System.out.println(svgFile);
        }
        catch(IOException ex){
        }
        finally {
        }

        return svgFile;

    }

}
