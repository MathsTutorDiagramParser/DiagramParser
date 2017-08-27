package com.tutor.parser.service.preProcessorService;

import com.tutor.parser.model.graphicalSVGObject.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
            list = (NodeList)xPath.evaluate("//svg/line", source, XPathConstants.NODESET);

            List<Element> lines = new ArrayList<>(list.getLength());
            for (int i = 0; i < list.getLength(); i++)
            {
                lines.add((Element)list.item(i));
            }

            source = new InputSource(new StringReader(svgFile));
            list = (NodeList)xPath.evaluate("//g/line", source, XPathConstants.NODESET);
            List<Element> glines = new ArrayList<>(list.getLength());
            for (int i = 0; i < list.getLength(); i++)
            {
                glines.add((Element)list.item(i));
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
            list = (NodeList)xPath.evaluate("//g/text", source, XPathConstants.NODESET);
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
            String dash;
            Double x1=0.0;
            Double x2=0.0;
            Double y1=0.0;
            Double y2=0.0;
            for (int i = 0; i < lines.size(); i++)
            {

                Element lineElement = lines.get(i);
               // System.out.println(i+"***"+lines.get(i).getAttribute("width"));
                //System.out.println(rectangles.get(i).getTagName());
                styl = lineElement.getAttribute("style");
                // Transform Operation
                trsfm=lineElement.getAttribute("transform");
                dash=(styl.split("stroke-dasharray")[1].split(";")[0]).replace(": ", "");
                x1= Double.parseDouble(lineElement.getAttribute("x1"))+Double.parseDouble((trsfm.substring(10,trsfm.length()).split("\\)")[0]).split(" ")[0]);
                x2 =Double.parseDouble(lineElement.getAttribute("x2"))+Double.parseDouble((trsfm.substring(10,trsfm.length()).split("\\)")[0]).split(" ")[0]);
                y1= Double.parseDouble(lineElement.getAttribute("y1"))+Double.parseDouble((trsfm.substring(10,trsfm.length()).split("\\)")[0]).split(" ")[1]);
                y2 =Double.parseDouble(lineElement.getAttribute("y2"))+Double.parseDouble((trsfm.substring(10,trsfm.length()).split("\\)")[0]).split(" ")[1]);
                if(styl.equals(null)||styl.equals("")) {
                   stkval=lineElement.getAttribute("stroke-width");

                }else{
                   stkval =(styl.split("stroke-width")[1].split(";")[0]).replace(": ", "");
                }


                if(dash.equals("none")) {
                    if(!(x1.equals(x2) && y1.equals(y2))) {
                        SVGLine line = new SVGLine(x1, y1, x2, y2, Integer.parseInt(stkval));
                        // If you want to check the values printing well, set the print
                        System.out.println(line.getX1());
                        System.out.println(line.getY1());
                        System.out.println(line.getX2());
                        System.out.println(line.getY2());
                        System.out.println(line.getStroke_width());

                        svgImage.addLine(line);
                    }  else System.out.println("same");
                }
            }
            for (int i = 0; i < glines.size(); i++)
            {
                Element glineElement=glines.get(i);
                Node nd=glineElement.getParentNode();
                Element e = (Element)nd;
                trsfm=e.getAttribute("transform");
                styl = glineElement.getAttribute("style");
                x1= Double.parseDouble(glineElement.getAttribute("x1"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[0]);
                x2 =Double.parseDouble(glineElement.getAttribute("x2"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[0]);
                y1= Double.parseDouble(glineElement.getAttribute("y1"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[1]);
                y2 =Double.parseDouble(glineElement.getAttribute("y2"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-1).split(" ")[1]);
                if(styl.equals(null)||styl.equals("")) {
                    stkval=glineElement.getAttribute("stroke-width");

                }else{
                    stkval =(styl.split("stroke-width")[1].split(";")[0]).replace(": ", "");
                }

                SVGLine line = new SVGLine(x1,y1,x2,y2,Integer.parseInt(stkval));
                // If you want to check the values printing well, set the print
                System.out.println("This is the lines inside g");
                System.out.println(line.getX1());
                System.out.println(line.getY1());
                System.out.println(line.getX2());
                System.out.println(line.getY2());
                System.out.println(line.getStroke_width());

                svgImage.addLine(line);

            }

            String t1;
            String t2;
            String tmp1;
            String tmp2;
            String c;
            Double tr1;
            Double tr2;
            Double scl1;
            Double scl2;
            Double xR;
            Double yR;
            Double wR;
            Double hR;
            Double xRT;
            Double yRT;
            Double wRT;
            Double hRT;


            for (int i = 0; i < rectangles.size(); i++)
            {
                Element rectangleElement = rectangles.get(i);
                //System.out.println(i+"***"+rectangles.get(i).getAttribute("width"));
                //System.out.println(rectangles.get(i).getTagName());
                trsfm=rectangleElement.getAttribute("transform");
                System.out.println(trsfm);
                if(trsfm.length()<25){
                    t1=(trsfm.substring(10,trsfm.length()-1).split(" ")[0]);
                    t2=(trsfm.substring(10,trsfm.length()-1).split(" ")[1]);
                    tr1=Double.parseDouble(t1);
                    tr2=Double.parseDouble(t2);
                    scl1=1.0;
                    scl2=1.0;
                }else{
                    t1=(trsfm.substring(10,trsfm.length()-1).split(" ")[0]);
                    t2=(trsfm.substring(10,trsfm.length()-1).split(" ")[1]);
                    tr1=Double.parseDouble(t1);
                    tr2=Double.parseDouble(t2.substring(0,t2.length()-1));
                    tmp1=trsfm.substring(10,trsfm.length()-1).split(" ")[2];
                    scl1=Double.parseDouble(tmp1.substring(6,tmp1.length()));
                    tmp2=trsfm.substring(10,trsfm.length()-1).split(" ")[3];
                    scl2=Double.parseDouble(tmp2);
                    System.out.println(tr1+" "+tr2+ " "+" "+scl1+" "+scl2 );

                }
                xRT=Double.parseDouble(rectangleElement.getAttribute("x"));
                System.out.println(xRT);
                yRT=Double.parseDouble(rectangleElement.getAttribute("y"));
                System.out.println(yRT);
                wRT=Double.parseDouble(rectangleElement.getAttribute("width"));
                System.out.println(wRT);
                hRT=Double.parseDouble(rectangleElement.getAttribute("height"));
                System.out.println(hRT);
                System.out.println("Rectangle values are x: "+xRT+" y: "+yRT);



                xR=getXvalue(xRT,wRT,tr1,scl1);
                yR=getYvalue(yRT,hRT,tr2,scl2);
                wR=getWvalue(wRT,scl1);
                hR=getHvalue(hRT,scl2);
                System.out.println("x-> "+xR+"      y-> "+yR+"      w-> "+wR+"      h-> "+hR);
                SVGRectangle rectangle = new SVGRectangle(xR,yR,hR,wR);

                svgImage.addRectangle(rectangle);
            }
            String fill;
            for (int i = 0; i < circles.size(); i++)
            {
                Element circleElement = circles.get(i);
                //System.out.println(i+"***"+circles.get(i).getAttribute("fill"));
                //System.out.println(rectangles.get(i).getTagName());
                trsfm=circleElement.getAttribute("transform");
                styl =circleElement.getAttribute("style");
                x1= Double.parseDouble(circleElement.getAttribute("cx"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-2).split(" ")[0]);
                y1 =Double.parseDouble(circleElement.getAttribute("cy"))+Double.parseDouble(trsfm.substring(10,trsfm.length()-2).split(" ")[1]);
                if(styl.equals(null)||styl.equals("")) {
                   fill=circleElement.getAttribute("fill");

                }else{
                    fill =(styl.split("fill")[1].split(";")[0]).replace(": ", "");
                }
                SVGCircle circle = new SVGCircle(x1,y1,fill);
                boolean isoverlapped = false;
                for (int j=0;j<svgImage.getCircles().size();j++){
                    if((svgImage.getCircles().get(j).getCx() <= circle.getCx()+5) && (svgImage.getCircles().get(j).getCx() >= circle.getCx()-5) ){
                        isoverlapped = true;
                    }
                }
                if(!isoverlapped){
                    svgImage.addCircle(circle);
                }

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
            int count=0;
            for (int i = 0; i < texts.size(); i++)
            {
                count=0;
                Element textElement = texts.get(i);
                Node nd=textElement.getParentNode();
                Element e = (Element)nd;
                String tspanConcate="";
                x1=0.0;
                y1=0.0;
                NodeList nodes=textElement.getChildNodes();
                    for (int j = 0; j < nodes.getLength(); j++) {
                        if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE && nodes.item(j).getNodeName()=="tspan") {
                            Element element = (Element) nodes.item(j);
                            tspanConcate+=element.getTextContent();
                            x1+=Double.parseDouble(element.getAttribute("x"));
                            y1+=Double.parseDouble(element.getAttribute("y"));
                            count++;
                        }
                    }
                x1=x1/count;
                y1=y1/count;
                trsfm=e.getAttribute("transform");
               x1= x1+Double.parseDouble((trsfm.substring(10,trsfm.length()).split("\\)")[0]).split(" ")[0]);
               y1 =y1+Double.parseDouble((trsfm.substring(10,trsfm.length()).split("\\)")[0]).split(" ")[1]);

              SVGText text = new SVGText(x1,y1,tspanConcate);
                System.out.println("For text labels x , y and labels are");
                System.out.println(x1);
                System.out.println(y1);
                System.out.println(tspanConcate);
                svgImage.addText(text);
            }

            return svgImage;

        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Double getXvalue(Double x, Double w,Double t1,Double s1){
        Double answer;
        answer=(x+t1)*s1-((w*s1)-w);
        return answer;
    }
    public Double getYvalue(Double y, Double h,Double t2,Double s2){
        Double answer;
        answer=(y+t2)*s2-((h*s2)-h);
        return answer;
    }
    public Double getWvalue(Double w,Double s1){
        Double answer;
        answer=w*s1;
        return answer;
    }
    public Double getHvalue(Double h,Double s2){
        Double answer;
        answer=h*s2;
        return answer;
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
