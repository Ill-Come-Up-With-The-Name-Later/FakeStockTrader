package utilities;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A table of values.
 *
 * <p>
 * Contains rows and columns. There cannot be an excess of rows relative to columns.
 * </p>
 *
 * Some methods return the modified table to allow for
 * chain method calls.
 *
 * @param <T> The type stored in the table's rows
 */
public class Table<T> {

    /**
     * Represents a row of a Table.
     *
     * <p>
     * A row cannot be wider than the number of
     * columns a table has.
     * </p>
     *
     * @param <T> The type stored in Row
     */
    public static class Row<T>  {
        private ArrayList<T> values;

        /**
         * Constructs a row
         *
         * @param size The size of the row
         */
        public Row(int size) {
            values = new ArrayList<>(size);
        }

        /**
         * Row size
         * @return The size of the row
         */
        public int getSize() {
            return this.values.size();
        }

        /**
         * Row values
         *
         * @return An <code>ArrayList</code> of the row's values
         */
        public ArrayList<T> getValues() {
            return values;
        }

        /**
         * Sets the values in the row. The value
         * list cannot be longer than the number of columns of
         * this row's table
         *
         * @param values A list of values
         */
        public void setValues(ArrayList<T> values) {
            if(values.size() != this.getSize()) {
                throw new IllegalArgumentException("Cannot change the size of a row.");
            }

            this.values = values;
        }

        /**
         * Gets the item in this row at index
         *
         * @param index The index to get from
         * @return The item at index
         */
        public T getItem(int index) {
            return values.get(index);
        }

        /**
         * Sets an item at a certain index
         *
         * @param item The item
         * @param index The index to put <code>item</code> at
         */
        public void setItem(T item, int index) {
            this.values.set(index, item);
        }

        /**
         * Adds an item to this row
         *
         * @param item The item to add to the row
         */
        public void addItem(T item) {
            this.values.add(item);
        }

        /**
         * Prints the row's values
         */
        public void print() {
            for(T value : values) {
                if(value == null) {
                    System.out.println("No Value\t\t\t");
                }

                System.out.print(value + "\t\t\t\t");
            }

            System.out.println();
        }

        /**
         * Prints the row's values, supports formatting
         *
         * @param format The string formatting to use for each item
         */
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

    /**
     * Represents a table column with a title
     */
    public static class Column {
        private String title;

        /**
         * Constructs a column
         *
         * @param title The title of the column
         */
        public Column(String title) {
            this.title = title;
        }

        /**
         * Column title
         *
         * @return The column's title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets the column's title
         *
         * @param title The new title for this column
         */
        public void setTitle(String title) {
            this.title = title;
        }
    }

    /**
     * Static utility to build a table of
     * dimensions <code>rows * cols</code>
     */
    public static class TableBuilder {

        /**
         * Builds a table of dimensions
         * <code>rows * cols</code>
         *
         * <p>
         * A call looks like:
         * <code>Table.TableBuilder.&lt V &gt build(rows, cols);</code>
         * </p>
         *
         * @param rows The number of rows
         * @param cols The number of columns
         * @return A table of dimensions <code>rows * cols</code>
         * @param <V> The type of data in the table rows
         */
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
    }

    private final ArrayList<Column> columns;
    private final ArrayList<Row<T>> rows;

