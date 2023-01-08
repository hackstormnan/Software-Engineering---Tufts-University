package jrails;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;



public class Model {

    private int id = 0;
    private final static String file_name = "P4_DataBase.txt";


    public void save() {
        /* this is an instance of the current model */
        try {
            if (id == 0) {
                id = ThreadLocalRandom.current().nextInt();                                                //create a new id

                StringBuilder contents = get_field_contents();                                             //get contents from field
                StringBuilder all = new StringBuilder();


                File file = new File(file_name);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    all.append(data);
                    all.append("\n");
                }
                all.append(contents.toString());
                all.append("\n");

                PrintWriter printWriter = new PrintWriter(file_name);                                      //write contents in files
                printWriter.println(all.toString());
                printWriter.close();
            } else {                                                                                       //replace the old contents
                File file = new File(file_name);
                Scanner scanner = new Scanner(file);
                StringBuilder new_contents = new StringBuilder();                                          //generate new lines
                Boolean find = false;

                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    if (data == "") {
                        continue;
                    }
                    String[] split = data.split("@");
                    int id_here = Integer.parseInt(split[0]);                                              //id in this line
                    if (id_here == id) {
                        new_contents.append(get_field_contents());                                         //add new value inside
                        find = true;
                    } else {
                        new_contents.append(data);                                                         //add original line
                    }
                    new_contents.append("\n");
                }
                scanner.close();

                if (find == false) {                                                                       //did not find the id
                    System.out.println("The given id is not found");
                    return;
                }
                PrintWriter printWriter = new PrintWriter(file_name);                                      //write contents in files
                printWriter.println(new_contents);
                printWriter.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public StringBuilder get_field_contents() {
        Field[] fields = this.getClass().getFields();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            stringBuilder.append(id);
            stringBuilder.append("@");
            for (Field field : fields) {
                Class<?> field_type = field.getType();
                if ((field.isAnnotationPresent(Column.class)) && (field_type == String.class || field_type == int.class
                        || field_type == boolean.class || field_type == Boolean.class)) {
                    Object object = field.get(this);
                    if (object == null) {
                        stringBuilder.append("NULL");
                    } else {
                        stringBuilder.append(object);
                    }
                }
                stringBuilder.append("@");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);

        return stringBuilder;
    }

    public static <T> T find(Class<T> c, int id) {
        Model instance = null;
        try {
            File file = new File(file_name);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {                                                            //go through each line in the file
                String data = scanner.nextLine();
                if (data == "") {
                    continue;
                }
                System.out.println(data);
                String[] split = data.split("@");                                                //get different attributes in this line

                int id_here = Integer.parseInt(split[0]);                                              //id in this line

                if (id_here == id) {
                    instance = (Model) c.getConstructor().newInstance();
                    Field[] fields = c.getFields();

                    int index = 1;
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Column.class)) {
                            Class<?> field_type = field.getType();
                            instance.set_id(id_here);
                            if (field_type == String.class) {
                                if (split[index].equals("NULL")) {
                                    field.set(instance, null);
                                    index++;
                                    continue;
                                }
                                field.set(instance, split[index]);
                            } else if (field_type == int.class) {
                                field.set(instance, Integer.parseInt(split[index]));
                            } else if (field_type == boolean.class || field_type == Boolean.class) {
                                field.set(instance, Boolean.parseBoolean(split[index]));
                            }
                            index++;
                        }
                    }
                }
            }
            scanner.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return (T) instance;
    }

    public static <T> List<T> all(Class<T> c) {
        // Returns a List<element type>
        List<T> instance_list = new ArrayList<>();
        try {
            File file = new File(file_name);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (data == "") {
                    continue;
                }
                String[] split = data.split("@");                                                //get different attributes in this line

                Field[] fields = c.getFields();
                Model instance = (Model) c.getConstructor().newInstance();
                int index = 1;

                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Class<?> field_type = field.getType();
                        if (field_type == String.class) {
                            if (split[index].equals("NULL")) {
                                field.set(instance, null);
                                index++;
                                continue;
                            }
                            field.set(instance, split[index]);
                        } else if (field_type == int.class) {
                            field.set(instance, Integer.parseInt(split[index]));
                        } else if (field_type == boolean.class) {
                            field.set(instance, Boolean.parseBoolean(split[index]));
                        }
                        index++;
                    }
                }
                instance.set_id(Integer.parseInt(split[0]));

                instance_list.add((T) instance);
            }
            scanner.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return instance_list;
    }

    public void destroy() {
        try {
            File file = new File(file_name);
            Scanner scanner = new Scanner(file);
            StringBuilder new_contents = new StringBuilder();
            Boolean id_exist = false;

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] split = data.split("@");
                if (data == "") {
                    continue;
                }
                if (Integer.parseInt(split[0]) == this.id) {
                    id_exist = true;
                    continue;
                }
                new_contents.append(data);
                new_contents.append("\n");
            }
            scanner.close();
            if (id_exist == false) {
                System.out.println("id does not exist");
                throw new UnsupportedOperationException();
            }

            PrintWriter printWriter = new PrintWriter(file_name);                                      //write contents in files
            printWriter.println(new_contents.toString());
            printWriter.close();

        } catch (Exception exception) {
            throw new UnsupportedOperationException();
        }
    }

    public static void reset() {
        try {
            PrintWriter printWriter = new PrintWriter(file_name);                                      //write contents in files
            printWriter.println("");
            printWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
