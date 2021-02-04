package Hangler_MoserSchwaiger;

import java.io.File;

public class DemoTest {

    public static void main(String[] args) throws Exception {
        XMLDataA7 xml = new XMLDataA7();

        xml.loadXml(new File("src/test/input.xml"));

        if(xml.getPrice("root").isPresent())
            System.out.println("root expected price: 155 -> actual: " + xml.getPrice("root").get());

        if(xml.getPrice("B3").isPresent())
        System.out.println("B3 expected price: 10 -> actual: " + xml.getPrice("B3").get());

        if(xml.getPrice("L2").isPresent())
        System.out.println("L2 expected price: 30 -> actual: " + xml.getPrice("L2").get());

        if(xml.getPrice("L1").isPresent())
        System.out.println("L1 expected price: 125 -> actual: " + xml.getPrice("L1").get());

        if(xml.getPrice("C3").isPresent())
            System.out.println("C3 expected price: 15 -> actual: " + xml.getPrice("C3").get());

        if(xml.getPrice("L4").isPresent())
            System.out.println("L4 expected price: 0 -> actual: " + xml.getPrice("L4").get());
        else
            System.out.println("The Item 'L4' is not a element of the xml file");



    }
}
