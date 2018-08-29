package com.example.apparao.sampleapplication;

import android.content.Context;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Test
    public void isLeanbackFeatureEnabled() {
        //assertEquals(4, 2 + 2);
        assertNotNull("Path does not exist",Paths.get(".").toAbsolutePath().normalize().toString());
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        File file = new File(Paths.get(".").toAbsolutePath().normalize().toString()+"/src/main/AndroidManifest.xml");
        BufferedReader br = null;
        String st = null;
        boolean isUsingLeanback=false;
        try {
            if (!file.exists())
                assertTrue("File not found", false);
            else {
                SAXBuilder saxBuilder = new SAXBuilder();
                Document document = saxBuilder.build(file);
                System.out.println("Root element :" + document.getRootElement().getName());
                Element classElement = document.getRootElement();

                List<Element> manifest = classElement.getChildren();
                System.out.println("----------------------------");

                for (int temp = 0; temp < manifest.size(); temp++) {
                    Element manifestChild = manifest.get(temp);
                    System.out.println("\nCurrent Element :"
                            + manifestChild.getName());
                    if(manifestChild.getName().equals("uses-feature")) {
                        List<Attribute> attributesList=manifestChild.getAttributes();
                        String feature =null;
                        for(int i=0;i<attributesList.size();i++){
                            if(feature==null&&(attributesList.get(i).getName().equals("name") && attributesList.get(i).getNamespacePrefix().equals("android"))) {
                                feature=attributesList.get(i).getValue();
                            }
                            if(feature.equals("android.software.leanback")&&(attributesList.get(i).getName().equals("required") && attributesList.get(i).getNamespacePrefix().equals("android"))) {
                                isUsingLeanback=attributesList.get(i).getValue().equals("true");
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        assertTrue("leanback is not enabled",isUsingLeanback);


    }
}