package books;

import jrails.Column;
import jrails.JRouter;
import jrails.JServer;
import jrails.Model;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {

//        String num = "-2096640429";
//        System.out.println(Integer.parseInt(num));
        String data = "1865249612@Programming Languages: Build, Prove, and Compare@Norman Ramsey@2222";
        String[] split = data.split("@");
        for (String s : split) {
            System.out.println(s);
        }

//        JRouter r = new JRouter();
//
//        r.addRoute("GET", "/", BookController.class, "index");
//        r.addRoute("GET", "/show", BookController.class, "show");
//        r.addRoute("GET", "/new", BookController.class, "new_book");
//        r.addRoute("GET", "/edit", BookController.class, "edit");
//        r.addRoute("POST", "/create", BookController.class, "create");
//        r.addRoute("POST", "/update", BookController.class, "update");
//        r.addRoute("GET", "/destroy", BookController.class, "destroy"); // Should be DELETE but no way to do that with a link
//
//        JServer.start(r);
    }
}
