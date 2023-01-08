package jrails;

public class View {
    public static Html empty() {
        return new Html();
    }

    public static Html br() {
        return new Html().br();
    }

    public static Html t(Object o) {
        // Use o.toString() to get the text for this
        return new Html().t(o);
    }

    public static Html p(Html child) {
        return new Html().p(child);
    }

    public static Html div(Html child) {
        return new Html().div(child);
    }

    public static Html strong(Html child) {
        return new Html().strong(child);
    }

    public static Html h1(Html child) {
        return new Html().h1(child);
    }

    public static Html tr(Html child) {
        return new Html().tr(child);
    }

    public static Html th(Html child) {
        return new Html().th(child);
    }

    public static Html td(Html child) {
        return new Html().td(child);
    }

    public static Html table(Html child) {
        return new Html().table(child);
    }

    public static Html thead(Html child) {
        return new Html().thead(child);
    }

    public static Html tbody(Html child) {
        return new Html().tbody(child);
    }

    public static Html textarea(String name, Html child) {
        return new Html().textarea(name, child);
    }

    public static Html link_to(String text, String url) {
        return new Html().link_to(text, url);
    }

    public static Html form(String action, Html child) {
        return new Html().form(action, child);
    }

    public static Html submit(String value) {
        return new Html().submit(value);
    }
}