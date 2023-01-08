package books;

import jrails.Html;
import jrails.View;

import java.util.List;

public class BookView extends View {
    public static Html index(List<Book> books) {
        Html table_body = empty();
        for (Book b : books) {
            table_body =
                    table_body.tr(td(t(b.title)).
                            td(t(b.author)).
                            td(t(b.num_copies)).
                            td(link_to("Show", "/show?id=" + b.id())).
                            td(link_to("Edit", "/edit?id=" + b.id())).
                            td(link_to("Delete", "/destroy?id=" + b.id())));
        }
        return table(thead(tr(th(t("Title")).
                th(t("Author")).
                th(t("Copies")).
                th(empty()).
                th(empty()))).
                tbody(table_body)).
                br().
                link_to("New book", "/new");
    }

    public static Html show(Book b) {
        return p(strong(t("Title:")).t(b.title)).
                p(strong(t("Author:")).t(b.author)).
                p(strong(t("Copies:")).t(b.num_copies)).
                t(link_to("Edit", "/edit?id=" + b.id())).t(" | ").
                t(link_to("Back", "/"));
    }

    public static Html new_book(Book b) {
        return h1(t("New Book")).seq(the_form("/create", b));
    }

    public static Html edit(Book b) {
        return h1(t("Edit Book")).seq(the_form("/update?id=" + b.id(), b));
    }

    private static Html the_form(String action, Book b) {
        return form(action,
                div(t("Title").textarea("title", t(b.title))).
                        div(t("Author").textarea("author", t(b.author))).
                        div(t("Copies").textarea("num_copies", t(b.num_copies))).
                        div(submit("Save")));
    }
}