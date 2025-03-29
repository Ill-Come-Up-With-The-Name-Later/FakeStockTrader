import utilities.Table;

public class Main {

    public static void main(String[] args) {
        Table<Double> table = Table.build(3, 4);

        for(int i = 0; i < table.getRowCount(); i++) {
            for(int j = 0; j < table.getColCount(); j++) {
                table.addValueToRow(9.92, i);
            }
        }

        table.print("%4.2f", 4);
    }
}
