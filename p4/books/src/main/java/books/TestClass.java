package books;

import jrails.Column;
import jrails.Model;

public class TestClass extends Model {
    @Column
    public String title;

    @Column
    public String author;

    @Column
    public int num_copies;

    @Column
    public Boolean has_title;

    @Column
    public Boolean has_author;
}
