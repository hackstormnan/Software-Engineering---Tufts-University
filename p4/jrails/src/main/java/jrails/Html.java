package jrails;

public class Html {
    private String contents = "";

    public void setter(String contents) {
        this.contents = contents;
    }

    public String toString() {
        return contents;
    }

    public Html get_new(String input) {
        Html new_html = new Html();
        new_html.setter(input);
        return new_html;
    }

    public String get_standard_name(String side, String middle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(side);
        stringBuilder.append(">");

        stringBuilder.append(middle);

        stringBuilder.append("</");
        stringBuilder.append(side);
        stringBuilder.append(">");

        return stringBuilder.toString();
    }

    public Html seq(Html h) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contents);
        stringBuilder.append(h.contents);

        return get_new(stringBuilder.toString());
    }

    public Html br() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contents);
        stringBuilder.append("<br/>");

        return get_new(stringBuilder.toString());
    }

    public Html t(Object o) {
        // Use o.toString() to get the text for this
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contents);
        stringBuilder.append(o.toString());

        return get_new(stringBuilder.toString());
    }

    public Html p(Html child) {
        String name = get_standard_name("p", child.contents);

        return get_new(name);
    }

    public Html div(Html child) {
        String name = get_standard_name("div", child.contents);

        return get_new(name);
    }

    public Html strong(Html child) {
        String name = get_standard_name("strong", child.contents);

        return get_new(name);
    }

    public Html h1(Html child) {
        String name = get_standard_name("h1", child.contents);

        return get_new(name);
    }

    public Html tr(Html child) {
        String name = get_standard_name("tr", child.contents);

        return get_new(name);
    }

    public Html th(Html child) {
        String name = get_standard_name("th", child.contents);

        return get_new(name);
    }

    public Html td(Html child) {
        String name = get_standard_name("td", child.contents);

        return get_new(name);
    }

    public Html table(Html child) {
        String name = get_standard_name("table", child.contents);

        return get_new(name);
    }

    public Html thead(Html child) {
        String name = get_standard_name("thead", child.contents);

        return get_new(name);
    }

    public Html tbody(Html child) {
        String name = get_standard_name("tbody", child.contents);

        return get_new(name);
    }

    //<<[textarea name="name">v15</textarea]>> but was:<<[name>v15</name]>>
    public Html textarea(String name, Html child) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<textarea name=\"");
        stringBuilder.append(name);
        stringBuilder.append("\">");

        stringBuilder.append(child.contents);

        stringBuilder.append("</textarea>");

        return get_new(stringBuilder.toString());
    }

    //<<[a href="url">text</a]>> but was:<<[text>url</text]>>
    public Html link_to(String text, String url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"");
        stringBuilder.append(url);
        stringBuilder.append("\">");

        stringBuilder.append(text);

        stringBuilder.append("</a>");

        return get_new(stringBuilder.toString());
    }

    //<<[form action="/action" accept-charset="UTF-8" method="post">v17</form]>> but was:<<[/action>v17<//action]>>
    public Html form(String action, Html child) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<form action=\"");
        stringBuilder.append(action);
        stringBuilder.append("\" accept-charset=\"UTF-8\" method=\"post\">");

        stringBuilder.append(child.contents);

        stringBuilder.append("</form>");

        return get_new(stringBuilder.toString());
    }

    //<[<input type="submit" value="v18"/>]> but was:<[aaa]>
    public Html submit(String value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<input type=\"submit\" value=\"");
        stringBuilder.append(value);
        stringBuilder.append("\"/>");

        return get_new(stringBuilder.toString());
    }
}