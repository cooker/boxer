//package cooker;
//
//import org.apache.commons.digester3.Digester;
//import org.junit.Test;
//import org.xml.sax.SAXException;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//
///**
// * Created by yu.kequn on 2017/8/14.
// */
//public class DigesterTest {
//    @Test
//    public void beanTest() throws IOException, SAXException {
//        File file = Paths.get("E:\\food.xml").toFile();
//        Digester digester = new Digester();
//        digester.addObjectCreate("food", "cooker.Food");
//        digester.addSetProperties("food/eat");
//
//        digester.addObjectCreate("food/eat", "cooker.Eat");
//        digester.addSetProperties("food/eat");
//        digester.addSetNext("food/eat", "setEat", "cooker.Eat");
//
//        digester.addObjectCreate("food/eat", "cooker.Eat");
//        digester.addSetProperties("food/eat");
//        digester.addSetNext("food/eat", "setE", "cooker.Eat");
//
//        Food food = digester.parse(file);
//        System.out.println(food.getEat().getE());
//    }
//}