    /**
     * Constructs a table with no rows or columns
     */
    public Table() {
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    /**
     * Constructs a table
     *
     * @param rows The amount of rows in this table
     * @param cols The amount of columns in this table
     */
    public Table(int rows, int cols) {
        this.columns = new ArrayList<>(cols);
        this.rows = new ArrayList<>(rows);
    }

    /**
     * Amount of columns in the table
     *
     * @return The amount of columns in the table
     */
    public int getColCount() {
        return this.columns.size();
    }

    /**
     * Amount of rows in the table
     *
     * @return The amount of rows in the table
     */
    public int getRowCount() {
        return this.rows.size();
    }

    /**
     * Gets the row at <code>rowIndex</code>
     *
     * @param rowIndex The index of the row to retrieve
     * @return The row at <code>rowIndex</code>
     */
    public Row<T> getRow(int rowIndex) {
        return this.rows.get(rowIndex);
    }

    /**
     * Gets the column at <code>index</code>
     *
     * @param index The index of the column to retrieve
     * @return The column at <code>index</code>
     */
    public Column getColum(int index) {
        return this.columns.get(index);
    }

    /**
     * Adds a column to this table. Automatically syncs rows
     * to match the new amount of columns
     *
     * @param title The tile of the new column
     * @return The table
     */
    public Table<T> addColumn(String title) {
        this.columns.add(new Column(title));

        this.rows.forEach(x -> {
            ArrayList<T> newRow = new ArrayList<>(this.columns.size());
            newRow.addAll(x.getValues());
            x.setValues(newRow);
        });

        return this;
    }

    /**
     * Adds a new row to the bottom of the table.
     *
     * @return The table
     */
    public Table<T> addRow() {
        this.rows.add(new Row<>(this.columns.size()));
        return this;
    }

    /**
     * Prints out this table
     */
    public void print() {
        for(Column column : columns) {
            System.out.printf("%s\t\t\t", column.getTitle());
        }

        System.out.println();

        for(Row<T> row : rows) {
            row.print();
        }
    }

    /**
     * Prints out this table, supports formatting
     * for the table values
     *
     * @param format The formatting for the table values
     */
    public void print(String format) {
        for(Column column : columns) {
            System.out.printf("%s\t\t\t", column.getTitle());
        }

        System.out.println();

        for(Row<T> row : rows) {
            row.print(format);
        }
    }

    /**
     * Prints out the table, allows for spacing out of the
     * column titles
     *
     * @param numTabs The number of <code>\t (tabs)</code> between column titles
     */
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

    /**
     * Prints out the table, supports formatting table values and spacing out
     * column titles
     * @param format The formatting for the table values
     * @param numTabs The number of <code>\t (tabs)</code> between column titles
     */
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

    /**
     * Sets the title of the column at index
     *
     * @param index The index of the column
     * @param title The new title for the column at <code>index</code>
     * @return The table
     */
    public Table<T> setColTitle(int index, String title) {
        this.getColum(index).setTitle(title);
        return this;
    }

    /**
     * Sets the value at an index of a row
     *
     * @param item The item
     * @param rowIndex The index of the row to modify
     * @param colIndex The index of the column to put <code>item</code> under
     * @return The table
     */
    public Table<T> setRowValue(T item, int rowIndex, int colIndex) {
        this.getRow(rowIndex).setItem(item, colIndex);
        return this;
    }

    /**
     * Adds a value to a row
     *
     * @param item The item to add
     * @param rowIndex The index of the row to add <code>item</code> to
     * @return The table
     */
    public Table<T> addValueToRow(T item, int rowIndex) {
        if(this.getRow(rowIndex).getSize() >= this.columns.size()) {
            throw new IllegalStateException("Row length cannot exceed number of columns");
        }

        this.rows.get(rowIndex).addItem(item);
        return this;
    }

    /**
     * Flattens the table into an <code>ArrayList</code> of its rows' values
     *
     * @return An <code>ArrayList</code> of the table's values from all rows
     */
    public ArrayList<T> flatten() {
        ArrayList<T> items = new ArrayList<>();
        this.rows.forEach(x -> items.addAll(x.getValues()));

        return items;
    }

    /**
     * Filters the table's rows' values for items matching a condition
     *
     * @param predicate The condition to filter by
     * @return An <code>ArrayList</code> of filtered values
     */
    public ArrayList<T> filter(Predicate<T> predicate) {
        ArrayList<T> items = new ArrayList<>();

        this.rows.forEach(x -> items.addAll(x.getValues().stream()
                .filter(predicate).collect(Collectors.toCollection(ArrayList::new))));

        return items;
    }

    /**
     * Sets the titles of columns using an <code>ArrayList</code>
     *
     * <p>
     * The title list's length cannot exceed the number of columns
     * in this table.
     * </p>
     *
     * @param titles An <code>ArrayList</code> of Strings representing the new title
     */
    public void setColTitles(ArrayList<String> titles) {
        if(titles.size() > this.columns.size()) {
            throw new IllegalArgumentException("Title list length must" +
                    " not exceed number of columns");
        }

        for(int i = 0; i < titles.size(); i++) {
            this.setColTitle(i, titles.get(i));
        }
    }
}