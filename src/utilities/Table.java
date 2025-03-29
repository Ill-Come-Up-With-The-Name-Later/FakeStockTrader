package utilities;

import java.util.ArrayList;

/**
 * A table of values.
 *
 * <p>
 * Contains rows and columns. There cannot be an excess of rows relative to columns.
 * </p>
 */
public class Table<T> {

    static class Row<T> {
        private ArrayList<T> values;

        public Row(int size) {
            values = new ArrayList<>(size);
        }

        public int getSize() {
            return this.values.size();
        }

        public ArrayList<T> getValues() {
            return values;
        }

        public void setValues(ArrayList<T> values) {
            if(values.size() != this.getSize()) {
                throw new IllegalArgumentException("Cannot change the size of a row.");
            }

            this.values = values;
        }

        public T getItem(int index) {
            return values.get(index);
        }

        public void setItem(T item, int index) {
            this.values.set(index, item);
        }

        public void addItem(T item) {
            this.values.add(item);
        }

        public void print() {
            for(T value : values) {
                if(value == null) {
                    System.out.println("No Value\t\t\t");
                }

                System.out.print(value + "\t\t\t\t");
            }

            System.out.println();
        }

        public void print(String format) {
            for(T value : values) {
                if(value == null) {
                    System.out.println("No Value\t\t\t");
                }

                System.out.print(String.format(format, value) + "\t\t\t\t");
            }

            System.out.println();
        }
    }

    static class Column {
        private String title;

        public Column(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    private final ArrayList<Column> columns;
    private final ArrayList<Row<T>> rows;

    public Table() {
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public Table(int rows, int cols) {
        this.columns = new ArrayList<>(cols);
        this.rows = new ArrayList<>(rows);
    }

    public int getColCount() {
        return this.columns.size();
    }

    public int getRowCount() {
        return this.rows.size();
    }

    public Table<T> addColumn(String title) {
        this.columns.add(new Column(title));

        return this;
    }

    public Table<T> addRow() {
        this.rows.add(new Row<>(this.columns.size()));
        return this;
    }

    public static <V> Table<V> build(int rows, int cols) {
        Table<V> table = new Table<>(rows, cols);

        for(int i = 0; i < cols; i++) {
            table.addColumn("Title");
        }

        for(int i = 0; i < rows; i++) {
            table.addRow();
        }

        return table;
    }

    public void print() {
        for(Column column : columns) {
            System.out.printf("%s\t\t\t", column.getTitle());
        }

        System.out.println();

        for(Row<T> row : rows) {
            row.print();
        }
    }

    public void print(String format) {
        for(Column column : columns) {
            System.out.printf("%s\t\t\t", column.getTitle());
        }

        System.out.println();

        for(Row<T> row : rows) {
            row.print(format);
        }
    }

    public void print(int numTabs) {
        for(Column column : columns) {
            System.out.printf("%s", column.getTitle());

            for(int i = 0; i < numTabs; i++) {
                System.out.print("\t");
            }
        }

        System.out.println();

        for(Row<T> row : rows) {
            row.print();
        }
    }

    public void print(String format, int numTabs) {
        for(Column column : columns) {
            System.out.printf("%s", column.getTitle());

            for(int i = 0; i < numTabs; i++) {
                System.out.print("\t");
            }
        }

        System.out.println();
        System.out.println();

        for(Row<T> row : rows) {
            row.print(format);
        }
    }

    public Table<T> setColTitle(int index, String title) {
        this.columns.get(index).setTitle(title);
        return this;
    }

    public Table<T> setRowValue(T item, int rowIndex, int colIndex) {
        this.rows.get(rowIndex).setItem(item, colIndex);
        return this;
    }

    public Table<T> addValueToRow(T item, int rowIndex) {
        if(this.rows.get(rowIndex).getSize() >= this.columns.size()) {
            throw new IllegalStateException("Row length cannot exceed number of columns");
        }

        this.rows.get(rowIndex).addItem(item);
        return this;
    }
}
